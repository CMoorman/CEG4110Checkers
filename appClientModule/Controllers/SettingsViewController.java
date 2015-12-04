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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class SettingsViewController extends BaseView implements Initializable, BaseViewController {

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
	ColorPicker lobbyDisconnectBtnTxtField;
	@FXML
	ColorPicker lobbyDisconnectBtnTxtTextField;
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
	@FXML
	ColorPicker spectatePlayer1SquareTxtField;
	@FXML
	ColorPicker spectatePlayer2SquareTxtField;
	@FXML
	ColorPicker spectateSendBtnTxtField;
	@FXML
	ColorPicker spectateReturnBtnTxtField;
	@FXML
	ColorPicker spectateBackgroundTxtField;
	@FXML
	ColorPicker spectatePlayer1AvatarTxtField;
	@FXML
	ColorPicker spectatePlayer2AvatarTxtField;
	@FXML
	ColorPicker spectatePlayer1CheckerTxtField;
	@FXML
	ColorPicker spectatePlayer2CheckerTxtField;
	@FXML
	ColorPicker spectateSendBtnTextTxtField;
	@FXML
	ColorPicker spectateReturnBtnTextTxtField;
	@FXML
	ColorPicker boardReadyBtnTxtField;
	@FXML
	ColorPicker boardReadyBtnTextTxtField;
	@FXML
	ColorPicker lobbySendBtnTxtField;
	@FXML
	ColorPicker lobbySendBtnTextTxtField;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cancelBtn.setOnAction(e -> cancelClicked(e));
		saveBtn.setOnAction(e -> saveClicked(e));

		titleTxtField.setValue((Color) Paint.valueOf(titleColor));

		quitBtnTxtField.setValue((Color) Paint.valueOf(quitBtnColor));

		settingsBtnTextField.setValue((Color) Paint.valueOf(settingsBtnColor));

		loginBtnTextField.setValue((Color) Paint.valueOf(loginBtnColor));

		backgroundTxtField.setValue((Color) Paint.valueOf(backgroundColor));

		quitBtnTxtTextField.setValue((Color) Paint.valueOf(quitBtnTextColor));

		settingsBtnTxtTextField.setValue((Color) Paint.valueOf(settingsBtnTextColor));

		loginBtnTxtTextField.setValue((Color) Paint.valueOf(loginBtnTextColor));

		loginTitleTxtField.setValue((Color) Paint.valueOf(loginTitleColor));

		loginCancelBtnTxtField.setValue((Color) Paint.valueOf(loginCancelBtnColor));

		loginSubmitBtnTxtField.setValue((Color) Paint.valueOf(loginSubmitBtnColor));

		loginBackgroundTxtField.setValue((Color) Paint.valueOf(loginBackgroundColor));

		loginCancelBtnTextTxtField.setValue((Color) Paint.valueOf(loginCancelBtnTextColor));

		loginSubmitBtnTextTxtField.setValue((Color) Paint.valueOf(loginSubmitBtnTextColor));

		lobbyTitleTxtField.setValue((Color) Paint.valueOf(lobbyTitleColor));

		lobbySpectateBtnTxtField.setValue((Color) Paint.valueOf(lobbySpectateBtnColor));

		lobbyHostBtnTxtField.setValue((Color) Paint.valueOf(lobbyHostBtnColor));

		lobbyJoinBtnTxtField.setValue((Color) Paint.valueOf(lobbyJoinBtnColor));

		lobbyRefreshBtnTtxField.setValue((Color) Paint.valueOf(lobbyRefreshBtnColor));

		lobbyBackgroundTxtField.setValue((Color) Paint.valueOf(lobbyBackgroundColor));

		lobbySpectateBtnTextTxtField.setValue((Color) Paint.valueOf(lobbySpectateBtnTextColor));

		lobbyHostBtnTextTxtField.setValue((Color) Paint.valueOf(lobbyHostBtnTextColor));

		lobbyJoinBtnTxtTextField.setValue((Color) Paint.valueOf(lobbyJoinBtnTextColor));

		lobbyRefreshBtnTextTtxField.setValue((Color) Paint.valueOf(lobbyRefreshBtnTextColor));

		lobbyInProgressTxtField.setValue((Color) Paint.valueOf(lobbyInProgressColor));

		lobbyOpenTablesTxtField.setValue((Color) Paint.valueOf(lobbyOpenTablesColor));

		lobbyUsernameTxtField.setValue((Color) Paint.valueOf(lobbyUsernameColor));

		lobbyDisconnectBtnTxtField.setValue((Color) Paint.valueOf(lobbyDisconnectBtnColor));

		lobbyDisconnectBtnTxtTextField.setValue((Color) Paint.valueOf(lobbyDisconnectBtnTextColor));

		boardMySquareTxtField.setValue((Color) Paint.valueOf(boardMySquareColor));

		boardOpponentSquareTxtField.setValue((Color) Paint.valueOf(boardOpponentSquareColor));

		boardSendBtnTxtField.setValue((Color) Paint.valueOf(boardSendBtnColor));

		boardConcedeBtnTxtField.setValue((Color) Paint.valueOf(boardConcedeBtnColor));

		boardBackgroundTxtField.setValue((Color) Paint.valueOf(boardBackgroundColor));

		boardMyAvatarTxtField.setValue((Color) Paint.valueOf(boardMyAvatarColor));

		boardOpponentAvatarTxtField.setValue((Color) Paint.valueOf(boardOpponentAvatarColor));

		boardSendBtnTextTxtField.setValue((Color) Paint.valueOf(boardSendBtnTextColor));

		boardConcedeBtnTextTxtField.setValue((Color) Paint.valueOf(boardConcedeBtnTextColor));

		boardMyCheckersTxtField.setValue((Color) Paint.valueOf(boardMyCheckersColor));

		boardOpponentCheckerTxtField.setValue((Color) Paint.valueOf(boardOpponentsCheckersColor));

		boardReadyBtnTxtField.setValue((Color) Paint.valueOf(boardReadyBtnColor));

		boardReadyBtnTextTxtField.setValue((Color) Paint.valueOf(boardReadyBtnTextColor));

		spectatePlayer1SquareTxtField.setValue((Color) Paint.valueOf(spectatePlayer1SquareColor));

		spectatePlayer2SquareTxtField.setValue((Color) Paint.valueOf(spectatePlayer2SquareColor));

		spectateSendBtnTxtField.setValue((Color) Paint.valueOf(spectateSendBtnColor));

		spectateReturnBtnTxtField.setValue((Color) Paint.valueOf(spectateReturnBtnColor));

		spectateBackgroundTxtField.setValue((Color) Paint.valueOf(spectateBackground));

		spectatePlayer1AvatarTxtField.setValue((Color) Paint.valueOf(spectatePlayer1AvatarColor));

		spectatePlayer2AvatarTxtField.setValue((Color) Paint.valueOf(spectatePlayer2AvatarColor));

		spectatePlayer1CheckerTxtField.setValue((Color) Paint.valueOf(spectatePlayer1CheckerColor));

		spectatePlayer2CheckerTxtField.setValue((Color) Paint.valueOf(spectatePlayer2CheckerColor));

		spectateSendBtnTextTxtField.setValue((Color) Paint.valueOf(spectateSendBtnTextColor));

		spectateReturnBtnTextTxtField.setValue((Color) Paint.valueOf(spectateReturnBtnTextColor));

		lobbySendBtnTxtField.setValue((Color) Paint.valueOf(lobbySendMsgBtnColor));

		lobbySendBtnTextTxtField.setValue((Color) Paint.valueOf(lobbySendMsgBtnTextColor));

	}

	@FXML
	public void checkInputLength(KeyEvent ev) {

		try {
			// -- Make sure that we have the correct input and input length.
			TextField currentField = (TextField) ev.getSource();
			if ("1234567890abcdefABCDEF".contains(ev.getCharacter()) && (currentField.getText().length() < 6)) {
				// -- Empty if. For some reason why I try to throw some nots in,
				// it does not work.
			} else
				ev.consume();
		} catch (Exception e) {
			// -- Need a try catch here. If the textfield does not have any text
			// in it initially, throws an error
		}
	}

	private void cancelClicked(ActionEvent e) {
		switchScene(MainViewController.getInstance().getScene());
	}

	private void saveClicked(ActionEvent e) {
		/**
		 * NEED TO THROW IN SAVING LOGIC HERE. WHAT EVER CHANGED, WE NEED TO SET
		 * IT IN THE BASE VIEW.
		 */
		// -- Main Page
		titleColor = getColorFromPicker(titleTxtField);
		loginBtnColor = getColorFromPicker(loginBtnTextField);
		backgroundColor = getColorFromPicker(backgroundTxtField);
		quitBtnTextColor = getColorFromPicker(quitBtnTxtTextField);
		quitBtnColor = getColorFromPicker(quitBtnTxtField);
		settingsBtnColor = getColorFromPicker(settingsBtnTextField);
		settingsBtnTextColor = getColorFromPicker(settingsBtnTxtTextField);
		loginBtnColor = getColorFromPicker(loginBtnTextField);

		// -- LOGIN PAGE
		loginBtnTextColor = getColorFromPicker(loginBtnTxtTextField);
		loginTitleColor = getColorFromPicker(loginTitleTxtField);
		loginCancelBtnColor = getColorFromPicker(loginCancelBtnTxtField);
		loginSubmitBtnColor = getColorFromPicker(loginSubmitBtnTxtField);
		loginBackgroundColor = getColorFromPicker(loginBackgroundTxtField);
		loginCancelBtnTextColor = getColorFromPicker(loginCancelBtnTextTxtField);
		loginSubmitBtnTextColor = getColorFromPicker(loginSubmitBtnTextTxtField);

		// -- LOBBY PAGE
		lobbySpectateBtnColor = getColorFromPicker(lobbySpectateBtnTxtField);
		lobbyTitleColor = getColorFromPicker(lobbyTitleTxtField);
		lobbyHostBtnColor = getColorFromPicker(lobbyHostBtnTxtField);
		lobbyJoinBtnColor = getColorFromPicker(lobbyJoinBtnTxtField);
		lobbyRefreshBtnColor = getColorFromPicker(lobbyRefreshBtnTtxField);
		lobbyBackgroundColor = getColorFromPicker(lobbyBackgroundTxtField);
		lobbySpectateBtnTextColor = getColorFromPicker(lobbySpectateBtnTextTxtField);
		lobbyHostBtnTextColor = getColorFromPicker(lobbyHostBtnTextTxtField);
		lobbyJoinBtnTextColor = getColorFromPicker(lobbyJoinBtnTxtTextField);
		lobbyRefreshBtnTextColor = getColorFromPicker(lobbyRefreshBtnTextTtxField);
		lobbyInProgressColor = getColorFromPicker(lobbyInProgressTxtField);
		lobbyOpenTablesColor = getColorFromPicker(lobbyOpenTablesTxtField);
		lobbyUsernameColor = getColorFromPicker(lobbyUsernameTxtField);
		lobbyDisconnectBtnColor = getColorFromPicker(lobbyDisconnectBtnTxtField);
		lobbyDisconnectBtnTextColor = getColorFromPicker(lobbyDisconnectBtnTxtTextField);
		lobbySendMsgBtnColor = getColorFromPicker(lobbySendBtnTxtField);
		lobbySendMsgBtnTextColor = getColorFromPicker(lobbySendBtnTextTxtField);

		// -- BOARD PAGE
		boardMySquareColor = getColorFromPicker(boardMySquareTxtField);
		boardOpponentSquareColor = getColorFromPicker(boardOpponentSquareTxtField);
		boardSendBtnColor = getColorFromPicker(boardSendBtnTxtField);
		boardConcedeBtnColor = getColorFromPicker(boardConcedeBtnTxtField);
		boardBackgroundColor = getColorFromPicker(boardBackgroundTxtField);
		boardMyAvatarColor = getColorFromPicker(boardMyAvatarTxtField);
		boardOpponentAvatarColor = getColorFromPicker(boardOpponentAvatarTxtField);
		boardSendBtnTextColor = getColorFromPicker(boardSendBtnTextTxtField);
		boardConcedeBtnTextColor = getColorFromPicker(boardConcedeBtnTextTxtField);
		boardMyCheckersColor = getColorFromPicker(boardMyCheckersTxtField);
		boardOpponentsCheckersColor = getColorFromPicker(boardOpponentCheckerTxtField);

		// --- SPECTATE PAGE
		spectatePlayer1SquareColor = getColorFromPicker(spectatePlayer1SquareTxtField);
		spectatePlayer2SquareColor = getColorFromPicker(spectatePlayer2SquareTxtField);
		spectateSendBtnColor = getColorFromPicker(spectateSendBtnTxtField);
		spectateReturnBtnColor = getColorFromPicker(spectateReturnBtnTxtField);
		spectateBackground = getColorFromPicker(spectateBackgroundTxtField);
		spectatePlayer1AvatarColor = getColorFromPicker(spectatePlayer1AvatarTxtField);
		spectatePlayer2AvatarColor = getColorFromPicker(spectatePlayer2AvatarTxtField);
		spectatePlayer1CheckerColor = getColorFromPicker(spectatePlayer1CheckerTxtField);
		spectatePlayer2CheckerColor = getColorFromPicker(spectatePlayer2CheckerTxtField);
		spectateSendBtnTextColor = getColorFromPicker(spectateSendBtnTextTxtField);
		spectateReturnBtnTextColor = getColorFromPicker(spectateReturnBtnTextTxtField);

		MainViewController.getInstance().updateUI();
		MainViewController.getInstance().saveColors();
		LoginViewController.getInstance().updateUI();
		LobbyViewController.getInstance().updateUI();
		CheckersBoardViewController.getInstance().updateUI();
		//SpectateViewController.getInstance().updateUI();

		switchScene(MainViewController.getInstance().getScene());
	}

	private String getColorFromPicker(ColorPicker picker) {
		String pickerColor = picker.getValue().toString().trim();
		if (!pickerColor.isEmpty() && pickerColor.length() > 3) {
			pickerColor = pickerColor.substring(2);
		} else {
			pickerColor = "";
		}
		return pickerColor;
	}

	private static Scene settingsScene = null;

	public Scene getScene() {
		return settingsScene;
	}

	private static SettingsViewController instance = null;

	public static SettingsViewController getInstance() {
		if (instance == null) {
			FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(SETTINGS_VIEW_FXML));
			Parent mainPane = null;
			try {
				mainPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			settingsScene = new Scene(mainPane);
			instance = loader.getController();
		}
		return instance;
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
