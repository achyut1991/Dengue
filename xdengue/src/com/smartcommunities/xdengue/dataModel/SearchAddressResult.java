package com.smartcommunities.xdengue.dataModel;

import java.util.List;

public class SearchAddressResult {
	private List<ReportDistance> ReportsDistance;
	private GeocodingResult GeocodingResult;
	private String Status;
	private Manifest Manifest;
	public Manifest getManifest() {
		return Manifest;
	}
	public void setManifest(Manifest manifest) {
		Manifest = manifest;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public GeocodingResult getGeocodingResult() {
		return GeocodingResult;
	}
	public void setGeocodingResult(GeocodingResult geocodingResult) {
		this.GeocodingResult = geocodingResult;
	}
	public List<ReportDistance> getReportsDistance() {
		return ReportsDistance;
	}
	public void setReportsDistance(List<ReportDistance> reportsDistance) {
		ReportsDistance = reportsDistance;
	}
}
