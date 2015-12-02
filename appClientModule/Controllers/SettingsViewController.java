package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SettingsViewController extends BaseView implements Initializable, BaseViewController{

	@FXML
	Pane settingsPane;
	@FXML
	ColorPicker titleTxtField;
	@FXML
	Button cancelBtn;
	@FXML
	Button saveBtn;
	@FXML
	ColorPicker quitBtnTxtField;
	@FXML
	ColorPicker settingsBtnTextField;
	@FXML
	ColorPicker loginBtnTextField;
	@FXML
	ColorPicker backgroundTxtField;
	@FXML
	ColorPicker quitBtnTxtTextField;
	@FXML
	ColorPicker settingsBtnTxtTextField;
	@FXML
	ColorPicker loginBtnTxtTextField;
	@FXML
	ColorPicker loginTitleTxtField;
	@FXML
	ColorPicker loginCancelBtnTxtField;
	@FXML
	ColorPicker loginSubmitBtnTxtField;
	@FXML
	ColorPicker loginBackgroundTxtField;
	@FXML
	ColorPicker loginCancelBtnTextTxtField;
	@FXML
	ColorPicker loginSubmitBtnTextTxtField;
	@FXML
	ColorPicker lobbyTitleTxtField;
	@FXML
	ColorPicker lobbySpectateBtnTxtField;
	@FXML
	ColorPicker lobbyHostBtnTxtField;
	@FXML
	ColorPicker lobbyJoinBtnTxtField;
	@FXML
	ColorPicker lobbyRefreshBtnTtxField;
	@FXML
	ColorPicker lobbyBackgroundTxtField;
	@FXML
	ColorPicker lobbySpectateBtnTextTxtField;
	@FXML
	ColorPicker lobbyHostBtnTextTxtField;
	@FXML
	ColorPicker lobbyJoinBtnTxtTextField;
	@FXML
	ColorPicker lobbyRefreshBtnTextTtxField;
	@FXML
	ColorPicker lobbyInProgressTxtField;
	@FXML
	ColorPicker lobbyOpenTablesTxtField;
	@FXML
	ColorPicker lobbyUsernameTxtField;
	@FXML
	ColorPicker boardMySquareTxtField;
	@FXML
	ColorPicker boardOpponentSquareTxtField;
	@FXML
	ColorPicker boardSendBtnTxtField;
	@FXML
	ColorPicker boardConcedeBtnTxtField;
	@FXML
	ColorPicker boardBackgroundTxtField;
	@FXML
	ColorPicker boardMyAvatarTxtField;
	@FXML
	ColorPicker boardOpponentAvatarTxtField;
	@FXML
	ColorPicker boardSendBtnTextTxtField;
	@FXML
	ColorPicker boardConcedeBtnTextTxtField;
	@FXML
	ColorPicker boardMyCheckersTxtField;
	@FXML
	ColorPicker boardOpponentCheckerTxtField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cancelBtn.setOnAction( e -> ButtonClicked(e) );
		saveBtn.setOnAction( e -> ButtonClicked(e) );
		
	}
	
	@FXML
	public void checkInputLength(KeyEvent ev){
		
		try {
			// -- Make sure that we have the correct input and input length.
			TextField currentField = (TextField) ev.getSource();
			if( "1234567890abcdefABCDEF".contains( ev.getCharacter() ) && (currentField.getText().length() < 6 ) ) { 
				// -- Empty if.  For some reason why I try to throw some nots in, it does not work.
			}
			else
				ev.consume();
		}catch(Exception e){
			// -- Need a try catch here.  If the textfield does not have any text in it initially, throws an error
		}
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		/**
		 * CANCEL OUT, DON"T SAVE ANYTHING.
		 */
		if ( e.getSource()== cancelBtn ){
			switchScene(MainViewController.getInstance().getScene());
        }else if ( e.getSource()== saveBtn){
        	/**
        	 * NEED TO THROW IN SAVING LOGIC HERE. WHAT EVER CHANGED, WE NEED TO SET IT IN THE BASE VIEW.
        	 */

			// -- MAIN MENU PAGE
			if(!titleTxtField.getValue().toString().trim().equals("")){
				titleColor = titleTxtField.getValue().toString();
			}
			if(!quitBtnTxtField.getValue().toString().trim().equals("")){
				quitBtnColor = quitBtnTxtField.getValue().toString();
			}
			if(!settingsBtnTextField.getValue().toString().trim().equals("")){
				settingsBtnColor = settingsBtnTextField.getValue().toString();
			}
			if(!loginBtnTextField.getValue().toString().trim().equals("")){
				loginBtnColor = loginBtnTextField.getValue().toString();
			}
			if(!backgroundTxtField.getValue().toString().trim().equals("")){
				backgroundColor = backgroundTxtField.getValue().toString();
			}
			if(!quitBtnTxtTextField.getValue().toString().trim().equals("")){
				quitBtnTextColor = quitBtnTxtTextField.getValue().toString();
			}
			if(!settingsBtnTxtTextField.getValue().toString().trim().equals("")){
				settingsBtnTextColor = settingsBtnTxtTextField.getValue().toString();
			}



			// -- LOGIN PAGE
			if(!loginBtnTxtTextField.getValue().toString().trim().equals("")){
				loginBtnTextColor = loginBtnTxtTextField.getValue().toString();
			}
			if(!loginTitleTxtField.getValue().toString().trim().equals("")){
				loginTitleColor = loginTitleTxtField.getValue().toString();
			}
			if(!loginCancelBtnTxtField.getValue().toString().trim().equals("")){
				loginCancelBtnColor = loginCancelBtnTxtField.getValue().toString();
			}
			if(!loginSubmitBtnTxtField.getValue().toString().trim().equals("")){
				loginSubmitBtnColor = loginSubmitBtnTxtField.getValue().toString();
			}
			if(!loginBackgroundTxtField.getValue().toString().trim().equals("")){
				loginBackgroundColor = loginBackgroundTxtField.getValue().toString();
			}
			if(!loginCancelBtnTextTxtField.getValue().toString().trim().equals("")){
				loginCancelBtnTextColor = loginCancelBtnTextTxtField.getValue().toString();
			}
			if(!loginSubmitBtnTextTxtField.getValue().toString().trim().equals("")){
				loginSubmitBtnTextColor = loginSubmitBtnTextTxtField.getValue().toString();
			}
			if(!loginSubmitBtnTextTxtField.getValue().toString().trim().equals("")){
				loginSubmitBtnTextColor = loginSubmitBtnTextTxtField.getValue().toString();
			}



			// -- LOBBY PAGE
			if(!lobbyTitleTxtField.getValue().toString().trim().equals("")){
				lobbyTitleColor = lobbyTitleTxtField.getValue().toString();
			}
			if(!lobbySpectateBtnTxtField.getValue().toString().trim().equals("")){
				lobbySpectateBtnColor = lobbySpectateBtnTxtField.getValue().toString();
			}
			if(!lobbyHostBtnTxtField.getValue().toString().trim().equals("")){
				lobbyHostBtnColor = lobbyHostBtnTxtField.getValue().toString();
			}
			if(!lobbyJoinBtnTxtField.getValue().toString().trim().equals("")){
				lobbyJoinBtnColor = lobbyJoinBtnTxtField.getValue().toString();
			}
			if(!lobbyRefreshBtnTtxField.getValue().toString().trim().equals("")){
				lobbyRefreshBtnColor = lobbyRefreshBtnTtxField.getValue().toString();
			}
			if(!lobbyBackgroundTxtField.getValue().toString().trim().equals("")){
				lobbyBackgroundColor = lobbyBackgroundTxtField.getValue().toString();
			}
			if(!lobbySpectateBtnTextTxtField.getValue().toString().trim().equals("")){
				lobbySpectateBtnTextColor = lobbySpectateBtnTextTxtField.getValue().toString();
			}
			if(!lobbyHostBtnTextTxtField.getValue().toString().trim().equals("")){
				lobbyHostBtnTextColor = lobbyHostBtnTextTxtField.getValue().toString();
			}
			if(!lobbyJoinBtnTxtTextField.getValue().toString().trim().equals("")){
				lobbyJoinBtnTextColor = lobbyJoinBtnTxtTextField.getValue().toString();
			}
			if(!lobbyRefreshBtnTextTtxField.getValue().toString().trim().equals("")){
				lobbyRefreshBtnTextColor = lobbyRefreshBtnTextTtxField.getValue().toString();
			}
			if(!lobbyInProgressTxtField.getValue().toString().trim().equals("")){
				lobbyInProgressColor = lobbyInProgressTxtField.getValue().toString();
			}
			if(!lobbyOpenTablesTxtField.getValue().toString().trim().equals("")){
				lobbyOpenTablesColor = lobbyOpenTablesTxtField.getValue().toString();
			}
			if(!lobbyUsernameTxtField.getValue().toString().trim().equals("")){
				lobbyUsernameColor = lobbyUsernameTxtField.getValue().toString();
			}



			// -- BOARD PAGE
			if(!boardMySquareTxtField.getValue().toString().trim().equals("")){
				boardMySquareColor = boardMySquareTxtField.getValue().toString();
			}
			if(!boardOpponentSquareTxtField.getValue().toString().trim().equals("")){
				boardOpponentSquareColor = boardOpponentSquareTxtField.getValue().toString();
			}
			if(!boardSendBtnTxtField.getValue().toString().trim().equals("")){
				boardSendBtnColor = boardSendBtnTxtField.getValue().toString();
			}
			if(!boardConcedeBtnTxtField.getValue().toString().trim().equals("")){
				boardConcedeBtnColor = boardConcedeBtnTxtField.getValue().toString();
			}
			if(!boardBackgroundTxtField.getValue().toString().trim().equals("")){
				boardBackgroundColor = boardBackgroundTxtField.getValue().toString();
			}
			if(!boardMyAvatarTxtField.getValue().toString().trim().equals("")){
				boardMyAvatarColor = boardMyAvatarTxtField.getValue().toString();
			}
			if(!boardOpponentAvatarTxtField.getValue().toString().trim().equals("")){
				boardOpponentAvatarColor = boardOpponentAvatarTxtField.getValue().toString();
			}
			if(!boardSendBtnTextTxtField.getValue().toString().trim().equals("")){
				boardSendBtnTextColor = boardSendBtnTextTxtField.getValue().toString();
			}
			if(!boardConcedeBtnTextTxtField.getValue().toString().trim().equals("")){
				boardConcedeBtnTextColor = boardConcedeBtnTextTxtField.getValue().toString();
			}
			if(!boardMyCheckersTxtField.getValue().toString().trim().equals("")){
				boardMyCheckersColor = boardMyCheckersTxtField.getValue().toString();
			}
			if(!boardOpponentCheckerTxtField.getValue().toString().trim().equals("")){
				boardOpponentsCheckersColor = boardOpponentCheckerTxtField.getValue().toString();
			}


        	switchScene(MainViewController.getInstance().getScene());
		}
	}

	private Scene settingsScene = null;

	public Scene getScene() {

		if (settingsScene == null) {
			try {
				FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(SETTINGS_VIEW_FXML));
				AnchorPane settingsPane = (AnchorPane) loader.load();
				settingsScene = new Scene(settingsPane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return settingsScene;

	}
	private static SettingsViewController instance = null;
	public static SettingsViewController getInstance() {
		if (instance == null) {
			instance = new SettingsViewController();
		}
		return instance;
	}
}
