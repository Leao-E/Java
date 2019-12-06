package apresentacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.ControleDataset;
import control.KNN;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Calculos;
import model.Dataset;
import model.DistChebychev;
import model.DistEuclidiana;
import model.DistManhattan;
import model.Imagem;

public class Controle implements Initializable{
	private Dataset dataset = new Dataset();
	private Dataset datasetTeste = new Dataset();
	private KNN knn = new KNN();
    @FXML
    private Label lblErros;

    @FXML
    private Label lblAcertos;

    @FXML
    private TextField inputK;

    @FXML
    private ComboBox<Calculos> cbCalc;

    @FXML
    private Button btnSet;

    @FXML
    private Button btnAnalisar;
    
    @FXML
    void clickAnalisar(ActionEvent event) {
    	if (cbCalc.getValue() == null) {
    		Alert alert = new Alert(AlertType.WARNING, "Nenhum metodo de calculo foi selecionado");
    		alert.show();
    	}else {
    		System.out.println(cbCalc.getValue().toString());
	    	String s;
	    	Integer acertos = 0;
	    	Integer erros = 0;
	    	int i = 1;
	    	for(Imagem img:datasetTeste.getImagens()) {
				s = knn.comparar(dataset, img, cbCalc.getValue());
				if(s.equals(img.getRotulo())) {
					acertos++;
				}else {
					erros++;
				}
				System.out.println("imagem "+i+":\n - rotulo: "+img.getRotulo()+"\n - resultado: "+s);
				i++;
	    	}
	    	lblAcertos.setText(acertos.toString());
	    	lblErros.setText(erros.toString());
    	}
    }

    @FXML
    void clickSet(ActionEvent event) {
    	try {
			knn.setValorK(new Integer(inputK.getText()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Valor de K invalido");
			alert.show();
		}
    }
    private void carregarCamposCB() {
    	ArrayList<Calculos> metodosCalc = new ArrayList<Calculos>();
    	metodosCalc.add(new DistEuclidiana());
    	metodosCalc.add(new DistManhattan());
    	metodosCalc.add(new DistChebychev());
    	cbCalc.setItems(FXCollections.observableArrayList(metodosCalc));
    }
    private void carregarDataset(Dataset dataset, String caminhoDataset) {
    	try {
    		dataset.setImagens(ControleDataset.carregarDataset(caminhoDataset));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Erro ao carregar o dataset");
			alert.show();
			e.printStackTrace();
		}	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarCamposCB();
		this.carregarDataset(dataset, "C:\\Users\\eeman\\Documents\\Eclipse\\Processamento-Img-OpenCV\\src\\dataset\\dataset.csv");
		this.carregarDataset(datasetTeste, "C:\\Users\\eeman\\Documents\\Eclipse\\Processamento-Img-OpenCV\\src\\dataset\\datasetTeste.csv");
	}

}
