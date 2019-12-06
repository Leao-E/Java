package model;

import java.util.List;

public class DistEuclidiana extends Calculos {
	@Override
	public float calcularDistancia(List<Float> x, List<Float> y, int k) {
		float soma = 0;
		for (int i = 0; i < k; i++) {
			soma += Math.pow((x.get(i) - y.get(i)), 2);;
		}
		return (float) Math.sqrt(soma);
	}
	@Override
	public String toString() {
		return "Distancia Euclidiana";
	}
}
