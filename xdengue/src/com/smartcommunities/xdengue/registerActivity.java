package com.smartcommunities.xdengue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.widget.Button;
import android.widget.EditText;

import com.smartcommunities.xdengue.net.RegisterTask;

public class registerActivity extends Activity {
	
	private EditText firstName,lastName,emailId,password;
	private static final int size = 4;
	private boolean formValidationbits[];
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static boolean isValidEmail(String emailId){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
		
	}
	private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.register);
	    formValidationbits = new boolean[size];
	    final Context cont = this;
	    final Activity currentActivity= this;
	    final Button registerButton = (Button) findViewById(R.id.registerButton);
	    registerButton.setEnabled(false);
	    firstName = (EditText)findViewById(R.id.register_fname);
    	lastName = (EditText)findViewById(R.id.register_lname);
    	emailId = (EditText)findViewById(R.id.register_emailId);
    	password = (EditText)findViewById(R.id.register_password);
    	firstName.addTextChangedListener(new TextWatcher() {
			boolean isFirstKey;
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(s.length()==0){
					firstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
					formValidationbits[0] = false;
					if(registerButton.isEnabled()){
						registerButton.setEnabled(false);
					}
				}
				else if(isFirstKey){
					firstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
					formValidationbits[0] = true;
					if(!registerButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]&&formValidationbits[2]&&formValidationbits[3]){
						registerButton.setEnabled(true);
					}
				}
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if(s.length()==0){
					isFirstKey = true;
				}
				else if(isFirstKey){
					isFirstKey = false;
				}
					
			}
			
			public void afterTextChanged(Editable s) {
				
			}
		});
    	
    	lastName.addTextChangedListener(new TextWatcher() {
    		boolean isFirstKey;
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(s.length()==0){
					lastName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
					formValidationbits[1] = false;
					if(registerButton.isEnabled()){
						registerButton.setEnabled(false);
					}
				}
				else if(isFirstKey){
					lastName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
					formValidationbits[1] = true;
					if(!registerButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]&&formValidationbits[2]&&formValidationbits[3]){
						registerButton.setEnabled(true);
					}
				}
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if(s.length()==0){
					isFirstKey = true;
				}
				else if(isFirstKey){
					isFirstKey = false;
				}
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	emailId.addTextChangedListener(new TextWatcher() {
    		boolean emailValid = false;
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(isValidEmail(s.toString())){
					if(!emailValid){
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationok, 0);
						emailValid = true;
						formValidationbits[2] = emailValid;
						if(!registerButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]&&formValidationbits[2]&&formValidationbits[3]){
							registerButton.setEnabled(true);
						}
					}
				}
				else{
					if(emailValid){
						emailId.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						emailValid = false;
						formValidationbits[2] = emailValid;
						if(registerButton.isEnabled()){
							registerButton.setEnabled(false);
						}
					}
				}
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
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
						formValidationbits[3] = passValid;
						if(!registerButton.isEnabled()&&formValidationbits[0]&&formValidationbits[1]&&formValidationbits[2]&&formValidationbits[3]){
							registerButton.setEnabled(true);
						}
					}
				}
				else{
					if(passValid){
						password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.formvalidationerror, 0);
						passValid = false;
						formValidationbits[3] = passValid;
						if(registerButton.isEnabled()){
							registerButton.setEnabled(false);
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
	    registerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {		
				List<NameValuePair> params = new LinkedList<NameValuePair>();
				params.add(new BasicNameValuePair("FirstName", firstName.getText().toString().trim()));
		        params.add(new BasicNameValuePair("LastName", lastName.getText().toString().trim()));
		        params.add(new BasicNameValuePair("EmailAddress", emailId.getText().toString().trim()));
		        params.add(new BasicNameValuePair("Password", password.getText().toString().trim()));
				String url = "http://www.x-dengue.com/mobilev1/FullCustomerData";
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				RegisterTask registerTask = new RegisterTask(cont,currentActivity);
				registerTask.execute(url);	    
			}
		});
	}
}
