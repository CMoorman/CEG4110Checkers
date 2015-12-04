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
import Objects.DialogHelper;

import java.io.*;

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
	public static String opponentName = "Seat is Empty";
	
	// -- Used for determining if we are joining a table to spectate. 
	public static boolean isSpectating;
	public static boolean isCurrentlyInGame;
	public static int currentTableID = -1;



	//MAIN MENU PAGE
	public static String titleColor;
	public static String quitBtnColor;
	public static String settingsBtnColor;
	public static String loginBtnColor;
	public static String backgroundColor;
	public static String quitBtnTextColor;
	public static String settingsBtnTextColor;
	public static String loginBtnTextColor;
	public static String loginTitleColor;
	public static String loginBackgroundColor;
	public static String loginSubmitBtnColor;
	public static String loginCancelBtnColor;
	public static String loginSubmitBtnTextColor;
	public static String loginCancelBtnTextColor;
	public static String lobbyTitleColor;
	public static String lobbySpectateBtnColor;
	public static String lobbyHostBtnColor;
	public static String lobbyJoinBtnColor;
	public static String lobbyRefreshBtnColor;
	public static String lobbyBackgroundColor;
	public static String lobbySpectateBtnTextColor;
	public static String lobbyHostBtnTextColor;
	public static String lobbyJoinBtnTextColor;
	public static String lobbyRefreshBtnTextColor;
	public static String lobbyInProgressColor;
	public static String lobbyOpenTablesColor;
	public static String lobbyUsernameColor;
	public static String lobbyDisconnectBtnColor;
	public static String lobbyDisconnectBtnTextColor;
	public static String boardMySquareColor;
	public static String boardOpponentSquareColor;
	public static String boardSendBtnColor;
	public static String boardConcedeBtnColor;
	public static String boardBackgroundColor;
	public static String boardMyAvatarColor;
	public static String boardOpponentAvatarColor;
	public static String boardSendBtnTextColor;
	public static String boardConcedeBtnTextColor;
	public static String boardMyCheckersColor;
	public static String boardOpponentsCheckersColor;
	public static String boardReadyBtnColor;
	public static String boardReadyBtnTextColor;
	public static String spectatePlayer1SquareColor;
	public static String spectatePlayer2SquareColor;
	public static String spectateReturnBtnColor;
	public static String spectateReturnBtnTextColor;
	public static String spectateSendBtnColor;
	public static String spectateSendBtnTextColor;
	public static String spectatePlayer1AvatarColor;
	public static String spectatePlayer2AvatarColor;
	public static String spectateBackground;
	public static String spectatePaneColor;
	public static String spectatePlayer1CheckerColor;
	public static String spectatePlayer2CheckerColor;
	public static String lobbySendMsgBtnColor;
	public static String lobbySendMsgBtnTextColor;




	public static String lobbyBtnColor = "000000";

	private static int[] tableList;
	
	private static boolean playerIsInGame;

	@Override
	public void start(Stage primaryStage) {
		
		isSpectating = false;
		playerIsInGame = false;

		loadColors();
		
		BaseView.currentStage = primaryStage;
		DialogHelper.initOwner(BaseView.currentStage.getOwner());
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
	public static final String SPECTATE_VIEW_FXML = "SpectateView.fxml";


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
	
	public static void setTableList(int[] tables) {
		tableList = tables;
	}

	
	public static int[] getTableList() {
		return tableList;
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

	public static void loadColors(){
		String[] values = null;
		BufferedReader br = null;
		String line = null;
		//don't think I need this
		Boolean fileFound = false;

		try {
			br = new BufferedReader(new FileReader("ColorSettings.txt"));
			fileFound = true;
		} catch (FileNotFoundException e) {
			fileFound = false;
			setDefaultColors();
		}

		try {
			while ((line = br.readLine()) != null) {
				values = line.split(", ");
				for (String str : values) {
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//WARNING: This number needs to equal the number of elements we can change in the settings menu!
		if(fileFound && values != null){
			System.out.println("File found!");
			System.out.println(values);
			System.out.println("Value Lenth: " + values.length);
			if(values.length == 56){
				for(int i = 0; i < values.length; i++){
					if(values[i].length() != 6){
						setDefaultColors();
					}
				}
				setColors(values);
			}else{
				setDefaultColors();
			}
		}else {
			setDefaultColors();
		}
	}

	public static void setColors(String[] values){

		titleColor = values[0];
		quitBtnColor = values[1];
		settingsBtnColor = values[2];
		loginBtnColor = values[3];
		backgroundColor = values[4];
		quitBtnTextColor = values[5];
		settingsBtnTextColor = values[6];
		loginBtnTextColor = values[7];
		loginTitleColor = values[8];
		loginBackgroundColor = values[9];
		loginSubmitBtnColor = values[10];
		loginCancelBtnColor = values[11];
		loginSubmitBtnTextColor = values[12];
		loginCancelBtnTextColor = values[13];
		lobbyTitleColor = values[14];
		lobbySpectateBtnColor = values[15];
		lobbyHostBtnColor = values[16];
		lobbyJoinBtnColor = values[17];
		lobbyRefreshBtnColor = values[18];
		lobbyBackgroundColor = values[19];
		lobbySpectateBtnTextColor = values[20];
		lobbyHostBtnTextColor = values[21];
		lobbyJoinBtnTextColor = values[22];
		lobbyRefreshBtnTextColor = values[23];
		lobbyInProgressColor = values[24];
		lobbyOpenTablesColor = values[25];
		lobbyUsernameColor = values[26];
		lobbyDisconnectBtnColor = values[27];
		lobbyDisconnectBtnTextColor = values[28];
		boardMySquareColor = values[29];
		boardOpponentSquareColor = values[30];
		boardSendBtnColor = values[31];
		boardConcedeBtnColor = values[32];
		boardBackgroundColor = values[33];
		boardMyAvatarColor = values[34];
		boardOpponentAvatarColor = values[35];
		boardSendBtnTextColor = values[36];
		boardConcedeBtnTextColor = values[37];
		boardMyCheckersColor = values[38];
		boardOpponentsCheckersColor = values[39];
		boardReadyBtnColor = values[40];
		boardReadyBtnTextColor = values[41];
		spectatePlayer1SquareColor = values[42];
		spectatePlayer2SquareColor = values[43];
		spectateReturnBtnColor = values[44];
		spectateReturnBtnTextColor = values[45];
		spectateSendBtnColor = values[46];
		spectateSendBtnTextColor = values[47];
		spectatePlayer1AvatarColor = values[48];
		spectatePlayer2AvatarColor = values[49];
		spectateBackground = values[50];
		spectatePaneColor = values[51];
		spectatePlayer1CheckerColor = values[52];
		spectatePlayer2CheckerColor = values[53];
		lobbySendMsgBtnColor = values[54];
		lobbySendMsgBtnTextColor = values[55];
	}

	public static void saveColors(){

		File file = new File("ColorSettings.txt");
		//file.getParentFile().mkdirs();

		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		// -- MAIN MENU
		printWriter.print(trimColor(titleColor) + ", ");

		printWriter.print(trimColor(quitBtnColor) + ", ");
		printWriter.print(trimColor(settingsBtnColor) + ", ");
		printWriter.print(trimColor(loginBtnColor) + ", ");
		printWriter.print(trimColor(backgroundColor) + ", ");
		printWriter.print(trimColor(quitBtnTextColor) + ", ");
		printWriter.print(trimColor(settingsBtnTextColor) + ", ");
		// -- LOGIN
		printWriter.print(trimColor(loginBtnTextColor) + ", ");
		printWriter.print(trimColor(loginTitleColor) + ", ");
		printWriter.print(trimColor(loginBackgroundColor) + ", ");
		printWriter.print(trimColor(loginSubmitBtnColor) + ", ");
		printWriter.print(trimColor(loginCancelBtnColor) + ", ");
		printWriter.print(trimColor(loginSubmitBtnTextColor) + ", ");
		printWriter.print(trimColor(loginCancelBtnTextColor) + ", ");
		// -- LOBBY
		printWriter.print(trimColor(lobbyTitleColor) + ", ");
		printWriter.print(trimColor(lobbySpectateBtnColor) + ", ");
		printWriter.print(trimColor(lobbyHostBtnColor) + ", ");
		printWriter.print(trimColor(lobbyJoinBtnColor) + ", ");
		printWriter.print(trimColor(lobbyRefreshBtnColor) + ", ");
		printWriter.print(trimColor(lobbyBackgroundColor) + ", ");
		printWriter.print(trimColor(lobbySpectateBtnTextColor) + ", ");
		printWriter.print(trimColor(lobbyHostBtnTextColor) + ", ");
		printWriter.print(trimColor(lobbyJoinBtnTextColor) + ", ");
		printWriter.print(trimColor(lobbyRefreshBtnTextColor) + ", ");
		printWriter.print(trimColor(lobbyInProgressColor) + ", ");
		printWriter.print(trimColor(lobbyOpenTablesColor) + ", ");
		printWriter.print(trimColor(lobbyUsernameColor) + ", ");
		printWriter.print(trimColor(lobbyDisconnectBtnColor) + ", ");
		printWriter.print(trimColor(lobbyDisconnectBtnTextColor) + ", ");
		// -- BOARD
		printWriter.print(trimColor(boardMySquareColor) + ", ");
		printWriter.print(trimColor(boardOpponentSquareColor) + ", ");
		printWriter.print(trimColor(boardSendBtnColor) + ", ");
		printWriter.print(trimColor(boardConcedeBtnColor) + ", ");
		printWriter.print(trimColor(boardBackgroundColor) + ", ");
		printWriter.print(trimColor(boardMyAvatarColor) + ", ");
		printWriter.print(trimColor(boardOpponentAvatarColor) + ", ");
		printWriter.print(trimColor(boardSendBtnTextColor) + ", ");
		printWriter.print(trimColor(boardConcedeBtnTextColor) + ", ");
		printWriter.print(trimColor(boardMyCheckersColor) + ", ");
		printWriter.print(trimColor(boardOpponentsCheckersColor) + ", ");
		printWriter.print(trimColor(boardReadyBtnColor) + ", ");
		printWriter.print(trimColor(boardReadyBtnTextColor) + ", ");
		// -- SPECTATE
		printWriter.print(trimColor(spectatePlayer1SquareColor) + ", ");
		printWriter.print(trimColor(spectatePlayer2SquareColor) + ", ");
		printWriter.print(trimColor(spectateReturnBtnColor) + ", ");
		printWriter.print(trimColor(spectateReturnBtnTextColor) + ", ");
		printWriter.print(trimColor(spectateSendBtnColor) + ", ");
		printWriter.print(trimColor(spectateSendBtnTextColor) + ", ");
		printWriter.print(trimColor(spectatePlayer1AvatarColor) + ", ");
		printWriter.print(trimColor(spectatePlayer2AvatarColor) + ", ");
		printWriter.print(trimColor(spectateBackground) + ", ");
		printWriter.print(trimColor(spectatePaneColor) + ", ");
		printWriter.print(trimColor(spectatePlayer1CheckerColor) + ", ");
		printWriter.print(trimColor(spectatePlayer2CheckerColor) + ", ");

		//LOBBY BUTTON
		printWriter.print(trimColor(lobbySendMsgBtnColor) + ", ");
		printWriter.print(trimColor(lobbySendMsgBtnTextColor) + ", ");
		printWriter.close();

	}
	public static void setDefaultColors(){
		titleColor = "8b979c";
		quitBtnColor = "8b979c";
		settingsBtnColor = "8b979c";
		loginBtnColor = "8b979c";
		backgroundColor = "f3f3f3";
		quitBtnTextColor = "000000";
		settingsBtnTextColor = "000000";
		loginBtnTextColor = "000000";
		loginTitleColor = "8b979c";
		loginBackgroundColor = "f3f3f3";
		loginSubmitBtnColor = "8b979c";
		loginCancelBtnColor = "8b979c";
		loginSubmitBtnTextColor = "000000";
		loginCancelBtnTextColor = "000000";
		lobbyTitleColor = "8b979c";
		lobbySpectateBtnColor = "8b979c";
		lobbyHostBtnColor = "8b979c";
		lobbyJoinBtnColor = "8b979c";
		lobbyRefreshBtnColor = "8b979c";
		lobbyBackgroundColor = "f3f3f3";
		lobbySpectateBtnTextColor = "000000";
		lobbyHostBtnTextColor = "000000";
		lobbyJoinBtnTextColor = "000000";
		lobbyRefreshBtnTextColor = "000000";
		lobbyInProgressColor = "000000";
		lobbyOpenTablesColor = "000000";
		lobbyUsernameColor = "000000";
		lobbyDisconnectBtnColor = "8b979c";
		lobbyDisconnectBtnTextColor = "000000";
		boardMySquareColor = "cc0000";
		boardOpponentSquareColor = "000000";
		boardSendBtnColor = "8b979c";
		boardConcedeBtnColor = "8b979c";
		boardBackgroundColor = "f3f3f3";
		boardMyAvatarColor = "0000cc";
		boardOpponentAvatarColor = "00b300";
		boardSendBtnTextColor = "000000";
		boardConcedeBtnTextColor = "000000";
		boardMyCheckersColor = "0000cc";
		boardOpponentsCheckersColor = "00b300";
		boardReadyBtnColor = "8b979c";
		boardReadyBtnTextColor = "000000";
		spectatePlayer1SquareColor = "ffffff";
		spectatePlayer2SquareColor = "000000";
		spectateReturnBtnColor = "8b979c";
		spectateReturnBtnTextColor = "000000";
		spectateSendBtnColor = "8b979c";
		spectateSendBtnTextColor = "000000";
		spectatePlayer1AvatarColor = "f3f3f3";
		spectatePlayer2AvatarColor = "000000";
		spectateBackground = "ffffff";
		spectatePaneColor = "f3f3f3";
		spectatePlayer1CheckerColor = "ffffff";
		spectatePlayer2CheckerColor = "000000";
		lobbySendMsgBtnColor = "8b979c";
		lobbySendMsgBtnTextColor = "000000";
	}


	public static String trimColor(String color){
		//System.out.println(color.length());
		if(color.length() == 6){
			return color;
		}else{
			color = color.substring(0, color.length() - 2).trim();
			System.out.println(color);
			return color;
		}

	}
}
