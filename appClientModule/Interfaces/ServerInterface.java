package Interfaces;

public interface ServerInterface {
	/**
	 * This interface defines a class that connects to the game server over TCP.
	 * All possible messages
	 * from the client to the server are defined by methods here.
	 * @author derk 8.15.08
	 */

	 //connect to the checkers server at address <ip> using username <userName>.
	 public boolean connectToServer(String ip, String userName);
	 //send the message <msg> to everyone in the lobby.
	 public void sendMsg_All(String msg);
	 //send the message <msg> o user <user>.
	 public void sendMsg(String to, String msg);
	 //tell the server you are disconnecting.
	 public void disconnect(boolean endProcess);
	 //tell the server you want to make a table.
	 public void makeTable(String user);
	 //tell the server you are joining table with id tid.
	 public void joinTable(String user, int tid);
	 //tell the server you are ready to play a game, after sitting on a table.
	 public void playerReady(String user);
	 //tell the server you are moving from (fr,fc) to (tr,tc). See CheckersClient interface.
	 public void move(String user, int fr, int fc, int tr, int tc);
	 //tell the server you are leaving the table you have joined.
	 public void leaveTable(String user);
	 //ask the server what the status of table with id tid is.
	 public void getTblStatus(String user, int tid);
	 //start observing the table with id tid.
	 public void observeTable(String user, int tid);
	 //stop observing the table with id tid.
	 public void stopObserving(String user, int tid);

}
