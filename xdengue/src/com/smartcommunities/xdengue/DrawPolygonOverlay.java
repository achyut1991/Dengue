package com.smartcommunities.xdengue;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class DrawPolygonOverlay extends Overlay {
	private ArrayList<GeoPoint> affectedArea = new ArrayList<GeoPoint>();
	private GeoPoint centerPoint;
	private int severity;
	private final Bitmap severityIcon;

	public DrawPolygonOverlay(ArrayList<GeoPoint> affectedArea, int severity, Bitmap severityIcon, GeoPoint centerPoint) {
		this.affectedArea = affectedArea;
		this.severity = severity;
		this.severityIcon = severityIcon;
		this.centerPoint = centerPoint;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
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
			Point screenPts = new Point();
			mapView.getProjection().toPixels(centerPoint, screenPts);
			canvas.drawBitmap(severityIcon, screenPts.x - severityIcon.getWidth() / 2, screenPts.y - severityIcon.getHeight(), null);
		}
		return super.draw(canvas, mapView, shadow, when);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
	}

	public ArrayList<GeoPoint> getAffectedArea() {
		return affectedArea;
	}

	public void setAffectedArea(ArrayList<GeoPoint> affectedArea) {
		this.affectedArea = affectedArea;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public GeoPoint getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(GeoPoint centerPoint) {
		this.centerPoint = centerPoint;
	}
}
