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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BaseViewController extends BaseView implements Initializable {
	
	@FXML
	Button settingsBtn = new Button();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		settingsBtn.setOnAction( e -> ButtonClicked(e) );
		
	}
	
	@Override
	public void ButtonClicked( ActionEvent e ){
	 if ( e.getSource()== settingsBtn ){
        	try {
				currentView = (AnchorPane) FXMLLoader.load( SettingsView.class.getResource("SettingsView.fxml") );
				Scene settingsScene = new Scene( currentView );
				Stage newState = new Stage();
				newState.setScene( settingsScene );
				
				Stage currentStage = this.getCurrentStage();
				currentStage.close();
				
				newState.show();
				
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
}
