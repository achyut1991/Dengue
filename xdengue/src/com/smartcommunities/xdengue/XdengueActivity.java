package com.smartcommunities.xdengue;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.smartcommunities.xdengue.net.LoginTask;

public class XdengueActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        final Context cont = this;
        final Activity currentActivity = this;
        
        String emailAddress = XdenguePreferences.readString(cont, XdenguePreferences.EMAIL, "");
		String passwordString = XdenguePreferences.readString(cont, XdenguePreferences.PASS, "");
		
		if(emailAddress.length()!=0 && passwordString.length()!=0){
	        List<NameValuePair> params = new LinkedList<NameValuePair>();
			String url = "http://www.x-dengue.com/mobilev1/FullCustomerData";
			params.add(new BasicNameValuePair("emailAddress", emailAddress));
			params.add(new BasicNameValuePair("password", passwordString));
			String paramString = URLEncodedUtils.format(params, "utf-8");
			url += "?" + paramString;
			LoginTask loginTask = new LoginTask(cont,currentActivity);
			loginTask.execute(url);
		}
        
        
		Button oldUserButton = (Button) findViewById(R.id.oldUserButton);
		oldUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, loginActivity.class));
			}
		});
		Button newUserButton = (Button) findViewById(R.id.newUserButton);
		newUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, registerActivity.class));
			}
		});
    }
}