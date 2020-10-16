package DictionaryApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {

		// set on click
		searchWordBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				showComponent("/Views/SearcherGui.fxml");
			}
		});
		addWordBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				showComponent("/Views/AdditionGui.fxml");
			}
		});
		translateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				showComponent("/Views/TranslationGui.fxml");
			}
		});
		// initial state
		tooltip1.setShowDelay(Duration.seconds(0.5));
		tooltip2.setShowDelay(Duration.seconds(0.5));
		tooltip3.setShowDelay(Duration.seconds(0.5));
		showComponent("/Views/SearcherGui.fxml");
	}

	private void setNode( Node node ) {
		container.getChildren().clear();
		container.getChildren().add((Node) node);

	}
	@FXML
	private void showComponent( String path ) {
		try {
			AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
			setNode(Component);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Tooltip tooltip1, tooltip2, tooltip3;

	@FXML
	private Button addWordBtn, translateBtn, searchWordBtn;

	@FXML
	private AnchorPane container;

}
