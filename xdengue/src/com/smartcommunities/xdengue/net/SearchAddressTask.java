package com.smartcommunities.xdengue.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.gson.Gson;
import com.smartcommunities.xdengue.DrawPinOverlay;
import com.smartcommunities.xdengue.R;
import com.smartcommunities.xdengue.XdengueGlobalState;
import com.smartcommunities.xdengue.myLocationActivity;
import com.smartcommunities.xdengue.dataModel.SearchAddressResult;

public class SearchAddressTask extends AsyncTask<String, Void, String> {
	private final Context context;
	private SearchAddressResult sar;
	private final Activity callingActivity;
	private final ProgressDialog dialog;
	private final int mode;

	private MapController mapController;
	private List<Overlay> mapOverlays;
	private DrawPinOverlay currentPinOverlay;

	public SearchAddressTask(Context context, Activity callingActivity) {
		this.context = context;
		this.callingActivity = callingActivity;
		this.mode = 1;
		dialog = new ProgressDialog(context);
	}

	public SearchAddressTask(Context context, Activity callingActivity, DrawPinOverlay currentPinOverlay, MapController mapController, List<Overlay> mapOverlays) {
		this.context = context;
		this.callingActivity = callingActivity;
		this.mode = 2;
		this.currentPinOverlay = currentPinOverlay;
		this.mapController = mapController;
		this.mapOverlays = mapOverlays;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage("Searching for address");
		this.dialog.setCancelable(false);
		this.dialog.setCanceledOnTouchOutside(false);
		this.dialog.show();
	}

	@Override
	protected String doInBackground(String... urls) {
		String response = "";
		for (String url : urls) {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			try {
				HttpResponse execute = client.execute(httpGet);
				InputStream content = execute.getEntity().getContent();

				BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					response += s;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	protected void onPostExecute(String result) {
		if (this.dialog.isShowing()) {
			this.dialog.dismiss();
		}
		System.out.println(result);
		try {
			JSONObject json = new JSONObject(result);
			Gson gson = new Gson();
			sar = gson.fromJson(result, SearchAddressResult.class);
			((XdengueGlobalState) callingActivity.getApplication()).setSearchAddressResult(sar);

			System.out.println(sar.getStatus());
			System.out.println(sar.getGeocodingResult().getFormatted_address());
			System.out.println(sar.getGeocodingResult().getGeometry().getLocation().getLat() + " " + sar.getGeocodingResult().getGeometry().getLocation().getLng());

			switch (mode) {
			case 1:
				postExecute1();
				break;
			case 2:
				postExecute2();
				break;
			default:
				break;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void postExecute1() {
		Intent intent = new Intent(context, myLocationActivity.class);
		Bundle b = new Bundle();
		b.putDouble("latitude", sar.getGeocodingResult().getGeometry().getLocation().getLat());
		b.putDouble("longitude", sar.getGeocodingResult().getGeometry().getLocation().getLng());
		b.putString("addressname", sar.getGeocodingResult().getFormatted_address());
		intent.putExtras(b);
		callingActivity.startActivity(intent);
	}

	private void postExecute2() {
		GeoPoint tempPoint = new GeoPoint((int) (sar.getGeocodingResult().getGeometry().getLocation().getLat() * 1E6), (int) (sar.getGeocodingResult().getGeometry().getLocation().getLng() * 1E6));
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.didyouknow);
		currentPinOverlay = new DrawPinOverlay(tempPoint, bmp);
		mapOverlays.add(currentPinOverlay);
		mapController.setZoom(17);
		mapController.animateTo(tempPoint);
	}
}
