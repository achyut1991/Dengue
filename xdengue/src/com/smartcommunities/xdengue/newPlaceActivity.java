package com.smartcommunities.xdengue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;

public class newPlaceActivity extends Activity {

	private GeoPoint markerPosition = null;
	private String markerAddress = null;
	private EditText placeType, placeName, placeAddress, placeMobiles;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			markerPosition = new GeoPoint(extras.getInt("latitude"), extras.getInt("longitude"));
			markerAddress = extras.getString("name");
		}

		placeType = (EditText) findViewById(R.id.placetype);
		placeName = (EditText) findViewById(R.id.placename);
		placeAddress = (EditText) findViewById(R.id.placeaddress);
		placeMobiles = (EditText) findViewById(R.id.placemobiles);

		if (markerAddress != null) {
			placeAddress.setText(markerAddress);
		}
	}

}
