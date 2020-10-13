package DictionaryApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {
		// initial state
		tooltip1.setShowDelay(Duration.seconds(0.5));
		tooltip2.setShowDelay(Duration.seconds(0.5));
		tooltip3.setShowDelay(Duration.seconds(0.5));
		// close application
		showSearchWordComponent();
	}

	private void setNode( Node node ) {
		container.getChildren().clear();
		container.getChildren().add((Node) node);

	}
	@FXML
	private void showSearchWordComponent() {
		try {
			AnchorPane Component = FXMLLoader.load(getClass().getResource("/Views/SearcherGui.fxml"));
			setNode(Component);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddNewWordComponent() {
		try {
			AnchorPane Component = FXMLLoader.load(getClass().getResource("/Views/AdditionGui.fxml"));
			setNode(Component);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showTranslationComponent() {
		try {
			AnchorPane Component = FXMLLoader.load(getClass().getResource("/Views/TranslationGui.fxml"));
			setNode(Component);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void handleOnClickHelpButton(ActionEvent event) {
		try {
			AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/Views/HelpGui.fxml"));
			setNode(anchorPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private AnchorPane container;
	@FXML
	private Tooltip tooltip1, tooltip2, tooltip3;
}
