package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;

public class MainViewController extends BaseView implements BaseViewController, Initializable {
	
	@FXML
	Button settingsBtn;
	
	@FXML
	Button loginBtn;
	
	@FXML
	Button quitBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		settingsBtn.setOnAction( e -> ButtonClicked(e) );
		loginBtn.setOnAction( e -> ButtonClicked(e) );
		quitBtn.setOnAction( e -> ButtonClicked(e) );
	}

	@Override
	public void ButtonClicked( ActionEvent e ){
	 if ( e.getSource() == settingsBtn ){
			switchScene(SettingsViewController.getViewInstance());
        }else if ( e.getSource() == loginBtn){
        	switchScene(LoginViewController.getViewInstance());
        }else if( e.getSource() == quitBtn ){
        	
        	if( network.isCurrentlyConnected ){
        		network.svrCommunicator.disconnect(true);
        	}
        	Platform.exit();
	        System.exit(0);
        }
	}

	private static Scene mainView = null;

	public static Scene getViewInstance() {
		if (MainViewController.mainView == null) {
			try {
				AnchorPane mainPane = (AnchorPane) FXMLLoader.load(BaseView.class.getResource(MAIN_VIEW_FMXL));
				MainViewController.mainView = new Scene(mainPane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return MainViewController.mainView;

	}
}
