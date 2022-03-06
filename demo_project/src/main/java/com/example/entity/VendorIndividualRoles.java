package com.example.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_individual_roles")
public class VendorIndividualRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_ind_role_id")
	private Integer vendorIndRoleId;
	
	@Column(name = "vendor_ind_id")
	private Long vendorIndId;
	
	@Column(name = "is_active")
	private Integer isActive;
	
	@Column(name = "is_primary")
	private Integer isPrimary;
	
	@Column(name = "vend_experience")
	private Integer experience;
	
	@Column(name = "vendor_role_id")
	private Integer vendorRoleId;
	
	@Column(name = "inserted_dtm")
	private Date insertedDate;
	
	@Column(name = "updated_dtm")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "vendor_ind_id", nullable = false)
	private VendorIndividual vendorIndividual;
	
}
