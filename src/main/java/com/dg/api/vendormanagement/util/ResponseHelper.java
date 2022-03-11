package com.dg.api.vendormanagement.util;

import org.springframework.stereotype.Component;

import com.dg.api.vendormanagement.response.DataResponse;
import com.dg.api.vendormanagement.response.ApplicationResponse;

@Component
public class ResponseHelper {

	public ApplicationResponse buildSuccessResponse(DataResponse dataResponse, String mesg) {
		ApplicationResponse response = new ApplicationResponse();
		response.setData(dataResponse);
		response.setMesg(mesg!=null?mesg:"");
		response.setStatus(Constants.SUCCESS);
		response.setStatusCode(Constants.SUCCESS_STATUS_CODE);
		return response;
	}

	public ApplicationResponse buildErrorResponse(DataResponse dataResponse, VendorMgmErrorCode statusCode,  String mesg) {
		ApplicationResponse response = new ApplicationResponse();
		response.setData(dataResponse);
		response.setMesg(mesg);
		response.setStatusCode(statusCode.getErrorCodeValue());
		response.setStatus(Constants.ERROR);
		return response;
	}
}
