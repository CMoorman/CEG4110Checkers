package Controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import Networking.CheckersClient;
import Networking.ServerCommunicator;
import Objects.TableListObject;
import UIPanes.BaseView;

public class NetworkingController extends BaseView implements CheckersClient {

	public String serverIP = "127.0.0.1";
	public ServerCommunicator svrCommunicator;
	
	public boolean isCurrentlyConnected;
	
	public NetworkingController() {
		svrCommunicator = new ServerCommunicator( this );
	}
	
	public boolean connectToServer(String ip, String userName ){
		System.out.println("Connecting to server!");
		isCurrentlyConnected = svrCommunicator.connectToServer(ip, userName);
		return isCurrentlyConnected;
	}
	
	public void setUsrName( String usrName ) {
		userName = usrName;
	}
	
	public String getUsrName() {
		return userName;
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
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			AnchorPane boardScene = (AnchorPane) FXMLLoader.load( getClass().getResource("BoardView.fxml"));
			CheckersBoardViewController controller = (CheckersBoardViewController) fxmlLoader.getController();
			controller.addGameMessage(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		setIsInGame();
	}

	@Override
	public void joinedTable(int tid) {
		currentTableID = tid;
		isCurrentlyInGame = true;
		System.out.println("Joined table: " + tid);
		setIsInGame();
	}

	@Override
	public void alertLeftTable() {
		System.out.println("You have left the table.");
		setIsNotInGame();
	}

	@Override
	public void gameStart() {
		System.out.println("The game has started.");
	}

	@Override
	public void colorBlack() {
		System.out.println("You're black");
		
	}

	@Override
	public void colorRed() {
		System.out.println("You're red");
		
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

	private static boolean isOppRed = false;
	private static boolean firstPass = true;
	@Override
	public void onTable(int tid, String blackSeat, String redSeat) {
		
		if( !getIsPlayerInGame() ) {
			TableListObject table = new TableListObject();
			if( blackSeat.equals("-1") )
				table.setBlackPlayer("(Empty Seat)");
			else
				table.setBlackPlayer(blackSeat);
			
			if( redSeat.equals("-1") )
				table.setRedPlayer("(Empty Seat)");
			else
				table.setRedPlayer(redSeat);
			
			table.setTableId(tid);
			
			LobbyViewController controller = LobbyViewController.getInstance();
			controller.updateGameList(table);
			
			addToTableList(table);
			
			try {
				svrCommunicator.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// -- Not sure if we are going to be black or red.  So, check to see if the username matches our users name.
		if( getIsPlayerInGame() ) {
			if( firstPass ) {
				if( blackSeat.equals(userName) ) {
					isOppRed = true; 
				}
				firstPass = false;
			}
			else {
				if( !isOppRed ) {
					opponentName = blackSeat;
				}else {
					opponentName = redSeat;
				}
			}	
		}
		
		svrCommunicator.notifyAll();
	}

	@Override
	public void tableList(int[] tids) {
		for( int i = 0; i < tids.length; i++ ) {
			svrCommunicator.getTblStatus(userName, tids[i]);
		}
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

	public void setOppName( String usrName ) {
		opponentName = usrName;
	}
	
	public String getOppName() {
		return opponentName;
	}
}
