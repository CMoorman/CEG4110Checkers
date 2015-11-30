package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;

public class CheckersBoardViewController extends BaseView implements Initializable, BaseViewController {

	@FXML
	Label yourUserNameLbl;
	
	@FXML
	Label opponentNameLbl;
	
	@FXML
	Button sendBtn;
	
	@FXML
	Button concedeBtn;
	
	@FXML
	TextArea sendMessageBox;
	
	@FXML
	ListView<String> messageBox;
	ObservableList<String> messageList = FXCollections.observableArrayList();
	
	public String oppName = "";
	
	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == concedeBtn ){
			network.svrCommunicator.leaveTable();
			switchScene(LobbyViewController.getViewInstance());
		}
		else if( e.getSource() == sendBtn ){
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
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText( BaseView.network.getUsrName() );
		
		sendBtn.setOnAction( e -> ButtonClicked(e) );
		concedeBtn.setOnAction( e -> ButtonClicked(e) );
	}
	private static Scene boardView = null;

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
	
	public void UpdateChatBox() {
		System.out.println("Updating chat box " + messageList.toString() );
		messageBox.setItems(messageList);
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
	
	public static void setCurrentBoardState(byte[][] boardState) {
		// -- NEED TO DRAW THE BOARD STATE HERE.
	}
}
