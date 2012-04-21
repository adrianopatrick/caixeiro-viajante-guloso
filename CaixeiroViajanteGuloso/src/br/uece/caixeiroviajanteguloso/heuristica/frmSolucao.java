package br.uece.caixeiroviajanteguloso.heuristica;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.TextField;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import br.uece.caixeiroviajanteguloso.utils.ArquivoUtils;

public class frmSolucao extends JFrame {
	
	private static final long serialVersionUID = 4374814109119448542L;
	
	Label label1 = null;
	TextField txtArquivo = null;
	File file = null;
	Celula[][] matriz = null;
	
	public frmSolucao(){
		inicializaComponentes();
	}
	
	private void inicializaComponentes() {

		this.setTitle("Caixeiro Viajante - Guloso com n partidas");
		JPanel panel = new javax.swing.JPanel();
		label1 = new Label();
		label1.setText("Arquivo");
		txtArquivo = new TextField(50);
		txtArquivo.setEditable(false);
		txtArquivo.setSize(20, 100);
		Button btnAbreArquivo = new Button("Selecione o arquivo");// createImageIcon("images/Open16.gif"))
		Button btnGeraSolucao = new Button("Gera solução");
		panel.add(label1);
		panel.add(txtArquivo);
		panel.add(btnAbreArquivo);
		panel.add(btnGeraSolucao);
		
		this.getContentPane().add(panel, java.awt.BorderLayout.CENTER);
		
		btnAbreArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evento) {
            	btnAbreArquivoActionPerformed(evento);
            }
        });
		
		btnGeraSolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evento) {
            	btnGeraSolucaoArquivoActionPerformed(evento);
            }
        });
	}
	
	private void btnGeraSolucaoArquivoActionPerformed(java.awt.event.ActionEvent evento) {
		if (file == null) {
			JOptionPane.showMessageDialog(this, "Antes de gerar a solução, é necessário escolher um arquivo.");
		} else {
			try {
			
				ArquivoUtils au = new ArquivoUtils();
				matriz = au.lerArquivo(file);
				desenhaPontos((Graphics2D)this.getGraphics());
				
				Escolhe escolhe = new Escolhe();
				matriz[0][0].getOrigem().setUsado(true);
				matriz[0][0].getOrigem().setOrigem(true);
				
				System.out.print(matriz[0][0].getOrigem().getId()+" -> ");
				Ponto proximo = escolhe.proximoPonto(matriz, matriz[0][0].getOrigem());;
				Thread.sleep(300);
				
				desenhaTrajetoria((Graphics2D)this.getGraphics(), matriz[0][0].getOrigem(), proximo);
				Ponto anterior = null;
				
				for(int i = 0; i < matriz.length - 1; i++){
					anterior = proximo;
					proximo = escolhe.proximoPonto(matriz, proximo);
					desenhaTrajetoria((Graphics2D)this.getGraphics(), anterior, proximo);
					Thread.sleep(300);
				}
			}
			catch(InterruptedException e) {}
			
		}
	}
	
	private void desenhaPontos(java.awt.Graphics2D g) {
		
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (matriz != null) {
			for(int i = 0; i < matriz.length; i++) {
				g.fill(new Ellipse2D.Double((matriz[i][0].getOrigem().getCoordX() + 100), (matriz[i][0].getOrigem().getCoordY() + 100), 5, 5));	
			}
		}
	}
	
	private void desenhaTrajetoria(java.awt.Graphics2D g, Ponto origem, Ponto destino) {
		
		g.setColor(Color.BLUE); 
		
		g.drawLine(origem.getCoordX().intValue() +100, origem.getCoordY().intValue() +100, destino.getCoordX().intValue() + 100, destino.getCoordY().intValue()+100);
	}
	
	private void btnAbreArquivoActionPerformed(java.awt.event.ActionEvent evento) {
		
			JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);
			
			fc.addChoosableFileFilter(new FileFilter() {
				
				@Override
				public String getDescription() {
					return null;
				}
				
				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) { return true; }

			        String extension = getExtension(f);
			        if (extension != null) {
			            if (extension.equals("txt")) {
			                    return true;
			            } else { return false; }
			        }
			        return false;
				}
			});	
			
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            
				file = fc.getSelectedFile();
	            txtArquivo.setText(file.getPath());
	        }
	}
	
	private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
