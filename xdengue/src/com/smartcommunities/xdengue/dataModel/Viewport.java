package com.smartcommunities.xdengue.dataModel;

public class Viewport {
	private GeoCodingLocation northeast;
   	private GeoCodingLocation southwest;
	public GeoCodingLocation getNortheast() {
		return northeast;
	}
	public void setNortheast(GeoCodingLocation northeast) {
		this.northeast = northeast;
	}
	public GeoCodingLocation getSouthwest() {
		return southwest;
	}
	public void setSouthwest(GeoCodingLocation southwest) {
		this.southwest = southwest;
	}
}
