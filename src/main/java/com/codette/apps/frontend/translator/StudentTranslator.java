
package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.Class;
import com.codette.apps.frontend.model.Section;
import com.codette.apps.frontend.model.Standard;
import com.codette.apps.frontend.model.Student;
import com.codette.apps.util.CommonConstants;
import com.google.gson.reflect.TypeToken;

@Component
public class StudentTranslator  extends BaseTranslator{

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTranslator.class);
	
	public List<UserDTO> convertToListOfStudentDTO(Object object) {
		LOGGER.debug("COnverting json to list of StudentDTO");
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		List<UserDTO> studentDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return studentDTOList;
	}
	
	public List<StaffClassDTO> convertToStaffClassDTOList(Object object) {
		LOGGER.debug("COnverting json to list of StaffClassDTO");
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		List<StaffClassDTO> staffClassDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return staffClassDTOList;
	}

	public List<Student> translateToStudentList(List<UserDTO> studentDTOList) {
		LOGGER.debug("Translate list of StudentDTO to list of Student");
		List<Student> studentList = null;
		if(studentDTOList != null && !studentDTOList.isEmpty()){
			studentList = new ArrayList<Student>();
			for(UserDTO studentDTO : studentDTOList){
				studentList.add(translateToStudent(studentDTO));
			}
		}
		return studentList;
	}

	private Student translateToStudent(UserDTO studentDTO) {
		LOGGER.debug("Translate StudentDTO to Student");
		Student student = null;
		if(studentDTO!= null){
			student = new Student();
			BeanUtils.copyProperties(studentDTO, student);
			if(studentDTO.getEmailAddresses() != null){
				student.setEmailAddresses(studentDTO.getEmailAddresses());
			}
			/*if(studentDTO.getStandard() != null && studentDTO.getSection() != null){
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
		*/
		}
		return student;
	}
	public UserDTO translateToStudentDTO(Student student) throws ParseException {
		UserDTO studentDTO = null;
		ClassesDTO classRoom = null;
		if(student != null){
			studentDTO = new UserDTO();
			
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
			classRoom = new ClassesDTO();
			if(student.getStandard() != null){
				StandardDTO standard = new StandardDTO();
				standard.setId(Integer.valueOf(student.getStandard().getId()));
				standard.setStandard(student.getStandard().getValue());
				classRoom.setStandard(standard);
			}
			if(student.getSection() != null){
				SectionDTO section = new SectionDTO();
				section.setId(Integer.valueOf(student.getSection().getId()));
				section.setSection(student.getSection().getValue());
				classRoom.setSection(section);
			}
			studentDTO.setClassRoom(classRoom);
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
				studentDTO.setEmailAddresses(student.getEmailAddresses());
			}
			if(student.getAddress() != null){
				List<AddressDTO> addresses = new ArrayList<AddressDTO>();
				AddressDTO studentAddressDTO = new AddressDTO();
				studentAddressDTO.setAddress(student.getAddress());
				addresses.add(studentAddressDTO);
				studentDTO.setAddresses(addresses);
			}
			if(student.getContact() != null){
				List<PhoneNumberDTO> phoneNumbers = new ArrayList<PhoneNumberDTO>();
				PhoneNumberDTO phoneNumber = new PhoneNumberDTO();
				phoneNumber.setPhoneNumber(student.getContact());
				phoneNumbers.add(phoneNumber);
				studentDTO.setPhoneNumbers(phoneNumbers);
			}
		}
		return studentDTO;
	}

	public List<Class> translateToClassList(List<StaffClassDTO> staffClassDTOList) {
		if(staffClassDTOList != null && !staffClassDTOList.isEmpty()){
			List<Class> classList = new ArrayList<>();
			for(StaffClassDTO staffClassDTO : staffClassDTOList){
				classList.add(translateToClass(staffClassDTO));
			}
			return classList;
		}
		return null;
	}

	public Class translateToClass(StaffClassDTO staffClassDTO) {
		if(staffClassDTO != null){
			Class classs = new Class();
			
			if(staffClassDTO.getId() != null){
				classs.setId(staffClassDTO.getId());
			}
			
			if(staffClassDTO.getClassRoom() != null ){
				Standard standard = new Standard();
				if(staffClassDTO.getClassRoom().getStandard() != null){
					if(staffClassDTO.getClassRoom().getStandard().getId() != null)
						standard.setId(staffClassDTO.getClassRoom().getStandard().getId());
					
					if(staffClassDTO.getClassRoom().getStandard().getStandard() != null){
						standard.setStandard(staffClassDTO.getClassRoom().getStandard().getStandard());
					}
					classs.setStandard(standard);
				}
				
				if(staffClassDTO.getClassRoom().getSection() != null){
					Section section = new Section();
					if(staffClassDTO.getClassRoom().getSection().getId() != null)
						section.setId(staffClassDTO.getClassRoom().getSection().getId());
					
					if(standard.getStandard() != null){
						section.setSection(staffClassDTO.getClassRoom().getSection().getSection());
					}
					classs.setSection(section);
				}
			}
			return classs;
		}
		
		return null;
	}


}
