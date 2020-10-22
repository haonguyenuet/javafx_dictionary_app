package DictionaryApplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	private double xOffset = 0;
	private double yOffset = 0;

	public static void main( String[] args ) {
		launch(args);
	}

	@Override
	public void start( final Stage stage ) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Views/DictionaryGui.fxml"));
		stage.setTitle("Dictionary app");
		stage.initStyle(StageStyle.TRANSPARENT);
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent event ) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent event ) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});

		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
}
