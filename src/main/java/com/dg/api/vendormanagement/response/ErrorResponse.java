package com.dg.api.vendormanagement.response;

public class ErrorResponse {

	private String errCode;
	private String errMesg;

	public String getErrMesg() {
		return errMesg;
	}

	public void setErrMesg(String errMesg) {
		this.errMesg = errMesg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String errCode, String errMesg) {
		super();
		this.errCode = errCode;
		this.errMesg = errMesg;
	}

}
