package Networking;

import java.io.*;

import Interfaces.TCPMsg;
import Interfaces.TCPServerInterface;

/**
 * This class defines the connection from the client to the server. Method
 * calls from the client are passed off to this class. It creates the message
 * in the format expected by the server and then sends it off via a TCP connection.
 * @author derek
 * 8.15.08
 */
public class ServerConnection implements TCPServerInterface{

	private DataOutputStream streamToServer;
	private ServerCommunicator communicator;

	public ServerConnection(ServerCommunicator clientCon, DataOutputStream toServer){
		streamToServer = toServer;
		communicator = clientCon;
	}

	public void disconnect(String user){
		try{
			communicator.outputToConsole("Disconnecting...");
			streamToServer.write((Integer.toString(TCPMsg.QUIT)+" "+user+TCPMsg.endOfMsg).getBytes());
			//first we need to join the listener thread
			communicator.killListenThread();
			streamToServer.close();
		}catch(IOException ex){
			communicator.outputToConsole("IOE Disconnecting: "+ex.getMessage());
		}
	}

	public void joinTable(String user, int tid) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.JOIN_TBL)+" "+user+" "+Integer.toString(tid)+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	public void leaveTable(String user) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.LEAVE_TBL)+" "+user+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	public void makeTable(String user) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.MAKE_TBL)+" "+user+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	public void move(String user, int fr, int fc, int tr, int tc) {
		String moveF = Integer.toString(fr)+","+ Integer.toString(fc);
		String moveT = Integer.toString(tr)+","+Integer.toString(tc);
		try{
			streamToServer.write((Integer.toString(TCPMsg.MOVE)+" "+user+" "+moveF+" "+moveT+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	public void msgAll(String user, String msg) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.MSG_ALL)+" "+user+" "+msg+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	public void msgPlayer(String from, String to, String msg) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.MSG_C)+" "+from+" "+to+" "+msg+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	public void playerReady(String user) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.READY)+" "+user+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	//@Override
	public void getTblStatus(String user, int tid) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.ASK_TBL_STATUS)+" "+user+" "+Integer.toString(tid)+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	@Override
	public void getProfile(String user, String profileFor) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.GET_PROFILE)+" "+user+" "+profileFor+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}
	}

	@Override
	public void login(String user, String pwd) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.LOGIN)+" "+user+" "+pwd+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	//@Override
	public void observeTable(String user, int tid) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.OBSERVE_TBL)+" "+user+" "+Integer.toString(tid)+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	@Override
	public void register(String user, String pwd) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.REGISTER)+" "+user+" "+pwd+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	//@Override
	public void stopObserving(String user, int tid) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.STOP_OBSERVING)+" "+user+" "+Integer.toString(tid)+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}

	@Override
	public void updateProfile(String user, String desc) {
		try{
			streamToServer.write((Integer.toString(TCPMsg.UPDATE_PROFILE)+" "+user+" "+desc+TCPMsg.endOfMsg).getBytes());
		}catch(IOException ex){
			communicator.outputToConsole(ex.getMessage());
		}

	}


}
