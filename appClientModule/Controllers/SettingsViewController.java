package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;

public class SettingsViewController extends BaseView implements Initializable{

	@FXML
	Pane settingsPane;
	
	@FXML
	TextField titleTxtField = new TextField();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	@FXML
	public void checkInputLength(KeyEvent ev){
		
		try {
			// -- Make sure that we have the correct input and input length.
			TextField currentField = (TextField) ev.getSource();
			if( "1234567890abcdefABCDEF".contains( ev.getCharacter() ) && (currentField.getText().length() < 6 ) ) { 
				// -- Empty if.  For some reason why I try to throw some nots in, it does not work.
			}
			else
				ev.consume();
		}catch(Exception e){
			// -- Need a try catch here.  If the textfield does not have any text in it initially, throws an error
		}
	}
}
