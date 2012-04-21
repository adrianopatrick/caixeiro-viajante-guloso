package br.uece.caixeiroviajanteguloso.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class frmPrincipal extends JFrame {	  
	 
	private static final long serialVersionUID = -8471870786289155575L;

	public frmPrincipal() {
		inicializaComponentes();
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo incializa os componentes do formulario
	 */
	private void inicializaComponentes() {
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Caixeiro Viajante");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo chama o formulario da solucao
	 * @param evento
	 */
	private void menuItemActionPerformed(java.awt.event.ActionEvent evento) {
		
		frmSolucao form = new frmSolucao();
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		form.setSize(500, 500);
		form.setLocationRelativeTo(null);
		form.setVisible(true);
	}
}
