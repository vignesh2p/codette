package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
public interface CommonDAO {

	public List<CommunityDTO> getCommunity();
	
	public List<ReligionDTO> getReligion();

    public List<DesignationDTO> getDesignation(Integer integer);

	public Integer getId(String entity, String type);

	public List<StandardDTO> getStandard(Integer integer);

	public List<SectionDTO> getSection(Integer integer);

}
