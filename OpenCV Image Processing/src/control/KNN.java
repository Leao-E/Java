package control;

import java.util.ArrayList;
import java.util.Comparator;

import model.Calculos;
import model.Dataset;
import model.Imagem;

public class KNN{
	/* valor k default é 3 */
	private int valorK = 3;
	
	public int getValorK() {
		return valorK;
	}
	public void setValorK(int valorK) {
		this.valorK = valorK;
	}
	/* Dado um dataset, uma imagem e um metodo de calculo compara se
	 * há ou não pessoas na imagem.
	 * Percorre o dataset e calcula cada imagem do dataset com
	 * a imagem dada e armazena num array de distacia
	 * */
	public String comparar (Dataset dataset, Imagem imagem, Calculos calculo) {
		ComparadorDistancia comparador = new ComparadorDistancia();
		ArrayList<Distancia> menoresDistancias = new ArrayList<Distancia>();
		for (Imagem imagemDataset : dataset.getImagens()) {
			Distancia distancia = new Distancia();
			distancia.setDistancia(calculo.calcularDistancia(imagemDataset.getAtributos(), imagem.getAtributos(), 1000));
			distancia.setRotulo(imagemDataset.getRotulo());
			menoresDistancias.add(distancia);
		}
		menoresDistancias.sort(comparador);
		return this.calcularResultado(menoresDistancias);
	}
	/* dado um array de distancia a função vê entre as k
	 * imagens com menor distantancia qual o rotulo mais frequente 
	 * entre elas e retorna ele
	 */
	private String calcularResultado(ArrayList<Distancia> distancias) {
		int pessoa = 0;
		int naoPessoa = 0;
		for (int i = 0; i < this.valorK; i++) {
			if(distancias.get(i).getRotulo().equals("person")) {
				pessoa++;
			}
			else {
				naoPessoa++;
			}
		}
		if(pessoa > naoPessoa) {
			return "person";
		}
		if (pessoa < naoPessoa) {
			return "noPerson";
		}
		return distancias.get(0).getRotulo();	
	}
	/* classe para representar distancia */
	private class Distancia{
		private float distancia;
		private String rotulo;
		
		public float getDistancia() {
			return distancia;
		}
		public void setDistancia(float distancia) {
			this.distancia = distancia;
		}
		public String getRotulo() {
			return rotulo;
		}
		public void setRotulo(String rotulo) {
			this.rotulo = rotulo;
		}
		@Override
		public String toString() {
			return "<Distancia: "+this.getDistancia()+", Rotulo: "+this.getRotulo()+">";
		}
	}
	/* comparador, é usado para poder ordenar o array de distancias
	 * com o metodo sort*/
	private class ComparadorDistancia implements Comparator<Distancia> {
		@Override
		public int compare(Distancia d1, Distancia d2) {
			if ( d1.getDistancia() < d2.getDistancia()) {
				return -1;
			}
			if ( d1.getDistancia() > d2.getDistancia()) {
				return 1;
			}
			return 0;
		}
	}
}
