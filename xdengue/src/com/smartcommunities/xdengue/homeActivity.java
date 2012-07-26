package com.smartcommunities.xdengue;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.net.SearchAddressTask;

public class homeActivity extends Activity {

	private EditText searchBox;
	private ImageButton logoButtonLeft, logoButtonRight;
	private ImageView logoHeader;
	private TextView logoText;
	final Context cont = this;
	final Activity currentActivity = this;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.home);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);

		logoButtonLeft = (ImageButton) findViewById(R.id.logoImageButtonLeft);
		logoButtonRight = (ImageButton) findViewById(R.id.logoImageButtonRight);
		logoHeader = (ImageView) findViewById(R.id.header);
		logoText = (TextView) findViewById(R.id.logoText);

		logoButtonRight.setVisibility(4);
		logoText.setVisibility(4);
		logoButtonLeft.setBackgroundResource(android.R.drawable.ic_menu_preferences);
		logoButtonLeft.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

			}
		});

		searchBox = (EditText) findViewById(R.id.home_searchText);

		CustomerData cd = null;
		try {
			cd = ((XdengueGlobalState) getApplication()).getCustomerData();
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("cutomer data: ", e.getMessage());
		}
		if (cd != null) {
			((TextView) findViewById(R.id.didyouknow)).setText(cd.getDidYouKnow());
		}
		final Context cont = this;
		ImageButton aboutUsButton = (ImageButton) findViewById(R.id.aboutUsButton);
		ImageButton preventionButton = (ImageButton) findViewById(R.id.preventionButton);
		ImageButton myLocationButton = (ImageButton) findViewById(R.id.myLocationButton);
		ImageButton myPlacesButton = (ImageButton) findViewById(R.id.myPlacesButton);
		ImageButton tellAFriendButton = (ImageButton) findViewById(R.id.tellAFriendButton);
		aboutUsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, aboutUsActivity.class));
			}
		});

		preventionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, preventionActivity.class));
			}
		});

		myLocationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, myLocationActivity.class));
			}
		});

		myPlacesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, MyPlacesActivity.class));
			}
		});
		
		tellAFriendButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
				  
				String aEmailList[] = { "achyut1991@gmail.com" };  
				String aEmailCCList[] = { "seshasendhil@gmail.com"};  
				String aEmailBCCList[] = { "user5@fakehost.com" };  
				  
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);  
				  
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My subject");  
				  
				emailIntent.setType("plain/text");  
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Check this out... X-Dengue is a new free service in Singapore that alerts you via SMS when a Dengue cluster is nearby. Just add your favourite places like home, work, school or parents to get an alert if a Dengue cluster is near you or your loved ones.");  
				  
				startActivity(emailIntent);  
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

}
