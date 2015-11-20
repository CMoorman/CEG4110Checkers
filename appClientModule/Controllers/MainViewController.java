package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import UIPanes.BaseView;
import UIPanes.LobbyView;
import UIPanes.SettingsView;

public class MainViewController extends BaseView implements BaseViewController, Initializable {
	@FXML
	Button settingsBtn;
	
	@FXML
	Button loginBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		settingsBtn.setOnAction( e -> ButtonClicked(e) );
		loginBtn.setOnAction( e -> ButtonClicked(e) );
	}

	@Override
	public void ButtonClicked( ActionEvent e ){
	 if ( e.getSource()== settingsBtn ){
        	try {
				AnchorPane currentView = (AnchorPane) FXMLLoader.load( SettingsView.class.getResource("SettingsView.fxml") );
				Scene settingsScene = new Scene( currentView );
				Stage newState = new Stage();
				newState.setScene( settingsScene );
				newState.show();
				
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if ( e.getSource()== loginBtn){
        	try {
        		AnchorPane currentView = (AnchorPane) FXMLLoader.load( LobbyView.class.getResource("LoginView.fxml") );
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
