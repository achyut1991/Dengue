package com.smartcommunities.xdengue.dataModel;

public class SearchAddressResult {
	private GeocodingResult geocodingResult;
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
		return geocodingResult;
	}
	public void setGeocodingResult(GeocodingResult geocodingResult) {
		this.geocodingResult = geocodingResult;
	}
}
