package com.smartcommunities.xdengue;

import android.app.Application;

import com.smartcommunities.xdengue.dataModel.CustomerData;

public class XdengueGlobalState extends Application {
	private CustomerData customerData;

	public CustomerData getCustomerData() {
		if(customerData==null){
			throw new RuntimeException("Customer Data not set");
		}
		return customerData;
	}

	public void setCustomerData(CustomerData customerData) {
		this.customerData = customerData;
	}
}
