package DictionaryApplication.Controllers;


import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import DictionaryApplication.DictionaryCommandLine.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdditionController implements Initializable {
	DictionaryManagement dictionaryManagement = new DictionaryManagement();
	private final String path = "src/main/resources/Utils/data.txt";

	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {

		boolean isEmptyInput = explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty();
		//initial state
		if (isEmptyInput) {
			addBtn.setDisable(true);
		}
		wordTargetInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent keyEvent ) {
				if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) {
					addBtn.setDisable(true);
				} else {
					addBtn.setDisable(false);
				}
			}
		});
		explanationInput.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent keyEvent ) {
				if (explanationInput.getText().isEmpty() || wordTargetInput.getText().isEmpty()) {
					addBtn.setDisable(true);
				} else {
					addBtn.setDisable(false);
				}
			}
		});


		closeButton.setOnMouseClicked(e -> {
			System.exit(0);
		});

	}

	@FXML
	private void handleClickAddBtn() {
		showAlertConfirmation();
	}

	private void showAlertInfo( String content ) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	private void showAlertConfirmation() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Add word");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("Bạn chắc chắn muốn thêm từ này?");
		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK) {
			Word word = new Word(wordTargetInput.getText(), explanationInput.getText());
			dictionaryManagement.addWord(word, path);
			showAlertInfo("Thêm thành công!");
			wordTargetInput.setText("");
			explanationInput.setText("");
		} else if (option.get() == ButtonType.CANCEL) {
			showAlertInfo("Thay đổi không được công nhận.");
			wordTargetInput.setText("");
			explanationInput.setText("");
		}
	}

	@FXML
	private ImageView closeButton;
	@FXML
	private Button addBtn;
	@FXML
	private TextField wordTargetInput;
	@FXML
	private TextArea explanationInput;
}