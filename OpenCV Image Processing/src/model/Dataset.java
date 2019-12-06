package model;


import java.util.ArrayList;

public class Dataset {
	private ArrayList <Imagem> imagens;
	
	public ArrayList<Imagem> getImagens() {
		return this.imagens;
	}
	public void setImagens(ArrayList<Imagem> imagens) {
		this.imagens = imagens;
	}
	@Override
	public String toString() {
		return this.getImagens().toString();
	}
}
