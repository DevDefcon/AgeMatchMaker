package matchMakerMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MatchMaker_Main extends Application{

	private static final String version = "3.2";

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/matchMakerView/MatchMaker_MainScene.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("MatchMaker "+version+"@Hades85");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
