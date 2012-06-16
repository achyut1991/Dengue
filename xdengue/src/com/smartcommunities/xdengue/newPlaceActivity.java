package com.smartcommunities.xdengue;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

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
