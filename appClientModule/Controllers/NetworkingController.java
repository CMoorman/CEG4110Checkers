package Controllers;

import Networking.CheckersClient;
import Networking.ServerCommunicator;

public class NetworkingController {

	public String serverIP = "127.0.0.1";
	public ServerCommunicator svrCommunicator;
	public CheckersClient gameClient;
	
	public boolean isCurrentlyConnected;
	
	public NetworkingController() {
		isCurrentlyConnected = false;
		svrCommunicator = new ServerCommunicator( gameClient );
	}
	
	public boolean connectToServer(String ip, String userName ){
		System.out.println("Connecting to server!");
		isCurrentlyConnected = svrCommunicator.connectToServer(ip, userName);
		return isCurrentlyConnected;
	}
}
