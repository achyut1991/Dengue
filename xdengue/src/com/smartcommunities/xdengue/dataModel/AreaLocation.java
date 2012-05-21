package com.smartcommunities.xdengue.dataModel;

import java.util.List;


public class AreaLocation {
	private List<Point> Points;
	private Point AreaCenter;
	public List<Point> getPoints() {
		return Points;
	}
	public void setPoints(List<Point> points) {
		Points = points;
	}
	public Point getAreaCenter() {
		return AreaCenter;
	}
	public void setAreaCenter(Point areaCenter) {
		AreaCenter = areaCenter;
	}
}
