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

public class CheckersBoardViewController extends BaseView implements Initializable {

	private static Scene boardView = null;
	
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
	ListView<String> messageBox;
	ObservableList<String> messageList = FXCollections.observableArrayList();
	
	public String oppName = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText( BaseView.network.getUsrName() );
		
		sendBtn.setOnAction( e -> sendBtnPressed(e) );
		concedeBtn.setOnAction( e -> concedeBtnPressed(e) );
	}

	public static Scene getViewInstance() {
		if (CheckersBoardViewController.boardView == null) {
			try {
				AnchorPane boardPane = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(BOARD_VIEW_FXML));
				CheckersBoardViewController.boardView = new Scene(boardPane);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return CheckersBoardViewController.boardView;
	}
	
	public void setOpponentUsername(String name) {
		oppName = name;
		opponentNameLbl.setText(opponentName);
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
	}
	
	public static void setCurrentBoardState(byte[][] boardState) {
		// -- NEED TO DRAW THE BOARD STATE HERE.
	}
	
	private void concedeBtnPressed( ActionEvent e ) {
		network.svrCommunicator.leaveTable();
		switchScene(LobbyViewController.getViewInstance());
	}
	
	private void sendBtnPressed( ActionEvent e ) {
		String msg = "";
		
		try {
			msg = sendMessageBox.getText();
		}catch( Exception ex ) {
			// -- Do some error handling here
		};

		if( msg.length() > 0 ) {
			System.out.println(oppName);
			System.out.println( "********Sending the message: " + msg );
			network.svrCommunicator.sendMsg(oppName, msg);
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
				System.out.println(oppName);
				System.out.println( "********Sending the message: " + msg );
				network.svrCommunicator.sendMsg(oppName, msg);
				messageList.add(msg);
				UpdateChatBox();
				// -- Clear out the text field.
				sendMessageBox.setText("");
			}
	    }
	} // -- End of buttonPressed.
}
