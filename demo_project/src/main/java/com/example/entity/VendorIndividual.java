package com.example.entity;

import java.sql.Date;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_individual")
public class VendorIndividual {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_ind_id")
	private Long vendorIndId;
	
	@Column(name = "vendor_status_id")
	private Integer vendorStatusId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "inserted_dtm")
	private Date insertedDate;
	
	@Column(name = "updated_dtm")
	private Date updatedDate;
	
	@Column(name = "about_me")
	private String aboutMe;

}
