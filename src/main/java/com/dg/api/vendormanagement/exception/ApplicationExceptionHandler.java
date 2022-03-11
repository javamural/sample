package com.dg.api.vendormanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dg.api.vendormanagement.response.ApplicationResponse;
import com.dg.api.vendormanagement.util.Constants;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = { ApplicationException.class })
	public ResponseEntity<ApplicationResponse> handleApplicationException(Exception ex) {
		ApplicationResponse response = null;
		ApplicationException exception = null;
		if (ex instanceof ApplicationException) {
			exception = (ApplicationException) ex;
			response = new ApplicationResponse(Constants.ERROR, exception.getMesg(), exception.getStatusCode(), null);
		} else {
			response = new ApplicationResponse(Constants.ERROR, "Unknown Error", Constants.UNKNOWN_EXCEPTION, null);
		}
		return new ResponseEntity<ApplicationResponse>(response, HttpStatus.OK);
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	public ResponseEntity<ApplicationResponse> handleInvalidRequestException(Exception ex) {
		ApplicationResponse response = null;
		InvalidRequestException exception = null;
		if (ex instanceof ApplicationException) {
			exception = (InvalidRequestException) ex;
			response = new ApplicationResponse(Constants.ERROR,exception.getMesg(), exception.getStatusCode(), null);
		} else {
			response = new ApplicationResponse(Constants.ERROR,  "Unknown Error" , Constants.UNKNOWN_EXCEPTION,null);
		}
		return new ResponseEntity<ApplicationResponse>(response, HttpStatus.OK);

	}
}
