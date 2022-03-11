package com.dg.api.vendormanagement.response;

import java.util.List;

import com.dg.api.vendormanagement.model.VendorIndividualDto;
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
public class VendorIndividualResponse implements DataResponse {

	private List<VendorIndividualDto> vendorIndividuals;

}
