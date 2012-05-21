package com.smartcommunities.xdengue.dataModel;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private long CustomerId;
	private String FirstName;
	private String LastName;
	private String EmailAddress;
	private List<MobileNumber> MobileNumbers = new ArrayList<MobileNumber>();
	private List<Place> Places = new ArrayList<Place>();
	public long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(long customerId) {
		CustomerId = customerId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public List<MobileNumber> getMobileNumbers() {
		return MobileNumbers;
	}
	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		MobileNumbers = mobileNumbers;
	}
	public List<Place> getPlaces() {
		return Places;
	}
	public void setPlaces(List<Place> places) {
		Places = places;
	}
}
