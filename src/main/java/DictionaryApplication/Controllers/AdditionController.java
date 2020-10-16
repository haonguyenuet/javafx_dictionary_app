package DictionaryApplication.Controllers;


import DictionaryApplication.Alerts.Alerts;
import DictionaryApplication.DictionaryCommandLine.Dictionary;
import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import DictionaryApplication.DictionaryCommandLine.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdditionController implements Initializable {
	private Dictionary dictionary = new Dictionary();
	private DictionaryManagement dictionaryManagement = new DictionaryManagement();
	private final String path = "src/main/resources/Utils/data.txt";
	// Alerts
	private Alerts alerts = new Alerts();

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
		dictionaryManagement.insertFromFile(dictionary , path);
		int indexOfWord = dictionaryManagement.searchWord(dictionary, wordTargetInput.getText());
		if(indexOfWord == -1 ) {
			Alert alertConfirmation = alerts.alertConfirmation("Add word",
					"Bạn chắc chắn muốn thêm từ này?");
			Optional<ButtonType> option = alertConfirmation.showAndWait();

			if (option.get() == ButtonType.OK) {
				Word word = new Word(wordTargetInput.getText(), explanationInput.getText());
				dictionaryManagement.addWord(word, path);
				// successfully
				alerts.showAlertInfo("Information", "Thêm thành công!");
				// reset input
				resetInput();
			} else if (option.get() == ButtonType.CANCEL) {
				alerts.showAlertInfo("Information", "Thay đổi không được công nhận.");
				resetInput();
			}
		}
		else{
			String explanationInputText = explanationInput.getText() + "\n";
			String meaing = dictionary.get(indexOfWord).getWordExplain() + "\n" + explanationInputText ;

			Alert alertConfirmation = alerts.alertConfirmation("Add word", "Từ này đã có trong từ điển, " +
					"OK để bổ sung nghĩa CANCEL để thay thế toàn bộ nghĩa cũ");
			Optional<ButtonType> option = alertConfirmation.showAndWait();

			if (option.get() == ButtonType.OK) {
				dictionaryManagement.updateWord(dictionary,indexOfWord,meaing,path);
				alerts.showAlertInfo("Information", "Thêm thành công!");
				//reset input
				resetInput();
			}
			// THay doi toan bo nghia cua tu cu = nghia moi nhap
			else if (option.get() == ButtonType.CANCEL) {
				dictionaryManagement.updateWord(dictionary,indexOfWord,explanationInputText,path);
				alerts.showAlertInfo("Information", "Thay đổi thành công!");
				 //reset input
				resetInput();
			}
		}
	}

	private void resetInput() {
		wordTargetInput.setText("");
		explanationInput.setText("");
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