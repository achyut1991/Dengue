package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class preventionActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.prevention);
	    
		final Context cont = this;
		Button personalProtectionButton = (Button) findViewById(R.id.personalProtectionButton);
		Button wipeoutButton = (Button) findViewById(R.id.wipeoutButton);
		Button breedingSitesButton = (Button) findViewById(R.id.breedingSitesButton);
		personalProtectionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, personalProtectionActivity.class));				
			}
		});
		
		wipeoutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, mozzieWipeoutActivity.class));				
			}
		});
		
		breedingSitesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, breedingSitesActivity.class));				
			}
		});
		
	}

}
