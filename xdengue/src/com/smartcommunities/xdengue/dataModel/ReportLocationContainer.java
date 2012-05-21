package com.smartcommunities.xdengue.dataModel;


public class ReportLocationContainer {
	private int LocationType;
	private PointLocation PointLocation;
	private AreaLocation AreaLocation;
	public int getLocationType() {
		return LocationType;
	}
	public void setLocationType(int locationType) {
		LocationType = locationType;
	}
	public PointLocation getPointLocation() {
		return PointLocation;
	}
	public void setPointLocation(PointLocation pointLocation) {
		PointLocation = pointLocation;
	}
	public AreaLocation getAreaLocation() {
		return AreaLocation;
	}
	public void setAreaLocation(AreaLocation areaLocation) {
		AreaLocation = areaLocation;
	}
}
