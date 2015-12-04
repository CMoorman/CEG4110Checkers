package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Objects.ColorStyleHelper;
import Objects.DialogHelper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
	ListView<String> messageBox;
	ObservableList<String> messageList = FXCollections.observableArrayList();

	public enum PlayerType {BLACK, RED};
	public PlayerType checkerColor = null;
	
	private static final String RED_ID = "redSquare";
	private static final String BLACK_ID = "blackSquare";
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
		setupBoardListener();
	}

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

		updateCheckerBoard();

		opponentAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardOpponentAvatarColor));
		myAvatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(boardMyAvatarColor));
	}
	
	private void clearUI(){
		boardState = new byte[8][8];
		opponentName = "";
		checkerColor = null;
		
	}


	private List<Pane> squares = new ArrayList<Pane>();
	private void setupBoardListener() {
		if(checkerBoard != null){
			for(Node child: checkerBoard.getChildren()){
				Pane pane = (Pane) child;
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED , new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						if(event.getSource() instanceof Pane){
							Pane pane = (Pane)event.getSource();
							int col = GridPane.getColumnIndex(pane);
							int row = GridPane.getRowIndex(pane);
							PlayerType checker = null;
							if(pane.getChildren().size() > 0 && pane.getChildren().size() < 3){
								Circle c = (Circle)pane.getChildren().get(0);
								if(c.getFill().equals(Color.BLACK)){
									checker = PlayerType.BLACK;
								}else if(c.getFill().equals(Color.RED)){
									checker = PlayerType.RED;
								}
							}
							boardPressed(row, col, checker);
						}
					}
				});
				squares.add(pane);
			}
		}
		
	}
	private class Location{
		public int x = 0;
		public int y = 0;
	}
	private Location firstPressLocation = null;
	private Location secondPressLocation = null;
	protected void boardPressed(int row, int col, PlayerType type) {
		if(!isTurn){
			DialogHelper.showInfoDialog("Not Your Move", null, "Please Wait for your turn");
		}
		if(firstPressLocation == null){
			if(type == null || !type.equals(checkerColor)){
				DialogHelper.showErrorDialog("Invalid move", null, "Please select a square that contains your Checker");
			}else{
				Location l = new Location();
				l.x = row;
				l.y = col;
				firstPressLocation = l;
			}
		}else{
			if(type == null){
				Location l = new Location();
				l.x = row;
				l.y = col;
				secondPressLocation = l;
				move();
			}else{
				DialogHelper.showErrorDialog("Invalid Move", null, "Cannot move here");
				firstPressLocation = null;
			}
		}
		
	}
	private void move() {
		if(isTurn && firstPressLocation != null && secondPressLocation != null){
			int fr = firstPressLocation.x;
			int fc = firstPressLocation.y;
			int tr = secondPressLocation.x;
			int tc = secondPressLocation.y;
			network.svrCommunicator.move(fr, fc, tr, tc);
			clearPressLocations();
		}
		
	}
	private void clearPressLocations() {
		firstPressLocation = null;
		secondPressLocation = null;
		
	}
	private boolean isTurn = false;
	public void setTurn(boolean turn){
		isTurn = true;
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
		messageList.clear();
		switchScene(LobbyViewController.getInstance().getScene());
	}

	private void readyBtnPressed( ActionEvent e ){
		network.svrCommunicator.playerReady(userName);
	}
	
	private void sendBtnPressed( ActionEvent e ) {
		SendMessage();
	}
	
	@FXML
	public void buttonPressed(KeyEvent e)
	{
	    if(e.getCode().equals(KeyCode.ENTER))
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
			NetworkingController.setBoardView(instance);
		}
		return instance;
	}
	public void drawBoard() {
		if(checkerBoard != null){
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					List<Node> children = checkerBoard.getChildren();
					for(Node temp : children){
						Pane node = (Pane) temp;
						node.getChildren().clear();
						int col = GridPane.getColumnIndex(node);
						int row = GridPane.getRowIndex(node);
						byte state = boardState[row][col];
						Circle circle = getChecker(state);
						
						if(circle != null){
							circle.setCenterX((node.getWidth() / 2));
							circle.setCenterY(node.getHeight() / 2);
						    node.getChildren().add(circle);
						    if(state > 2){
						    	Text king = new Text( circle.getCenterX(), circle.getCenterY(), "K");
						    	king.setFill(Color.WHITE);;
						    	node.getChildren().add(king);
						    }
						}
						
					}
					
				}

				private Circle getChecker(byte state) {
					Circle circle = null;
					if(state == 1 || state == 3){
						circle = new Circle(15, Color.BLACK);
					}else if(state == 2 || state == 4){
						circle = new Circle(15, Color.RED);
					}
					
					return circle;
				}
			});
			
		}
	}
	private byte[][] boardState = initBoard();
	public void updateBoardState(int tid, byte[][] boardState) {
		if(tid == currentTableID){
			this.boardState = boardState.clone();
		}
		drawBoard();
	}
	private byte[][] initBoard() {
		byte[][] b = new byte[8][8];
		for(int i = 0; i < 8; ++i){
			for(int j = 0; j < 8; ++j){
				if(i < 3){
					if(i % 2 == 0 && j % 2 == 1){
						b[i][j] = 1;
					}else if(i % 2 == 1 && j % 2 == 0){
						b[i][j] = 1;
					}else{
						b[i][j] = 0;
					}
				}else if (i > 4){
					if(i % 2 == 1 && j % 2 == 0){
						b[i][j] = 2;
					}else if(i % 2 == 0 && j % 2 == 1){
						b[i][j] = 2;
					}else{
						b[i][j] = 0;
					}
				}
			}
		}
		return b;
	}

	public void SendMessage() {
		String msg = "";
		String receiver = "";
		
		try {
			msg = sendMessageBox.getText();
			receiver = oponentNameLbl.getText();
		}catch( Exception ex ) {
			// TODO: investigate removing this try/catch, was probably null pointer related
		};

		if( msg.length() > 0 && receiver != "Seat is Empty" && receiver != "" ) {
			msg = userName + ": " + msg;
			network.svrCommunicator.sendMsg(receiver, msg);
			messageList.add(msg);
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					updateChatBox();
					// -- Clear out the text field.
					sendMessageBox.setText("");
				}
			});
		}
	}
	
}
