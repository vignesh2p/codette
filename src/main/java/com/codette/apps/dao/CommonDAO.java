package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
public interface CommonDAO {

	public Object getCommunity();
	
	public Object getReligion();

    public Object getDesignation(Integer orgId);

	public Object getId(String entity, String type);

	public Object getStandard(Integer orgId);

	public Object getSection(Integer orgId);

}
