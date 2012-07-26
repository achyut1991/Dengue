package com.smartcommunities.xdengue;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeBaseAdapter extends BaseAdapter {
	private static List<String> homeList;
	private final Context context;

	private final LayoutInflater mInflater;

	public HomeBaseAdapter(Context context, List<String> homeList) {
		this.homeList = homeList;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return homeList.size();
	}

	public Object getItem(int position) {
		return homeList.get(position);
	}

	public long getItemId(int position) {
	return position;
	}

	public void removeItem(int position) {
		homeList.remove(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.homeitem, null);
			holder = new ViewHolder();
			holder.homeitemdidyouknow = (TextView) convertView.findViewById(R.id.homeitemdidyouknow);
			holder.aboutUsButton = (ImageButton) convertView.findViewById(R.id.homeitemaboutUsButton);
			holder.preventionButton = (ImageButton) convertView.findViewById(R.id.homeitempreventionButton);
			holder.myLocationButton = (ImageButton) convertView.findViewById(R.id.homeitemmyLocationButton);
			holder.myPlacesButton = (ImageButton) convertView.findViewById(R.id.homeitemmyPlacesButton);
			holder.tellAFriendButton = (ImageButton) convertView.findViewById(R.id.homeitemtellAFriendButton);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.aboutUsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				context.startActivity(new Intent(context, aboutUsActivity.class));
			}
		});

		holder.preventionButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				context.startActivity(new Intent(context, preventionActivity.class));
			}
		});

		holder.myLocationButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				context.startActivity(new Intent(context, myLocationActivity.class));
			}
		});

		holder.myPlacesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				context.startActivity(new Intent(context, MyPlacesActivity.class));
			}
		});
		
		holder.tellAFriendButton.setOnClickListener(new View.OnClickListener() {
			
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
				  
				context.startActivity(emailIntent);  
			}
		});
		
		holder.homeitemdidyouknow.setText(homeList.get(position));
		
		return convertView;
	}

	static class ViewHolder {
		ImageButton aboutUsButton;
		ImageButton preventionButton;
		ImageButton myLocationButton;
		ImageButton myPlacesButton;
		ImageButton tellAFriendButton;
		TextView homeitemdidyouknow;
	}
}

