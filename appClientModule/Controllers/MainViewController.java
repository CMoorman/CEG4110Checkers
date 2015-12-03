package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Objects.ColorStyleHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import UIPanes.BaseView;
import javafx.scene.layout.AnchorPane;
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

		settingsBtn.setOnAction(e -> settingsClicked(e));
		loginBtn.setOnAction(e -> loginClicked(e));
		quitBtn.setOnAction(e -> quitClicked(e));
		
		settingsBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(settingsBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(settingsBtnTextColor) + ";");
		loginBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginBtnTextColor) + ";");
		quitBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(quitBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(quitBtnTextColor) + ";");

		mainViewPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(backgroundColor));
		mainMenuTitle.setStyle(ColorStyleHelper.getTextFillStyle(titleColor));
		
	}

	public void updateUI() {

		settingsBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(settingsBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(settingsBtnTextColor) + ";");
		loginBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginBtnTextColor) + ";");
		quitBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(quitBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(quitBtnTextColor) + ";");
		mainViewPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(backgroundColor));
		mainMenuTitle.setStyle(ColorStyleHelper.getTextFillStyle(titleColor));
	}



	private void settingsClicked(ActionEvent e) {
		switchScene(SettingsViewController.getInstance().getScene());
	}
	
	private void loginClicked(ActionEvent e) {
		switchScene(LoginViewController.getInstance().getScene());
	}

	private void quitClicked(ActionEvent e){
		if (network.isCurrentlyConnected) {
			network.svrCommunicator.disconnect(true);
		}
		Platform.exit();
		System.exit(0);
	}
	private static Scene mainScene = null;

	public Scene getScene() {
		return mainScene;
	}

	private static MainViewController instance = null;

	public static MainViewController getInstance() {
		if (instance == null) {
			FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(MAIN_VIEW_FMXL));
			Parent mainPane = null;
			try {
				mainPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainScene = new Scene(mainPane);
			instance = loader.getController();
		}
		return instance;
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
