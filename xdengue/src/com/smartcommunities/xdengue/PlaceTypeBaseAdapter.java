package com.smartcommunities.xdengue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.PlaceTypeUtil;

public class PlaceTypeBaseAdapter extends BaseAdapter {
	private final List<String> placeType;
	private final Context context;

	private final LayoutInflater mInflater;

	public PlaceTypeBaseAdapter(Context context) {
		this.placeType = new ArrayList<String>();
		this.placeType.addAll(PlaceTypeUtil.getPlaceTypes().keySet());
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return placeType.size();
	}

	public Object getItem(int position) {
		return placeType.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		placeType.remove(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.placetypeitem, null);
			holder = new ViewHolder();
			holder.placeTypeName = (TextView) convertView.findViewById(R.id.placetype);
			holder.icon = (ImageView) convertView.findViewById(R.id.placetypeitemicon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.placeTypeName.setText(PlaceTypeUtil.getTypeNameId(placeType.get(position)));
		holder.icon.setImageResource(PlaceTypeUtil.getIconId(placeType.get(position)));

		return convertView;
	}

	static class ViewHolder {
		TextView placeTypeName;
		ImageView icon;
	}

}
