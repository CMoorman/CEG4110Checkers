package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Networking.ServerCommunicator;
import Objects.ColorStyleHelper;
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
	ListView<Integer> joinListView;

	@FXML
	ListView<Integer> observeListView;

	ObservableList<Integer> tableList = FXCollections.observableArrayList();
	ObservableList<Integer> observerTableList = FXCollections.observableArrayList();

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

		userNameLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyUsernameColor));
		lobbyAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(lobbyBackgroundColor));
		openTablesLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyOpenTablesColor));
		inProgressLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyInProgressColor));
		lobbyTitleLbl.setStyle(ColorStyleHelper.getTextFillStyle(lobbyTitleColor));

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				loadGames();
			}
		});
	}

	public void updateUI(){
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
		disconnectBtn.setStyle("-fx-background-color: #" + lobbyDisconnectBtnColor + ";" +
						"-fx-text-fill: #" + lobbyDisconnectBtnTextColor + ";"
		);


		userNameLbl.setStyle("-fx-text-fill: #" + lobbyUsernameColor);
		lobbyAnchorPane.setStyle("-fx-background-color: #" + lobbyBackgroundColor);
		openTablesLbl.setStyle("-fx-text-fill: #" + lobbyOpenTablesColor);
		inProgressLbl.setStyle("-fx-text-fill: #" + lobbyInProgressColor);
		lobbyTitleLbl.setStyle("-fx-text-fill: #" + lobbyTitleColor);
	}

	@Override
	public void ButtonClicked(ActionEvent e) {
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
				// -- We have selected a table, grab it's number
				String option = joinListView.getSelectionModel().getSelectedItem().toString();

				// -- Send the message to join the table.
				svrCom.joinTable(Integer.parseInt(option));

				// -- DO SOMETHING HERE ****************************************
				setIsNotSpectating();
				switchScene(CheckersBoardViewController.getInstance().getScene());

			} catch (Exception ex) {
				// -- we tried to click join without selecting anything.
			}
		} else if (source == spectateBtn) {
			try {
				// -- We have selected a table, grab it's number
				String option = observeListView.getSelectionModel().getSelectedItem().toString();

				// -- Send the message to observe the table.
				svrCom.observeTable(userName, Integer.parseInt(option));

				setIsSpectating();

				// -- DO SOMETHING HERE ****************************************
				switchScene(CheckersBoardViewController.getInstance().getScene());

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
			if (getTableList() != null) {
				for (int i = 0; i < getTableList().length; i++) {
					System.out.println(getTableList()[i]);
					tableList.add(getTableList()[i]);
				}

				for (int i = 0; i < getTableList().length; i++) {
					System.out.println(getTableList()[i]);
					observerTableList.add(getTableList()[i]);
				}

				joinListView.setItems(tableList);
				observeListView.setItems(observerTableList);

				System.out.println(getTableList().length);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
