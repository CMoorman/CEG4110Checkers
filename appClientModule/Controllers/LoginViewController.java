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
import javafx.scene.layout.AnchorPane;

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
		if (e.getSource() == submitBtn) {

			boolean loginSuccess = false;

			String userName = usernameTxtField.getText();

			// -- Make sure that there is something there and that we set a
			// limit on it.
			if (userName.length() > 0 && userName.length() <= 20) {

				loginSuccess = network.connectToServer(serverIP, userName);

				// -- Try to connect. Trying to connect will yield a boolean
				// value.
				if (loginSuccess) {
					network.setUsrName(userName);
					switchScene(LobbyViewController.getViewInstance());
				}
			}
		}
		else if ( e.getSource()== cancelBtn ){
				switchScene(MainViewController.getViewInstance());
        }
		
	}
	private static AnchorPane loginView = null;

	public static AnchorPane getViewInstance() {

		if (LoginViewController.loginView == null) {
			try {
				LoginViewController.loginView = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(LOGIN_VIEW_FXML));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return LoginViewController.loginView;

	}
}
