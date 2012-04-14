package br.uece.caixeiroviajanteguloso.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import br.uece.caixeiroviajanteguloso.heuristica.MatrizAdjacencias;
import br.uece.caixeiroviajanteguloso.heuristica.Celula;
import br.uece.caixeiroviajanteguloso.heuristica.Ponto;

/**
 * @author raquel silveira e paulo alberto
 * @version 1.0
 * A classe ArquivoUtils sera responsavel por ler o arquivo e indetificar seu tipo
 * De acordo com o tipo, sera gerada a matriz
 * */
public class ArquivoUtils {
	
	public static void salvar(String arquivo, String conteudo) {
		
		File file = new File(arquivo);
		
		if(!file.exists()){
			
		}	
	}	
	
	private enum TipoArquivo
	{
		matriz,
		ponto
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo le o arquivo e transforma na matriz simetrica
	 * @param arquivo
	 * @return celula[][] - matriz simetrica
	 */
	public Celula[][] lerArquivo(File arquivo) {
		
		Celula[][] matriz = null;
		try
		{
			BufferedReader arquivoLeitura = new BufferedReader(new FileReader(arquivo.getAbsolutePath()));
			String linha1 = arquivoLeitura.readLine();
			TipoArquivo arq = identificaTipoArquivo(linha1);
			int n = identificaQtdeNos(linha1);
			matriz = new Celula[n][n];
						
			int indiceLinha = 0;
			switch (arq) {
				case matriz: {
					while(arquivoLeitura.ready())
					{ lerArquivoMatriz(matriz, arquivoLeitura.readLine(), indiceLinha); indiceLinha++; }
					new MatrizAdjacencias().geraMatrizAdjacenciasApartirDeMatrizIncompleta(matriz);
					break;
				}
				case ponto: {
					List<Ponto> listaPontos = new ArrayList<Ponto>();
					while(arquivoLeitura.ready())
					{ listaPontos.add(indiceLinha, lerArquivoPonto(arquivoLeitura.readLine())); indiceLinha++; }
					matriz = new MatrizAdjacencias().geraMatrizAdjacenciasApartirDePontos(listaPontos);
					break;
				}
			}
					   
	        arquivoLeitura.close();
		}
		catch (FileNotFoundException  e) {
			// TODO: handle exception
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		return matriz;
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo identifica o tipo de arquivo (matriz ou ponto) de acordo com a primeira linha do aquivo
	 * @param arquivoLeitura
	 * @return o tipo de arquivo (matriz ou ponto)
	 */
	private TipoArquivo identificaTipoArquivo(String linha) {
		
		TipoArquivo arquivo = null;
		if (linha.contains("matriz"))
		{ arquivo = TipoArquivo.matriz;  }
		else
		{ arquivo = TipoArquivo.ponto; }
		return arquivo;
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo identifica a quantidade de nos presentes no arquivo
	 * @param linha
	 * @return quantidade de nos
	 */
	private int identificaQtdeNos(String linha) {
		
		int qtdeNos = 0;
		StringTokenizer token = new StringTokenizer(linha, "N = Sol");
		qtdeNos = Integer.parseInt(token.nextToken());
		return qtdeNos;
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo obtem da linha os valores da matrizz
	 * @param matriz
	 * @param linha
	 * @param indiceLinha
	 */
	private void lerArquivoMatriz(Celula[][] matriz, String linha, int indiceLinha) {
		
		StringTokenizer token = new StringTokenizer(linha, " ");
		matriz[indiceLinha][indiceLinha] = obtemCelulaDiagonal(indiceLinha);
		int indiceToken = indiceLinha+1;
		while(token.hasMoreTokens()) {
    		Ponto origem = new Ponto(); origem.setId(indiceLinha);
    		Ponto destino = new Ponto(); destino.setId(indiceToken);
    		matriz[indiceLinha][indiceToken] = new Celula(origem, destino, Double.parseDouble(token.nextToken()), false);
	       	indiceToken++;
    	}
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo obtem o ponto da diagonal da matriz
	 * @param indice
	 * @return celula
	 */
	private Celula obtemCelulaDiagonal(int indice) {
		
		Ponto origem = new Ponto();
		origem.setId(indice);
		Ponto destino = new Ponto();
		destino.setId(indice);
		Celula celula = new Celula(origem, destino, 0d, false);
		return celula;
	}
	
	/**
	 * @author raquel silveira e paulo alberto
	 * @version 1.0
	 * Este metodo obtem da linha os valores dos pontos
	 * Primeiro valor: ID; Segundo valor: Coordenada X; Terceiro valor: Coordenada Y. 
	 * @param linha - Representa a linha do arquivo
	 * @return ponto - Representa o ponto com os valores da linha
	 */
	private Ponto lerArquivoPonto(String linha) {
		
		Ponto ponto = new Ponto();
		StringTokenizer token = new StringTokenizer(linha, " ");
		int indiceToken = 0;
    	while(token.hasMoreTokens())
    	{
    		String valor = token.nextToken();
    		switch (indiceToken) {
				case 0: { ponto.setId(Integer.parseInt(valor)); break; }
				case 1: { ponto.setCoordX(Float.parseFloat(valor)); break; }
				case 2: { ponto.setCoordY(Float.parseFloat(valor)); break; }
    		}    		
			indiceToken++;
    	} 
    	return ponto;
	}
}
