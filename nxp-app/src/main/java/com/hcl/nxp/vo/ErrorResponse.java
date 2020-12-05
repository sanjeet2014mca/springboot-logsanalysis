package com.hcl.nxp.vo;

public class ErrorResponse {

	public int errorCode;
	public String errorMessage;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
