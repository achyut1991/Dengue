package com.smartcommunities.xdengue.dataModel;


public class PlaceReport extends Report {
	private int PlaceId;
	private int DistanceFromPlace;
	public int getPlaceId() {
		return PlaceId;
	}
	public void setPlaceId(int placeId) {
		PlaceId = placeId;
	}
	public int getDistanceFromPlace() {
		return DistanceFromPlace;
	}
	public void setDistanceFromPlace(int distanceFromPlace) {
		DistanceFromPlace = distanceFromPlace;
	}
}
