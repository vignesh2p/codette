package com.codette.apps.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.CommonDAO;
import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.BloodGroupDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.StudentRelationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class CommonService {
	
	final static Logger logger = Logger.getLogger(CommonService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonDAO commonDAO;
	
	public Object getCommunity() {
		// TODO Auto-generated method stub
		return commonDAO.getCommunity();
	}
	
	public Object getReligion() {
		// TODO Auto-generated method stub
		return commonDAO.getReligion();
	}
	
	public Object getDesignation(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getDesignation(orgId);
	}

	public Object getStandard(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getStandard(Integer.valueOf(orgId));
	}

	public Object getSection(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getSection(orgId);
	}

  public Integer getAcademicYearId(){
	  Calendar cal = Calendar.getInstance();
	  Date date = cal.getTime();
	  return commonDAO.getAcademinYearId(date);
  }
	
	
	
	
	//common methods used to get basic ids 
	
	
	
	public Integer getId(String entity, String type){
		Object object = commonDAO.getId(entity, type);
		if(object instanceof Integer){
			return (Integer) object;
		}
		return null;

	}
	
	

	public UserDTO getBasicIds(UserDTO userDTO){
		GenderDTO genderDTO = null;
		RoleDTO roleDTO = null;
		ReligionDTO religionDTO =null;
		CommunityDTO communityDTO = null;
		DesignationDTO designationDTO = null;
		BloodGroupDTO bloodGroup = null;
		StandardDTO standard = null;
		SectionDTO section = null; 
		StudentRelationDTO relation = null;
			
		if(userDTO.getStandard() != null){
				standard = userDTO.getStandard() ;
				standard.setId(getId(standard.getStandard(), CommonConstants.STANDARD));
				userDTO.setStandard(standard);
			}
		if(userDTO.getSection() != null){
				section = userDTO.getSection() ;
				section.setId(getId(section.getSection(), CommonConstants.SECTION));
				userDTO.setSection(section);
			}
		if(userDTO.getGender() != null){
			genderDTO = userDTO.getGender() ;
			genderDTO.setId(getId(genderDTO.getGender(), CommonConstants.GENDER));
			userDTO.setGender(genderDTO);
		}
		if(userDTO.getRole() != null){
			roleDTO = userDTO.getRole() ;
			roleDTO.setId(getId(roleDTO.getRole(), CommonConstants.ROLE));
			userDTO.setRole(roleDTO);
		}
		if(userDTO.getReligion() != null){
			religionDTO = userDTO.getReligion() ;
			religionDTO.setId(getId(religionDTO.getReligion(), CommonConstants.RELIGION));
			userDTO.setReligion(religionDTO);
		}
		if(userDTO.getCommunity() != null){
			communityDTO = userDTO.getCommunity() ;
			communityDTO.setId(getId(communityDTO.getCommunity(), CommonConstants.COMMUNITY));
			userDTO.setCommunity(communityDTO);
		}
		if(userDTO.getDesignation() != null){
			designationDTO = userDTO.getDesignation() ;
			designationDTO.setId(getId(designationDTO.getDesignation(), CommonConstants.DESIGNATION));
			userDTO.setDesignation(designationDTO);
		}
		if(userDTO.getBloodGroup() != null){
			bloodGroup = userDTO.getBloodGroup() ;
			bloodGroup.setId(getId(bloodGroup.getBloodGroup(), CommonConstants.BLOOD_GROUP));
			userDTO.setBloodGroup(bloodGroup);
		}
		if(userDTO.getPhoneNumbers()!= null){
		for(PhoneNumberDTO phone: userDTO.getPhoneNumbers()){
		 relation = phone.getStudentRelation();
			relation.setId(getId(relation.getStudentRelation(),CommonConstants.RELATION));
			phone.setStudentRelation(relation);
		}
		}
		if(userDTO.getAddresses() != null){
			for(AddressDTO address: userDTO.getAddresses()){
			 relation = address.getStudentRelation();
				relation.setId(getId(relation.getStudentRelation(),CommonConstants.RELATION));
				address.setStudentRelation(relation);
			}

	}
		return userDTO;
	
		
	}
	
	
        /**
         * This method generates random string
         * @return
         */
        public String generateRandomString(){
             
            StringBuffer randStr = new StringBuffer();
            for(int i=0; i<CommonConstants.RANDOM_STRING_LENGTH; i++){
                int number = getRandomNumber();
                char ch = CommonConstants.CHAR_LIST.charAt(number);
                randStr.append(ch);
            }
            return randStr.toString();
        }
         
        /**
         * This method generates random numbers
         * @return int
         */
        private int getRandomNumber() {
            int randomInt = 0;
            Random randomGenerator = new Random();
            randomInt = randomGenerator.nextInt(CommonConstants.CHAR_LIST.length());
            if (randomInt - 1 == -1) {
                return randomInt;
            } else {
                return randomInt - 1;
            }
        }
	
	
}
