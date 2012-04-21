package br.uece.caixeiroviajanteguloso.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.TextField;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import br.uece.caixeiroviajanteguloso.heuristica.Celula;
import br.uece.caixeiroviajanteguloso.heuristica.Escolhe;
import br.uece.caixeiroviajanteguloso.heuristica.Ponto;
import br.uece.caixeiroviajanteguloso.utils.ArquivoUtils;

public class frmSolucao extends JFrame {
	
	Label label1 = null;
	TextField txtArquivo = null;
	JPanel panel = null;
	JPanel panel2 = null;
	JLabel lblN = null;
	JLabel lblOtima = null;
	File file = null;
	Celula[][] matriz = null;
	int valorZoom = 100;
	Boolean solucaoGerada = false;
	
	public frmSolucao(){
		inicializaComponentes();
		this.setExtendedState(MAXIMIZED_BOTH);
	}
	
	private void inicializaComponentes() {

		this.setTitle("Caixeiro Viajante - Guloso com n partidas");
		panel = new javax.swing.JPanel();
		label1 = new Label();
		label1.setText("Arquivo");
		txtArquivo = new TextField(50);
		txtArquivo.setEditable(false);
		txtArquivo.setSize(20, 100);
		Button btnAbreArquivo = new Button("Selecionar arquivo");
		Button btnGeraSolucao = new Button("Gerar solução");
		panel.add(label1);
		panel.add(txtArquivo);
		panel.add(btnAbreArquivo);
		panel.add(btnGeraSolucao);
		
		this.getContentPane().add(panel, java.awt.BorderLayout.NORTH);
		
		panel2 = new javax.swing.JPanel();
		panel2.setBackground(Color.LIGHT_GRAY);
		this.getContentPane().add(panel2, java.awt.BorderLayout.CENTER);
		panel2.setLayout(null);
		
		JLabel label2 = new JLabel();
		label2.setText("n: ");
		label2.setBounds(10, 10, 20, 20);
		panel2.add(label2);
		
		lblN = new JLabel();
		lblN.setBounds(30, 10, 100, 20);
		panel2.add(lblN);
		
		JLabel label3 = new JLabel();
		label3.setText("Solução ótima: ");
		label3.setBounds(10, 30, 100, 20);
		panel2.add(label3);
		
		lblOtima = new JLabel();
		lblOtima.setBounds(100, 30, 500, 20);
		panel2.add(lblOtima);
		
		
		
		/*slider = new JSlider(0, 200, 100);
		slider.setBorder(BorderFactory.createTitledBorder("Zoom"));
	    slider.setMajorTickSpacing(50);
	    slider.setMinorTickSpacing(5);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    
	    slider.addChangeListener(  
	            new ChangeListener() {  
	            
	            public void stateChanged(ChangeEvent evt)  
	            {  
	            	if (!slider.getValueIsAdjusting())
	            	{ valorZoom = slider.getValue(); }
	            	if (solucaoGerada) { geraSolucao(); }
	            }  
	         }); 
	    panel2.add(slider);	*/	
		
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
			geraSolucao();
		}
	}
	
	private void geraSolucao() {
		
		try {		
			ArquivoUtils au = new ArquivoUtils();
			matriz = au.lerArquivo(file);
			
			int distancia = 0;
			lblN.setText(matriz.length+"");
			lblOtima.setText(distancia+"");
			
			Graphics2D grafico = (Graphics2D)panel2.getGraphics();
			limpaTela(grafico);
			desenhaPontos(grafico);
			desenhaCoordenada(grafico);
							
			Escolhe escolhe = new Escolhe();
			matriz[0][0].getOrigem().setUsado(true);
			matriz[0][0].getOrigem().setOrigem(true);
			
			System.out.print(matriz[0][0].getOrigem().getId()+" -> ");
			Ponto proximo = escolhe.proximoPonto(matriz, matriz[0][0].getOrigem());;
			distancia += matriz[0][0].getDistancia();
			lblOtima.setText(distancia+"");
			Thread.sleep(300);
			
			desenhaTrajetoria(grafico, matriz[0][0].getOrigem(), proximo);
			Ponto anterior = null;
			
			for(int i = 0; i < matriz.length - 1; i++){
				anterior = proximo;
				proximo = escolhe.proximoPonto(matriz, proximo);
				
				distancia += matriz[anterior.getId()-1][proximo.getId()-1].getDistancia();
				lblOtima.setText(distancia+"");				
				
				desenhaTrajetoria(grafico, anterior, proximo);		
				Thread.sleep(200);
			}
			solucaoGerada = true;
		}
		catch(InterruptedException e) {}	
	}
	
	private void desenhaCoordenada(java.awt.Graphics2D g) {
		
		g.setColor(Color.DARK_GRAY);
		g.drawLine(0, panel2.getHeight()/2, panel2.getWidth(), panel2.getHeight()/2);
		g.drawLine(panel2.getWidth()/2, 0, panel2.getWidth()/2, panel2.getWidth()/2);
	}
	
	private void limpaTela(java.awt.Graphics2D g) {
		
		panel2.paintAll(g);
	}
		
	private void desenhaPontos(java.awt.Graphics2D g) {
			
		if (matriz != null) {
			for(int i = 0; i < matriz.length; i++) {
				g.fill(new Ellipse2D.Double((matriz[i][0].getOrigem().getCoordX() + panel2.getWidth()/2 - 2), (panel2.getHeight()/2 - matriz[i][0].getOrigem().getCoordY() - 2), 4, 4));
			}
		}
	}
			
	private void desenhaTrajetoria(java.awt.Graphics2D g, Ponto origem, Ponto destino) {
		
		g.setColor(Color.BLUE); 
		g.drawLine((int)(origem.getCoordX() + panel2.getWidth()/2), (int)(panel2.getHeight()/2 - origem.getCoordY()), (int)(destino.getCoordX() + panel2.getWidth()/2), (int)(panel2.getHeight()/2 - destino.getCoordY()));
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