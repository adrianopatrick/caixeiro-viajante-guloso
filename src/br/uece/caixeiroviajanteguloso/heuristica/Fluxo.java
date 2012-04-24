package br.uece.caixeiroviajanteguloso.heuristica;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.uece.caixeiroviajanteguloso.utils.ArquivoUtils;

public class Fluxo extends Thread{
	
	private Celula[][] matriz;
	private static List<Caminho> caminhos = new ArrayList<Caminho>();
	private Integer index;
	
	public Fluxo(ThreadGroup tg, String nome, Integer index, File file){
		super(tg, nome);
		this.index = index;
		ArquivoUtils arquivo = new ArquivoUtils();
		matriz = arquivo.lerArquivo(file);
	}
	
	@Override
	public void run() {
			
		Ponto[] ponto = new Ponto[matriz.length];
		Escolhe escolhe = new Escolhe();
		matriz[index][0].getOrigem().setUsado(true);
		matriz[index][0].getOrigem().setOrigem(true);
		
		System.out.print(getName()+":"+matriz[index][0].getOrigem().getId()+" -> ");
		Ponto proximo = escolhe.proximoPonto(matriz, matriz[index][0].getOrigem());
		ponto[0] = proximo;
		Double distancia = 0.0;
		for(int j = 0; j < matriz.length - 1; j++){
			proximo = escolhe.proximoPonto(matriz, proximo);
			ponto[j+1] = proximo;
			distancia += matriz[ponto[j].getId()-1][ponto[j+1].getId()-1].getDistancia();
		}
		
		Caminho caminho = new Caminho();
		caminho.setCaminho(ponto);
		caminho.setDistancia(distancia);
		caminhos.add(caminho);
		
		System.out.println("End "+getName());
				
	}

	public static List<Caminho> getCaminhos() {
		Collections.sort(caminhos);
		return caminhos;
	}

}
