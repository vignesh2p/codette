package com.codette.apps.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.controller.OrganizationsController;
import com.codette.apps.dao.OrganizationDAO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.ImageDTO;
import com.codette.apps.dto.NewOrganizationDTO;
import com.codette.apps.dto.OrganizationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.SubjectDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.dto.YearDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrganizationDAOImpl extends NamedParameterJdbcDaoSupport implements OrganizationDAO{

	final static Logger logger = Logger.getLogger(OrganizationsController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Override
	public Object createOrganization(NewOrganizationDTO newOrgDTO) {
		
		Integer orgId = (Integer) CreateNewOrganization(newOrgDTO.getOrganization());
		
		SaveOrganizationLogo(newOrgDTO.getImage(),orgId);
		
		PopulateStandardsForOrganization(newOrgDTO.getStandards(),orgId);
		
		populateSectionForOrganization(newOrgDTO.getSections(),orgId);
		
		createAdminUserForOrganization(newOrgDTO.getAdminUser(),orgId);
		
		populateReligonForOrganization(newOrgDTO.getReligons(),orgId);
		
		populateCommunityForOrganization(newOrgDTO.getCommunities(),orgId);
		
		populateSubjectForOrganization(newOrgDTO.getSubjects(),orgId);
		
		populateAcademicYear(newOrgDTO.getYears(),orgId);
		
		return null;
	}
	
	
	private void populateAcademicYear(List<YearDTO> years, Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private void populateSubjectForOrganization(List<SubjectDTO> subjects,
			Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private void populateCommunityForOrganization(
			List<CommunityDTO> communities, Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private void populateReligonForOrganization(List<ReligionDTO> religons,
			Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private void createAdminUserForOrganization(UserDTO adminUser, Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private void populateSectionForOrganization(List<SectionDTO> sections,
			Integer orgId) {
		// TODO Auto-generated method stub
		
	}


	private Object PopulateStandardsForOrganization(List<StandardDTO> standards,
			Integer orgId) {
				return orgId;
		// TODO Auto-generated method stub
		
	}
	private Object SaveOrganizationLogo(ImageDTO image,Integer orgId) {
		return image;
		// TODO Auto-generated method stub
		
	}
	private Object CreateNewOrganization(OrganizationDTO organization) {
		// TODO Auto-generated method stub
		return null;
	}

}
