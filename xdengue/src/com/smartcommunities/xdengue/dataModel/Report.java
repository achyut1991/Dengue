package com.smartcommunities.xdengue.dataModel;


public class Report {
	private int ReportId;
	private String ReportDate;
	private String ReportDateFormat;
	private String Comments;
	private int Severity;
	private int Cases;
	private ReportTimeInterval TimeInterval;
	private ReportLocationContainer LocationContainer;
	public int getReportId() {
		return ReportId;
	}
	public void setReportId(int reportId) {
		ReportId = reportId;
	}
	public String getReportDate() {
		return ReportDate;
	}
	public void setReportDate(String reportDate) {
		ReportDate = reportDate;
	}
	public String getReportDateFormat() {
		return ReportDateFormat;
	}
	public void setReportDateFormat(String reportDateFormat) {
		ReportDateFormat = reportDateFormat;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public int getSeverity() {
		return Severity;
	}
	public void setSeverity(int severity) {
		Severity = severity;
	}
	public int getCases() {
		return Cases;
	}
	public void setCases(int cases) {
		Cases = cases;
	}
	public ReportTimeInterval getTimeInterval() {
		return TimeInterval;
	}
	public void setTimeInterval(ReportTimeInterval timeInterval) {
		TimeInterval = timeInterval;
	}
	public ReportLocationContainer getLocationContainer() {
		return LocationContainer;
	}
	public void setLocationContainer(ReportLocationContainer locationContainer) {
		LocationContainer = locationContainer;
	}
}
