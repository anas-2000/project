package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// This is the controller class of the fxml named "Main". It controls the playing screen of the game.
public class SudokuController implements Initializable{
	
	@FXML
	Button button1;
	@FXML
	Button button2;
	@FXML
	Button button3;
	@FXML
	Button button4;
	@FXML
	Button button5;
	@FXML
	Button button6;
	@FXML
	Button button7;
	@FXML
	Button button8;
	@FXML
	Button button9;
	@FXML
	Button check;
	@FXML
	Canvas canvas;
	@FXML
	Label checks;
	@FXML
	Label movesLabel;
	@FXML
	Label ErrorLabel;
	
	int selected_row, selected_column;
	static int lvl;
	
	Stage gameoverStage= new Stage();
	
	Board board;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* The concept of polymorphism is applied here. We have declared an object of "Board" type above. The different levels of the games have different
		 * number of maximum allowed moves, and maximum allowed checks. The user must solve the grid before running out of the remaining moves, and before
		 * using all the checks. Therefore the three sub-classes BoardEasy, BoardMedium and BoardHard, have different number of moves and
		 * checks. Since all their other properties are same, we simply re-use the code, by using the concept of inheritance. 
		 * Based on the level chosen by the user we have to create an object corresponding to that sub-class. To make this possible, we declared an object
		 * of the "Board" type, and inside the initialise function we decide its actual type, ensuring that a grid of the corresponding difficulty level is loaded.
		 * if the user selects Easy, lvl = 1, and hence we set the actual type of the object to BoardEasy().
		 * actual type = BoardMedium() if lvl =2.
		 * actual type = BoardHard() if lvl = 3. */
		if(lvl == 1) { 
	    board = new BoardEasy();
		}
		else if(lvl == 2) {
		board = new BoardMedium();
		}
		else {
		board = new BoardHard();
		}	
			
		selected_row = 0;
		selected_column = 0;
		
		// getGraphicsContext2D() returns the graphics context associated with the canvas.
		GraphicsContext context = canvas.getGraphicsContext2D();
		
		checks.setText("" + board.CHECKS);
		movesLabel.setText("" + board.moves);
		
		drawOnCanvas(context); // call to the drawOnCanvas method.
		
	}
	
	public void drawOnCanvas(GraphicsContext context){ // method to draw Sudoku board on the Canvas.
		context.clearRect(0, 0, 450, 450); // clears the whole area of the canvas of any drawing.
		//Using nested loops we create a 9 by 9 grid of rectangles.
		for(int i = 0 ; i<9 ; i++) {
			for(int j =0 ; j<9 ; j++) {
				int vertical_position, horizontal_position;
				/* Since the canvas is 450 by 450 and we need to make 9 rectangles in each row and 9 rectangles in each column 
				 * it gives us a 50 by 50 area for each rectangle. So to get the vertical position of the rectangle
				 * we multiply 50 by its row number. 2 is then added to add the offset 
				 * similarly for horizontal position column number is mutliplied by 50 and 2 is added*/ 
				vertical_position = 50*i + 2;
				horizontal_position = 50*j+2;
				int width = 46; // width of the each individual rectangle.
				
				context.setFill(Color.GAINSBORO); // setting the colour of the rectangles. 
				
				
				//Drawing a rounded arc rectangle of equal width and length, at the vertical and horizontal position calculated.
				context.fillRect(vertical_position, horizontal_position, width, width);
				
				

			}
		}
		context.setStroke(Color.GOLDENROD); // to highlight the cell selected by the user.
		
		context.setLineWidth(5); //setting the width of the stroke.
		
		//Drawing the stroke of color and stroke width specified above, around the rounded rectangles. 
		context.strokeRoundRect((selected_column*50)+2, (selected_row*50)+2, 46, 46, 10, 10);
		
		/* The following code is to draw the numbers that the user enters.
		 * The initial grid and the numbers that the user enters all are stored in the 2 dimensional array called "grid". In addition the computer generated
		 * numbers of the grid are also stored in the 2D array "solGrid". */ 
		int iniGrid[][] = board.getGrid();
		for(int i=0 ; i<9 ; i++) {
			for(int j=0 ; j<9 ; j++) {
				int vertical_position = i*50;
				int horizontal_position = j*50;
				
				context.setFill(Color.BLACK);
				
				context.setFont(new Font(20));
				
				if(iniGrid[i][j] !=0) { // If the specific position of the grid has not been filled, then we leave the corresponding rectangle blank.
					context.fillText(iniGrid[i][j] + "", horizontal_position+18, vertical_position+30 );
				}
			}
		}
	
	}
	/* Method to handle the player's mouse clicks, to determine which rectangle has been clicked.*/
	public void MouseClick() throws InvalidCellException {
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				//Getting the position of the mouse pointer on the canvas, and casting that into an integer.
				int mousex = (int)event.getX();
				int mousey = (int)event.getY();
				
				
				// dividing the total width of the canvas associated for the cells by 50(width allocated to each cell including the spacing on both the sides)
				// we will get a number representing the column of the cell clicked. 
				// similarly dividing the total height of the canvas by 50(height allocated to each cell including the spacing), we will get the row number
				// of the cell clicked.
				
				selected_row = (int)(mousey/50);
				selected_column = (int)(mousex/50);
			
				
				drawOnCanvas(canvas.getGraphicsContext2D());
				
			
				}
		});
	}
	
	/* Methods to allocate functions to the buttons.
	 * In these methods we have used the principle of Exception handling. The numbers in the grid that are generated by the computer must not be edited.
	 * This is ensured using exception handling. If the user tries to edit a pre-defined cell, an exception is displayed on the screen saying that the
	 * user cannot edit that specific cell, and therefore that cell remains unchanged.
	 * The row and column of the cell the user is trying to edit is determined, and it is checked whether the solGrid array has a non-zero number at this
	 * index, since the solGrid contains only the computer generated numbers. 
	 * If solGrid contains a non-zero number, an exception is displayed, otherwise the user is allowed to edit the cell.
	 * Number of moves is decremented if the user is successfully able to enter the number.
	 */
	
	public void Button1Pressed() throws InvalidCellException {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(1, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button2Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(2, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button3Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(3, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button4Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(4, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button5Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(5, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button6Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(6, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button7Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(7, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button8Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(8, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Button9Pressed() {
		try {
			if(board.solGrid[selected_row][selected_column]!=0) {
				throw new InvalidCellException(" \nYou cannot edit this cell");
			}
		board.editGrid(9, selected_row, selected_column);
		board.moves--;
		}
		catch(InvalidCellException invalidcellexception) {
			
			ErrorLabel.setText("Exception: "+invalidcellexception);
		}
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	/* This method contains the action performed by the "checks" button.
	 * If the number entered by the user is correct, it remains unchanged, however, if the number entered is incorrect it is removed from the grid.
	 * In both the cases, number of checks remaining is decremented. 
	 */
	public void CheckButtonPressed() throws Exception {
		board.checkrefGrid();
		board.CHECKS--;
		try {
			CheckIfGameover();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Method to check if the game is over.
	 * The game gets over when one of the following conditions is met:
	 * 1) The user runs out of his moves
	 * 2) The user uses all his checks without solving the grid.
	 * 3) The user solves the grid within the allowed moves and checks.
	 * If the game gets over, the playing window of the game is closed and another window is displayed.*/
	public void CheckIfGameover() throws Exception {
		if (board.CHECKS< 0 || board.moves == 0 || board.CheckIfWin()) {
			if(board.CheckIfWin() == true)
				GOController.win = 1;
			movesLabel.setText("" + board.moves);
			checks.setText("" + board.CHECKS);
			Parent root = FXMLLoader.load(getClass().getResource("/application/GameOver.fxml"));
			
			/* assign the root to a new scene and define its dimensions */			
			Scene scene = new Scene(root, 670, 480);
			/* set the title of the stage (window) */
			gameoverStage.setTitle("Sudoku");
			/* set the scene of the stage to our newly created from the layout scene */
			gameoverStage.setScene(scene);
			/* show the stage */
			gameoverStage.show();
			check.getScene().getWindow().hide();
		}
		else {
			movesLabel.setText("" + board.moves);
			checks.setText("" + board.CHECKS);
			drawOnCanvas(canvas.getGraphicsContext2D());
		}
	}
	
}

// Custom exception class
class InvalidCellException extends Exception{
	InvalidCellException(String s){
		super(s);
	}
}

