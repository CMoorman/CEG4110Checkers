package UIPanes;

import Controllers.MainViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import Controllers.NetworkingController;

public class BaseView extends Application implements Runnable {
	
	private static Stage currentStage;
	Thread gameThread;
	
	// -- static server communication items.
	public static String serverIP = "127.0.0.1";

	// -- Public network controller.  Used to house the current state or settings of the network.
	public static NetworkingController network = new NetworkingController();
	
	// -- FXML items.
	@FXML
	Button settingsBtn;
	Button lobbyBtn;
	
	// ------ Variables that will change throughout the lifecycle of the game.
	public static String userName;
	public static String opponentName;
	
	// -- Used for determining if we are joining a table to spectate. 
	public static boolean isSpectating;
	public static boolean isCurrentlyInGame;
	public static int currentTableID = -1;

	//MAIN MENU PAGE
	public static String titleColor = "8b979c";
	public static String quitBtnColor = "8b979c";
	public static String settingsBtnColor = "8b979c";
	public static String loginBtnColor = "8b979c";
	public static String backgroundColor = "f3f3f3";
	public static String quitBtnTextColor = "000000";
	public static String settingsBtnTextColor = "000000";
	public static String loginBtnTextColor = "000000";

	//LOGIN PAGE
	public static String loginTitleColor = "8b979c";
	public static String loginBackgroundColor = "f3f3f3";
	public static String loginSubmitBtnColor = "8b979c";
	public static String loginCancelBtnColor = "8b979c";
	public static String loginSubmitBtnTextColor = "000000";
	public static String loginCancelBtnTextColor = "000000";

	//LOBBY PAGE
	public static String lobbyTitleColor = "8b979c";
	public static String lobbySpectateBtnColor = "8b979c";
	public static String lobbyHostBtnColor = "8b979c";
	public static String lobbyJoinBtnColor = "8b979c";
	public static String lobbyRefreshBtnColor = "8b979c";
	public static String lobbyBackgroundColor = "f3f3f3";
	public static String lobbySpectateBtnTextColor = "000000";
	public static String lobbyHostBtnTextColor = "000000";
	public static String lobbyJoinBtnTextColor = "000000";
	public static String lobbyRefreshBtnTextColor = "000000";
	public static String lobbyInProgressColor = "000000";
	public static String lobbyOpenTablesColor = "000000";
	public static String lobbyUsernameColor = "000000";

	//BOARD PAGE
	public static String boardMySquareColor = "cc0000";
	public static String boardOpponentSquareColor = "000000";
	public static String boardSendBtnColor = "8b979c";
	public static String boardConcedeBtnColor = "8b979c";
	public static String boardBackgroundColor = "f3f3f3";
	public static String boardMyAvatarColor = "0000cc";
	public static String boardOpponentAvatarColor = "00b300";
	public static String boardSendBtnTextColor = "000000";
	public static String boardConcedeBtnTextColor = "000000";
	public static String boardMyCheckersColor = "0000cc";
	public static String boardOpponentsCheckersColor = "00b300";

	public static String lobbyBtnColor = "000000";


	private static boolean playerIsInGame;

	@Override
	public void start(Stage primaryStage) {
		
		isSpectating = false;
		playerIsInGame = false;
		
		BaseView.currentStage = primaryStage;
		
		try {
			
			Scene scene = MainViewController.getInstance().getScene();
			BaseView.currentStage.setScene( scene );
			BaseView.currentStage.show();
			
			// -- Make sure that we stop our threads when we leave.
			BaseView.currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		    	   
		    	   if( network.isCurrentlyConnected ){
		        		network.svrCommunicator.disconnect(true);
		        	}
		    	   
		    	   Platform.exit();
		    	   System.exit(0);
		       }
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {
		if( gameThread == null){
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void ButtonClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Static strings corresponding to the file name of our fxml view files
	 */
	public static final String MAIN_VIEW_FMXL = "MainView.fxml";
	public static final String LOBBY_VIEW_FXML = "LobbyView.fxml";
	public static final String BOARD_VIEW_FXML = "BoardView.fxml";
	public static final String LOGIN_VIEW_FXML = "LoginView.fxml";
	public static final String SETTINGS_VIEW_FXML = "SettingsView.fxml";
	/**
	 * changes the stage of the current view
	 * @param anchorPane: anchorpane who's view will be set as the main stage's current view
	 */
	public void switchScene(Scene scene){
		currentStage.setScene(scene);
		currentStage.show();
	}
	
	public static void setCurrentTableID(int id) {
		currentTableID = id;
	}
	public static void clearCurrentTaqbleID() {
		currentTableID = -1;
	}
	
	public static void setIsInGame() {
		playerIsInGame = true;
	}
	
	public static void setIsNotInGame() {
		playerIsInGame = false;
	}
	
	public static boolean getIsPlayerInGame() {
		return playerIsInGame;
	}
	
	public static void setIsSpectating() {
		isSpectating = true;
	}
	public static void setIsNotSpectating() {
		isSpectating = false;
	}
	
	public Scene getScene(){
		return null;
	}
}
