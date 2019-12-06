package control;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;
import java.util.List;
import java.util.ArrayList;

public class ControleImagem {
	//carrega imagem do sistema e já seta os atributos da imagem
	public static ArrayList<Float> carregarAtributos (String caminhoParaImagem) {
		Mat imagem = new Mat();
		ArrayList<Float> atributos = new ArrayList<Float>();
		imagem = Imgcodecs.imread(caminhoParaImagem, Imgcodecs.IMREAD_GRAYSCALE);
		Imgproc.resize(imagem, imagem, new Size(64,128), 0.5, 0.5, Imgproc.INTER_LINEAR);
		atributos.addAll(ControleImagem.calculaHOG(imagem));
		return atributos;
	}
	//Calcula o hog a partir de uma imagem Mat passada 
	private static List<Float> calculaHOG (Mat imagem){
		HOGDescriptor hog = new HOGDescriptor();
		MatOfFloat atributos = new MatOfFloat();
		hog.compute(imagem, atributos);
		return atributos.toList();
	}
}
