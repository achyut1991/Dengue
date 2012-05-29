package com.smartcommunities.xdengue;

import com.smartcommunities.xdengue.dataModel.CustomerData;

import android.app.Application;
import android.content.Intent;

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
