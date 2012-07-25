package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class newPlaceMobileListActivity extends Activity {

	public final static int SUCCESS_RETURN_CODE = 1;

	private ImageButton logoButtonLeft, logoButtonRight;
	private ImageView logoHeader;
	private TextView logoText;
	final Context cont = this;
	final Activity currentActivity = this;
	private String[] mobileNumbers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newplaceaddress);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);

		logoButtonLeft = (ImageButton) findViewById(R.id.logoImageButtonLeft);
		logoButtonRight = (ImageButton) findViewById(R.id.logoImageButtonRight);
		logoHeader = (ImageView) findViewById(R.id.header);
		logoText = (TextView) findViewById(R.id.logoText);

		logoButtonLeft.setVisibility(4);
		logoHeader.setVisibility(4);
		logoText.setText("Address");
		logoButtonRight.setBackgroundResource(R.drawable.mosquitored);
		logoButtonRight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putStringArray("mobilenumbers", mobileNumbers);
				intent.putExtras(b);
				setResult(SUCCESS_RETURN_CODE, intent);
				finish();
			}
		});

	}

}
