package com.dg.api.vendormanagement.util;

public enum VendorMgmErrorCode {

	APPLICATION_EXCEPTION("VENDOR_MGM_E0000"), 
	INVALID_REQUEST("VENDOR_MGM_E0001"), 
	INVALID_HEADERS("VENDOR_MGM_E0002"),
	DATA_BASE_ERROR("VENDOR_MGM_E0003");
	
	private final String value;

    private VendorMgmErrorCode(String value) {
        this.value = value;
    }

    public String getErrorCodeValue() {
        return value;
    }
}
