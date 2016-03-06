package com.codette.apps.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.CommonDAO;
import com.codette.apps.dto.BloodGroupDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.StudentRelationDTO;
import com.codette.apps.dto.SubjectDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.dto.YearDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class CommonService {
	
	final static Logger logger = Logger.getLogger(CommonService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Autowired
	HttpServletRequest request;
	@Resource
	private CommonDAO commonDAO;
	
	public Object getCommunity(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getCommunity(orgId);
	}
	
	public Object getReligion(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getReligion(orgId);
	}
	
	public Object getDesignation(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getDesignation(orgId);
	}

	public Object getStandard(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getStandard(Integer.valueOf(orgId));
	}
	public Object getSubject(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getSubject(orgId);
	}

	public Object getSection(Integer orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getSection(orgId);
	}

  public Integer getAcademicYearId(){
	  Calendar cal = Calendar.getInstance();
	  Date date = cal.getTime();
	  Integer idYear = (Integer) commonDAO.getAcademinYearId(date,getOrganizationId());
	return idYear;
  }
	
	
  
	public Integer getIdClass(Integer standardId, Integer sectionId) {
		// TODO Auto-generated method stub
		return (Integer) commonDAO.getclassId(standardId,sectionId,getOrganizationId());
	}
	
	
	//common methods used to get basic ids 
	
	
	
	public Integer getId(String entity, String type){
		Object object = commonDAO.getId(entity, type,getOrganizationId());
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
		ClassesDTO classes = null;	
		YearDTO yearDto = new YearDTO();
		
		if(userDTO.getClassRoom() != null){
			classes = userDTO.getClassRoom() ;
			if(classes.getStandard() != null){
					standard = classes.getStandard() ;
					standard.setId(getId(standard.getStandard(), CommonConstants.STANDARD));
					classes.setStandard(standard);
				}
			if(classes.getSection() != null){
					section = classes.getSection() ;
					section.setId(getId(section.getSection(), CommonConstants.SECTION));
					classes.setSection(section);
				}
		userDTO.setClassRoom(classes);
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
		yearDto.setId(getAcademicYearId());
		userDTO.setYear(yearDto);
/*		if(userDTO.getPhoneNumbers()!= null){
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

	}*/
		return userDTO;
	
		
	}
	
	public List<StaffClassDTO> getBasicIds(List<StaffClassDTO> staffClasses){

		StandardDTO standard = null;
		SectionDTO section = null;
		SubjectDTO subject = null;
		List<StaffClassDTO> classes = new ArrayList<StaffClassDTO>();
		ClassesDTO classeRoom = null;
		YearDTO yearDto = new YearDTO();
		for(StaffClassDTO staffClass : staffClasses){	
			if(staffClass.getClassRoom() != null){
				classeRoom = staffClass.getClassRoom() ;
				if(classeRoom.getId() == null){
				if(classeRoom.getStandard() != null){
						standard = classeRoom.getStandard() ;
						standard.setId(getId(standard.getStandard(), CommonConstants.STANDARD));
						classeRoom.setStandard(standard);
					}
				if(classeRoom.getSection() != null){
						section = classeRoom.getSection() ;
						section.setId(getId(section.getSection(), CommonConstants.SECTION));
						classeRoom.setSection(section);
					}
				classeRoom.setId(getIdClass(standard.getId(),section.getId()));
				}else{
					staffClass.setClassRoom(classeRoom);
				}
				staffClass.setClassRoom(classeRoom);
			}
			if(staffClass.getSubject() != null){
				subject = staffClass.getSubject() ;
				subject.setId(getId(subject.getSubject(), CommonConstants.SUBJECT));
				staffClass.setSubject(subject);
			}
			yearDto.setId(getAcademicYearId());
			staffClass.setYear(yearDto);
			classes.add(staffClass);
		}
		return classes;
	
		
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

        private Integer getOrganizationId() {
    		if(request.getHeader(CommonConstants.XORG_ID) != null){
    			return Integer.valueOf(request.getHeader(CommonConstants.XORG_ID));
    		}
    		return null;
    	}

	
	
}
