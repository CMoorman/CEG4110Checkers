package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Objects.ColorStyleHelper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
	Button readyBtn;
	
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
		readyBtn.setOnAction(e -> readyBtnPressed(e));

		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardSendBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardSendBtnTextColor) + ";"
		);
		concedeBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardConcedeBtnColor) + ";" +
				ColorStyleHelper.getTextFillStyle(boardConcedeBtnTextColor) + ";"
		);

		readyBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardReadyBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardReadyBtnTextColor) + ";"
		);

		checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardBackgroundColor));

		updateCheckerBoard();

		opponentAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentAvatarColor));
		myAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMyAvatarColor));

	}
	private static final String RED_ID = "redSquare";
	private static final String BLACK_ID = "blackSquare";
	private void updateCheckerBoard(){
		if(checkerBoard != null){
			List<Node> children = checkerBoard.getChildren();
			for(Node node : children){
				String colorVal = "";
				if(node.getId().equals(RED_ID)){
					colorVal = ColorStyleHelper.getBackgroundColorStyle(boardMySquareColor);
					if(!colorVal.trim().isEmpty()){
						node.setStyle(colorVal);
					}
				}else if (node.getId().equals(BLACK_ID)){
					colorVal = ColorStyleHelper.getBackgroundColorStyle(boardOpponentSquareColor);
					if(!colorVal.trim().isEmpty()){
						node.setStyle(colorVal);
					}
				}
			}
		}
	}
	public void updateUI(){
		yourUserNameLbl.setText(BaseView.network.getUsrName());
		oponentNameLbl.setText(BaseView.network.getOppName());
		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardSendBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardSendBtnTextColor) + ";"
		);
		concedeBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardConcedeBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardConcedeBtnTextColor) + ";"
		);
		readyBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardReadyBtnColor) + ";" +
						ColorStyleHelper.getTextFillStyle(boardReadyBtnTextColor) + ";"
		);
		checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardBackgroundColor));

		opponentAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentAvatarColor));
		myAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMyAvatarColor));
	}


	
	public void setOpponentUsername(String name) {
		oponentNameLbl.setText(BaseView.opponentName);
	}
	
	public void addGameMessage(String msg) {
		Platform.runLater( new Runnable() {
			@Override
			public void run() {
				System.out.println("Adding a new message");
				messageList.add(msg);
				System.out.println("Message list: " + messageList.toString());
				updateChatBox();
			}
		});
	}
	
	public void updateChatBox() {
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

	private void readyBtnPressed( ActionEvent e ){
		//do something magical
	}
	
	private void sendBtnPressed( ActionEvent e ) {
		SendMessage();
	}
	
	@FXML
	public void buttonPressed(KeyEvent e)
	{
	    if(e.getCode().toString().equals("ENTER"))
	    {
			SendMessage();
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
	
	private void SendMessage() {
		String msg = "";
		String receiver = "";
		
		try {
			msg = sendMessageBox.getText();
			receiver = oponentNameLbl.getText();
		}catch( Exception ex ) {
			// TODO: investigate removing this try/catch, was probably null pointer related
		};

		if( msg.length() > 0 && receiver != "Seat is Empty" && receiver != "" ) {
			network.svrCommunicator.sendMsg(receiver, msg);
			messageList.add(msg);
			updateChatBox();
			// -- Clear out the text field.
			sendMessageBox.setText("");
		}
	}
	
}
