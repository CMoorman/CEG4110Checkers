package Controllers;

import java.awt.*;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.xml.soap.Node;

public class SettingsViewController extends BaseView implements Initializable, BaseViewController{

	@FXML
	Pane settingsPane;
	@FXML
	TextField titleTxtField;
	@FXML
	Button cancelBtn;
	@FXML
	Button saveBtn;
	@FXML
	TextField quitBtnTxtField;
	@FXML
	TextField settingsBtnTextField;
	@FXML
	TextField loginBtnTextField;
	@FXML
	TextField backgroundTxtField;
	@FXML
	TextField quitBtnTxtTextField;
	@FXML
	TextField settingsBtnTxtTextField;
	@FXML
	TextField loginBtnTxtTextField;
	@FXML
	TextField loginTitleTxtField;
	@FXML
	TextField loginCancelBtnTxtField;
	@FXML
	TextField loginSubmitBtnTxtField;
	@FXML
	TextField loginBackgroundTxtField;
	@FXML
	TextField loginCancelBtnTextTxtField;
	@FXML
	TextField loginSubmitBtnTextTxtField;
	@FXML
	TextField lobbyTitleTxtField;
	@FXML
	TextField lobbySpectateBtnTxtField;
	@FXML
	TextField lobbyHostBtnTxtField;
	@FXML
	TextField lobbyJoinBtnTxtField;
	@FXML
	TextField lobbyRefreshBtnTtxField;
	@FXML
	TextField lobbyBackgroundTxtField;
	@FXML
	TextField lobbySpectateBtnTextTxtField;
	@FXML
	TextField lobbyHostBtnTextTxtField;
	@FXML
	TextField lobbyJoinBtnTxtTextField;
	@FXML
	TextField lobbyRefreshBtnTextTtxField;
	@FXML
	TextField lobbyInProgressTxtField;
	@FXML
	TextField lobbyOpenTablesTxtField;
	@FXML
	TextField lobbyUsernameTxtField;

	@FXML
	TextField boardMySquareTxtField;
	@FXML
	TextField boardOpponentSquareTxtField;
	@FXML
	TextField boardSendBtnTxtField;
	@FXML
	TextField boardConcedeBtnTxtField;
	@FXML
	TextField boardBackgroundTxtField;
	@FXML
	TextField boardMyAvatarTxtField;
	@FXML
	TextField boardOpponentAvatarTxtField;
	@FXML
	TextField boardSendBtnTextTxtField;
	@FXML
	TextField boardConcedeBtnTextTxtField;
	@FXML
	TextField boardMyCheckersTxtField;
	@FXML
	TextField boardOpponentCheckerTxtField;

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
			switchScene(MainViewController.getViewInstance());
        }else if ( e.getSource()== saveBtn){
        	/**
        	 * NEED TO THROW IN SAVING LOGIC HERE. WHAT EVER CHANGED, WE NEED TO SET IT IN THE BASE VIEW.
        	 */

			// -- MAIN MENU PAGE
			if(!titleTxtField.getText().toString().trim().equals("")){
				titleColor = titleTxtField.getText();
			}
			if(!quitBtnTxtField.getText().toString().trim().equals("")){
				quitBtnColor = quitBtnTxtField.getText();
			}
			if(!settingsBtnTextField.getText().toString().trim().equals("")){
				settingsBtnColor = settingsBtnTextField.getText();
			}
			if(!loginBtnTextField.getText().toString().trim().equals("")){
				loginBtnColor = loginBtnTextField.getText();
			}
			if(!backgroundTxtField.getText().toString().trim().equals("")){
				backgroundColor = backgroundTxtField.getText();
			}
			if(!quitBtnTxtTextField.getText().toString().trim().equals("")){
				quitBtnTextColor = quitBtnTxtTextField.getText();
			}
			if(!settingsBtnTxtTextField.getText().toString().trim().equals("")){
				settingsBtnTextColor = settingsBtnTxtTextField.getText();
			}



			// -- LOGIN PAGE
			if(!loginBtnTxtTextField.getText().toString().trim().equals("")){
				loginBtnTextColor = loginBtnTxtTextField.getText();
			}
			if(!loginTitleTxtField.getText().toString().trim().equals("")){
				loginTitleColor = loginTitleTxtField.getText();
			}
			if(!loginCancelBtnTxtField.getText().toString().trim().equals("")){
				loginCancelBtnColor = loginCancelBtnTxtField.getText();
			}
			if(!loginSubmitBtnTxtField.getText().toString().trim().equals("")){
				loginSubmitBtnColor = loginSubmitBtnTxtField.getText();
			}
			if(!loginBackgroundTxtField.getText().toString().trim().equals("")){
				loginBackgroundColor = loginBackgroundTxtField.getText();
			}
			if(!loginCancelBtnTextTxtField.getText().toString().trim().equals("")){
				loginCancelBtnTextColor = loginCancelBtnTextTxtField.getText();
			}
			if(!loginSubmitBtnTextTxtField.getText().toString().trim().equals("")){
				loginSubmitBtnTextColor = loginSubmitBtnTextTxtField.getText();
			}
			if(!loginSubmitBtnTextTxtField.getText().toString().trim().equals("")){
				loginSubmitBtnTextColor = loginSubmitBtnTextTxtField.getText();
			}



			// -- LOBBY PAGE
			if(!lobbyTitleTxtField.getText().toString().trim().equals("")){
				lobbyTitleColor = lobbyTitleTxtField.getText();
			}
			if(!lobbySpectateBtnTxtField.getText().toString().trim().equals("")){
				lobbySpectateBtnColor = lobbySpectateBtnTxtField.getText();
			}
			if(!lobbyHostBtnTxtField.getText().toString().trim().equals("")){
				lobbyHostBtnColor = lobbyHostBtnTxtField.getText();
			}
			if(!lobbyJoinBtnTxtField.getText().toString().trim().equals("")){
				lobbyJoinBtnColor = lobbyJoinBtnTxtField.getText();
			}
			if(!lobbyRefreshBtnTtxField.getText().toString().trim().equals("")){
				lobbyRefreshBtnColor = lobbyRefreshBtnTtxField.getText();
			}
			if(!lobbyBackgroundTxtField.getText().toString().trim().equals("")){
				lobbyBackgroundColor = lobbyBackgroundTxtField.getText();
			}
			if(!lobbySpectateBtnTextTxtField.getText().toString().trim().equals("")){
				lobbySpectateBtnTextColor = lobbySpectateBtnTextTxtField.getText();
			}
			if(!lobbyHostBtnTextTxtField.getText().toString().trim().equals("")){
				lobbyHostBtnTextColor = lobbyHostBtnTextTxtField.getText();
			}
			if(!lobbyJoinBtnTxtTextField.getText().toString().trim().equals("")){
				lobbyJoinBtnTextColor = lobbyJoinBtnTxtTextField.getText();
			}
			if(!lobbyRefreshBtnTextTtxField.getText().toString().trim().equals("")){
				lobbyRefreshBtnTextColor = lobbyRefreshBtnTextTtxField.getText();
			}
			if(!lobbyInProgressTxtField.getText().toString().trim().equals("")){
				lobbyInProgressColor = lobbyInProgressTxtField.getText();
			}
			if(!lobbyOpenTablesTxtField.getText().toString().trim().equals("")){
				lobbyOpenTablesColor = lobbyOpenTablesTxtField.getText();
			}
			if(!lobbyUsernameTxtField.getText().toString().trim().equals("")){
				lobbyUsernameColor = lobbyUsernameTxtField.getText();
			}



			// -- BOARD PAGE
			if(!boardMySquareTxtField.getText().toString().trim().equals("")){
				boardMySquareColor = boardMySquareTxtField.getText();
			}
			if(!boardOpponentSquareTxtField.getText().toString().trim().equals("")){
				boardOpponentSquareColor = boardOpponentSquareTxtField.getText();
			}
			if(!boardSendBtnTxtField.getText().toString().trim().equals("")){
				boardSendBtnColor = boardSendBtnTxtField.getText();
			}
			if(!boardConcedeBtnTxtField.getText().toString().trim().equals("")){
				boardConcedeBtnColor = boardConcedeBtnTxtField.getText();
			}
			if(!boardBackgroundTxtField.getText().toString().trim().equals("")){
				boardBackgroundColor = boardBackgroundTxtField.getText();
			}
			if(!boardMyAvatarTxtField.getText().toString().trim().equals("")){
				boardMyAvatarColor = boardMyAvatarTxtField.getText();
			}
			if(!boardOpponentAvatarTxtField.getText().toString().trim().equals("")){
				boardOpponentAvatarColor = boardOpponentAvatarTxtField.getText();
			}
			if(!boardSendBtnTextTxtField.getText().toString().trim().equals("")){
				boardSendBtnTextColor = boardSendBtnTextTxtField.getText();
			}
			if(!boardConcedeBtnTextTxtField.getText().toString().trim().equals("")){
				boardConcedeBtnTextColor = boardConcedeBtnTextTxtField.getText();
			}
			if(!boardMyCheckersTxtField.getText().toString().trim().equals("")){
				boardMyCheckersColor = boardMyCheckersTxtField.getText();
			}
			if(!boardOpponentCheckerTxtField.getText().toString().trim().equals("")){
				boardOpponentsCheckersColor = boardOpponentCheckerTxtField.getText();
			}


        	switchScene(MainViewController.getViewInstance());
		}
	}

	private static Scene settingsView = null;

	public static Scene getViewInstance() {

		if (SettingsViewController.settingsView == null) {
			try {
				AnchorPane settingsPane = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(SETTINGS_VIEW_FXML));
				SettingsViewController.settingsView = new Scene(settingsPane);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return SettingsViewController.settingsView;

	}
}
