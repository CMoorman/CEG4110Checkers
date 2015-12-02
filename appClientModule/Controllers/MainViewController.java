package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		settingsBtn.setStyle(
				"-fx-background-color: #" + settingsBtnColor + ";" + "-fx-text-fill: #" + settingsBtnTextColor + ";");

		loginBtn.setStyle(
				"-fx-background-color: #" + loginBtnColor + ";" + "-fx-text-fill: #" + loginBtnTextColor + ";");
		quitBtn.setStyle("-fx-background-color: #" + quitBtnColor + ";" + "-fx-text-fill: #" + quitBtnTextColor + ";");

		mainViewPane.setStyle("-fx-background-color: #" + backgroundColor);
		mainMenuTitle.setStyle("-fx-text-fill: #" + titleColor);
		System.out.println(this.toString());
	}

	public void updateUI() {

		settingsBtn.setStyle(
				"-fx-background-color: #" + settingsBtnColor + ";" + "-fx-text-fill: #" + settingsBtnTextColor + ";");
		loginBtn.setStyle(
				"-fx-background-color: #" + loginBtnColor + ";" + "-fx-text-fill: #" + loginBtnTextColor + ";");
		quitBtn.setStyle("-fx-background-color: #" + quitBtnColor + ";" + "-fx-text-fill: #" + quitBtnTextColor + ";");
		mainViewPane.setStyle("-fx-background-color: #" + backgroundColor);
		mainMenuTitle.setStyle("-fx-text-fill: #" + titleColor);
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		if (e.getSource() == settingsBtn) {
			switchScene(SettingsViewController.getInstance().getScene());
		} else if (e.getSource() == loginBtn) {
			switchScene(LoginViewController.getInstance().getScene());
		} else if (e.getSource() == quitBtn) {

			if (network.isCurrentlyConnected) {
				network.svrCommunicator.disconnect(true);
			}
			Platform.exit();
			System.exit(0);
		}
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
}
