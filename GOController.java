package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GOController implements Initializable {
		
		@FXML
		Button quit;
		@FXML
		 Label result;
		@FXML
		ImageView view;
		
		static int win = 0;
		public void set() throws FileNotFoundException {
			if (win > 0) {
				FileInputStream stream = new FileInputStream("E:\\src (3)\\src\\application\\GameOver.JPG"); // FileStream containign the location of the 
				// image file.
				Image image = new Image(stream, 500, 235, false, false); // loading the image from the location.
				view.setImage(image); // Setting the image into the image viewer.
				result.setText("CONGRATULATIONS! YOU WIN");
			
			}
			else {
				FileInputStream stream = new FileInputStream("E:\\src (3)\\src\\application\\GameOver.JPG");
				Image image = new Image(stream, 500, 235, false, false);
				view.setImage(image);
				result.setText("YOU LOST. BETTER LUCK NEXT TIME!");
			}
		}
	
	public void QuitPressed() {
			System.exit(0);
		}

	/* Since the image viewer and the label are created in the fxml using the scene builder, instead of coding in the controller class, the initialize 
	 * function allows us to display image and text according to the situation, as soon as the fxml is loaded
	 *  instead of displaying the same image and text, which is the case if the image and text are hard coded in the fxml. */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			set();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
