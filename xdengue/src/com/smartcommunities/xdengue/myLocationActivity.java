package com.smartcommunities.xdengue;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.MapView.LayoutParams;
import android.view.View;
import android.widget.LinearLayout;

public class myLocationActivity extends MapActivity {

	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocOverlay;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylocation);

		mapView = (MapView) findViewById(R.id.mapview1);
		mapView.setBuiltInZoomControls(true);
		LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.zoomOption);
		View zoomView = mapView.getZoomControls();

		zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mapView.displayZoomControls(true);

		mapController = mapView.getController();

		myLocOverlay = new MyLocationOverlay(this, mapView);
		myLocOverlay.enableMyLocation();
		mapView.getOverlays().add(myLocOverlay);

		myLocOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.setZoom(17);
				mapController.animateTo(myLocOverlay.getMyLocation());
			}
		});

	}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
