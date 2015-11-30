package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;

public class CheckersBoardViewController extends BaseView implements Initializable, BaseViewController {

	@FXML
	Label yourUserNameLbl;
	
	@FXML
	Button sendBtn;
	
	@FXML
	Button concedeBtn;
	
	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource() == concedeBtn ){
			network.svrCommunicator.leaveTable();
			switchScene(LobbyViewController.getViewInstance());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText( BaseView.network.getUsrName() );
		
		sendBtn.setOnAction( e -> ButtonClicked(e) );
		concedeBtn.setOnAction( e -> ButtonClicked(e) );
	}
	private static AnchorPane boardView = null;

	public static AnchorPane getViewInstance() {
		if (CheckersBoardViewController.boardView == null) {
			try {
				CheckersBoardViewController.boardView = (AnchorPane) FXMLLoader
						.load(BaseView.class.getResource(BOARD_VIEW_FXML));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return CheckersBoardViewController.boardView;
	}
}
