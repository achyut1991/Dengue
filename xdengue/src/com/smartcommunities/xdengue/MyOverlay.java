package com.smartcommunities.xdengue;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyOverlay extends Overlay {
	private ArrayList<GeoPoint> affectedArea = new ArrayList<GeoPoint>();

	public MyOverlay(ArrayList<GeoPoint> affectedArea) {
		this.affectedArea = affectedArea;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
			long when) {
		// TODO Auto-generated method stub
		Projection projection = mapView.getProjection();
		if (shadow == false) {

			Path path = new Path();
			for (int j = 0; j < affectedArea.size(); j++) {
				GeoPoint gP1 = affectedArea.get(j);
				Point currentScreenPoint = new Point();
				projection.toPixels(gP1, currentScreenPoint);
				if (j == 0)
					path.moveTo(currentScreenPoint.x, currentScreenPoint.y);
				else
					path.lineTo(currentScreenPoint.x, currentScreenPoint.y);
			}

			Paint mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setColor(Color.RED);
			mPaint.setAlpha(100);
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(2);

			canvas.drawPath(path, mPaint);
		}
		return super.draw(canvas, mapView, shadow, when);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub

		super.draw(canvas, mapView, shadow);
	}

	public ArrayList<GeoPoint> getAffectedArea() {
		return affectedArea;
	}

	public void setAffectedArea(ArrayList<GeoPoint> affectedArea) {
		this.affectedArea = affectedArea;
	}
}
