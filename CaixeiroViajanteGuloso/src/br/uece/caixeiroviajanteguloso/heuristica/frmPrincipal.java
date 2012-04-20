package br.uece.caixeiroviajanteguloso.heuristica;

import java.awt.Label;
import java.awt.TextField;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class frmPrincipal extends JFrame {	  
	 
	frmPrincipal() {
		inicializaComponentes();
	}

	private void inicializaComponentes() {
		
		this.setSize(500, 500);
		this.setTitle("Caixeiro Viajante");
		
		JMenuBar menu = new JMenuBar();
		JMenu menu1 = new JMenu();
		menu1.setText("Arquivo");
		menu.add(menu1);
		JMenuItem menuItem = new JMenuItem();
		menuItem.setText("Entrada de dados");
		menu1.add(menuItem);				
		JMenu menu2 = new JMenu();
		menu2.setText("Sobre");
		menu.add(menu2);
		setJMenuBar(menu); 
		
		menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evento) {
            	menuItemActionPerformed(evento);
            }
        });
	}
	
	private void menuItemActionPerformed(java.awt.event.ActionEvent evento) {
		
		frmSolucao form = new frmSolucao();
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		form.setSize(500, 500);
		form.setLocationRelativeTo(null);
		form.setVisible(true);
	}
}
