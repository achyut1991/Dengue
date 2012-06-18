package com.smartcommunities.xdengue.dataModel;

public class ReportDistance extends Report{
	private double Distance;
	private String FormatedDistance;
	public double getDistance() {
		return Distance;
	}
	public void setDistance(double distance) {
		Distance = distance;
	}
	public String getFormatedDistance() {
		return FormatedDistance;
	}
	public void setFormatedDistance(String formatedDistance) {
		FormatedDistance = formatedDistance;
	}
}
