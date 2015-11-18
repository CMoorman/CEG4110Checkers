package UIPanes;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@FXML
	ListView FUCK;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("MainView.fxml"));
			Scene scene = new Scene( page );
			primaryStage.setScene( scene );
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
