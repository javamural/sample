package com.dg.api.vendormanagement.model;

import java.util.List;

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
public class VendorIndividualDto {

	private Long vendorIndId;

	//private int vendorStatusId;
	
	private String aboutMe;
	private List<VendorIndividualRolesDto> vendorIndividualRoles;
	private UserDetailsDto userDetails;
	
}
