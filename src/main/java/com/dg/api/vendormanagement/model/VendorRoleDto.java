package com.dg.api.vendormanagement.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class VendorRoleDto {
	
	private Long vendorRoleId;
	
	private String vendorRoleName;
	
	private String vendorRoleDesc;
	
	private int isVendInd;
	
	private int isVendOrg;
	
	private Date insertedDt;

	private Date updatedDt;
}
