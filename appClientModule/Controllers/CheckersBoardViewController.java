package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;
import javafx.scene.layout.Pane;

public class CheckersBoardViewController extends BaseView implements Initializable {

	private Scene boardScene = null;
	
	@FXML
	Label yourUserNameLbl;
	
	@FXML
	Label opponentNameLbl;
	
	@FXML
	Button sendBtn;
	
	@FXML
	Button concedeBtn;
	
	@FXML
	TextField sendMessageBox;

	@FXML
	Pane redSquare;

	@FXML
	Pane blackSquare;

	@FXML
	AnchorPane checkersAnchorPane;

	@FXML
	Pane opponentAvatar;

	@FXML
	Pane myAvatar;
	
	@FXML
	ListView<String> messageBox;
	ObservableList<String> messageList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText(BaseView.network.getUsrName());
		
		sendBtn.setOnAction(e -> sendBtnPressed(e));
		concedeBtn.setOnAction(e -> concedeBtnPressed(e));

		sendBtn.setStyle("-fx-background-color: #" + boardSendBtnColor + ";" +
						"-fx-text-fill: #" + boardSendBtnTextColor + ";"
		);
		concedeBtn.setStyle("-fx-background-color: #" + boardConcedeBtnColor + ";" +
						"-fx-text-fill: #" + boardConcedeBtnTextColor + ";"
		);
		checkersAnchorPane.setStyle("-fx-background-color: #" + boardBackgroundColor);

		/*redSquare.setStyle("-fx-background-color: #" + boardMySquareColor);
		blackSquare.setStyle("-fx-background-color: #" + boardOpponentSquareColor);*/

		opponentAvatar.setStyle("-fx-background-color: #" + boardOpponentAvatarColor);
		myAvatar.setStyle("-fx-background-color: #" + boardMyAvatarColor);



	}

	public Scene getScene() {
		if (boardScene == null) {
			try {
				FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(BOARD_VIEW_FXML));
				AnchorPane boardPane = (AnchorPane) loader.load();
				boardScene = new Scene(boardPane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return boardScene;
	}
	
	public void setOpponentUsername(String name) {
		opponentNameLbl.setText(BaseView.opponentName);
	}
	
	public void addGameMessage(String msg) {
		System.out.println("Adding a new message");
		messageList.add(msg);
		System.out.println("Message list: " + messageList.toString());
		UpdateChatBox();
	}
	
	public void UpdateChatBox() {
		System.out.println("Updating chat box " + messageList.toString() );
		messageBox.setItems(messageList);
		
		if( messageList.size() > 0)
			messageBox.scrollTo( messageList.size() - 1 );
	}
	
	public static void setCurrentBoardState(byte[][] boardState) {
		// -- NEED TO DRAW THE BOARD STATE HERE.
	}
	
	private void concedeBtnPressed( ActionEvent e ) {
		network.svrCommunicator.leaveTable();
		switchScene(LobbyViewController.getInstance().getScene());
	}
	
	private void sendBtnPressed( ActionEvent e ) {
		String msg = "";
		
		try {
			msg = sendMessageBox.getText();
		}catch( Exception ex ) {
			// -- Do some error handling here
		};

		if( msg.length() > 0 ) {
			System.out.println(BaseView.opponentName);
			System.out.println( "********Sending the message: " + msg );
			network.svrCommunicator.sendMsg(BaseView.opponentName, msg);
			messageList.add(msg);
			UpdateChatBox();
			// -- Clear out the text field.
			sendMessageBox.setText("");
		}
	}
	
	@FXML
	public void buttonPressed(KeyEvent e)
	{
	    if(e.getCode().toString().equals("ENTER"))
	    {
	    	String msg = "";
			
			try {
				msg = sendMessageBox.getText();
			}catch( Exception ex ) {
				// -- Do some error handling here
			};

			if( msg.length() > 0 ) {
				System.out.println(BaseView.opponentName);
				System.out.println( "********Sending the message: " + msg );
				network.svrCommunicator.sendMsg(BaseView.opponentName, msg);
				messageList.add(msg);
				UpdateChatBox();
				// -- Clear out the text field.
				sendMessageBox.setText("");
			}
	    }
	} // -- End of buttonPressed.
	private static CheckersBoardViewController instance = null;
	public static CheckersBoardViewController getInstance(){
		if (instance == null) {
			instance = new CheckersBoardViewController();
		}
		return instance;
	}
}
