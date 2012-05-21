package com.smartcommunities.xdengue.dataModel;

public class Manifest {
	private boolean HasError;
	private int ErrorNumber;
	private String ErrorDescription;
	public boolean isHasError() {
		return HasError;
	}
	public void setHasError(boolean hasError) {
		HasError = hasError;
	}
	public int getErrorNumber() {
		return ErrorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		ErrorNumber = errorNumber;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
}
