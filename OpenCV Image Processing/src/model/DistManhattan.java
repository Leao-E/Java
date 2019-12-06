package model;

import java.util.List;

public class DistManhattan extends Calculos {
	@Override
	public float calcularDistancia(List<Float> x, List<Float> y, int k) {
		float soma = 0;
		for(int i = 0; i < k; i++) {
			soma += Math.abs((x.get(i)-y.get(i)));
		}
		return soma;
	}
	@Override
	public String toString() {
		return "Distancia Manhattan";
	}
}
