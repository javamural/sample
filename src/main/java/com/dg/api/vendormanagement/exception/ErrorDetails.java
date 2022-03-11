package com.dg.api.vendormanagement.exception;

import org.springframework.http.HttpStatus;

public class ErrorDetails extends Exception {

	private String errCode;
	private String errMesg;

	private HttpStatus status;

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

	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ErrorDetails(String errCode, String errMesg, HttpStatus status) {
		super();
		this.errCode = errCode;
		this.errMesg = errMesg;
		this.status = status;
	}

}
