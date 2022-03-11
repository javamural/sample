package com.dg.api.vendormanagement.util;

public class Constants {

	public static final String SERVICE_NAME = "User-Management";
	public static final String INVALID_REQUEST = "INVALID_REQUST";
	public static final String DATA_BASE_ERROR = "DATA_BASE_ERROR";
	public static final String INVALID_HEADERS = "INVALID_HEADERS";

	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

	public static final String APPLICATION_ID_KEY = "APPLICATION_ID";
	public static final String APPLICATION_ID_VALUE = "VENDOR-MANAGEMENT";

	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String FAILURE = "FAILURE";
	public static final String OK = "OK";
	
	public static final String SUCCESS_STATUS_CODE = "VENDOR_MGM_S0001";

	public static final String APPLICATION_EXCEPTION = "APPLICATION_EXCEPTION";
	public static final String UNKNOWN_EXCEPTION = "UNKNWN_EXCEPTION";
	
	public static final String EXTERNAL_SERVICE_CALL_FAILED = "E0014";

	public static final String DG_SUB_APP_ID = "DG_SubApplicationId";
}
