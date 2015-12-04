package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Networking.ServerCommunicator;
import Objects.ColorStyleHelper;
import Objects.DialogHelper;
import Objects.TableListObject;
import UIPanes.BaseView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LobbyViewController extends BaseView implements Initializable, BaseViewController {

	@FXML
	Pane lobbyPane;

	@FXML
	AnchorPane lobbyAnchorPane;

	@FXML
	Button refreshBtn;

	@FXML
	Button spectateBtn;

	@FXML
	Button joinBtn;

	@FXML
	Button hostBtn;

	@FXML
	Button disconnectBtn;

	@FXML
	Label userNameLbl;

	@FXML
	Label openTablesLbl;

	@FXML
	Label lobbyTitleLbl;

	@FXML
	Label inProgressLbl;

	@FXML
	ListView<String> joinListView;

	@FXML
	ListView<String> observeListView;
	
	boolean joinedTableFull = false;
	
	// -- Chat box and who's in lobby views
	@FXML
	ListView<String> messageBox;
	
	@FXML
	ListView<String> lobbyWhosInLobbyListView;
	
	@FXML
	TextField sendMessageBox;
	
	@FXML
	Button sendBtn;

	@FXML
	Label lobbyInfoLbl;

	@FXML
	Label lobbyWhosInLobbyLbl;
	
	// -- Lists to hold the table objects.
	ObservableList<String> tableList = FXCollections.observableArrayList();
	ObservableList<String> observerTableList = FXCollections.observableArrayList();

	ObservableList<String> messageList = FXCollections.observableArrayList();
	
	ObservableList<String> lobbyUserList = FXCollections.observableArrayList();
	
	private ArrayList<Integer> tableIdList = new ArrayList<>();
	public ArrayList<TableListObject> tableListObjects;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("Username: " + BaseView.network.getUsrName());
		userNameLbl.setText(BaseView.network.getUsrName());
		// TODO: make separate functions for each button to improve modularity
		// and maintainability
		joinBtn.setOnAction(e -> ButtonClicked(e));
		hostBtn.setOnAction(e -> ButtonClicked(e));
		refreshBtn.setOnAction(e -> ButtonClicked(e));
		spectateBtn.setOnAction(e -> ButtonClicked(e));
		disconnectBtn.setOnAction(e -> ButtonClicked(e));
		sendBtn.setOnAction( e -> sendBtnPressed(e) );

		refreshBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyRefreshBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyRefreshBtnTextColor) + ";");
		hostBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyHostBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyHostBtnTextColor) + ";");
		spectateBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbySpectateBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbySpectateBtnTextColor) + ";");
		joinBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyJoinBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyJoinBtnTextColor) + ";");
		disconnectBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyDisconnectBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyDisconnectBtnTextColor) + ";");
		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbySendMsgBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbySendMsgBtnTextColor) + ";");

		userNameLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyUsernameColor));
		lobbyAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyBackgroundColor));
		openTablesLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
		inProgressLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyInProgressColor));
		lobbyTitleLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyTitleColor));
		lobbyInfoLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
		lobbyWhosInLobbyLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				loadGames();
			}
		});
	}

	public void updateUI(){

		refreshBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyRefreshBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyRefreshBtnTextColor) + ";");
		hostBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyHostBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyHostBtnTextColor) + ";");
		spectateBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbySpectateBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbySpectateBtnTextColor) + ";");
		joinBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyJoinBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyJoinBtnTextColor) + ";");
		disconnectBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyDisconnectBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbyDisconnectBtnTextColor) + ";");
		sendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbySendMsgBtnColor) + ";"
				+ ColorStyleHelper.getTextFillStyle(lobbySendMsgBtnTextColor) + ";");


		userNameLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyUsernameColor));
		lobbyAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyBackgroundColor));
		openTablesLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
		inProgressLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyInProgressColor));
		lobbyTitleLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyTitleColor));
		lobbyInfoLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
		lobbyWhosInLobbyLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
	}
	
	public void addToTableList( TableListObject table ) {
		tableListObjects.add(table);
	}
	
	public ArrayList<TableListObject> getTableListObjects() {
		return tableListObjects;
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		joinedTableFull = false;
		ServerCommunicator svrCom = BaseView.network.svrCommunicator;
		Object source = e.getSource();
		if (source == hostBtn) {
			svrCom.makeTable();
			setIsNotSpectating();
			switchScene(CheckersBoardViewController.getInstance().getScene());
		} else if (source == refreshBtn) {
			System.out.println("Refresh");
			tableList.removeAll(tableList);
			observerTableList.removeAll(observerTableList);
			loadGames();
		} else if (source == joinBtn) {
			try {
				
				CheckersBoardViewController controller = CheckersBoardViewController.getInstance();
				// -- We have selected a table, grab it's number
				String option = joinListView.getSelectionModel().getSelectedItem().toString();

				// - grab the table id off of the string.
				String tableId = option.substring(6, 10);

				svrCom.joinTable(Integer.parseInt(tableId));
				Thread.sleep(3300);
				
				if(joinedTableFull == true){
					DialogHelper.showErrorDialog("Full Table", null,
							"Table is Full, Select Different Table or Spectate.");
				}else{
					setIsNotSpectating();
					
					switchScene(controller.getScene());
				}

			} catch (Exception ex) {
				// -- we tried to click join without selecting anything.
				ex.printStackTrace();
			}
		} else if (source == spectateBtn) {
			try {
				SpectateViewController controller = SpectateViewController.getInstance();

				// -- We have selected a table, grab it's number
				//String option = joinListView.getSelectionModel().getSelectedItem().toString();

				// - grab the table id off of the string.
				//String tableId = option.substring(6, 10);

				//svrCom.observeTable( userName, Integer.parseInt(tableId) );

				//setIsSpectating();

				// -- DO SOMETHING HERE ****************************************
				switchScene(controller.getScene());

			} catch (Exception ex) {
				// -- we tried to click join without selecting anything.
			}
		} else if (source == disconnectBtn) {
			svrCom.disconnect(false);
			switchScene(MainViewController.getInstance().getScene());
		}
	}

	private void loadGames() {
		try {
			if ( this.tableListObjects != null) {
				
				for (int i = 0; i < this.tableListObjects.size() - 1; i++) {
					TableListObject table = this.tableListObjects.get(i);
					tableList.add("Table " + table.getTableId() + ": "+ table.getRedPlayer() + "   VS   " + table.getBlackPlayer() );
				}

				joinListView.setItems(tableList);
				observeListView.setItems(tableList);

				System.out.println(getTableList().length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateGameList( TableListObject table ) {
		Platform.runLater( new Runnable() {
			@Override
			public void run() {
				if( !tableIdList.contains(table.getTableId() ) ) {
					tableList.add("Table " + table.getTableId() + ": "+ table.getRedPlayer() + "   VS   " + table.getBlackPlayer() );
					joinListView.setItems(tableList);
					observeListView.setItems(tableList);
					tableIdList.add( table.getTableId() );
				}
			}
		});
		this.tableListObjects.add(table);
	}
	
	public void addGameMessage(String user, String msg, boolean pm) {
		Platform.runLater( new Runnable() {
			@Override
			public void run() {
				String newMsg = msg;
				if( pm ){
					newMsg = "**PM FROM " + user + ": " + msg;
				}
				else {
					newMsg = user +": " + msg;
				}
				messageList.add(newMsg);
				
				updateChatBox();
			}
		});
	}
	
	public void addLobbyUser(String user) {
		Platform.runLater( new Runnable() {

			@Override
			public void run() {
				if( !lobbyUserList.contains(user) ) {
					lobbyUserList.add(user);
					updateLobbyBox();
				}	
			}
		});
	}
	
	public void removeLobbyUser(String user) {
		Platform.runLater( new Runnable() {

			@Override
			public void run() {
				if( lobbyUserList.contains(user) ) {
					lobbyUserList.remove(user);
					updateLobbyBox();
				}	
			}
		});
	}
	
	public void updateLobbyBox() {
		lobbyWhosInLobbyListView.setItems(lobbyUserList);
	}
	
	public void updateChatBox() {
		messageBox.setItems(messageList);
		if( messageList.size() > 0)
			messageBox.scrollTo( messageList.size() - 1 );
	}
	
	private void sendBtnPressed( ActionEvent e ) {
		SendMessage();
	}
	
	@FXML
	public void buttonPressed(KeyEvent e)
	{
	    if(e.getCode().toString().equals("ENTER"))
	    {
			SendMessage();
	    }
	} // -- End of buttonPressed.
	
	private void SendMessage() {
		String msg = "";
		String receiver = "";
		boolean isPM = false;
		
		try {
			msg = sendMessageBox.getText();
		}catch( Exception ex ) {
			// TODO: investigate removing this try/catch, was probably null pointer related
		};

		if( msg.charAt(0) == '@' ){
			receiver = msg.substring(0, msg.indexOf(' '));
			receiver = receiver.substring(receiver.indexOf('@') + 1);
			isPM = true;
			msg = msg.substring(msg.indexOf(' '));
		}
		
		if( msg.length() > 0 && isPM ) {
			network.svrCommunicator.sendMsg(receiver, msg);
			msg = "**PM TO " + receiver + ": " + msg;
			messageList.add(msg);
			updateChatBox();
			sendMessageBox.setText("");
		}else if( msg.length() > 0 ) {
			network.svrCommunicator.sendMsg_All(msg);
			// -- Clear out the text field.
			sendMessageBox.setText("");
		}
	}

	private static Scene lobbyScene = null;

	public Scene getScene() {
		return lobbyScene;
	}

	private static LobbyViewController instance = null;

	public static LobbyViewController getInstance() {
		if (instance == null) {
			FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(LOBBY_VIEW_FXML));
			Parent mainPane = null;
			try {
				mainPane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lobbyScene = new Scene(mainPane);
			instance = loader.getController();
		}
		return instance;
	}
}
