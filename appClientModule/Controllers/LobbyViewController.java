package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Networking.CheckersClient;
import Networking.ServerCommunicator;
import UIPanes.BaseView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class LobbyViewController extends BaseView implements Initializable, BaseViewController{

	@FXML
	Pane lobbyPane;
	
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
	ListView joinListView;
	
	@FXML
	ListView observeListView;
	
	ObservableList<Integer> tableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println( "Username: " + BaseView.network.getUsrName() );
		userNameLbl.setText( BaseView.network.getUsrName() );
		
		Platform.runLater( new Runnable() {

			@Override
			public void run() {
				loadGames();
			}
		});
		
		joinBtn.setOnAction( e -> ButtonClicked(e) );
		hostBtn.setOnAction( e -> ButtonClicked(e) );
		refreshBtn.setOnAction( e -> ButtonClicked(e) );
		spectateBtn.setOnAction( e -> ButtonClicked(e) );
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		ServerCommunicator svrCom = BaseView.network.svrCommunicator;
		
		if ( e.getSource() == hostBtn ) { 
			svrCom.makeTable();
		}
		else if( e.getSource() == refreshBtn ) {
			System.out.println("Refresh");
			tableList.removeAll(tableList);
			loadGames();
		}
		else if( e.getSource() == joinBtn ){
			try{
				// -- We have selected a table, grab it's number
				String option = joinListView.getSelectionModel().getSelectedItem().toString();
				
				// -- Send the message to join the table.
				svrCom.joinTable( Integer.parseInt(option) );
				
				// -- DO SOMETHING HERE ****************************************
				
			}catch( Exception ex ){
				// -- we tried to click join without selecting anything.
			}
		}
		else if( e.getSource() == spectateBtn ) {
			try{
				// -- We have selected a table, grab it's number
				String option = observeListView.getSelectionModel().getSelectedItem().toString();
				
				// -- Send the message to observe the table.
				svrCom.observeTable(userName, Integer.parseInt(option));
				
				// -- DO SOMETHING HERE ****************************************
				
			}catch( Exception ex ){
				// -- we tried to click join without selecting anything.
			}
		}
	} 
	
	private void loadGames() {
	
		ServerCommunicator svrCom = BaseView.network.svrCommunicator;
				
		for( int i = 0; i < svrCom.getTables().length; i++ ){
			System.out.println( svrCom.getTables()[i] );
			tableList.add( svrCom.getTables()[i] );
		}
		
		joinListView.setItems(tableList);
		observeListView.setItems(tableList);
		
		System.out.println( svrCom.getTables().length );
	}
}

