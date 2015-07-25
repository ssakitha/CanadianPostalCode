package com.sakisolutions.canadianpostalcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
/**
 * This is a simple postal code validation activity class where when user types postal code on the edit text field it validate
 * the postal code and only allows valid postal code to be entered.
 * @author Sakitha Sathis
 *
 */
public class MainActivity extends Activity {
	EditText postalCode;
	String postalCodeRegEx = "^[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}\\d{1}[ABCDEFGHJKLMNPQRSTUVWXYZ]{1}\\d{1}$";
	static final int MAX_LENGTH = 6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		postalCode = (EditText) findViewById(R.id.postalCode);
		
		postalCode.setInputType(InputType.TYPE_CLASS_TEXT);
		postalCode.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				int length = postalCode.getText().length();
				if(length ==1 || length ==3 || length == 5){
					postalCode.setInputType(InputType.TYPE_CLASS_NUMBER);
				}else{
					postalCode.setInputType(InputType.TYPE_CLASS_TEXT);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String input = postalCode.getText().toString();
				int length = input.length();
				if (input != null && length <= MAX_LENGTH) {
					if(!isMatch(input)){
						if (length > 0) {
						postalCode.setText(input.substring(0,input.length()-1));
						postalCode.setSelection(postalCode.getText().length());
					}
						Log.v("match: " , input);
						
					}else{
						
					}
				}
			}
		});
	}
	/**
	 * 
	 * @param input Postal code string to match
	 * @return Return true if the input string is matching so far or full match is achieved 
	 *         otherwise return false
	 */
	protected boolean isMatch(String input) {
		input = input.toUpperCase();//This warning is due to language preferences
		Pattern pattern = Pattern.compile(postalCodeRegEx);
		Matcher matcher = pattern.matcher(input);
			//Full postal code match
			if (matcher.matches()) {
				return true;
			}
			return matcher.hitEnd();//return true if partial match while user typing

	}
}
