package Networking;

import java.io.*;
import java.net.*;

import Interfaces.CheckersClient;
import Interfaces.ServerInterface;

/**
 * This class facilitates communication from the checkers client to the server.
 *
 * Client messages are received here and then passed to the ServerInterface
 * module where the message is translated into a TCP message the server can
 * understand. That message is then sent across the network.
 *
 * @author derek 9.7.15
 */
public class ServerCommunicator implements ServerInterface {

	private Socket socket = null;

  private DataInputStream streamFromServer;
	private DataOutputStream streamToServer;

	private String userName;

	private CheckersClient client;
	private ServerConnection serverCon;

	private TCPListenerThread listener;

  /* c is an object implementing CheckersClient. It should be an object
		from your project able to receive all of the TCP messages sent by
		the server. */
	public ServerCommunicator(CheckersClient c){
		socket = null;
		streamFromServer = null;
		streamToServer = null;
		client = c;
		userName = "";
		serverCon = null;
		listener = null;
	}

	public boolean connectToServer(String ip, String userName) {
		  try {
	      	socket = new Socket(ip, 45322);
	        lnOut("Connected to server!");;
	        streamFromServer = new DataInputStream(socket.getInputStream());
					streamToServer = new DataOutputStream(socket.getOutputStream());

				//With the connection established, create the server connection module.
				serverCon = new ServerConnection(this, streamToServer);
				//next start a thread that listens to incoming
				//messages from the game server via TCP.
				listener = new TCPListenerThread(this, client, streamFromServer);

				//finally send the requested user name to the server.
				streamToServer.write(userName.getBytes());
				return true;
			}
		  	catch (UnknownHostException e) {
	            System.out.println("Don't know about host: "+ip);
	            System.out.println(e.getMessage());
	            return false;
	        } catch (IOException e) {
	            System.out.println("Couldn't get I/O for the connection to: "+ip);
	            System.out.println(e.getMessage());
	            return false;
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        	return false;
	        }
	}

	public void killListenThread() throws IOException{
		if(listener != null){
			listener.active = false;
			listener.interrupt();
			listener = null;
			socket.close();
		}
	}

	public void disconnect(boolean endProcess) {
		serverCon.disconnect(userName);
		if(endProcess){
			System.exit(1);
		}
	}

	//@Override
	public void joinTable(int tid) {
		serverCon.joinTable(userName, tid);
	}

	//@Override
	public void leaveTable() {
		serverCon.leaveTable(userName);
	}

	//@Override
	public void makeTable() {
		serverCon.makeTable(userName);
	}

	//@Override
	public void move(int fr, int fc, int tr, int tc) {
		serverCon.move(userName, fr, fc, tr, tc);
	}

	//@Override
	public void ready() {
		serverCon.playerReady(userName);
	}

	public void sendMsg(String to, String msg) {
		serverCon.msgPlayer(userName, to, msg);
	}

	public void sendMsg_All(String msg) {
		serverCon.msgAll(userName, msg);
	}

	/** Game playing methods **/
	public void getTblStatus(String user, int tid) {
		serverCon.getTblStatus(user, tid);
	}

	public void joinTable(String user, int tid) {
		serverCon.joinTable(user, tid);
	}

	public void leaveTable(String user) {
		serverCon.leaveTable(user);
	}

	public void makeTable(String user) {
		serverCon.makeTable(user);
	}

	public void move(String user, int fr, int fc, int tr, int tc) {
		serverCon.move(user, fr, fc, tr, tc);
	}

	public void playerReady(String user) {
		serverCon.playerReady(user);
	}

	public void observeTable(String user, int tid) {
		serverCon.observeTable(user, tid);
	}

	public void stopObserving(String user, int tid) {
		serverCon.stopObserving(user, tid);

	}

	/**
	 * Console helper methods
	 */
	private void lnOut(String s){System.out.println(s);}

	public void outputToConsole(String s){
		lnOut(s);
	}

}
