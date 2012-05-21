package com.smartcommunities.xdengue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartcommunities.xdengue.dataModel.CustomerData;

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
		setContentView(R.layout.login);

		formValidationbits = new boolean[size];
		emailId = (EditText) findViewById(R.id.uname);
		password = (EditText) findViewById(R.id.pass);
		
		final Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setEnabled(false);

		emailId.addTextChangedListener(new TextWatcher() {
			boolean emailValid = false;
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(isValidEmail(s.toString())){
					if(!emailValid){
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
						emailValid = true;
						formValidationbits[0] = emailValid;
						if(!loginButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]){
							loginButton.setEnabled(true);
						}
					}
				}
				else{
					if(emailValid){
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						emailValid = false;
						formValidationbits[0] = emailValid;
						if(loginButton.isEnabled()){
							loginButton.setEnabled(false);
						}
					}
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		password.addTextChangedListener(new TextWatcher() {
			boolean passValid = false;
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length()>=6){
					if(!passValid){
						password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
						passValid = true;
						formValidationbits[1] = passValid;
						if(!loginButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]){
							loginButton.setEnabled(true);
						}
					}
				}
				else{
					if(passValid){
						password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						passValid = false;
						formValidationbits[1] = passValid;
						if(loginButton.isEnabled()){
							loginButton.setEnabled(false);
						}
					}
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		final Context cont = this;
		
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				List<NameValuePair> params = new LinkedList<NameValuePair>();
				String url = "http://www.x-dengue.com/mobilev1/FullCustomerData";
				params.add(new BasicNameValuePair("emailAddress", emailId
						.getText().toString().trim()));
				params.add(new BasicNameValuePair("password", password
						.getText().toString().trim()));
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				LoginTask loginTask = new LoginTask(cont);
				loginTask.execute(url);
			}
		});
	}

	private class LoginTask extends AsyncTask<String, Void, String> {
		Context context;

		public LoginTask(Context context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						response += s;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(result);
			try {
				JSONObject json = new JSONObject(result);
				if (json.getBoolean("HasErrorLogin")) {
					Toast.makeText(context, R.string.login_error,
							Toast.LENGTH_LONG).show();
				} else {
					Gson gson = new Gson();
					CustomerData cd = gson.fromJson(result, CustomerData.class);

					// Set the CustomerData
					((XdengueGlobalState) getApplication()).setCustomerData(cd);

					System.out.println(cd.getCustomer().getFirstName());
					startActivity(new Intent(context, homeActivity.class));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
