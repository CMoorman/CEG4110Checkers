package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import UIPanes.BaseView;

public class CheckersBoardViewController extends BaseView implements Initializable, BaseViewController {

	@FXML
	Label yourUserNameLbl;
	
	@Override
	public void ButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		yourUserNameLbl.setText( BaseView.network.getUsrName() );
		
		
	}
}
