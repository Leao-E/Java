package control;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Calculos;
import model.Dataset;
import model.DistChebychev;
import model.DistEuclidiana;
import model.DistManhattan;
import model.Imagem;
import control.KNN;

public class ControleGUI implements Initializable {
	
	private String caminhoDisplayImg = null;
	/* Caminho para o dataset */
	private String caminhoDataset = Paths.get(System.getProperty("user.dir"),"src","dataset","dataset.csv").toString();
	private Dataset dataset = new Dataset();
	private Imagem imagem = new Imagem();
	private KNN knn = new KNN();
	
	@FXML
    private Button btnSelecionar;

    @FXML
    private ImageView displayImg;

    @FXML
    private ComboBox<Calculos> cbCalculo;

    @FXML
    private Button btnAnalisar;

    @FXML
    private Label lblResultado;

    @FXML
    private CheckMenuItem debug;

    @FXML
    private AnchorPane painelDebug;
    
    @FXML
    private TextField valorK;
    
    @FXML
    void setKDebug(ActionEvent event) {
    	try {
			knn.setValorK(new Integer(valorK.getText()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Valor de K invalido");
			alert.show();
		}
    }
    
    @FXML
    void checkDebug(ActionEvent event) {
    	if(debug.isSelected()){
    		painelDebug.setVisible(true);
    	}else {
    		painelDebug.setVisible(false);
    		knn.setValorK(1);
    	}
    }
    
    @FXML
    void clickAnalisar(ActionEvent event) {
    	Alert alert;
    	if(this.cbCalculo.getValue()==null) {
    		alert = new Alert(AlertType.WARNING, "Nenhum metodo de calculo foi selecionado");
    		alert.show();
    	}else {
    		if(this.caminhoDisplayImg == null) {
        		alert = new Alert(AlertType.WARNING, "Nenhuma imagem selecionada ou imagem com erro\nSelecione outra imagem");
        		alert.show();
        	}else {
        		if(knn.comparar(dataset, imagem, cbCalculo.getValue()).equals("person")) {
        			lblResultado.setText("Person");
        		}else {
        			lblResultado.setText("NoPerson");
        		}
        	}
    	}
    }

    @FXML
    void clickSelecionar(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Selecione imagem");
		FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Arquivos de imagens", "*.png","*.jpeg","*jpg");
		chooser.getExtensionFilters().addAll(filtroImg);
		File arquivo = chooser.showOpenDialog(null);
		try {
			displayImg.setImage(new Image(arquivo.toURI().toURL().toString()));
			caminhoDisplayImg = arquivo.getPath();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.NONE,"Nenhuma imagem foi selecionada",ButtonType.CLOSE);
			alert.setTitle("Aviso");
			alert.show();
			return;
		}
		try {
			imagem.setAtributos(ControleImagem.carregarAtributos(caminhoDisplayImg));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.NONE,"Erro no processamento da imagem",ButtonType.CLOSE);
			alert.setTitle("Aviso");
			alert.show();
			caminhoDisplayImg = null;
		}
		
    }
    @FXML
    void clickComoUsar(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION, "Utilize o botão 'Selecionar Imagem' para carregar um imagem (jpeg, jpg ou png do sitema. Em seguida selecione um metodo de calculo e clique em analisar. O resultado logo será exibido");
    	alert.show();
    }
    @FXML
    void clickSobre(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION, "Projeto da disciplina Linguagem de Programação 2 (LP-II). Implementado por Emanuel Felipe G. Leão\nContato: eemanuelleao@gmail.com");
    	alert.show();
    }
    private void carregarCamposCB() {
    	ArrayList<Calculos> metodosCalc = new ArrayList<Calculos>();
    	metodosCalc.add(new DistEuclidiana());
    	metodosCalc.add(new DistManhattan());
    	metodosCalc.add(new DistChebychev());
    	cbCalculo.setItems(FXCollections.observableArrayList(metodosCalc));
    }
    private void carregarDataset() {
    	try {
    		this.dataset.setImagens(ControleDataset.carregarDataset(caminhoDataset));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Erro ao carregar o dataset");
			alert.show();
			e.printStackTrace();
		}
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.carregarCamposCB();
		this.carregarDataset();
	}

}
