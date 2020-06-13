package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	Button easy;
	@FXML
	Button medium;
	@FXML
	Button hard;
	
	Stage mainStage = new Stage();
	
	public void EasyPressed() throws Exception{
		setLevel(1); // setting lvl=1.
		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		/* assign the root to a new scene and define its dimensions */
		Scene scene = new Scene(root, 740, 550);
		/* set the title of the stage (window) */
		mainStage.setTitle("Sudoku Challenger");
		/* set the scene of the stage to our newly created from the layout scene */
		mainStage.setScene(scene);
		/* show the stage */
		mainStage.show();
		easy.getScene().getWindow().hide();
	}
	
	public void MediumPressed() throws Exception{	
		setLevel(2); // setting lvl =2;
		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));		
		/* assign the root to a new scene and define its dimensions */
		Scene scene = new Scene(root, 740, 550);
		/* set the title of the stage (window) */
		mainStage.setTitle("Sudoku Challenger");
		/* set the scene of the stage to our newly created from the layout scene */
		mainStage.setScene(scene);
		/* show the stage */
		mainStage.show();
		medium.getScene().getWindow().hide(); // hiding the previous stage.
	}
	
	public void HardPressed() throws Exception{	
		setLevel(3); // setting lvl =3;
		Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		/* assign the root to a new scene and define its dimensions */
		
		Scene scene = new Scene(root, 740, 550);
		/* set the title of the stage (window) */
		mainStage.setTitle("Sudoku Challenger");
		/* set the scene of the stage to our newly created from the layout scene */
		mainStage.setScene(scene);
		/* show the stage */
		mainStage.show();
		hard.getScene().getWindow().hide(); // hiding the previous stage.
	}
	/* Method to set the value of the static int variable called "lvl" that is created in the "SudokuController" class. Using this simple variable allows us
	 * to create a grid corresponding to the level chosen by the user. This is done using the concept of polymorphism*/
	static void setLevel(int x) { 
		SudokuController.lvl =x;
	}
}