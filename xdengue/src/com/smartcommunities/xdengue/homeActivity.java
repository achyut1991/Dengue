package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class homeActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		final Context cont = this;
		ImageButton aboutUsButton = (ImageButton) findViewById(R.id.aboutUsButton);
		ImageButton preventionButton = (ImageButton) findViewById(R.id.preventionButton);
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
		
	}

}
