package br.uece.caixeiroviajanteguloso.heuristica;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.uece.caixeiroviajanteguloso.utils.ArquivoUtils;

public class Fluxo extends Thread{
	
	private Celula[][] matriz;
	public static List<Caminho> caminhos = new ArrayList<Caminho>();
	private Integer index;
	
	public Fluxo(ThreadGroup tg, String nome, Integer index, File file){
		super(tg, nome);
		this.index = index;
		ArquivoUtils arquivo = new ArquivoUtils();
		matriz = arquivo.lerArquivo(file);
	}
	
	@Override
	public void run() {
			
		Ponto[] ponto = new Ponto[matriz.length+1];
		Escolhe escolhe = new Escolhe();
		matriz[index][0].getOrigem().setUsado(true);
		matriz[index][0].getOrigem().setOrigem(true);
		
		ponto[0] = matriz[index][0].getOrigem();
		
		System.out.print(getName()+":"+matriz[index][0].getOrigem().getId()+" -> ");
		Ponto proximo = escolhe.proximoPonto(matriz, matriz[index][0].getOrigem());
		ponto[1] = proximo;
		Double distancia = matriz[index][proximo.getId()].getDistancia();		
		for(int j = 0; j < matriz.length - 1; j++){
			proximo = escolhe.proximoPonto(matriz, proximo);
			ponto[j+2] = proximo;
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
