package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SettingsViewController extends BaseView implements Initializable, BaseViewController{

	@FXML
	Pane settingsPane;
	
	@FXML
	TextField titleTxtField = new TextField();
	
	@FXML
	Button cancelBtn;
	
	@FXML
	Button saveBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cancelBtn.setOnAction( e -> ButtonClicked(e) );
		saveBtn.setOnAction( e -> ButtonClicked(e) );
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

	@Override
	public void ButtonClicked(ActionEvent e) {
		/**
		 * CANCEL OUT, DON"T SAVE ANYTHING.
		 */
		if ( e.getSource()== cancelBtn ){
			switchScene(MainViewController.getViewInstance());
        }else if ( e.getSource()== saveBtn){
        	/**
        	 * NEED TO THROW IN SAVING LOGIC HERE. WHAT EVER CHANGED, WE NEED TO SET IT IN THE BASE VIEW.
        	 */
        	switchScene(MainViewController.getViewInstance());
        }
	}
	private static AnchorPane settingsView = null;

	public static AnchorPane getViewInstance() {

		if (SettingsViewController.settingsView == null) {
			try {
				SettingsViewController.settingsView = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(SETTINGS_VIEW_FXML));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return SettingsViewController.settingsView;

	}
}
