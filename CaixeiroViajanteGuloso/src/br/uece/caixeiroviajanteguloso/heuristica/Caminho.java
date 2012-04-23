package br.uece.caixeiroviajanteguloso.heuristica;

/**
 * @author raquel silveira e paulo alberto
 * @version 1.0
 * A classe Caminho sera responsavel por definir o melhor caminho na matriz
 * O melhor caminho eh definido de acordo com a heuristica Guloso de n partidas
 * */
public class Caminho {
	
	/**
	 * @author adriano patrick, raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo realiza o melhor caminho na matriz e retorna os pontos em sequencia
	 * @param matriz
	 * @return vetor de pontos que define a sequencia de pontos visitados
	 */
	public Ponto[] realizaCaminho(Celula[][] matriz) {
		
		Ponto[] ponto = new Ponto[matriz.length+1];
		Escolhe escolhe = new Escolhe();
		matriz[0][0].getOrigem().setUsado(true);
		matriz[0][0].getOrigem().setOrigem(true);
		
		System.out.print(matriz[0][0].getOrigem().getId()+" -> ");
		ponto[0] = matriz[0][0].getOrigem();
		
		Ponto proximo = escolhe.proximoPonto(matriz, matriz[0][0].getOrigem());
		ponto[1] = proximo;
				
		for(int i = 0; i < matriz.length - 1; i++){
			
			proximo = escolhe.proximoPonto(matriz, proximo);
			ponto[i+2] = proximo;
		}
		return ponto;
	}

}
