package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	private ImageButton logoButtonLeft, logoButtonRight;
	private ImageView logoHeader;
	private TextView logoText;
	private Button signOutButton;
	final Context cont = this;
	final Activity currentActivity = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.settings);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);

		logoButtonLeft = (ImageButton) findViewById(R.id.logoImageButtonLeft);
		logoButtonRight = (ImageButton) findViewById(R.id.logoImageButtonRight);
		logoHeader = (ImageView) findViewById(R.id.header);
		logoText = (TextView) findViewById(R.id.logoText);

		logoButtonLeft.setVisibility(4);
		logoHeader.setVisibility(4);
		logoText.setText("Settings");
		logoButtonRight.setBackgroundResource(android.R.drawable.ic_dialog_dialer);
		logoButtonRight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent HomeIntent = new Intent(cont, homeActivity.class);
				HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HomeIntent);
				currentActivity.finish();
			}
		});

		signOutButton = (Button) findViewById(R.id.signoutbutton);
		signOutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				XdenguePreferences.getEditor(cont).clear().commit();
				Intent HomeIntent = new Intent(cont, XdengueActivity.class);
				HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(HomeIntent);
				finish();
			}
		});
	}

}
