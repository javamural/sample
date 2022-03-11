package com.dg.api.vendormanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dg.api.vendormanagement.entity.VendorIndividualRoles;

@Repository
public interface VendorIndividualRolesRepository extends JpaRepository<VendorIndividualRoles, Long> {

	@Query("SELECT vendorIndRoles FROM VendorIndividualRoles vendorIndRoles, VendorIndividual vendorInd "
			+ "WHERE vendorIndRoles.vendorIndividual.vendorIndId = ?1 and vendorIndRoles.vendorRoleId = ?2")
	public VendorIndividualRoles findByVendorIndId(Long vendorIndId, Integer vendorRoleId);
	
	public List<VendorIndividualRoles> getAllVendorIndividualByVendorRoleId(int vendorRoleId);
	
	@Modifying
    @Query(value = "delete from vendor_individual_roles where vendor_ind_id =:vendorIndId", nativeQuery = true)
    public int deleteByVendorIndId(@Param("vendorIndId") Long vendorIndId);
}
