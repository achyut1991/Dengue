package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    
	    final Context cont = this;
	    Button loginButton = (Button) findViewById(R.id.loginButton);
	    loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(cont,homeActivity.class));
			}
		});
	}
}
