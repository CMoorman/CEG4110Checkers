package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import UIPanes.LobbyView;
import UIPanes.SettingsView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        	try {
				AnchorPane currentView = (AnchorPane) FXMLLoader.load( SettingsView.class.getResource("MainView.fxml") );
				Scene settingsScene = new Scene( currentView );
				Stage newState = new Stage();
				newState.setScene( settingsScene );
				newState.show();
				
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if ( e.getSource()== saveBtn){
        	/**
        	 * NEED TO THROW IN SAVING LOGIC HERE. WHAT EVER CHANGED, WE NEED TO SET IT IN THE BASE VIEW.
        	 */
        	try {
        		AnchorPane currentView = (AnchorPane) FXMLLoader.load( LobbyView.class.getResource("MainView.fxml") );
				Scene settingsScene = new Scene( currentView );
				Stage newState = new Stage();
				newState.setScene( settingsScene );
				newState.show();
				
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
}
