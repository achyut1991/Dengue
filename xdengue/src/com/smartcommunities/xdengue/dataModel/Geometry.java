package com.smartcommunities.xdengue.dataModel;

public class Geometry {
	private GeoCodingLocation location;
   	private String location_type;
   	private Viewport viewport;
	public Viewport getViewport() {
		return viewport;
	}
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public GeoCodingLocation getLocation() {
		return location;
	}
	public void setLocation(GeoCodingLocation location) {
		this.location = location;
	}
}
