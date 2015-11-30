package Controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Window;
import Networking.CheckersClient;
import Networking.ServerCommunicator;
import UIPanes.BaseView;

public class NetworkingController extends BaseView implements CheckersClient {

	public String serverIP = "127.0.0.1";
	public ServerCommunicator svrCommunicator;
	
	public boolean isCurrentlyConnected;
	
	public String usrName;
	
	public NetworkingController() {
		isCurrentlyConnected = false;
		svrCommunicator = new ServerCommunicator( this );
	}
	
	public boolean connectToServer(String ip, String userName ){
		System.out.println("Connecting to server!");
		isCurrentlyConnected = svrCommunicator.connectToServer(ip, userName);
		return isCurrentlyConnected;
	}
	
	public void setUsrName( String usrName ) {
		this.usrName = usrName;
	}
	
	public String getUsrName() {
		return this.usrName;
	}

	@Override
	public void connectionOK() {
		System.out.println("Connected OK");
	}

	@Override
	public void youInLobby() {
		System.out.println("You are now in the lobby.");
	}

	@Override
	public void youLeftLobby() {
		System.out.println("You have left the lobby.");
	}

	@Override
	public void newMsg(String user, String msg, boolean pm) {
		System.out.println("New message from: " + user + "." + " Message: " + msg + ". Private: " + pm );

		Scene boardScene;
		CheckersBoardViewController board;
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		try {
			boardScene = fxmlLoader.load(getClass().getResource("BoardView.fxml").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = (CheckersBoardViewController) fxmlLoader.getController();
		board.addGameMessage(msg);
	}

	@Override
	public void usersInLobby(String[] users) {
		String usrs = "";
		for(int i = 0; i < users.length; i++ )
			usrs += users[i];
		System.out.println("Users in the lobby: " + usrs );
	}

	@Override
	public void nowJoinedLobby(String user) {
		System.out.println(user + " join the lobby." );
	}

	@Override
	public void nowLeftLobby(String user) {
		System.out.println(user + " left the lobby." );
	}

	@Override
	public void newTable(int tid) {
		System.out.println("New table: " + tid);
		svrCommunicator.joinTable(userName, tid);
	}

	@Override
	public void joinedTable(int tid) {
		currentTableID = tid;
		isCurrentlyInGame = true;
		System.out.println("Joined table: " + tid);
	}

	@Override
	public void alertLeftTable() {
		isCurrentlyInGame = false;
		System.out.println("You have left the table.");
	}

	@Override
	public void gameStart() {
		System.out.println("The game has started.");
	}

	@Override
	public void colorBlack() {
		
		
	}

	@Override
	public void colorRed() {
		
		
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) {
		
		
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState) {
		
	}

	@Override
	public void youWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTable(int tid, String blackSeat, String redSeat) {
		
		Scene boardScene;
		CheckersBoardViewController board;
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		try {
			boardScene = fxmlLoader.load(getClass().getResource("BoardView.fxml").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = (CheckersBoardViewController) fxmlLoader.getController();
		
		// -- Not sure if we are going to be black or red.  So, check to see if the username matches our users name.
		if( blackSeat != userName ){
			
			opponentName = blackSeat;
			System.out.println("blackSeat: " + opponentName);
			board.setOpponentUsername(opponentName);
		}
		else if( redSeat != userName ){
			
			opponentName = redSeat;
			System.out.println("redSeat"  + opponentName);
			board.setOpponentUsername(opponentName);
		}
		
		
	}

	@Override
	public void tableList(int[] tids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void yourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowObserving(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoppedObserving(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void networkException(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameInUseError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameIllegal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void illegalMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableFull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tblNotExists() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameNotCreatedYet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notYourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notObserving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppNotReady() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorInLobby() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void badMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppLeftTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notInLobby() {
		// TODO Auto-generated method stub
		
	}	
}
