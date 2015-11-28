package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import UIPanes.SettingsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginViewController extends BaseView implements Initializable, BaseViewController {

	@FXML
	TextField usernameTxtField;
	
	@FXML
	Button submitBtn;
	
	@FXML
	Button cancelBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		submitBtn.setOnAction( e -> ButtonClicked(e) );
		cancelBtn.setOnAction( e -> ButtonClicked(e) );
	}
	
	@Override
	public void ButtonClicked( ActionEvent e ){ 
		if ( e.getSource()== submitBtn ){
        	try {
        		
        		boolean loginSuccess = false;
        		
        		String userName = usernameTxtField.getText();
        		
        		// -- Make sure that there is something there and that we set a limit on it.
        		if( userName.length() > 0 && userName.length() <= 20 ) {
        			
        			loginSuccess = network.connectToServer(serverIP, userName );
        			
        			// -- Try to connect.  Trying to connect will yield a boolean value.
        			if( loginSuccess ) {    
        				BaseView.network.setUsrName(userName);        				
            			currentView = (AnchorPane) FXMLLoader.load( SettingsView.class.getResource("LobbyView.fxml") );
        				Scene settingsScene = new Scene( currentView );
        				Stage newState = new Stage();
        				newState.setScene( settingsScene );
        				newState.show();	
            		}
        		}
        		
        		
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		else if ( e.getSource()== cancelBtn ){
        	try {
				currentView = (AnchorPane) FXMLLoader.load( SettingsView.class.getResource("MainView.fxml") );
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
