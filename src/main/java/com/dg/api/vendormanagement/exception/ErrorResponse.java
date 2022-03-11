package com.dg.api.vendormanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends Exception{

	private String status;
	private String statusCode;
	private String mesg;

	
}
