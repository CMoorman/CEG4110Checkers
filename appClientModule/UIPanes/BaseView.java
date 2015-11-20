package UIPanes;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class BaseView extends Application implements Runnable{
	
	public Stage currentStage;
	
	public AnchorPane currentView;
	
	Thread gameThread;
	
	@FXML
	Button settingsBtn;
	Button lobbyBtn;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.currentStage = primaryStage;
		
		try {
			setCurrentView((AnchorPane) FXMLLoader.load(BaseView.class.getResource("MainView.fxml")));
			Scene scene = new Scene( getCurrentView() );
			currentStage.setScene( scene );
			currentStage.show();
			
			// -- Make sure that we stop our threads when we leave.
			currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		          Platform.exit();
		          System.exit(0);
		       }
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ButtonClicked(ActionEvent e) { }
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {
		if( gameThread == null){
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	public Stage getCurrentStage() {
		return currentStage;
	}
	public AnchorPane getCurrentView() {
		return currentView;
	}

	public void setCurrentView(AnchorPane currentView) {
		this.currentView = currentView;
	}

	public void ButtonClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
