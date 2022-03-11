package com.dg.api.vendormanagement.exception;

import org.springframework.http.HttpStatus;

import com.dg.api.vendormanagement.util.Constants;

public class InvalidRequestException extends ErrorResponse {

	private static final long serialVersionUID = -6208830607100795849L;

	public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(String errCode, String mesg, HttpStatus status) {
		super(Constants.ERROR, mesg, errCode);
	}

}
