package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Objects.DialogHelper;
import Objects.ColorStyleHelper;
import UIPanes.BaseView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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

	private String loginBackgroundBtnColor;
	boolean loginSuccess = false;
	boolean nameIllegal = false;
	boolean nameAvailable = true;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// -- Button action events.
		submitBtn.setOnAction(e -> submitBtnPressed(e));
		cancelBtn.setOnAction(e -> cancelBtnPressed(e));

		submitBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginSubmitBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginSubmitBtnTextColor));
		cancelBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginCancelBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginCancelBtnTextColor));

		loginViewPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginBackgroundColor));
		loginMenuTitle.setStyle(ColorStyleHelper.getTextFillStyle(loginTitleColor));


	}

	public void updateUI(){
		submitBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginSubmitBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginSubmitBtnTextColor));
		cancelBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginCancelBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(loginCancelBtnTextColor));

		loginViewPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(loginBackgroundColor));
		loginMenuTitle.setStyle(ColorStyleHelper.getTextFillStyle(loginTitleColor));
	}

	private void submitBtnPressed(ActionEvent e) {
		login();
	} 

	private void cancelBtnPressed(ActionEvent e) {
		switchScene(MainViewController.getInstance().getScene());
	}

	@FXML
	public void buttonPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			login();
		}
	} // -- End of buttonPressed.

	private void login() {
		nameAvailable = true;
		nameIllegal = false;
		loginSuccess = false;
		String userName = usernameTxtField.getText().trim();
		
		String optionalIPAddress = ipAddressTxtField.getText();
		LobbyViewController controller = LobbyViewController.getInstance();
		// -- Make sure that there is something there and that we set a
		// limit on it.
		if (userName.length() > 0 && userName.length() <= 20) {
			if (optionalIPAddress.isEmpty()) {
				loginSuccess = network.connectToServer(serverIP, userName);
				controller.setRefreshIp(serverIP);
			} else {
				loginSuccess = network.connectToServer(optionalIPAddress,
						userName);
				controller.setRefreshIp(optionalIPAddress);
			}
			
			
			int attempts = 0;
			int maxAttempts = 10;
			while (getTableList() == null && attempts <= maxAttempts && nameAvailable==true) {
				try {
					Thread.sleep(1000);
					++attempts;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (loginSuccess) {
				network.setUsrName(userName);
				if (grabTableList()) {
					switchScene(LobbyViewController.getInstance().getScene());
				}
			} else {
				if (nameAvailable == false){
					DialogHelper.showErrorDialog("Unable to Connect", null,
							"Name is Not Available.");
					clearLoginControls(); 
				}
				else if (nameIllegal == true ){
					DialogHelper.showErrorDialog("Unable to Connect", null,
							"Illegal Name.");
					clearLoginControls(); 
				}
				else{
					DialogHelper.showErrorDialog("Unable to Connect", null,
						"A server connection could not be established. Please check the IP address and try again");
					clearLoginControls();
				}
				
			}
			
			
			
		}
	}

	private void clearLoginControls() {
		usernameTxtField.setText("");
		ipAddressTxtField.setText("");
		
	}

	public boolean grabTableList() {
		
		LobbyViewController controller = LobbyViewController.getInstance();
		
		int index = 0;
		if( controller.getTableListObjects() != null && getTableList() != null ) {
			while( controller.getTableListObjects().size() - 1 < getTableList().length ) {
				if( controller.getTableListObjects().size() - 1 < index ) {
					index++;
				}
			}
		}
		return true;
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
}
