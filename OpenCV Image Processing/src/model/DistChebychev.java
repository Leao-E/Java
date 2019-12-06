package model;

import java.util.List;

public class DistChebychev extends Calculos {
	@Override
	public float calcularDistancia(List<Float> x, List<Float> y, int k) {
		float max = 0;
		for(int i = 0; i < k; i++) {
			if (Math.abs(x.get(i)-y.get(i)) > max) {
				max =  Math.abs(x.get(i)-y.get(i));
			}
		}
		return max;
	}
	@Override
	public String toString() {
		return "Distancia Chebychev";
	}
}
