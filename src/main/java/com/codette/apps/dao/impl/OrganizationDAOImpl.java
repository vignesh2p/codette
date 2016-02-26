package com.codette.apps.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.controller.OrganizationsController;
import com.codette.apps.dao.OrganizationDAO;
import com.codette.apps.dto.ImageDTO;
import com.codette.apps.dto.NewOrganizationDTO;
import com.codette.apps.dto.OrganizationDTO;
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
		return null;
	}
	private Object PopulateStandardsForOrganization(List<String> standards,
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
