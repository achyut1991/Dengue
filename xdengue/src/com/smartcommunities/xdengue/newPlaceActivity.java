package com.smartcommunities.xdengue;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class newPlaceActivity extends Activity {
	
	private GeoPoint markerPosition = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Bundle extras = getIntent().getExtras();
	
	    if(extras!=null)
		{
			markerPosition = new GeoPoint(extras.getInt("latitude"), extras.getInt("longitude"));
		}
	}

}
