package com.smartcommunities.xdengue;

import android.app.Application;

import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.dataModel.SearchAddressResult;

public class XdengueGlobalState extends Application {
	private CustomerData customerData;
	private SearchAddressResult searchAddressResult;

	public CustomerData getCustomerData() {
		if (customerData == null) {
			throw new RuntimeException("Customer Data not set");
		}
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}

	public SearchAddressResult getSearchAddressResult() {
		if (searchAddressResult == null) {
			throw new RuntimeException("Search Address not available");
		}
		return searchAddressResult;
	}

	public void setSearchAddressResult(SearchAddressResult searchAddressResult) {
		this.searchAddressResult = searchAddressResult;
	}
}
