package Controllers;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;
import javafx.scene.layout.Pane;

public class MainViewController extends BaseView implements BaseViewController, Initializable {
	
	@FXML
	private Button settingsBtn;

	@FXML
	private Button loginBtn;
	
	@FXML
	private Button quitBtn;

    @FXML
    private javafx.scene.control.Label mainMenuTitle;

    @FXML
    private Pane mainViewPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		settingsBtn.setOnAction(e -> ButtonClicked(e));
		loginBtn.setOnAction(e -> ButtonClicked(e));
		quitBtn.setOnAction(e -> ButtonClicked(e));
        settingsBtn.setStyle("-fx-background-color: #" + settingsBtnColor + ";" +
                        "-fx-text-fill: #" + settingsBtnTextColor + ";"
        );

        loginBtn.setStyle("-fx-background-color: #" + loginBtnColor + ";" +
                        "-fx-text-fill: #"+ loginBtnTextColor +";"
        );
        quitBtn.setStyle("-fx-background-color: #" + quitBtnColor + ";" +
                        "-fx-text-fill: #" + quitBtnTextColor + ";"
        );

        mainViewPane.setStyle("-fx-background-color: #" + backgroundColor);
        mainMenuTitle.setStyle("-fx-text-fill: #" + titleColor);

    }

    public void updateUI(){
        main.settingsBtn.setStyle("-fx-background-color: #" + settingsBtnColor + ";" +
                        "-fx-text-fill: #" + settingsBtnTextColor + ";"
        );

        main.loginBtn.setStyle("-fx-background-color: #" + loginBtnColor + ";" +
                        "-fx-text-fill: #"+ loginBtnTextColor +";"
        );
        main.quitBtn.setStyle("-fx-background-color: #" + quitBtnColor + ";" +
                        "-fx-text-fill: #" + quitBtnTextColor + ";"
        );

        main.mainViewPane.setStyle("-fx-background-color: #" + backgroundColor);
        main.mainMenuTitle.setStyle("-fx-text-fill: #" + titleColor);
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
