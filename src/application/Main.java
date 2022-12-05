package application;
	
import java.io.IOException;

import dao.PokemonDetailDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
    public void start(Stage stage) throws Exception {
        prepareControl(stage);
        stage.show();
    }

    private void prepareControl(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Main.fxml"));
        VBox newPane = (VBox) fxmlLoader.load();

        Scene scene = new Scene(newPane);
        stage.setTitle("PokemonPartyManagementTool");
        stage.setScene(scene);
    }
}
