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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class registerActivity extends Activity {
	
	private EditText firstName,lastName,emailId,password;
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
	    final Context cont = this;
	    Button registerButton = (Button) findViewById(R.id.registerButton);
	    registerButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				/*ProgressDialog dialog = ProgressDialog.show(cont, "", 
                        "Loading. Please wait...", true);*/
				
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.x-dengue.com/mobilev1/Register");
			    TextView registerMessage = (TextView) findViewById(R.id.registerMessage);

			    try {
			        // Add your data
			    	firstName = (EditText)findViewById(R.id.register_fname);
			    	lastName = (EditText)findViewById(R.id.register_lname);
			    	emailId = (EditText)findViewById(R.id.register_emailId);
			    	password = (EditText)findViewById(R.id.register_password);
			    	Log.d("Firstname", firstName.getText().toString());
			    	Log.d("Lastname",lastName.getText().toString());
			    	Log.d("Email",emailId.getText().toString());
			    	Log.d("Password",password.getText().toString());
			    	
			    	String fnameValue, lnameValue, emailValue, passValue; 
			    	boolean isFormValid = true;
			    	fnameValue = firstName.getText().toString().trim();
			    	lnameValue = lastName.getText().toString().trim();
			    	emailValue = emailId.getText().toString().trim();
			    	passValue = password.getText().toString().trim();
			    	
			    	
			    	if(fnameValue.length()==0){
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
			    	else if(passValue.length()==0){
			    		registerMessage.setText(R.string.pass_error);
			    		isFormValid = false;
			    	}
			    	
			    	if(!isFormValid){
			    		registerMessage.setVisibility(TextView.VISIBLE);
			    		return;
			    	}
			    	
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
			        nameValuePairs.add(new BasicNameValuePair("FirstName", firstName.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("LastName", lastName.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("EmailAddress", emailId.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("Password", password.getText().toString()));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        
			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        HttpEntity entity = response.getEntity();
			        InputStream instream = entity.getContent();
			        String result= convertStreamToString(instream);
			        
			        JSONObject json=new JSONObject(result);
			        Log.d("JSon obj", json.toString());
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
			        
			        instream.close();
			        //dialog.dismiss();
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
