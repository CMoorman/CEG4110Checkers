package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.ActionEvent;
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

public class LoginViewController extends BaseView implements Initializable {

	private static Scene loginView = null;
	
	@FXML
	TextField usernameTxtField;
	
	@FXML
	TextField ipAddressTxtField;
	
	@FXML
	Button submitBtn;

	@FXML
	private javafx.scene.control.Label loginMenuTitle;


	@FXML
	Button cancelBtn;

	@FXML
	Pane loginViewPane;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// -- Button action events.
		submitBtn.setOnAction ( e -> submitBtnPressed(e) );
		cancelBtn.setOnAction(e -> cancelBtnPressed(e));

		submitBtn.setStyle("-fx-background-color: #" + loginSubmitBtnColor +";" +
						"-fx-text-fill: #" + loginSubmitBtnTextColor + ";"
		);
		cancelBtn.setStyle("-fx-background-color: #" + loginCancelBtnColor + ";" +
						"-fx-text-fill: #" + loginCancelBtnTextColor + ";"
		);
		loginViewPane.setStyle("-fx-background-color: #" + loginBackgroundColor);
		loginMenuTitle.setStyle("-fx-text-fill: #" + loginTitleColor);
		
	}

	/**
	 *  Grab the current state of the scene.  If the state does not exist, create it.
	 * @return
	 */
	public static Scene getViewInstance() {

		if (LoginViewController.loginView == null) {
			try {
				AnchorPane loginPane = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(LOGIN_VIEW_FXML));
				LoginViewController.loginView = new Scene(loginPane);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return LoginViewController.loginView;
	}

	public void submitBtnPressed( ActionEvent e ){
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
	} // -- End submitBtnPressed
	
	public void cancelBtnPressed( ActionEvent e ){
		switchScene(MainViewController.getViewInstance());
	}
	
	@FXML
	public void buttonPressed(KeyEvent e)
	{
	    if(e.getCode().toString().equals("ENTER"))
	    {
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
	} // -- End of buttonPressed.
}
