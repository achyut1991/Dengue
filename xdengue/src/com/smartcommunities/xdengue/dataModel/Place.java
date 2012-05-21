package com.smartcommunities.xdengue.dataModel;

import java.util.List;


public class Place {
	private int PlaceId;
	private String Synonym;
	private int AlertRadius;
	private int PlaceType;
	private PointLocation Location;
	private List<MobileNumber> MobileNumbers;
	private List<PlaceReport> PlaceReports;
	public int getPlaceId() {
		return PlaceId;
	}
	public void setPlaceId(int placeId) {
		PlaceId = placeId;
	}
	public String getSynonym() {
		return Synonym;
	}
	public void setSynonym(String synonym) {
		Synonym = synonym;
	}
	public int getAlertRadius() {
		return AlertRadius;
	}
	public void setAlertRadius(int alertRadius) {
		AlertRadius = alertRadius;
	}
	public int getPlaceType() {
		return PlaceType;
	}
	public void setPlaceType(int placeType) {
		PlaceType = placeType;
	}
	public PointLocation getLocation() {
		return Location;
	}
	public void setLocation(PointLocation location) {
		Location = location;
	}
	public List<MobileNumber> getMobileNumbers() {
		return MobileNumbers;
	}
	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		MobileNumbers = mobileNumbers;
	}
	public List<PlaceReport> getPlaceReports() {
		return PlaceReports;
	}
	public void setPlaceReports(List<PlaceReport> placeReports) {
		PlaceReports = placeReports;
	}
}
