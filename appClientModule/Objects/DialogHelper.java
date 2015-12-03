package Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Window;

public class DialogHelper {
	private static Window owner = null;
	public static void initOwner(Window owner){
		DialogHelper.owner = owner;
	}
	/**
	 * Creates and shows an information dialog
	 * @param title: String title of the dialog
	 * @param header: String header text of the dialog, pass in null to disable header
	 * @param message: String message text to be displayed in content area of popup
	 */
	public static void showInfoDialog(String title, String header, String message){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		if(owner != null){
			alert.initOwner(owner);
			alert.initModality(Modality.WINDOW_MODAL);
		}
		alert.showAndWait();
	}
	/**
	 * Creates and shows a warning dialog
	 * @param title: String title of the dialog
	 * @param header: String header text of the dialog, pass in null to disable header
	 * @param message: String message text to be displayed in content area of popup
	 */
	public static void showWarningDialog(String title, String header, String message){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		if(owner != null){
			alert.initOwner(owner);
			alert.initModality(Modality.WINDOW_MODAL);
		}
		alert.showAndWait();
	}
	/**
	 * Creates and shows an Error dialog
	 * @param title: String title of the dialog
	 * @param header: String header text of the dialog, pass in null to disable header
	 * @param message: String message text to be displayed in content area of popup
	 */
	public static void showErrorDialog(String title, String header, String message){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		if(owner != null){
			alert.initOwner(owner);
			alert.initModality(Modality.WINDOW_MODAL);
		}
		alert.showAndWait();
	}
}
