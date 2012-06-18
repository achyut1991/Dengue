package com.smartcommunities.xdengue.dataModel;

import java.util.List;

public class GeocodingResult {
	private List<String> types;
	private String formatted_address;
	private List<AddressComponents> address_components;
	private Geometry geometry;
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public List<AddressComponents> getAddress_components() {
		return address_components;
	}
	public void setAddress_components(List<AddressComponents> address_components) {
		this.address_components = address_components;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}

}
