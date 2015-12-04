package Controllers;

import Controllers.CheckersBoardViewController.PlayerType;
import javafx.application.Platform;
import Networking.CheckersClient;
import Networking.ServerCommunicator;
import Objects.DialogHelper;
import Objects.TableListObject;
import UIPanes.BaseView;

public class NetworkingController extends BaseView implements CheckersClient {

	public String serverIP = "192.168.0.109";
	public ServerCommunicator svrCommunicator;
	private static CheckersBoardViewController checkersView = null;
	public static void setBoardView(CheckersBoardViewController boardView){
		checkersView = boardView;
	}
	public boolean isCurrentlyConnected;
	
	public NetworkingController() {
		svrCommunicator = new ServerCommunicator( this );
	}
	
	public boolean connectToServer(String ip, String userName ){
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
		if( getIsPlayerInGame() ) {
			// -- Send message to chat box in checkerboard view.
			CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
			controller.addGameMessage(msg);
		}
		else {
			// -- Send message to global chat in lobby.
			LobbyViewController controller = LobbyViewController.getInstance();
			controller.addGameMessage(user, msg, pm);
		}
	}

	@Override
	public void usersInLobby(String[] users) {
		LobbyViewController controller = LobbyViewController.getInstance();
		for(int i = 0; i < users.length; i++ )
			controller.addLobbyUser(users[i]);
	}

	@Override
	public void nowJoinedLobby(String user) {
		LobbyViewController controller = LobbyViewController.getInstance();
		controller.addLobbyUser(user);
	}

	@Override
	public void nowLeftLobby(String user) {
		LobbyViewController controller = LobbyViewController.getInstance();
		controller.removeLobbyUser(user);
	}

	@Override
	public void newTable(int tid) {
		TableListObject tbl = new TableListObject();
		tbl.setTableId(tid);
		LobbyViewController.getInstance().addToTableList(tbl);
		
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
		checkersView.drawBoard();
	}

	@Override
	public void colorBlack() {
		System.out.println("You're black: ");
		checkersView.checkerColor = PlayerType.BLACK;
	}

	@Override
	public void colorRed() {
		System.out.println("You're red");
		checkersView.checkerColor = PlayerType.RED;
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) {
		System.out.println("Opponent has moved");
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState) {
		checkersView.updateBoardState(tid, boardState);
	}

	@Override
	public void youWin() {
		DialogHelper.showInfoDialog("Winner!", "You are the man", "You are a Checkers Champion");
		clearBoard();
	}

	private void clearBoard() {
		
	}

	@Override
	public void youLose() {
		DialogHelper.showInfoDialog("Loser!", "You are not the man", "You are a not Checkers Champion");
		clearBoard();
	}

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

			try {
				svrCommunicator.wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( getIsPlayerInGame() ) {
			Platform.runLater( new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					CheckersBoardViewController controller = checkersView;

					if( blackSeat.equals(userName) && !redSeat.equals("-1") ) {
						controller.oponentNameLbl.setText(redSeat);
					}
					else if( redSeat.equals(userName) && !blackSeat.equals("-1") ){
						controller.oponentNameLbl.setText(blackSeat);
					}
				}
			});
		}
	}

	@Override
	public void tableList(int[] tids) {
		// TODO Auto-generated method stub
		setTableList(tids);
		for( int i = 0; i < tids.length; i++ ){
			svrCommunicator.getTblStatus(userName, tids[i]);
		}
	}

	@Override
	public void yourTurn() {
		System.out.println("Players turn");
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				DialogHelper.showInfoDialog("Make a move", null, "It is now your turn. Please make a move");
				checkersView.setTurn(true);
			}
		});
	}

	@Override
	public void nowObserving(int tid) {
		// TODO Auto-generated method stub
		// revoke moving peices capabilities
		// remove concede button?
	}

	@Override
	public void stoppedObserving(int tid) {
		// TODO Auto-generated method stub
		// change scene?
	}

	@Override
	public void networkException(String msg) {
		// TODO Auto-generated method stub
		System.out.println("Hmm, not sure. Feels Bad Man.");
	}

	@Override
	public void nameInUseError() {
		System.out.println("NAme in use");
		LoginViewController controller = LoginViewController.getInstance();
		controller.nameAvailable=false;
		controller.loginSuccess=false;
	}

	@Override
	public void nameIllegal() {
		System.out.println("Name illegal");
		LoginViewController controller = LoginViewController.getInstance();
		controller.nameIllegal=true;
		controller.loginSuccess=false;
	}

	@Override
	public void illegalMove() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				DialogHelper.showErrorDialog("Illegal Move", null, "The move is illegal");
				
			}
		});
		//CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
		//handled in view
	}

	@Override
	public void tableFull() {
		// TODO Auto-generated method stub
		LobbyViewController controller = LobbyViewController.getInstance();
		controller.joinedTableFull = true;
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
		CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
		controller.sendMessageBox.setText("It is not my turn!!");
		controller.SendMessage();
	}

	@Override
	public void notObserving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppNotReady() {
		// TODO Auto-generated method stub
		CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
		controller.sendMessageBox.setText("Im Ready! Let's GO!");
		controller.SendMessage();
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
		CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
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
