package com.smartcommunities.xdengue.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.smartcommunities.xdengue.dataModel.SearchAddressResult;

public class SearchAddressTask extends AsyncTask<String, Void, String> {
	private final Context context;
	private final Activity callingActivity;
	private final ProgressDialog dialog;

	public SearchAddressTask(Context context, Activity callingActivity) {
		this.context = context;
		this.callingActivity = callingActivity;
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
			SearchAddressResult sar = gson.fromJson(result, SearchAddressResult.class);

			System.out.println(sar.getStatus());
			System.out.println(sar.getGeocodingResult().getFormatted_address());
			System.out.println(sar.getGeocodingResult().getGeometry().getLocation().getLat() + " " + sar.getGeocodingResult().getGeometry().getLocation().getLng());

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
