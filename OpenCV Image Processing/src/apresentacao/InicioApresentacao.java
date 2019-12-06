package apresentacao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InicioApresentacao extends Application {
	private String caminhoLogo = "file:///C:/Users/eeman/Documents/Eclipse/Processamento-Img-OpenCV/src/data/Logo.png";
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GUIApresentacao.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("LP2 - Teste Dataset");
		stage.getIcons().add(new Image(caminhoLogo));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
