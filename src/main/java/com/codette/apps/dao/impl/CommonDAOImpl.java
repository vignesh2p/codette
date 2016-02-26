package com.codette.apps.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.CommonDAO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.mapper.CommunityRowMapper;
import com.codette.apps.mapper.DesignationRowMapper;
import com.codette.apps.mapper.ReligionRowMapper;
import com.codette.apps.mapper.SectionRowMapper;
import com.codette.apps.mapper.StandardRowMapper;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonDAOImpl  extends NamedParameterJdbcDaoSupport implements CommonDAO {

	final static Logger logger = Logger.getLogger(CommonDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
		
	@Resource
	CommonUtil commonUtil;
	@Override
	public Object getCommunity() {
		List<CommunityDTO> communityList = new ArrayList<CommunityDTO>();
		String communities = "SELECT * FROM community";
		try{
		  communityList =  getJdbcTemplate().query(
				  communities,new CommunityRowMapper());
		}
		catch (Exception e){
			System.out.println( e.getMessage());
		}
		return communityList;
	}

	@Override
	public Object getReligion() {
		List<ReligionDTO> religionList = new ArrayList<ReligionDTO>();
		String religion = "SELECT * FROM religion";
		try{
		  religionList =  getJdbcTemplate().query(
				  religion,new ReligionRowMapper());
		}
		catch (Exception e){
			System.out.println( e.getMessage());
		}
		
		return religionList;
	}
	@Override
	public Object getDesignation(Integer orgId) {
		List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();
		String designations = "SELECT * FROM designation WHERE ID_ORGANIZATION ="+orgId;
		try{
			designationList =  getJdbcTemplate().query(
					designations,new DesignationRowMapper());
		}
		catch (Exception e){
			System.out.println( e.getMessage());
			   
		}
		
		return designationList;
	}


	@Override
	public Object getId(String entity, String type) {
	 String GET_ID = "SELECT ID FROM "+type+" WHERE "+type+" = "+ commonUtil.stringFeilds(entity);
	 Integer id = getJdbcTemplate().queryForObject(
				GET_ID,Integer.class); 
	return id;
	}


	@Override
	public Object getStandard(Integer orgId) {
		List<StandardDTO> standardList = new ArrayList<StandardDTO>();
		String designations = "SELECT * FROM STANDARD  WHERE ID_ORGANIZATION = "+orgId;
		try{
			standardList =  getJdbcTemplate().query(
					designations,new StandardRowMapper());
		}
		catch (Exception e){
			   System.out.println( e.getMessage());
			   
		}		return standardList;
	}


	@Override
	public Object getSection(Integer orgId) {
		List<SectionDTO> sectionList = new ArrayList<SectionDTO>();
		String section = "SELECT * FROM SECTION  WHERE ID_ORGANIZATION = "+orgId;
		try{
			sectionList =  getJdbcTemplate().query(
					section,new SectionRowMapper());
		}
		catch (Exception e){
			System.out.println( e.getMessage());
			   
		}		
		return sectionList;
	}

	@Override
	public Integer getAcademinYearId(Date date) {
		String getAcademicYearId = "SELECT ID FROM YEAR WHERE end_date > ? order by end_date desc ";
		Object[] inputs = new Object[]{date};
		return getJdbcTemplate().queryForObject(getAcademicYearId, inputs,Integer.class);
	}




	
}
