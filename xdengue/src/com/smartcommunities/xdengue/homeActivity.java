package com.smartcommunities.xdengue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.CustomerData;

public class homeActivity extends customLogoActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		CustomerData cd = null;
		try{
		cd = ((XdengueGlobalState)getApplication()).getCustomerData();
		}
		catch(Exception e){
			e.printStackTrace();
			Log.v("cutomer data: ", e.getMessage());
		}
		if(cd!=null){
			((TextView)findViewById(R.id.didyouknow)).setText(cd.getDidYouKnow());
		}
		final Context cont = this;
		ImageButton aboutUsButton = (ImageButton) findViewById(R.id.aboutUsButton);
		ImageButton preventionButton = (ImageButton) findViewById(R.id.preventionButton);
		ImageButton myLocationButton = (ImageButton) findViewById(R.id.myLocationButton);
		ImageButton myPlacesButton = (ImageButton) findViewById(R.id.myPlacesButton);
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
		
		myPlacesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, MyPlacesActivity.class));
			}
		});
	}

}
