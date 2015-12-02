package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Networking.ServerCommunicator;
import UIPanes.BaseView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LobbyViewController extends BaseView implements Initializable, BaseViewController{

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
	Label userNameLbl;

	@FXML
	Label openTablesLbl;

	@FXML
	Label lobbyTitleLbl;

	@FXML
	Label inProgressLbl;
	
	@FXML
	ListView<Integer> joinListView;
	
	@FXML
	ListView<Integer> observeListView;
	
	ObservableList<Integer> tableList = FXCollections.observableArrayList();
	ObservableList<Integer> observerTableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Username: " + BaseView.network.getUsrName());
		userNameLbl.setText(BaseView.network.getUsrName());
	
		joinBtn.setOnAction(e -> ButtonClicked(e));
		hostBtn.setOnAction( e -> ButtonClicked(e) );
		refreshBtn.setOnAction(e -> ButtonClicked(e));
		spectateBtn.setOnAction(e -> ButtonClicked(e));

		refreshBtn.setStyle("-fx-background-color: #" + lobbyRefreshBtnColor + ";" +
						"-fx-text-fill: #" + lobbyRefreshBtnTextColor + ";"
		);
		hostBtn.setStyle("-fx-background-color: #" + lobbyHostBtnColor + ";" +
						"-fx-text-fill: #" + lobbyHostBtnTextColor + ";"
		);
		spectateBtn.setStyle("-fx-background-color: #" + lobbySpectateBtnColor + ";" +
						"-fx-text-fill: #" + lobbySpectateBtnTextColor + ";"
		);
		joinBtn.setStyle("-fx-background-color: #" + lobbyJoinBtnColor + ";" +
						"-fx-text-fill: #" + lobbyJoinBtnTextColor + ";"
		);
		userNameLbl.setStyle("-fx-text-fill: #" + lobbyUsernameColor);
		lobbyAnchorPane.setStyle("-fx-background-color: #" + lobbyBackgroundColor);
		openTablesLbl.setStyle("-fx-text-fill: #" + lobbyOpenTablesColor);
		inProgressLbl.setStyle("-fx-text-fill: #" + lobbyInProgressColor);
		lobbyTitleLbl.setStyle("-fx-text-fill: #" + lobbyTitleColor);



		
		Platform.runLater( new Runnable() {

			@Override
			public void run() {
				loadGames();
			}
		});
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		ServerCommunicator svrCom = BaseView.network.svrCommunicator;
		Object source = e.getSource();
		if ( source == hostBtn ) { 
			svrCom.makeTable();
			setIsNotSpectating();
			switchScene(CheckersBoardViewController.getViewInstance());	
		}
		else if( source == refreshBtn ) {
			System.out.println("Refresh");
			tableList.removeAll( tableList );
			observerTableList.removeAll( observerTableList );
			loadGames();
		}
		else if( source == joinBtn ){
			try{
				// -- We have selected a table, grab it's number
				String option = joinListView.getSelectionModel().getSelectedItem().toString();
				
				// -- Send the message to join the table.
				svrCom.joinTable( Integer.parseInt(option) );
				
				// -- DO SOMETHING HERE ****************************************
				setIsNotSpectating();
				switchScene(CheckersBoardViewController.getViewInstance());	
				
			}catch( Exception ex ){
				// -- we tried to click join without selecting anything.
			}
		}
		else if( source == spectateBtn ) {
			try{
				// -- We have selected a table, grab it's number
				String option = observeListView.getSelectionModel().getSelectedItem().toString();
				
				// -- Send the message to observe the table.
				svrCom.observeTable(userName, Integer.parseInt(option));
				
				setIsSpectating();
				
				// -- DO SOMETHING HERE ****************************************
				switchScene(CheckersBoardViewController.getViewInstance());
				
			}catch( Exception ex ){
				// -- we tried to click join without selecting anything.
			}
		}
	} 
	
	private void loadGames() {
	try{
		ServerCommunicator svrCom = BaseView.network.svrCommunicator;
		
		for( int i = 0; i < svrCom.getTables().length; i++ ){
			System.out.println( svrCom.getTables()[i] );
			tableList.add( svrCom.getTables()[i] );
		}
		
		for( int i = 0; i < svrCom.getTables().length; i++ ){
			System.out.println( svrCom.getTables()[i] );
			observerTableList.add( svrCom.getTables()[i] );
		}
		
		joinListView.setItems( tableList );
		observeListView.setItems( observerTableList );
		
		System.out.println( svrCom.getTables().length );
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
	
	private static Scene lobbyView = null;

	public static Scene getViewInstance() {

		if (LobbyViewController.lobbyView == null) {
			try {
				AnchorPane lobbyPane = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(LOBBY_VIEW_FXML));
				LobbyViewController.lobbyView = new Scene(lobbyPane);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return LobbyViewController.lobbyView;

	}
}

