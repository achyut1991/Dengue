package com.smartcommunities.xdengue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.net.SearchAddressTask;

public class newPlaceAddressActivity extends MapActivity {

	public final static int SUCCESS_RETURN_CODE = 1;

	private ImageButton logoButtonLeft, logoButtonRight;
	private ImageView logoHeader;
	private TextView logoText;
	private EditText searchBox;
	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocOverlay;
	private List<Overlay> mapOverlays;
	final Context cont = this;
	final Activity currentActivity = this;
	GeoPoint markerPoint;
	String markerName;
	private DrawPinOverlay currentPinOverlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.newplaceaddress);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);

		markerPoint = null;
		currentPinOverlay = null;

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
				b.putDouble("latitude", markerPoint.getLatitudeE6());
				b.putDouble("longitude", markerPoint.getLongitudeE6());
				b.putString("addressname", markerName);
				intent.putExtras(b);
				setResult(SUCCESS_RETURN_CODE, intent);
				finish();
			}
		});

		searchBox = (EditText) findViewById(R.id.newplaceaddress_searchText);

		mapView = (MapView) findViewById(R.id.newpalceaddress_mapview);
		mapView.setBuiltInZoomControls(true);
		LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.newplaceaddress_zoomOption);
		View zoomView = mapView.getZoomControls();
		zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mapView.displayZoomControls(true);
		mapController = mapView.getController();
		mapOverlays = mapView.getOverlays();
		myLocOverlay = new MyLocationOverlay(this, mapView);
		myLocOverlay.enableMyLocation();
		mapOverlays.add(myLocOverlay);

		myLocOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.setZoom(17);
				mapController.animateTo(myLocOverlay.getMyLocation());
			}
		});

		searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					performSearch();
					return true;
				}
				return false;
			}
		});

		CustomerData cd = null;
		try {
			cd = ((XdengueGlobalState) getApplication()).getCustomerData();
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("cutomer data: ", e.getMessage());
		}
		for (int i = 0; i < cd.getOpenReports().size(); i++) {
			int severity = cd.getOpenReports().get(i).getSeverity();
			ArrayList<GeoPoint> affected = new ArrayList<GeoPoint>();
			for (int j = 0; j < cd.getOpenReports().get(i).getLocationContainer().getAreaLocation().getPoints().size(); j++) {
				affected.add(new GeoPoint((int) (cd.getOpenReports().get(i).getLocationContainer().getAreaLocation().getPoints().get(j).getLatitude() * 1E6), (int) (cd.getOpenReports().get(i)
						.getLocationContainer().getAreaLocation().getPoints().get(j).getLongitude() * 1E6)));
			}
			GeoPoint centerPoint = new GeoPoint((int) (cd.getOpenReports().get(i).getLocationContainer().getAreaLocation().getAreaCenter().getLatitude() * 1E6), (int) (cd.getOpenReports().get(i)
					.getLocationContainer().getAreaLocation().getAreaCenter().getLongitude() * 1E6));
			Bitmap bmp = null;
			if (severity == 0) {
				bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mosquitoyellow);
			} else if (severity == 2)
				bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mosquitored);
			mapOverlays.add(new DrawPolygonOverlay(affected, severity, bmp, centerPoint));
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void performSearch() {
		CustomerData cd = null;
		try {
			cd = ((XdengueGlobalState) getApplication()).getCustomerData();
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("cutomer data: ", e.getMessage());
		}
		String emailAddress = cd.getCustomer().getEmailAddress();
		String passwordString = XdenguePreferences.readString(cont, XdenguePreferences.PASS, "");
		String address = searchBox.getText().toString();
		String maxRadius = "10";
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		String url = "http://www.x-dengue.com/mobilev1/SearchAddress";
		params.add(new BasicNameValuePair("email", emailAddress));
		params.add(new BasicNameValuePair("password", passwordString));
		params.add(new BasicNameValuePair("address", address));
		params.add(new BasicNameValuePair("maxRadius", maxRadius));
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url += "?" + paramString;
		System.out.println(url);
		SearchAddressTask searchTask = new SearchAddressTask(cont, currentActivity);
		searchTask.execute(url);
	}

	public void focusMapView(double latitude, double longitude) {
		GeoPoint tempPoint = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.didyouknow);
		currentPinOverlay = new DrawPinOverlay(tempPoint, bmp);
		mapOverlays.add(currentPinOverlay);
		mapController.setZoom(17);
		mapController.animateTo(tempPoint);
		markerPoint = tempPoint;
	}

}
