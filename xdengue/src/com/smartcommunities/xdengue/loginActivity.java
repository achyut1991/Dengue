package com.smartcommunities.xdengue;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.smartcommunities.xdengue.net.LoginTask;

public class loginActivity extends Activity {

	private EditText emailId, password;
	private static final int size = 2;
	private boolean formValidationbits[];

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static boolean isValidEmail(String emailId) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		formValidationbits = new boolean[size];
		emailId = (EditText) findViewById(R.id.uname);
		password = (EditText) findViewById(R.id.pass);

		final Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setEnabled(false);

		emailId.addTextChangedListener(new TextWatcher() {
			boolean emailValid = false;

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (isValidEmail(s.toString())) {
					if (!emailValid) {
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
						emailValid = true;
						formValidationbits[0] = emailValid;
						if (!loginButton.isEnabled() && formValidationbits[0] && formValidationbits[1]) {
							loginButton.setEnabled(true);
						}
					}
				} else {
					if (emailValid) {
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						emailValid = false;
						formValidationbits[0] = emailValid;
						if (loginButton.isEnabled()) {
							loginButton.setEnabled(false);
						}
					}
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		password.addTextChangedListener(new TextWatcher() {
			boolean passValid = false;

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() >= 6) {
					if (!passValid) {
						password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
						passValid = true;
						formValidationbits[1] = passValid;
						if (!loginButton.isEnabled() && formValidationbits[0] && formValidationbits[1]) {
							loginButton.setEnabled(true);
						}
					}
				} else {
					if (passValid) {
						password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						passValid = false;
						formValidationbits[1] = passValid;
						if (loginButton.isEnabled()) {
							loginButton.setEnabled(false);
						}
					}
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		final Context cont = this;
		final Activity currentActivity = this;

		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String emailAddress = emailId.getText().toString().trim();
				String passwordString = password.getText().toString().trim();
				List<NameValuePair> params = new LinkedList<NameValuePair>();
				String url = "http://www.x-dengue.com/mobilev1/FullCustomerData";
				params.add(new BasicNameValuePair("emailAddress", emailAddress));
				params.add(new BasicNameValuePair("password", passwordString));
				XdenguePreferences.writeString(cont, XdenguePreferences.EMAIL, emailAddress);
				XdenguePreferences.writeString(cont, XdenguePreferences.PASS, passwordString);
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				LoginTask loginTask = new LoginTask(cont, currentActivity);
				loginTask.execute(url);
			}
		});
	}
}
