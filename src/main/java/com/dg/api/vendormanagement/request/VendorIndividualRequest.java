package com.dg.api.vendormanagement.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class VendorIndividualRequest {
	
	private int vendorStatusId;
	private String vendorType;
	private List<VendorSkills> skills;
	private String isOtherSkill;
	private List<AvailableTimings> availableTimings;
	private String isActive;
	private String aboutMe;

}
