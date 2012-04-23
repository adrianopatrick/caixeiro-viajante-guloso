package br.uece.caixeiroviajanteguloso.heuristica;

import java.util.Arrays;

public class Escolhe {
	
	public Ponto proximoPonto(Celula[][] matriz, Ponto origem){
		
		Ponto destino = null;
		
		Celula[] celulas = matriz[origem.getId() - 1];
		Arrays.sort(celulas);
		
		for (int i = 0; i < celulas.length; i++) {
			if(celulas[i].getDistancia() != 0 && !celulas[i].getDestino().isOrigem() && !celulas[i].getDestino().isUsado()){
				destino = celulas[i].getDestino();
				celulas[i].getDestino().setUsado(true);				
				break;
			}
		}
		
		if(destino != null){
			System.out.print(destino.getId()+" -> ");
			return destino;			
		}
		else {
			for (int i = 0; i < celulas.length; i++) {
				if(celulas[i].getDestino().isOrigem()){
					System.out.println(celulas[i].getDestino().getId());
					return celulas[i].getDestino();					
				}
			}
		}			
		
		return null;
	}

}
