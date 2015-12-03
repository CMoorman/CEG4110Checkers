package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Objects.ColorStyleHelper;
import Objects.TableListObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import UIPanes.BaseView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class SpectateViewController extends BaseView implements Initializable {

    @FXML
    Button returnToLobbyBtn;

    @FXML
    Button globalChatSendBtn;

    @FXML
    Pane player2Avatar;

    @FXML
    Pane player1Avatar;

    @FXML
    AnchorPane checkersAnchorPane;

    @FXML
    Pane spectatePane;


    public void initialize(URL location, ResourceBundle resources) {


        returnToLobbyBtn.setOnAction(e -> returnToLobbyBtnClicked(e));

        globalChatSendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateSendBtnColor) + ";" +
                        ColorStyleHelper.getTextFillStyle(spectateSendBtnTextColor) + ";"
        );
        returnToLobbyBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateReturnBtnColor) + ";" +
                        ColorStyleHelper.getTextFillStyle(spectateReturnBtnTextColor) + ";"
        );
        checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateBackground));

        player1Avatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePlayer1AvatarColor));

        player2Avatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePlayer2AvatarColor));

        spectatePane.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePaneColor));


    }

    private void returnToLobbyBtnClicked(ActionEvent e) {
        switchScene(LobbyViewController.getInstance().getScene());
    }

    public void updateUI(){
        globalChatSendBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateSendBtnColor) + ";" +
                        ColorStyleHelper.getTextFillStyle(spectateSendBtnTextColor) + ";"
        );
        returnToLobbyBtn.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateReturnBtnColor) + ";" +
                        ColorStyleHelper.getTextFillStyle(spectateReturnBtnTextColor) + ";"
        );
        checkersAnchorPane.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectateBackground));

        player1Avatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePlayer1AvatarColor));

        player2Avatar.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePlayer2AvatarColor));

        spectatePane.setStyle(ColorStyleHelper.getBackgroundColorStyle(spectatePaneColor));
    }



    private static Scene spectateScene = null;

    public Scene getScene() {
        return spectateScene;
    }
    private static SpectateViewController instance = null;

    public static SpectateViewController getInstance(){
    if (instance == null) {
        FXMLLoader loader = new FXMLLoader(BaseView.class.getResource(SPECTATE_VIEW_FXML));
        Parent mainPane = null;
        try {
            mainPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spectateScene = new Scene(mainPane);
        instance = loader.getController();
    }
    return instance;
}
}
