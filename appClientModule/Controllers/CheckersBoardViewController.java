package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Objects.ColorStyleHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CheckersBoardViewController extends BaseView implements Initializable {
	
	@FXML
	Label yourUserNameLbl;
	
	@FXML
	Label oponentNameLbl;

	@FXML
	GridPane checkerBoard;
	
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
	javafx.scene.Node currentTile;
	
	@FXML
	ListView<String> messageBox;
	ObservableList<String> messageList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText(BaseView.network.getUsrName());
		oponentNameLbl.setText(BaseView.network.getOppName());
		sendBtn.setOnAction(e -> sendBtnPressed(e));
		concedeBtn.setOnAction(e -> concedeBtnPressed(e));

		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardSendBtnColor) + ";" +
				ColorStyleHelper.getTextFillStyle(boardSendBtnTextColor) + ";"
		);
		concedeBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardConcedeBtnColor) + ";" +
				ColorStyleHelper.getTextFillStyle(boardConcedeBtnTextColor) + ";"
		);
		checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardBackgroundColor));


		/*redSquare.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMySquareColor));
		blackSquare.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentSquareColor));*/

		opponentAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentAvatarColor));
		myAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMyAvatarColor));

	}

	public void updateUI(){
		yourUserNameLbl.setText(BaseView.network.getUsrName());
		oponentNameLbl.setText(BaseView.network.getOppName());
		sendBtn.setOnAction(e -> sendBtnPressed(e));
		concedeBtn.setOnAction(e -> concedeBtnPressed(e));

		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardSendBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardSendBtnTextColor) + ";"
		);
		concedeBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardConcedeBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardConcedeBtnTextColor) + ";"
		);
		checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardBackgroundColor));


		/*redSquare.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMySquareColor));
		blackSquare.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentSquareColor));*/

		opponentAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentAvatarColor));
		myAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMyAvatarColor));
	}


	
	public void setOpponentUsername(String name) {
		oponentNameLbl.setText(BaseView.opponentName);
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
	private static Scene boardScene = null;

	public Scene getScene() {
		return boardScene;
	}
	private static CheckersBoardViewController instance = null;

	public static CheckersBoardViewController getInstance() {
		if (instance == null) {
			FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(BOARD_VIEW_FXML));
			Parent mainPane = null;
			try {
				mainPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			boardScene = new Scene(mainPane);
			instance = loader.getController();
		}
		return instance;
	}
}
