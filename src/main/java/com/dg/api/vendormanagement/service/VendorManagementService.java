package com.dg.api.vendormanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.api.vendormanagement.entity.UserMasterData;
import com.dg.api.vendormanagement.entity.VendorIndividual;
import com.dg.api.vendormanagement.entity.VendorIndividualRoles;
import com.dg.api.vendormanagement.entity.VendorRole;
import com.dg.api.vendormanagement.exception.ApplicationException;
import com.dg.api.vendormanagement.model.VendorIndividualDto;
import com.dg.api.vendormanagement.model.VendorRoleDto;
import com.dg.api.vendormanagement.repository.VendorIndividualRepository;
import com.dg.api.vendormanagement.repository.VendorIndividualRolesRepository;
import com.dg.api.vendormanagement.repository.VendorRoleRepository;
import com.dg.api.vendormanagement.request.VendorIndividualRequest;
import com.dg.api.vendormanagement.request.VendorSkills;
import com.dg.api.vendormanagement.response.ApplicationResponse;
import com.dg.api.vendormanagement.response.VendorIndividualResponse;
import com.dg.api.vendormanagement.response.VendorRoleResponse;
import com.dg.api.vendormanagement.util.Constants;
import com.dg.api.vendormanagement.util.ResponseHelper;
import com.dg.api.vendormanagement.util.VendorMgmErrorCode;

@Service
public class VendorManagementService {

	Logger logger = LoggerFactory.getLogger(VendorManagementService.class);

	@Autowired
	private VendorIndividualRepository vendorIndividualRepository;

	@Autowired
	private VendorRoleRepository vendorRoleRepository;
	
	@Autowired
	VendorIndividualRolesRepository vendorIndividualRolesRepository;
	
	@Autowired
	private ResponseHelper helper;
	
	@Transactional
	public ApplicationResponse saveOrUpdateVendorIndividual(Long userId,
			VendorIndividualRequest vendorIndividualRequest, HttpServletRequest request) throws ApplicationException {

		logger.info("Before Save Vendor Individual in database::::" + System.currentTimeMillis());
		String errMesg = "";
		try {
			//VendorIndividualDto vendorIndividualDto = null;
			//List<VendorIndividualDto> vendorIndividualDtoList = new ArrayList<VendorIndividualDto>();
			List<VendorIndividualDto> vendorIndividualDtoList = null;
			VendorIndividual vendorIndividual = vendorIndividualRepository.findByUserId(userId);
			//List<VendorIndividualRoles> vendorIndividualRolesLists = vendorIndividual.getVendorIndividualRoles();
			List<VendorSkills> skills = vendorIndividualRequest.getSkills();
			List<VendorIndividualRoles> vendorIndividualRolesList = new ArrayList<VendorIndividualRoles>();
			if (vendorIndividual != null) {
				vendorIndividual = saveOrUpdateVendorInd(userId, vendorIndividualRequest, vendorIndividual);
				vendorIndividualRepository.save(vendorIndividual);
				
				vendorIndividualRolesRepository.deleteByVendorIndId(vendorIndividual.getVendorIndId());
				for (VendorSkills vendorSkill : skills) {
					VendorIndividualRoles vendorIndividualRoles = saveOrUpdateVendorIndRoles(
							vendorIndividualRequest, vendorSkill, vendorIndividual, new VendorIndividualRoles());
					vendorIndividualRolesList.add(vendorIndividualRoles);
				}
				vendorIndividualRolesRepository.saveAll(vendorIndividualRolesList);
				

			} else {
				VendorIndividual vendorIndividuals = new VendorIndividual();
				saveOrUpdateVendorInd(userId, vendorIndividualRequest, vendorIndividuals);
				vendorIndividuals = vendorIndividualRepository.save(vendorIndividuals);
				
				if (vendorIndividualRequest.getIsOtherSkill().equalsIgnoreCase("yes")) {
					for (VendorSkills vendorSkill : skills) {
						VendorIndividualRoles vendorIndRoles = new VendorIndividualRoles();
						VendorIndividualRoles vendorIndividualRoles = saveOrUpdateVendorIndRoles(
								vendorIndividualRequest, vendorSkill, vendorIndividuals, vendorIndRoles);
						vendorIndividualRolesList.add(vendorIndividualRoles);
					}
					vendorIndividualRolesRepository.saveAll(vendorIndividualRolesList);
				} else {
					VendorIndividualRoles vendorIndRoles = new VendorIndividualRoles();
					VendorIndividualRoles vendorIndividualRoles = saveOrUpdateVendorIndRoles(vendorIndividualRequest,
							skills.get(0), vendorIndividuals, vendorIndRoles);
					vendorIndividualRolesRepository.save(vendorIndividualRoles);
				}

			}
			VendorIndividualResponse vendorIndividualResponse = VendorIndividualResponse.builder()
					.vendorIndividuals(vendorIndividualDtoList).build();

			logger.info("After Save Vendor Individual in database::::" + System.currentTimeMillis());

			return helper.buildSuccessResponse(vendorIndividualResponse, "Added/Updated Vendor Individual successfully");

		} catch (EntityNotFoundException e) {
			logger.error("saveVendorIndividual -> Failed to update Vendor Individual Details. Exception :{}",
					e.getMessage());
		} catch (Exception e) {
			logger.error("saveVendorIndividual -> Failed to Save Vendor Individual. Exception :{}", e.getMessage());
			errMesg = e.getMessage();
			throw new ApplicationException(Constants.APPLICATION_EXCEPTION,
					"Failed to Save Vendor Individual." + errMesg);
		}
		return helper.buildErrorResponse(null, VendorMgmErrorCode.APPLICATION_EXCEPTION,
				"Error while saving Data. Please retry");
	}

	private VendorIndividualRoles saveOrUpdateVendorIndRoles(VendorIndividualRequest vendorIndividualRequest,
			VendorSkills vendorSkill, VendorIndividual vendorIndividual, VendorIndividualRoles vendorIndividualRole) {
		// vendorIndividualRole.setVendorIndId(vendorIndividual);
		vendorIndividualRole.setVendorIndividual(vendorIndividual);
		vendorIndividualRole.setVendorRoleId(vendorSkill.getId());
		vendorIndividualRole.setIsActive(vendorIndividualRequest.getIsActive());
		vendorIndividualRole.setInsertedDt(new Date());
		vendorIndividualRole.setUpdatedDt(new Date());
		vendorIndividualRole.setIsPrimary(vendorSkill.getIsPrimary());
		vendorIndividualRole.setVendExperience(vendorSkill.getExpr());
		return vendorIndividualRole;
	}

	private VendorIndividual saveOrUpdateVendorInd(Long userId, VendorIndividualRequest vendorIndividualRequest,
			VendorIndividual vendorIndividual) {
		UserMasterData userMasterData = new UserMasterData();
		userMasterData.setUserId(userId);
		//vendorIndividual.setUserMasterData(userMasterData);
		vendorIndividual.setUserId(userId);
		vendorIndividual.setVendorStatusId(vendorIndividualRequest.getVendorStatusId());
		vendorIndividual.setInsertedDt(new Date());
		vendorIndividual.setUpdatedDt(new Date());
		vendorIndividual.setAboutMe(vendorIndividualRequest.getAboutMe());
		return vendorIndividual;
	}

	

	public ApplicationResponse getAllVendorIndividualRules() throws ApplicationException {
		List<VendorRole> vendorRoleList = vendorRoleRepository.getAllVendorIndividualRules();
		String errMesg = "";
		try {
			VendorRoleResponse vendorRolesResponse = buildVendorRolesResponse(vendorRoleList);
			return helper.buildSuccessResponse(vendorRolesResponse, "Getting All Vendor Role details.");
			
		}catch (EntityNotFoundException e) {
			logger.error("getAllVendorIndividualRules  -> Failed to get all vendor individual Roles. Exception :{}",
					e.getMessage());
		} catch (Exception e) {
			logger.error("getAllVendorIndividualRules -> Failed to get all vendor individual Role. Exception :{}", e.getMessage());
			errMesg = e.getMessage();
			throw new ApplicationException(Constants.APPLICATION_EXCEPTION,
					"Failed to Failed to get all vendor Roles Details." + errMesg);
		}
		return helper.buildErrorResponse(null, VendorMgmErrorCode.APPLICATION_EXCEPTION,
				"Error while get all vendor individual Roles. Please retry");

	}
	
	public ApplicationResponse getAllVendorRules() throws ApplicationException {
		List<VendorRole> vendorRoleList = vendorRoleRepository.findAll();
		String errMesg = "";
		try {
			VendorRoleResponse vendorRolesResponse = buildVendorRolesResponse(vendorRoleList);
			return helper.buildSuccessResponse(vendorRolesResponse, "Getting All Vendor Role details.");
			
		}catch (EntityNotFoundException e) {
			logger.error("getAllVendorRules  -> Failed to get all vendor Roles. Exception :{}",
					e.getMessage());
		} catch (Exception e) {
			logger.error("getAllVendorRules -> Failed to get all vendor Role. Exception :{}", e.getMessage());
			errMesg = e.getMessage();
			throw new ApplicationException(Constants.APPLICATION_EXCEPTION,
					"Failed to Failed to get all vendor Roles Details." + errMesg);
		}
		return helper.buildErrorResponse(null, VendorMgmErrorCode.APPLICATION_EXCEPTION,
				"Error while get all vendor Roles. Please retry");

	}

	private VendorRoleResponse buildVendorRolesResponse(List<VendorRole> vendorRoleList) {
		List<VendorRoleDto> vendorRoleDtoList = new ArrayList<VendorRoleDto>();
		VendorRoleDto vendorRoleDto = null;
		if (!vendorRoleList.isEmpty() && vendorRoleList.size() > 0) {
			for (VendorRole vendorRole : vendorRoleList) {
				vendorRoleDto = VendorRoleDto.builder().vendorRoleId(vendorRole.getVendorRoleId())
						.vendorRoleName(vendorRole.getVendorRoleName()).vendorRoleDesc(vendorRole.getVendorRoleDesc())
						.isVendInd(vendorRole.getIsVendInd()).isVendOrg(vendorRole.getIsVendOrg()).build();
				vendorRoleDtoList.add(vendorRoleDto);
			}
		}
		VendorRoleResponse vendorRolesResponse = VendorRoleResponse.builder().vendorRoleList(vendorRoleDtoList)
				.build();
		return vendorRolesResponse;
	}

}
