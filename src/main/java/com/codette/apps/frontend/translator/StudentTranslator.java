/**
 * 
 */
package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.StudentAddressDTO;
import com.codette.apps.dto.StudentDTO;
import com.codette.apps.dto.StudentPhoneNumberDTO;
import com.codette.apps.frontend.model.Class;
import com.codette.apps.frontend.model.Section;
import com.codette.apps.frontend.model.Standard;
import com.codette.apps.frontend.model.Student;
import com.codette.apps.util.CommonConstants;
/**
 * @author Vignesh
 *
 */
@Component
public class StudentTranslator  extends BaseTranslator{

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTranslator.class);
	
	public List<StudentDTO> convertToListOfStudentDTO(Object object) {
		LOGGER.debug("COnverting json to list of StudentDTO");
		Type listType = new TypeToken<List<StudentDTO>>() {}.getType();
		List<StudentDTO> studentDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return studentDTOList;
	}

	public List<Student> translateToStudentList(List<StudentDTO> studentDTOList) {
		LOGGER.debug("Translate list of StudentDTO to list of Student");
		List<Student> studentList = null;
		if(studentDTOList != null && !studentDTOList.isEmpty()){
			studentList = new ArrayList<Student>();
			for(StudentDTO studentDTO : studentDTOList){
				studentList.add(translateToStudent(studentDTO));
			}
		}
		return studentList;
	}

	private Student translateToStudent(StudentDTO studentDTO) {
		LOGGER.debug("Translate StudentDTO to Student");
		Student student = null;
		if(studentDTO!= null){
			student = new Student();
			BeanUtils.copyProperties(studentDTO, student);
			if(studentDTO.getEmailAddress() != null){
				student.setEmailAddresses(studentDTO.getEmailAddress());
			}
			if(studentDTO.getStandard() != null && studentDTO.getSection() != null){
				Class classes = new Class(); Standard standard = new Standard(); Section section = new Section();
				if(studentDTO.getStandard().getId() != null){
					standard.setId(studentDTO.getStandard().getId());
				}
				if(studentDTO.getStandard().getStandard() != null){
					standard.setStandard(studentDTO.getStandard().getStandard());
				}
				if(studentDTO.getSection().getId() != null){
					section.setId(studentDTO.getSection().getId());
				}
				if(studentDTO.getStandard().getStandard() != null){
					section.setSection(studentDTO.getSection().getSection());
				}
				classes.setStandard(standard);
				classes.setSection(section);
			}
		
		}
		return student;
	}
	public StudentDTO translateToStudentDTO(Student student) throws ParseException {
		StudentDTO studentDTO = null;
		if(student != null){
			studentDTO = new StudentDTO();
			
			if(student.getFirstName() != null){
				studentDTO.setFirstName(student.getFirstName());
			}
			
			if(student.getLastName() != null){
				studentDTO.setLastName(student.getLastName());
			}
			if(student.getDateOfBirth() != null){
				studentDTO.setDateOfBirth(commonUtil.formatgivenStringToDate(student.getDateOfBirth(), CommonConstants.DATE_DD_MMMM_YYYY,  CommonConstants.DATE_FORMAT));
			}
			if(student.getDateOfJoining() != null){
				studentDTO.setDateOfJoining(commonUtil.formatgivenStringToDate(student.getDateOfJoining(), CommonConstants.DATE_DD_MMMM_YYYY,  CommonConstants.DATE_FORMAT));
			}
			if(student.getStandard() != null){
				StandardDTO standard = new StandardDTO();
				standard.setId(Integer.valueOf(student.getStandard().getId()));
				standard.setStandard(student.getStandard().getValue());
				studentDTO.setStandard(standard);
			}
			if(student.getSection() != null){
				SectionDTO section = new SectionDTO();
				section.setId(Integer.valueOf(student.getSection().getId()));
				section.setSection(student.getSection().getValue());
				studentDTO.setSection(section);
			}
			if(student.getCommunity() != null){
				CommunityDTO communityDTO = new CommunityDTO();
				communityDTO.setId(Integer.valueOf(student.getCommunity().getId()));
				communityDTO.setCommunity(student.getCommunity().getValue());
				studentDTO.setCommunity(communityDTO);
			}
			if(student.getReligion() != null){
				ReligionDTO religionDTO = new ReligionDTO();
				religionDTO.setId(Integer.valueOf(student.getReligion().getId()));
				religionDTO.setReligion(student.getReligion().getValue());
				studentDTO.setReligion(religionDTO);
			}
			if(student.getFatherName() != null){
				studentDTO.setFatherName(student.getFatherName());
			}
			if(student.getMotherName() != null){
				studentDTO.setMotherName(student.getMotherName());
			}
			if(student.getGender() != null){
				GenderDTO gender = new GenderDTO();
				gender.setGender(student.getGender());
				studentDTO.setGender(gender);
			}
			if(student.getEmailAddresses() != null){
				studentDTO.setEmailAddress(student.getEmailAddresses());
			}
			if(student.getAddresses() != null){
				List<StudentAddressDTO> addresses = new ArrayList<StudentAddressDTO>();
				StudentAddressDTO studentAddressDTO = new StudentAddressDTO();
				studentAddressDTO.setAddress(student.getAddresses());
				addresses.add(studentAddressDTO);
				studentDTO.setAddresses(addresses);
			}
			if(student.getContact() != null){
				List<StudentPhoneNumberDTO> phoneNumbers = new ArrayList<StudentPhoneNumberDTO>();
				StudentPhoneNumberDTO phoneNumber = new StudentPhoneNumberDTO();
				phoneNumber.setPhoneNumber(student.getContact());
				phoneNumbers.add(phoneNumber);
				studentDTO.setPhoneNumber(phoneNumbers);
			}
		}
		return studentDTO;
	}
	

}
