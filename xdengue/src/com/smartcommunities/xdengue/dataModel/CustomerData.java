package com.smartcommunities.xdengue.dataModel;

import java.util.List;


public class CustomerData {
	private boolean HasErrorLogin;
	private Customer Customer;
	private List<Report> OpenReports;
	private String DidYouKnow;
	private News News;
	private Manifest Manifest;
	public boolean isHasErrorLogin() {
		return HasErrorLogin;
	}
	public void setHasErrorLogin(boolean hasErrorLogin) {
		HasErrorLogin = hasErrorLogin;
	}
	public Customer getCustomer() {
		return Customer;
	}
	public void setCustomer(Customer customer) {
		Customer = customer;
	}
	public List<Report> getOpenReports() {
		return OpenReports;
	}
	public void setOpenReports(List<Report> openReports) {
		OpenReports = openReports;
	}
	public String getDidYouKnow() {
		return DidYouKnow;
	}
	public void setDidYouKnow(String didYouKnow) {
		DidYouKnow = didYouKnow;
	}
	public News getNews() {
		return News;
	}
	public void setNews(News news) {
		News = news;
	}
	public Manifest getManifest() {
		return Manifest;
	}
	public void setManifest(Manifest manifest) {
		Manifest = manifest;
	}
}
