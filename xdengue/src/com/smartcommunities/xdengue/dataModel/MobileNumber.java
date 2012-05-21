package com.smartcommunities.xdengue.dataModel;

public class MobileNumber {
	private int MobileNumberId;
	private String Synonym;
	private String CountryCode;
	private String Number;
	public int getMobileNumberId() {
		return MobileNumberId;
	}
	public void setMobileNumberId(int mobileNumberId) {
		MobileNumberId = mobileNumberId;
	}
	public String getSynonym() {
		return Synonym;
	}
	public void setSynonym(String synonym) {
		Synonym = synonym;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
}
