package apresentacao;
import org.opencv.core.Core;

import control.ControleDataset;
import control.KNN;
import model.Dataset;
import model.DistChebychev;
import model.DistEuclidiana;
import model.DistManhattan;
import model.Imagem;

public class Teste {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	/* debug only 
	 * testa o arquivo datasetTeste*/
	public static void main(String[] args) {
		Dataset dO = new Dataset();
		Dataset dT = new Dataset();
		KNN knn = new KNN();
		String s = new String();
		int a =0;
		int e=0;
		//Seleciona o valor K a ser utilizado
		knn.setValorK(5);
		try {
			/* É esperado que ocorra um erro na hora de carregar o dataset
			 * para corrigir tal erro basta mudar o caminha dos arquivos dataset			
			 * */
			dO.setImagens(ControleDataset.carregarDataset("C:\\Users\\eeman\\Documents\\Eclipse\\Processamento-Img-OpenCV\\src\\dataset\\dataset.csv"));
			dT.setImagens(ControleDataset.carregarDataset("C:\\Users\\eeman\\Documents\\Eclipse\\Processamento-Img-OpenCV\\src\\dataset\\datasetTeste.csv"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for(Imagem img:dT.getImagens()) {
			/* O DistManhattan pode ser substituido por qualquer outra
			 * classe filha de Calculos*/
			s = knn.comparar(dO, img, new DistManhattan());
			System.out.println(img.getRotulo() + " - " + s);
			if(s.equals(img.getRotulo())) {
				a++;
			}else {
				e++;
			}
		}
		System.out.println(a + " - "+e);
	}
}
