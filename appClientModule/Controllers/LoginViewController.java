package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class LoginViewController extends BaseView implements Initializable {
	
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

	@FXML
	javafx.scene.Node currentTile;

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

	private static Scene loginScene = null;

	public Scene getScene() {
		return loginScene;
	}

	private static LoginViewController instance = null;

	public static LoginViewController getInstance() {
		if (instance == null) {
			FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(LOGIN_VIEW_FXML));
			Parent mainPane = null;
			try {
				mainPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			loginScene = new Scene(mainPane);
			instance = loader.getController();
		}
		return instance;
	}
	public void submitBtnPressed( ActionEvent e ){
		boolean loginSuccess = false;

		String userName = usernameTxtField.getText().trim();
		
		String optionalIPAddress = ipAddressTxtField.getText();
		// -- Make sure that there is something there and that we set a
		// limit on it.
		if (userName.length() > 0 && userName.length() <= 20) {
			if(optionalIPAddress.isEmpty()){
				loginSuccess = network.connectToServer(serverIP, userName);
			}else{
				loginSuccess = network.connectToServer(optionalIPAddress, userName);
			}

			// -- Try to connect. Trying to connect will yield a boolean
			// value.
			if (loginSuccess) {
				network.setUsrName(userName);
				switchScene(LobbyViewController.getInstance().getScene());
			}
		}
	} // -- End submitBtnPressed
	
	public void cancelBtnPressed( ActionEvent e ){
		switchScene(MainViewController.getInstance().getScene());
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
					switchScene(LobbyViewController.getInstance().getScene());
				}
			}
	    }
	} // -- End of buttonPressed.
}
