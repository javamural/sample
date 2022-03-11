package com.dg.api.vendormanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dg.api.vendormanagement.entity.VendorIndividual;
import com.dg.api.vendormanagement.util.SqlQueries;

@Repository
public interface VendorIndividualRepository extends JpaRepository<VendorIndividual, Long> {

	@Query(value = "select vi.* from vendor_individual vi where user_id = ?1", nativeQuery = true)
	VendorIndividual findByUserId(Long userId);

	@Query(value = "select vi.* from vendor_individual vi, vendor_individual_roles vir "
			+ "where vi.vendor_ind_id = vir.vendor_ind_id and vir.vendor_role_id = :vendorRoleId", nativeQuery = true)
	List<VendorIndividual> getAllVendorUsersByVendorRoleId(@Param("vendorRoleId") int vendorRoleId);
	
	@Query(value = "select vi.* from vendor_individual vi, vendor_availability_schedule vas "
			+ "where vi.vendor_ind_id = vas.vendor_ind_id and vi.vendor_ind_id = :vendorIndId", nativeQuery = true)
	VendorIndividual getVendorIndividualDetailsByVendorIndId(@Param("vendorIndId") int vendorIndId);
	
	@Query(value = SqlQueries.GET_ALL_VENDOR_IND_BY_SERVICE_ID, nativeQuery = true)
	List<Object[]> getAllVendorIndividualByVendorRoleId(@Param("userId") Long userId, @Param("vendorRoleId") int vendorRoleId);

}
