package DictionaryApplication.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {
		closeButton.setOnMouseClicked(e ->{
			System.exit(0);
		});
	}
	@FXML
	private ImageView closeButton;
}