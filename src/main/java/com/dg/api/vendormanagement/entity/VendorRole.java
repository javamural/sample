package com.dg.api.vendormanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vendor_role")
public class VendorRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_role_id", updatable = false)
	private Long vendorRoleId;
	
	@Column(name = "vendor_role_name")
	private String vendorRoleName;
	
	@Column(name = "vendor_role_desc")
	private String vendorRoleDesc;
	
	@Column(name = "is_vend_ind")
	private int isVendInd;
	
	@Column(name = "is_vend_org")
	private int isVendOrg;
	
	@Column(name = "inserted_dtm")
	private Date insertedDt;

	@Column(name = "updated_dtm")
	private Date updatedDt;
}
