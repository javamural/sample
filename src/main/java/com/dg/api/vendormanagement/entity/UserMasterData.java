package com.dg.api.vendormanagement.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "user_master")
public class UserMasterData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", updatable = false)
	private Long userId;

	@Column(name = "user_mobile_number")
	private Long mobileNum;

	@Column(name = "user_mpin")
	private String mpin;

	@Column(name = "user_name")
	private String uname;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "user_status_id")
	private Long userStatusId;

	@Column(name = "inserted_dtm")
	private Date insertedDt;

	@Column(name = "updated_dtm")
	private Date updatedDt;
	
	@Column(name = "iam_user_id")
	private String iamUserId;
	
	@Column(name = "last_login_time")
	private Date lastLoginTime;
	
	@Column(name = "last_failed_login_time")
	private Date lastFailedLoginTime;
	
	@Column(name = "failed_login_count")
	private Long failedLoginCount;
	
	/*@OneToOne(mappedBy = "userMasterData", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private UserProfilePic userProfilePic; */
	
	@OneToOne(mappedBy = "userMasterData", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private VendorIndividual vendorIndividual;
	
}
