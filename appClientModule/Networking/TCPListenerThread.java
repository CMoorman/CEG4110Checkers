package Networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Interfaces.CheckersClient;
import Interfaces.TCPMsg;


/**
 * This class represents a thread that listens for TCP messages from the game
 * server. It decodes messages received by the client and the invokes an appropriate
 * method on the CheckesClient.
 * @author derek 8.15.08
 */
public class TCPListenerThread extends Thread {

	private ServerCommunicator communicator;
	private CheckersClient client;
	private DataInputStream fromServerStream;
	public boolean active;

	public TCPListenerThread(ServerCommunicator c, CheckersClient cl, DataInputStream serverStream){
		super("TCP Message Listener");
		communicator = c;
		client = cl;
		fromServerStream = serverStream;
		start();	//start the thread when the object is created.
		active = true;
	}

	//The run method is the method that is executed in a thread when start()
	// is invoked.
	public void run(){
		while(active){
			if(!listenForMessages()) //if the connection was severed, terminate the thread.
				break;
		}
	}

	public boolean listenForMessages(){
		try{
			byte[] data = new byte[1024];
			//the read method blocks until a message from the server is received.
			int bytesRead = fromServerStream.read(data);
			String s ="";
			// read a line of data from the stream
			for(int i=0; i<bytesRead;i++)
				s = s+ (char)data[i];
			String[] msgs;
			if(!s.equals("")){
				//this is in case we read multiple messages from the server.
				//this is possible if the server sends messages at a rate
				// faster than the client can process them.
				msgs = s.split(TCPMsg.endOfMsg);
				for(String msg:msgs){
					if(!msg.equals("ping")){
						communicator.outputToConsole("Incoming TCP Msg: "+msg+"\r\n");
						processMessage(msg);
					}else{
						communicator.outputToConsole("got ping");
					}
				}
			}
			return true;
		}
		catch(IOException ex){
			//this means the stream was cut off. time to disconnect.
			return false;
		}
	}

	public void processMessage(String s){
		try{
			if(!s.startsWith("Send")){	//if server is not asking for username,
				String text ="";
				//separate out the tcp code with its parameters into entries of the data array
				String[] data = s.split("\\s");
				int code;
				try{
					code = Integer.parseInt(data[0]);
				}catch(Exception e){
					code = -1;
				}
				 /* TCP messages to clients */
				switch(code){
					case TCPMsg.CONN_OK: 	client.connectionOK();
						break;
					case TCPMsg.IN_LOBBY:	client.youInLobby();
						break;
					case TCPMsg.OUT_LOBBY: 	client.youLeftLobby();
						break;
					case TCPMsg.MSG:
						for(int i=3; i<data.length; i++)
							text += data[i]+" ";
						client.newMsg(data[1], text, data[2].equals("1"));
						break;
					case TCPMsg.WHO_IN_LOBBY:
						String[] users = new String[0];
						if(data.length > 1){
							users = new String[data.length-1];
							for(int i=0; i<data.length-1; i++){
								users[i] = data[i+1];
							}
						}
						client.usersInLobby(users);
						break;
					case TCPMsg.NOW_IN_LOBBY: client.nowJoinedLobby(data[1]);
						break;
					case TCPMsg.NOW_LEFT_LOBBY:	client.nowLeftLobby(data[1]);
						break;
					/**
					 * Game playing.
					 */
					case TCPMsg.YOUR_TURN: client.yourTurn();
					break;
					case TCPMsg.NEW_TBL:	client.newTable(Integer.parseInt(data[1]));
					break;
					case TCPMsg.GAME_START:	client.gameStart();
					break;
					case TCPMsg.COLOR_BLACK: client.colorBlack();
					break;
					case TCPMsg.COLOR_RED:	client.colorRed();
					break;
					case TCPMsg.OPP_MOVE:
						int fr = Integer.parseInt(data[1].substring(0,1));
						int fc = Integer.parseInt(data[1].substring(2,3));
						int tr = Integer.parseInt(data[2].substring(0,1));
						int tc = Integer.parseInt(data[2].substring(2,3));
					client.oppMove(fr,fc,tr,tc);
					break;
					case TCPMsg.BOARD_STATE:
						int tid = Integer.parseInt(data[1]);
						String state = data[2]; //state of (i,j) is at index (8*i)+j of string
						byte[][] boardState = new byte[8][8];
						for(int i=0; i<8; i++)
							for(int j=0; j<8; j++)
								boardState[i][j] = Byte.parseByte(String.valueOf(state.charAt((8*i)+j)));
						client.curBoardState(tid, boardState);
					break;
					case TCPMsg.GAME_WIN:	client.youWin();
					break;
					case TCPMsg.GAME_LOSE: 	client.youLose();
					break;
					case TCPMsg.TBL_JOINED:	client.joinedTable(Integer.parseInt(data[1]));
					break;
					case TCPMsg.OPP_LEFT_TABLE: client.oppLeftTable();
					break;
					case TCPMsg.WHO_ON_TBL:
					client.onTable(Integer.parseInt(data[1]), data[2], data[3]);
					break;
					case TCPMsg.TBL_LIST:
						int[] tids = new int[0];
						if(data.length > 1){
							ArrayList<Integer> ids = new ArrayList<Integer>();
							for(int i=1; i<data.length; i++)
								ids.add(Integer.parseInt(data[i]));
							//sloppy way
							Integer[] temp = new Integer[ids.size()];
							ids.toArray(temp);
							tids = new int[ids.size()];
							int k=0;
							for(Integer x:temp)
								tids[k++] = x.intValue();
						}
						client.tableList(tids);
					break;
					case TCPMsg.TBL_LEFT:	client.alertLeftTable();
					break;
					case TCPMsg.NOW_OBSERVING: client.nowObserving(Integer.parseInt(data[1]));
					break;
					case TCPMsg.STOPPED_OBSERVING: client.stoppedObserving(Integer.parseInt(data[1]));
					break;

					/**
					 *  Error Messages
					 * */
					case TCPMsg.NET_EXCEPTION: client.networkException(data[1]);
					break;
					case TCPMsg.NAME_IN_USE:
						client.nameInUseError();
						communicator.disconnect(false);
					break;
					case TCPMsg.BAD_NAME:
						client.nameIllegal();
						communicator.disconnect(false);
					break;
					case TCPMsg.ILLEGAL:	client.illegalMove();
					break;
					case TCPMsg.TBL_FULL:	client.tableFull();
					break;
					case TCPMsg.TBL_NOT_EXIST: client.tblNotExists();
					break;
					case TCPMsg.GAME_NOT_CREATED: client.gameNotCreatedYet();
					break;
					case TCPMsg.NOT_YOUR_TURN: client.notYourTurn();
					break;
					case TCPMsg.NOT_IN_LOBBY: client.notInLobby();
					break;
					case TCPMsg.BAD_MESSAGE: client.badMessage();
					break;
					case TCPMsg.ERR_IN_LOBBY: client.errorInLobby();
					break;
					case TCPMsg.OPP_NOT_READY: client.oppNotReady();
					break;
					case TCPMsg.NOT_OBSERVING: client.notObserving();
					break;

					/**disabled messages. **/
					/*
					case TCPMsg.REGISTER_OK: client.registerOK();
					break;
					case TCPMsg.LOGIN_OK: client.loginOk();
					break;
					case TCPMsg.PROFILE_UPDATED: client.profileUpdated();
					break;
					case TCPMsg.USER_PROFILE:
						for(int i=4; i<data.length; i++)
							text += data[i]+" ";
						client.userProfile(data[1], text, data[2], data[3]);
					break;
					case TCPMsg.LOGIN_FAIL: client.loginFailed();
					break;
					*/
					case -1:	//server sent us a blank message.
						communicator.disconnect(false);
				}
			}
		}
		catch(Exception ex){
			communicator.outputToConsole("Invalid msg from server: "+ex.getMessage());
		}
	}
}
