package DictionaryApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController implements Initializable {

	private String sourceLanguage = "en";
	private String toLanguage = "vi";
	private boolean isToVietnameseLang = true;

	@Override
	public void initialize( URL url , ResourceBundle resourceBundle ) {
		translateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				try {
					handleOnClickTranslateBtn();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// when user click on english text field
		sourceLangField.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent keyEvent ) {
				if(sourceLangField.getText().trim().isEmpty()){
					translateBtn.setDisable(true);
				}else {
					translateBtn.setDisable(false);
				}
			}
		});
		//initial state
		translateBtn.setDisable(true);
		toLangField.setEditable(false);
		// click close button
		closeButton.setOnMouseClicked(e -> {
			System.exit(0);
		});

	}

	@FXML
	private void handleOnClickTranslateBtn() throws IOException {
		String rootAPI = "https://clients5.google.com/translate_a/t?client=dict-chrome-ex&"
				+ "sl=" + sourceLanguage
				+ "&tl="+ toLanguage
				+"&dt=t&q=";
		String srcText = sourceLangField.getText();
		String urlString = rootAPI + srcText;
		urlString = urlString.replace(" ", "%20");

		// initialize connection
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		// set get request
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String line;
		StringBuffer content = new StringBuffer();
		while ((line = in.readLine()) != null) {
			content.append(line);
		}
		// disconnect
		in.close();
		con.disconnect();

		// handle json data
		JSONParser jsonParse = new JSONParser();
		try {
			JSONObject data = (JSONObject) jsonParse.parse(content.toString());
			JSONArray sentences = (JSONArray) data.get("sentences");
			JSONObject jsonObject = (JSONObject) sentences.get(0);
			String trans = (String) jsonObject.get("trans");
			toLangField.setText(trans);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@FXML
	private void handleOnClickSwitchToggle(){
		sourceLangField.clear();
		toLangField.clear();
		if(isToVietnameseLang){
			englishLabel.setLayoutX(365);
			vietnameseLabel.setLayoutX(95);
			sourceLanguage = "vi";
			toLanguage = "en";
		}else {
			englishLabel.setLayoutX(92);
			vietnameseLabel.setLayoutX(365);
			sourceLanguage = "en";
			toLanguage = "vi";
		}
		isToVietnameseLang = !isToVietnameseLang;
	}

	@FXML
	private ImageView closeButton;

	@FXML
	private TextArea sourceLangField, toLangField;

	@FXML
	private Button translateBtn, switchToggle;

	@FXML
	private Label englishLabel , vietnameseLabel;
}