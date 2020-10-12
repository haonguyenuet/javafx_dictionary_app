package DictionaryApplication.Alerts;

import javafx.scene.control.Alert;

public class Alerts {
	public void showAlertInfo(String title, String content){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public void showAlertWarning(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public Alert alertConfirmation(String title, String content){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		return alert;
	}
	public Alert alertWarning(String title, String content){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		return alert;
	}
}
