package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.VendorIndividualDto;
import com.example.entity.VendorIndividual;
import com.example.repository.VendorIndividualRepository;
import com.example.service.VendorIndividualService;

public class VendorIndividualServiceImpl implements VendorIndividualService {

	@Autowired
	VendorIndividualRepository vendorIndividualRepository;
	
	@Override
	public VendorIndividualDto saveVendorInd(VendorIndividualDto venDto) throws Exception {
		VendorIndividual vendorIndividual = VendorIndividual.builder()
				.build();
		return null;
	}

}
