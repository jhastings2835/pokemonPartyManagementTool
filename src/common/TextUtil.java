package common;

import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;

public class TextUtil {

	public static void setTextAreaRestrictions(TextArea textArea) {

		Pattern notNumberPattern = Pattern.compile("[^0-9]+");
		TextFormatter<String> lowerFormatter = new TextFormatter<>(change -> {
			String newStr = notNumberPattern.matcher(change.getText()).replaceAll("");
			int diffcount = change.getText().length() - newStr.length();
			change.setAnchor(change.getAnchor() - diffcount);
			change.setCaretPosition(change.getCaretPosition() - diffcount);
			change.setText(newStr);

			if (change.getText().length() > Constants.EFFORT_VALUE_MAX_LENGTH) {
				change.setText(change.getText().substring(0, Constants.EFFORT_VALUE_MAX_LENGTH));
			}

			return change;
		});
		textArea.setTextFormatter(lowerFormatter);
		textArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue.length() > Constants.EFFORT_VALUE_MAX_LENGTH) {
					textArea.setText(newValue.substring(0, Constants.EFFORT_VALUE_MAX_LENGTH));
				}
			}
		});

	}

}
