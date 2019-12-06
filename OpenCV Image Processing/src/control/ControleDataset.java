package control;

import model.Imagem;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class ControleDataset {
	/* A partir do caminho especificado o dataset
	 * será carregado e armazenado num array de imagens*/
	public static ArrayList<Imagem> carregarDataset(String caminhoDataset) throws Exception{
		BufferedReader leitor = new BufferedReader(new FileReader(caminhoDataset));
		ArrayList<String> atributos = new ArrayList<String>();
		ArrayList<Imagem> dataset = new ArrayList<Imagem>();
		atributos = ControleDataset.lerLinha(leitor);
		while(leitor.ready()){		
			atributos = ControleDataset.lerLinha(leitor);
			dataset.add(ControleDataset.criarImagem(atributos));
		}
		leitor.close();
		return dataset;
	}
	/* Recebe um BuferredReader para ler uma linha e 
	 * retornar ela separada num array de String*/
	private static ArrayList<String> lerLinha(BufferedReader leitor) throws Exception{
		ArrayList<String> linha = new ArrayList<String>();
		linha.addAll(Arrays.asList(leitor.readLine().split(",")));
		return linha;
	}
	/* A partir de um array  de String ele separa os
	 * atributos (considerando o rotulo como ultimo)
	 * e cria uma imagem a partir deles*/
	private static Imagem criarImagem (ArrayList<String> atributos) {
		Imagem imagem = new Imagem();
		imagem.setRotulo(atributos.get(atributos.size() - 1));
		atributos.remove(atributos.size() - 1);
		imagem.setAtributos(ControleDataset.converterFloat(atributos));
		return imagem;
	}
	/* Dado um array de String o metodo converte cada 
	 * String para float e retorna um array de float*/
	private static ArrayList<Float> converterFloat(ArrayList<String> numerosString){
		ArrayList<Float> numerosFloat = new ArrayList<Float>();
		for(String numero: numerosString) {
			numerosFloat.add(new Float(numero));
		}
		return numerosFloat;
	}
}
