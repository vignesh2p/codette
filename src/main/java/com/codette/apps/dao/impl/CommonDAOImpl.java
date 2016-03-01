package com.codette.apps.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.CommonDAO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.SubjectDTO;
import com.codette.apps.mapper.CommunityRowMapper;
import com.codette.apps.mapper.DesignationRowMapper;
import com.codette.apps.mapper.ReligionRowMapper;
import com.codette.apps.mapper.SectionRowMapper;
import com.codette.apps.mapper.StandardRowMapper;
import com.codette.apps.mapper.SubjectRowMapper;
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
	public Object getCommunity(Integer orgId) {
		List<CommunityDTO> communityList = new ArrayList<CommunityDTO>();
		String communities = "SELECT * FROM community WHERE ID_ORGANIZATION = "+orgId;
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
	public Object getReligion(Integer orgId) {
		List<ReligionDTO> religionList = new ArrayList<ReligionDTO>();
		String religion = "SELECT * FROM religion  WHERE ID_ORGANIZATION = "+orgId;
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
	public Object getId(String entity, String type, Integer orgId) {
	 String GET_ID = "SELECT ID FROM "+type+" WHERE "+type+" = "+ commonUtil.stringFeilds(entity)+" AND ID_ORGANIZATION = "+orgId;
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
	public Object getAcademinYearId(Date date,Integer orgId) {
		String getAcademicYearId = "SELECT ID FROM YEAR WHERE ID_ORGANIZATION = "+orgId+" AND end_date > CURDATE() order by end_date desc ";
		return getJdbcTemplate().queryForObject(getAcademicYearId,Integer.class);
	}

	@Override
	public Object getSubject(Integer orgId) {
		// TODO Auto-generated method stub
		List<SubjectDTO> subjectList = new ArrayList<SubjectDTO>();
		String subject = "SELECT * FROM SUBJECT WHERE ID_ORGANIZATION = "+orgId;
		try{
			subjectList =  getJdbcTemplate().query(
					subject,new SubjectRowMapper());
		}
		catch (Exception e){
			System.out.println( e.getMessage());
			   
		}		
		return subjectList;
	}

	@Override
	public Object getclassId(Integer standardId, Integer sectionId,Integer orgId) {
		
		String getClassId = "SELECT ID FROM CLASSES WHERE ID_ORGANIZATION = "+orgId+" AND ID_STANDARD = "+standardId+" AND ID_SECTION = "+sectionId;
		return getJdbcTemplate().queryForObject(getClassId,Integer.class);
	}




	
}
