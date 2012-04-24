package br.uece.caixeiroviajanteguloso.heuristica;

/**
 * @author raquel silveira e paulo alberto
 * @version 1.0
 * A classe Caminho sera responsavel por definir o melhor caminho na matriz
 * O melhor caminho eh definido de acordo com a heuristica Guloso de n partidas
 * */
public class Caminho implements Comparable<Caminho>{
	
	private Ponto[] caminho;
	private Double distancia;
	
	/**
	 * @author adriano patrick, raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo realiza o melhor caminho na matriz e retorna os pontos em sequencia
	 * @param matriz
	 * @return vetor de pontos que define a sequencia de pontos visitados
	 */
	@Deprecated
	public Ponto[] realizaCaminho(Celula[][] matriz) {
		
		Ponto[] ponto = new Ponto[matriz.length];
		Escolhe escolhe = new Escolhe();
		matriz[0][0].getOrigem().setUsado(true);
		matriz[0][0].getOrigem().setOrigem(true);
		
		System.out.print(matriz[0][0].getOrigem().getId()+" -> ");
		Ponto proximo = escolhe.proximoPonto(matriz, matriz[0][0].getOrigem());
		ponto[0] = proximo;
		
		for(int i = 0; i < matriz.length - 1; i++){
			proximo = escolhe.proximoPonto(matriz, proximo);
			ponto[i+1] = proximo;
		}
		return ponto;
	}

	public Ponto[] getCaminho() {
		return caminho;
	}

	public void setCaminho(Ponto[] caminho) {
		this.caminho = caminho;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	@Override
	public int compareTo(Caminho o) {
		return this.distancia.compareTo(o.getDistancia());
	}

}
