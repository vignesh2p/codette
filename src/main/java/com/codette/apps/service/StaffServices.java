package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.dao.StaffDAO;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.StudentAddressDTO;
import com.codette.apps.dto.StudentDTO;
import com.codette.apps.dto.StudentPhoneNumberDTO;
import com.codette.apps.dto.StudentRelationDTO;
import com.codette.apps.util.CommonConstants;


@Component
public class StaffServices {

	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	StaffDAO staffDAO;
	
	@Resource
	CommonService commonService ;
	/**
	 * 
	 * @param studentDTO
	 * @return
	 */
	 @Transactional
	public ResponseBean updateStudent(StudentDTO studentDTO, Integer accessId) {
		studentDTO = getBasicIds(studentDTO);
		return staffDAO.updateStudent(studentDTO, accessId);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	 @Transactional
	public ResponseBean deleteStudent(Integer studentId,Integer phoneNumberId,Integer addressId, Integer accessId) {
		return staffDAO.deleteStudent(studentId,phoneNumberId,addressId, accessId);
	}

	/**
	 * 
	 * @param studentDTO
	 * @return
	 * @throws Exception 
	 */
	 @Transactional
	public ResponseBean createStudent(StudentDTO studentDTO, Integer accessId) throws Exception {
		studentDTO = getBasicIds(studentDTO);
		System.out.println("studentDTO>>>>>>>"+gson.toJson(studentDTO));
		return staffDAO.createStudent(studentDTO, accessId);
	}
	 
	 @Transactional
		public ResponseBean updateAttendence(List<Integer> idStudent, Integer accessId) throws Exception {
			return staffDAO.updateAttendence(idStudent, accessId);
		}

	/**
	 * 
	 * @return
	 */
	public List<StudentDTO> getStudents(String role, String search, Integer standardId, Integer sectionId) {
		System.out.println("role---"+role + "standardId--------"+standardId+"sectionId-------"+sectionId);
		return staffDAO.getStudents(role, standardId, sectionId);
	}

	public List<StaffClassDTO> getClassList(Integer staffId) {
		return staffDAO.getClassList(staffId);
	}
	

	public StudentDTO getStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return staffDAO.getStudent(studentId);
	}

	
	public StudentDTO getBasicIds(StudentDTO studentDTO){
		GenderDTO genderDTO = null;
		ReligionDTO religionDTO =null;
		CommunityDTO communityDTO = null;
		StandardDTO standard = null;
		SectionDTO section = null; 
		StudentRelationDTO relation = null;
		if(studentDTO.getGender() != null){
			genderDTO = studentDTO.getGender() ;
			genderDTO.setId(commonService.getId(genderDTO.getGender(), CommonConstants.GENDER));
			studentDTO.setGender(genderDTO);
		}
	
		if(studentDTO.getReligion() != null){
			religionDTO = studentDTO.getReligion() ;
			religionDTO.setId(commonService.getId(religionDTO.getReligion(), CommonConstants.RELIGION));
			studentDTO.setReligion(religionDTO);
		}
		if(studentDTO.getCommunity() != null){
			communityDTO = studentDTO.getCommunity() ;
			communityDTO.setId(commonService.getId(communityDTO.getCommunity(), CommonConstants.COMMUNITY));
			studentDTO.setCommunity(communityDTO);
		}
		if(studentDTO.getStandard() != null){
			standard = studentDTO.getStandard() ;
			standard.setId(commonService.getId(standard.getStandard(), CommonConstants.STANDARD));
			studentDTO.setStandard(standard);
		}
		if(studentDTO.getSection() != null){
			section = studentDTO.getSection() ;
			section.setId(commonService.getId(section.getSection(), CommonConstants.SECTION));
			studentDTO.setSection(section);
		}
		/*if(studentDTO.getPhoneNumbers()!= null){
			for(StudentPhoneNumberDTO phone: studentDTO.getPhoneNumbers()){
			 relation = phone.getStudentRelation();
				relation.setId(commonService.getId(relation.getRelation(),CommonConstants.RELATION));
				phone.setStudentRelation(relation);
			}
			}
			if(studentDTO.getAddresses() != null){
				for(StudentAddressDTO address: studentDTO.getAddresses()){
				 relation = address.getStudentRelation();
					relation.setId(commonService.getId(relation.getRelation(),CommonConstants.RELATION));
					address.setStudentRelation(relation);
				}
			
		}*/
	
		return studentDTO;
	}

	public List<ClassesDTO> getAllClassList() {
		// TODO Auto-generated method stub
		return staffDAO.getAllClassList();
	}

	public List<AttendenceDTO> getAttendence(Integer staffId) {
		// TODO Auto-generated method stub
		return staffDAO.getAttendence(staffId);
	}

	public ResponseBean enableAttendence(Integer staffId) {
		// TODO Auto-generated method stub
		return staffDAO.enableAttendence(staffId);
	}

}
