package com.smartcommunities.xdengue.dataModel;

public class News {
	private String NewsURL;
	private boolean BreakingNews;
	public String getNewsURL() {
		return NewsURL;
	}
	public void setNewsURL(String newsURL) {
		NewsURL = newsURL;
	}
	public boolean isBreakingNews() {
		return BreakingNews;
	}
	public void setBreakingNews(boolean breakingNews) {
		BreakingNews = breakingNews;
	}
}
