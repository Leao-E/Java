package view;

import java.io.IOException;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Inicio extends Application {
	/* Caminho do logo */
	private String caminhoLogo = "file:///C:/Users/eeman/Documents/Eclipse/Processamento-Img-OpenCV/src/data/Logo.png";
	/*Carrega a GUI 
	 *seta a GUI no stage
	 *desativa o resize
	 *adiciona o titulo e o logo
	 *deixa o stage visivel*/
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("LP2 - Processamento de Imagem Java");
		stage.getIcons().add(new Image(caminhoLogo));
		stage.show();
	}
	//necessario para o OpenCV
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
