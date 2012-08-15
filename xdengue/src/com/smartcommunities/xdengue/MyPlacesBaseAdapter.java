package com.smartcommunities.xdengue;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.Place;
import com.smartcommunities.xdengue.dataModel.PlaceTypeUtil;

public class MyPlacesBaseAdapter extends BaseAdapter {
	private static List<Place> myPlacesList;
	private final Context context;

	private final LayoutInflater mInflater;

	public MyPlacesBaseAdapter(Context context, List<Place> myPlacesList) {
		this.myPlacesList = myPlacesList;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return myPlacesList.size();
	}

	public Object getItem(int position) {
		return myPlacesList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		myPlacesList.remove(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.myplacesitem, null);
			holder = new ViewHolder();
			holder.placeName = (TextView) convertView.findViewById(R.id.placename);
			holder.placeAddress = (TextView) convertView.findViewById(R.id.placeaddress);
			holder.icon = (ImageView) convertView.findViewById(R.id.myplaceitemicon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Place thisPlace = myPlacesList.get(position);
		if (thisPlace.getPlaceReports().size() != 0) {
			// Red Zone
			holder.placeName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cross_shield, 0, 0, 0);
		} else {
			// Green Zone
			holder.placeName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tick_shield, 0, 0, 0);
		}
		holder.placeName.setText(thisPlace.getSynonym());
		holder.placeAddress.setText(thisPlace.getLocation().getAddress());
		holder.icon.setImageResource(PlaceTypeUtil.getIconId(thisPlace.getIconName()));

		return convertView;
	}

	static class ViewHolder {
		TextView placeName;
		ImageView icon;
		TextView placeAddress;
	}

}
