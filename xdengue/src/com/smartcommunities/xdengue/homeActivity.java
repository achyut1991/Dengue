package com.smartcommunities.xdengue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.net.LoginTask;
import com.smartcommunities.xdengue.net.SearchAddressTask;
import com.smartcommunities.xdengue.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.smartcommunities.xdengue.pulltorefresh.library.PullToRefreshListView;

public class homeActivity extends Activity {

	private EditText searchBox;
	private PullToRefreshListView homeListViewWrapper;
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
			homeListViewWrapper = (PullToRefreshListView) findViewById(R.id.homelist);
			ListView homeListView = homeListViewWrapper.getRefreshableView();
			List<String> didyouKnow = new ArrayList<String>();
			didyouKnow.add(cd.getDidYouKnow());
			HomeBaseAdapter homeBaseAdapter = new HomeBaseAdapter(cont, didyouKnow);
			homeListView.setAdapter(homeBaseAdapter);

			homeListViewWrapper.setOnRefreshListener(new OnRefreshListener() {
				public void onRefresh() {
					homeListViewWrapper.setLastUpdatedLabel(DateUtils.formatDateTime(getApplicationContext(),
							System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
									| DateUtils.FORMAT_ABBREV_ALL));

					// Do work to refresh the list here.
					String emailAddress = XdenguePreferences.readString(cont, XdenguePreferences.EMAIL, "");
					String passwordString = XdenguePreferences.readString(cont, XdenguePreferences.PASS, "");

					if (emailAddress.length() != 0 && passwordString.length() != 0) {
						List<NameValuePair> params = new LinkedList<NameValuePair>();
						String url = "http://www.x-dengue.com/mobilev1/FullCustomerData";
						params.add(new BasicNameValuePair("emailAddress", emailAddress));
						params.add(new BasicNameValuePair("password", passwordString));
						String paramString = URLEncodedUtils.format(params, "utf-8");
						url += "?" + paramString;
						LoginTask loginTask = new LoginTask(cont, currentActivity, homeListViewWrapper);
						loginTask.execute(url);
					} else {
						homeListViewWrapper.onRefreshComplete();
					}
				}
			});
		}
		final Context cont = this;

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
