package model;

import java.util.ArrayList;

public class Imagem {
	private ArrayList <Float> atributos;
	private String rotulo;
	
	public Imagem () {
		this.rotulo = "semRotulo";
		this.atributos = new ArrayList<Float>();
	}
	
	public void setAtributos(ArrayList<Float> atributos) {
		this.atributos = atributos;
	}
	public ArrayList<Float> getAtributos() {
		return this.atributos;
	}
	public String getRotulo() {
		return this.rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	@Override
	public String toString() {
		return "<Rotulo: "+this.getRotulo()+", Atributos: "+this.getAtributos().toString();
	}
}
