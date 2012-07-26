package com.smartcommunities.xdengue.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartcommunities.xdengue.HomeBaseAdapter;
import com.smartcommunities.xdengue.R;
import com.smartcommunities.xdengue.XdengueGlobalState;
import com.smartcommunities.xdengue.XdenguePreferences;
import com.smartcommunities.xdengue.homeActivity;
import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.pulltorefresh.library.PullToRefreshListView;

public class LoginTask extends AsyncTask<String, Void, String> {
	private final Context context;
	private final Activity callingActivity;
	private final ProgressDialog dialog;
	private final PullToRefreshListView homeListViewWrapper;

	public LoginTask(Context context, Activity callingActivity) {
		this.context = context;
		this.callingActivity = callingActivity;
		this.homeListViewWrapper = null;
		dialog = new ProgressDialog(context);
	}

	public LoginTask(Context context, Activity callingActivity, PullToRefreshListView homeListViewWrapper) {
		this.context = context;
		this.callingActivity = callingActivity;
		this.homeListViewWrapper = homeListViewWrapper;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		if (homeListViewWrapper == null) {
			this.dialog.setMessage("Logging in");
			this.dialog.show();
		}
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
			if (json.getBoolean("HasErrorLogin")) {
				Toast.makeText(context, R.string.login_error, Toast.LENGTH_LONG).show();
				XdenguePreferences.getEditor(context).clear().commit();
			} else {
				Log.d("Email from prefs", XdenguePreferences.readString(context, XdenguePreferences.EMAIL, ""));
				Log.d("Pass from prefs", XdenguePreferences.readString(context, XdenguePreferences.PASS, ""));
				Gson gson = new Gson();
				CustomerData cd = gson.fromJson(result, CustomerData.class);

				// Set the CustomerData
				((XdengueGlobalState) callingActivity.getApplication()).setCustomerData(cd);

				System.out.println(cd.getCustomer().getFirstName());
				if (homeListViewWrapper == null) {
					context.startActivity(new Intent(context, homeActivity.class));
					callingActivity.finish();
				} else {
					List<String> didyouKnow = new ArrayList<String>();
					didyouKnow.add(cd.getDidYouKnow());
					HomeBaseAdapter homeBaseAdapter = new HomeBaseAdapter(context, didyouKnow, callingActivity);
					homeListViewWrapper.getRefreshableView().setAdapter(homeBaseAdapter);
					homeListViewWrapper.onRefreshComplete();
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			XdenguePreferences.getEditor(context).clear().commit();
			e.printStackTrace();
		}
	}
}
