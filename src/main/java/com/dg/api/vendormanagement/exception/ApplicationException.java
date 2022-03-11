package com.dg.api.vendormanagement.exception;

import com.dg.api.vendormanagement.util.Constants;

public class ApplicationException extends ErrorResponse {

	private static final long serialVersionUID = 867785250846733835L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String mesg, String statusCode) {
		super(Constants.ERROR, statusCode, mesg);

	}

}
