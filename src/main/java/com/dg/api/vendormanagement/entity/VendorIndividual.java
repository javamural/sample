package com.dg.api.vendormanagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "vendor_individual")
public class VendorIndividual {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_ind_id", updatable = false)
	private Long vendorIndId;
	
	@Column(name = "vendor_status_id")
	private int vendorStatusId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "about_me")
	private String aboutMe;
	
	@Column(name = "inserted_dtm")
	private Date insertedDt;

	@Column(name = "updated_dtm")
	private Date updatedDt;
	
	
	@OneToMany(mappedBy = "vendorIndividual", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<VendorIndividualRoles> vendorIndividualRoles;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private UserMasterData userMasterData;
}
