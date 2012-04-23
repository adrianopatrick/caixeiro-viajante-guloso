package br.uece.caixeiroviajanteguloso.heuristica;

import br.uece.caixeiroviajanteguloso.ui.frmPrincipal;


public class Main {
	
	public static void main(String[] args) {
		
		frmPrincipal form = new frmPrincipal();
		form.setVisible(true);  
		
		/*JFrame f = new JFrame();  
		f.getContentPane().add(form);  
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		f.setSize(500, 500);  
		f.setVisible(true);*/
				
		/*File file = new File("/Users/patrick/Documents/MestradoUECE/PCV2/ulyss16.txt");
		ArquivoUtils au = new ArquivoUtils();
		Celula[][] matriz = au.lerArquivo(file);
		
		Escolhe escolhe = new Escolhe();
		matriz[0][0].getOrigem().setUsado(true);
		matriz[0][0].getOrigem().setOrigem(true);
		
		System.out.print(matriz[0][0].getOrigem().getId()+" -> ");
		Ponto proximo = escolhe.proximoPonto(matriz, matriz[0][0].getOrigem());;
		for(int i = 0; i < matriz.length - 1; i++){
			proximo = escolhe.proximoPonto(matriz, proximo);
		}*/	
		
	}
}
