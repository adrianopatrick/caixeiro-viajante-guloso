package br.uece.caixeiroviajanteguloso.heuristica;

import java.io.File;

import br.uece.caixeiroviajanteguloso.utils.ArquivoUtils;

public class Main {
	public static void main(String[] args) {
		
		File file = new File("/Users/patrick/Documents/MestradoUECE/PCV2/ulyss16.txt");
		ArquivoUtils au = new ArquivoUtils();
		Celula[][] matriz = au.lerArquivo(file);
		
		for (Celula[] celulas : matriz) {
			for (Celula celula : celulas) {
				System.out.println(celula.getOrigem());
			}
		}
	}
}
