package DictionaryApplication.Controllers;

import DictionaryApplication.DictionaryCommandLine.Dictionary;
import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearcherController implements Initializable {
	Dictionary dictionary = new Dictionary();
	DictionaryManagement dictionaryManagement = new DictionaryManagement();
	private int indexOfSelectedWord;
	private final String path = "src/main/resources/Utils/data.txt";

	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {
		dictionaryManagement.insertFromFile(dictionary , path);
		// random initial word list and definition are displayed
		int index = (int) (Math.random() * (dictionary.size()));
		for (int i = index; i < index + 15; i++) {
			listResults.getItems().add(dictionary.get(i).getWordTarget());
		}
		englishWord.setText(dictionary.get(index).getWordTarget());
		explanation.setText(dictionary.get(index).getWordExplain());
		// initial state
		explanation.setEditable(false);
		saveBtn.setVisible(false);
		searchBtn.setDisable(true);
		// when user types in search field
		searchTerm.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent keyEvent ) {
				if (searchTerm.getText().isEmpty()) {
					searchBtn.setDisable(true);
				} else {
					searchBtn.setDisable(false);
				}
			}
		});
		headerList.setText("15 Từ ngẫu nhiên");
		// close app
		closeBtn.setOnMouseClicked(e -> {
			System.exit(0);
		});
	}

	// click search button
	@FXML
	private void handleClickSearchBtn() {
		ObservableList<String> list = FXCollections.observableArrayList();
		String searchKey = searchTerm.getText().trim();
		int limit = 0;
		for (int i = 0; i < dictionary.size() && limit < 15; i++) {
			String englishWord = dictionary.get(i).getWordTarget();
			if (englishWord.toLowerCase().startsWith(searchKey.toLowerCase())) {
				list.add(englishWord);
				++limit;
			}
		}
		if (list.isEmpty()) {
			showAlertNotFound();
		} else {
			headerList.setText("Kết quả liên quan");
			listResults.setItems(list);
		}
		//reset input
		searchTerm.clear();
	}

	// click a word in word list is found
	@FXML
	private void handleMouseClickAWord( MouseEvent arg0 ) {
		// search binary
		String selectedWord = listResults.getSelectionModel().getSelectedItem();
		indexOfSelectedWord = dictionaryManagement.searchWord(dictionary , selectedWord);
		if (indexOfSelectedWord == -1) {
			return;
		}
		englishWord.setText(dictionary.get(indexOfSelectedWord).getWordTarget());
		explanation.setText(dictionary.get(indexOfSelectedWord).getWordExplain());

		explanation.setEditable(false);
		saveBtn.setVisible(false);
	}

	// click edit button
	@FXML
	private void handleClickEditBtn() {
		explanation.setEditable(true);
		saveBtn.setVisible(true);
		showAlertInfo("Bạn đã cho phép chỉnh sửa nghĩa từ này!");
	}

	@FXML
	private void handleClickSaveBtn() {
		showAlertConfirmation();
	}

	@FXML
	private void handleClickDeleteBtn() {
		showAlertWarning();
	}


	// Alerts
	private void showAlertNotFound() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Not found");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("Rất tiếc. Từ điển hiện tại không cung cấp từ này!");

		alert.showAndWait();
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
		alert.setTitle("Update word");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("Bạn chắc chắn muốn cập nhật từ này?");
		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK) {
			dictionaryManagement.updateWord(dictionary , indexOfSelectedWord , explanation.getText() , path);
			showAlertInfo("Cập nhập thành công");
		} else if (option.get() == ButtonType.CANCEL) {
			showAlertInfo("Thay đổi không được công nhận");
		}
	}

	private void showAlertWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Delete word");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("Bạn chắc chắn muốn xóa từ này?");

		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.OK , ButtonType.CANCEL);

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK) {
			dictionaryManagement.deleteWord(dictionary , indexOfSelectedWord , path);
			showAlertInfo("Xóa thành công");
		} else if (option.get() == ButtonType.CANCEL) {
			showAlertInfo("Thay đổi không được công nhận");
		}
	}


	// FXML elements
	@FXML
	private TextField searchTerm;

	@FXML
	private Button searchBtn, saveBtn;

	@FXML
	private ImageView closeBtn;

	@FXML
	private Label englishWord;

	@FXML
	private TextArea explanation;

	@FXML
	private Label headerList;

	@FXML
	private ListView<String> listResults;
}