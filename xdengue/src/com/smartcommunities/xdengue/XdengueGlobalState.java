package com.smartcommunities.xdengue;

import com.smartcommunities.xdengue.dataModel.CustomerData;

import android.app.Application;

public class XdengueGlobalState extends Application {
	private CustomerData customerData;

	public CustomerData getCustomerData() {
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}

}
