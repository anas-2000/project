package application;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

	Stage menuStage = new Stage();
	/* modify the method declaration to throw generic Exception (in case any of the steps fail) */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

		/* load layout.fxml from file and assign it to a scene root object */
		Parent root = FXMLLoader.load(getClass().getResource("/application/MainMenu.fxml"));

		/* assign the root to a new scene and define its dimensions */
		Scene scene = new Scene(root, 600, 480);
		
	
		/* set the title of the stage (window) */
		menuStage.setTitle("Sudoku Challenger");
		/* set the scene of the stage to our newly created from the layout scene */
		menuStage.setScene(scene);
		/* show the stage */
		menuStage.show();

		} catch(Exception e) {
			e.printStackTrace();
	}
	}
	/***
	 *
	 * @param args to configure launch with
	 */
	public static void main(String[] args) {
		launch(args);
	}
}