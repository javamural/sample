package com.dg.api.vendormanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg.api.vendormanagement.exception.ApplicationException;
import com.dg.api.vendormanagement.request.VendorIndividualRequest;
import com.dg.api.vendormanagement.response.ApplicationResponse;
import com.dg.api.vendormanagement.service.VendorManagementService;

@RestController
@RequestMapping("/vendors")
public class VendorManagementController {

	@Autowired
	private VendorManagementService vendorManagementService;

	@PostMapping(value = "/addVendorIndividual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationResponse addVendorIndividual(@RequestHeader("DG_UserId") Long userId,
			@RequestBody @NotNull @NotBlank VendorIndividualRequest vendorIndividualRequest,
			HttpServletRequest httpRequest) throws ApplicationException {
		return vendorManagementService.saveOrUpdateVendorIndividual(userId, vendorIndividualRequest, httpRequest);
	}
	
	@GetMapping(value = "/vendorInd/services", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationResponse getAllVendorIndividualRules(@RequestHeader("DG_UserId") Long userId,
			HttpServletRequest httpRequest) throws ApplicationException {
		return vendorManagementService.getAllVendorIndividualRules();
	}
	
	@GetMapping(value = "/services", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationResponse getAllVendorRules(@RequestHeader("DG_UserId") Long userId,
			HttpServletRequest httpRequest) throws Exception {
		return vendorManagementService.getAllVendorRules();
	}
	
}
