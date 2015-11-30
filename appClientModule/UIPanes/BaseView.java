package UIPanes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import Controllers.NetworkingController;
import Networking.*;

public class BaseView extends Application implements Runnable {
	
	private static Stage currentStage;
	private static AnchorPane currentView;
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
	public String userName;
	public String opponentName;
	
	// -- Used for determining if we are joining a table to spectate. 
	public static boolean isSpectating;
	public static boolean isCurrentlyInGame;
	public static int currentTableID = -1;
	
	@Override
	public void start(Stage primaryStage) {
		
		isSpectating = false;
		
		BaseView.currentStage = primaryStage;
		
		try {
			setCurrentView((AnchorPane) FXMLLoader.load(BaseView.class.getResource("MainView.fxml")));
			Scene scene = new Scene( getCurrentView() );
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
	
	public AnchorPane getCurrentView() {
		return currentView;
	}

	public void setCurrentView(AnchorPane currentView) {
		BaseView.currentView = currentView;
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
		currentStage.setScene( scene );
		currentStage.show();
	}
	
	public void setCurrentTableID(int id) {
		currentTableID = id;
	}
	public void clearCurrentTaqbleID() {
		currentTableID = -1;
	}
	
	public void setIsSpectating() {
		isSpectating = true;
	}
	public void setIsNotSpectating() {
		isSpectating = false;
	}
	
	public static Scene getViewInstance(){
		return null;
	}
}
