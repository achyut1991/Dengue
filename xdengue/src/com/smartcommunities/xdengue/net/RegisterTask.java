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
import android.widget.Toast;

import com.smartcommunities.xdengue.R;

public class RegisterTask extends AsyncTask<String, Void, String> {
	private Context context;
	private Activity callingActivity;
	private ProgressDialog dialog;

	public RegisterTask(Context context, Activity callingActivity) {
		this.context = context;
		this.callingActivity = callingActivity;
		dialog = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage("Registering");
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

				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(content));
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
			if (json.getBoolean("HasValidationErrors")) {
				Toast.makeText(context, R.string.validation_error,
						Toast.LENGTH_LONG).show();
			} else {
				int isnewRegister = json.getInt("RegistrationStatus");
		        if(isnewRegister==0){
		        	System.out.println("NEw Registration");
		        }
		        else if(isnewRegister == 1){
		        	System.out.println(R.string.oldUser_error);
		        	Toast.makeText(context, R.string.oldUser_error,
							Toast.LENGTH_LONG).show();
		        }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
