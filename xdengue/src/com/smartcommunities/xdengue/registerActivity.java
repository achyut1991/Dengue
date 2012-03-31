package com.smartcommunities.xdengue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
				/*ProgressDialog dialog = ProgressDialog.show(cont, "", 
                        "Loading. Please wait...", true);*/
				
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.x-dengue.com/mobilev1/Register");
			    TextView registerMessage = (TextView) findViewById(R.id.registerMessage);

			    try {
			        // Add data
			    	
			    	Log.d("Firstname", firstName.getText().toString());
			    	Log.d("Lastname",lastName.getText().toString());
			    	Log.d("Email",emailId.getText().toString());
			    	Log.d("Password",password.getText().toString());
			    	
			    	String fnameValue, lnameValue, emailValue, passValue; 
			    	//boolean isFormValid = true;
			    	fnameValue = firstName.getText().toString().trim();
			    	lnameValue = lastName.getText().toString().trim();
			    	emailValue = emailId.getText().toString().trim();
			    	passValue = password.getText().toString().trim();
			    	
			    	
			    	/*if(fnameValue.length()==0){
			    		firstName.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.formvalidationerror, 0);
			    		registerMessage.setText(R.string.fname_error);
			    		isFormValid = false;
			    	}
			    	else if(lnameValue.length()==0){
			    		registerMessage.setText(R.string.lname_error);
			    		isFormValid = false;
			    	}
			    	else if(!isValidEmail(emailValue)){
			    		registerMessage.setText(R.string.email_error);
			    		isFormValid = false;
			    	}
			    	else if(passValue.length()<6){
			    		registerMessage.setText(R.string.pass_error);
			    		isFormValid = false;
			    	}
			    	
			    	if(!isFormValid){
			    		registerMessage.setVisibility(TextView.VISIBLE);
			    		return;
			    	}
			    	*/
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
			        nameValuePairs.add(new BasicNameValuePair("FirstName", fnameValue));
			        nameValuePairs.add(new BasicNameValuePair("LastName", lnameValue));
			        nameValuePairs.add(new BasicNameValuePair("EmailAddress", emailValue));
			        nameValuePairs.add(new BasicNameValuePair("Password", passValue));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        
			        // Execute HTTP Post Requests
			        HttpResponse response = httpclient.execute(httppost);
			        HttpEntity entity = response.getEntity();
			        InputStream instream = entity.getContent();
			        String result= convertStreamToString(instream);
			        
			        JSONObject json=new JSONObject(result);
			        Log.d("JSon obj", json.toString());
			        boolean isValidationError = json.getBoolean("HasValidationErrors");
			        if(!isValidationError){
			        	int isnewRegister = json.getInt("RegistrationStatus");
				        if(isnewRegister==0){
				        	System.out.println("NEw Registration");
				        	startActivity(new Intent(cont,homeActivity.class));
				        }
				        else if(isnewRegister == 1){
				        	System.out.println(R.string.oldUser_error);
				        	registerMessage.setText(R.string.oldUser_error);
				        	registerMessage.setVisibility(TextView.VISIBLE);
				        }
			        }
			        else{
			        	registerMessage.setText("Validation Error");
			        	registerMessage.setVisibility(TextView.VISIBLE);
			        }
			        
			        
			        instream.close();
			        //dialog.dismiss();
			        
			    } catch (ClientProtocolException e) {
			        Log.e("ClientProtocolException", e.getMessage());
			        e.printStackTrace();
			    } catch (IOException e) {
			    	Log.e("IOException", e.getMessage());
			    	e.printStackTrace();
			    } catch (JSONException e) {
			    	Log.e("JSONException", e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}
}
