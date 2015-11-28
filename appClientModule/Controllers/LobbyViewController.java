package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import UIPanes.BaseView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Label userNameLbl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println( "Username: " + BaseView.network.getUsrName() );
		userNameLbl.setText( BaseView.network.getUsrName() );
		
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
