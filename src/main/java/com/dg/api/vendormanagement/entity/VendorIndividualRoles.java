package com.dg.api.vendormanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vendor_individual_roles")
public class VendorIndividualRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_ind_role_id", updatable = false)
	private int vendorIndRoleId;
	
	/*
	 * @Column(name = "vendor_ind_id") private Long vendorIndId;
	 */
	
	@Column(name = "vendor_role_id")
	private int vendorRoleId;
	
	@Column(name = "is_active")
	private String isActive;
	
	@Column(name = "inserted_dtm")
	private Date insertedDt;

	@Column(name = "updated_dtm")
	private Date updatedDt;

	@Column(name = "is_primary")
	private Integer isPrimary;
	
	@Column(name = "vend_experience")
	private Integer vendExperience;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_ind_id", nullable = false)
	private VendorIndividual vendorIndividual;
}
