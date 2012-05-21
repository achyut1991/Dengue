package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class homeActivity extends customLogoActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		XdengueGlobalState globalState = (XdengueGlobalState)getApplication();
		if(globalState!=null){
			((TextView)findViewById(R.id.didyouknow)).setText(globalState.getCustomerData().getDidYouKnow());
		}
		final Context cont = this;
		ImageButton aboutUsButton = (ImageButton) findViewById(R.id.aboutUsButton);
		ImageButton preventionButton = (ImageButton) findViewById(R.id.preventionButton);
		ImageButton myLocationButton = (ImageButton) findViewById(R.id.myLocationButton);
		aboutUsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, aboutUsActivity.class));
			}
		});
		
		preventionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, preventionActivity.class));				
			}
		});

		myLocationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, myLocationActivity.class));
			}
		});
		
	}

}
