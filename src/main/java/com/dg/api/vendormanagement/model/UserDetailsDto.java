package com.dg.api.vendormanagement.model;

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
public class UserDetailsDto {

	private Long userId;
	
	private String name;
	
	private Long mobileNumber;
	
	private String rating;
	
	private String comments;
	
	private String distance;
	
	private String imageName;
	
	private String profileImage;
	
	
	//private List<VendorIndividualRolesDto> vendorIndividualRoles;
}
