package com.smartcommunities.xdengue;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcommunities.xdengue.dataModel.Report;

public class OpenReportBaseAdapter extends BaseAdapter {
	private static List<Report> openReportsList;
	private final Context context;

	private final LayoutInflater mInflater;

	public OpenReportBaseAdapter(Context context, List<Report> openReportsList) {
		this.openReportsList = openReportsList;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return openReportsList.size();
	}

	public Object getItem(int position) {
		return openReportsList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		openReportsList.remove(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.myplacesitem, null);
			holder = new ViewHolder();
			holder.openreportaddress = (TextView) convertView.findViewById(R.id.placename);
			holder.openreportinfo = (TextView) convertView.findViewById(R.id.placeaddress);
			holder.icon = (ImageView) convertView.findViewById(R.id.myplaceitemicon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Report thisReport = openReportsList.get(position);
		if (thisReport.getSeverity() == 0) {
			holder.icon.setImageResource(R.drawable.mosquitoyellow);
		} else {
			holder.icon.setImageResource(R.drawable.mosquitored);
		}
		String reportinfo = String.format("%d cases, open for %d days", thisReport.getCases(), thisReport.getTimeInterval()
				.getDays());
		holder.openreportaddress.setText(thisReport.getLocationContainer().getAreaLocation().getAddress());
		holder.openreportinfo.setText(reportinfo);

		return convertView;
	}

	static class ViewHolder {
		TextView openreportaddress;
		ImageView icon;
		TextView openreportinfo;
	}

}
