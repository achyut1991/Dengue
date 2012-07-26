package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;

public class newPlaceActivity extends Activity {

	private ImageButton logoButtonLeft, logoButtonRight;
	private ImageView logoHeader;
	private TextView logoText;
	private GeoPoint markerPosition = null;
	private String markerAddress = null;
	private EditText placeName, placeAddress, placeMobiles;
	private Spinner placeType;
	private final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newplace);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);

		logoButtonLeft = (ImageButton) findViewById(R.id.logoImageButtonLeft);
		logoButtonRight = (ImageButton) findViewById(R.id.logoImageButtonRight);
		logoHeader = (ImageView) findViewById(R.id.header);
		logoText = (TextView) findViewById(R.id.logoText);

		logoButtonLeft.setVisibility(4);
		logoHeader.setVisibility(4);
		logoText.setText("Add New Place");
		logoButtonRight.setBackgroundResource(R.drawable.mosquitoyellow);
		logoButtonRight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

			}
		});

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			markerPosition = new GeoPoint(extras.getInt("latitude"), extras.getInt("longitude"));
			markerAddress = extras.getString("name");
		}

		placeType = (Spinner) findViewById(R.id.placetype);
		PlaceTypeBaseAdapter adapter = new PlaceTypeBaseAdapter(context);
		placeType.setAdapter(adapter);

		placeName = (EditText) findViewById(R.id.placename);
		placeAddress = (EditText) findViewById(R.id.placeaddress);
		placeMobiles = (EditText) findViewById(R.id.placemobiles);

		if (markerAddress != null) {
			placeAddress.setText(markerAddress);
		}
	}
}
