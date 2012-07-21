package com.smartcommunities.xdengue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class DrawPinOverlay extends Overlay {
	private GeoPoint centerPoint;
	private final Bitmap pinIcon;

	public DrawPinOverlay(GeoPoint centerPoint, Bitmap pinIcon) {
		this.centerPoint = centerPoint;
		this.pinIcon = pinIcon;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		if (shadow == false) {
			Point screenPts = new Point();
			mapView.getProjection().toPixels(centerPoint, screenPts);
			canvas.drawBitmap(pinIcon, screenPts.x - pinIcon.getWidth() / 2, screenPts.y - pinIcon.getHeight(), null);
		}
		return super.draw(canvas, mapView, shadow, when);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
	}

	public GeoPoint getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(GeoPoint centerPoint) {
		this.centerPoint = centerPoint;
	}
}
