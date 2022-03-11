package com.dg.api.vendormanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dg.api.vendormanagement.entity.VendorRole;

@Repository
public interface VendorRoleRepository extends JpaRepository<VendorRole, Long> {

	@Query("SELECT vendorRole FROM VendorRole vendorRole WHERE vendorRole.isVendInd = 1")
	List<VendorRole> getAllVendorIndividualRules();
  
	VendorRole getVendorRoleByVendorRoleName(String vendorRoleName);
	
}
