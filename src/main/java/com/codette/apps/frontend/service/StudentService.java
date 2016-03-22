
package com.codette.apps.frontend.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.Student;
import com.codette.apps.frontend.translator.StudentTranslator;
import com.codette.apps.util.CommonConstants;
import com.google.gson.JsonSyntaxException;


@Component

public class StudentService extends BaseService{
	
	@Autowired
	StudentTranslator studentTranslator;
	

	public List<Student> getStudentsList(String standardId, String sectionId, HttpSession session) throws Exception {
		List<UserDTO> studentDTOList = null; 
		List<Student> studentist = null; 
		try {
			HttpEntity<String> requestEntity = prepareGet(session); 
		
			ResponseEntity<Object> response = restTemplate.exchange( getAPIBaseURL()
							+ CommonConstants.STUDENTS_BASE_URL + CommonConstants.STANDARD_URL +"/" + standardId
							+ CommonConstants.SECTION_URL + "/" + sectionId ,
							HttpMethod.GET, requestEntity, Object.class);
			
			System.out.println("response.getBody()>>>>>>>>>>>>>>>>>>>>>"+gson.toJson(response.getBody()));
			studentDTOList = studentTranslator.convertToListOfStudentDTO(response.getBody()); 
			studentist = studentTranslator.translateToStudentList(studentDTOList);
			
		} catch (RestClientException | IOException e) {
			e.printStackTrace();
			throw e;
		}
		return studentist;
		}


	public Object getClassesList(Map<String, String> queryString, HttpSession session) throws Exception {
		ResponseEntity<Object> response = null;
		try {
		 String parameters = translateQueryParams(queryString);
		 HttpEntity<String> requestEntity = prepareGet(session); 
		
		 response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.CLASS_BASE_URL 
				 + CommonConstants.LIST_URL +"?" +parameters, HttpMethod.GET, requestEntity, Object.class);
		
		 System.out.println("response.getBody()--"+gson.toJson(response.getBody()));
		 
		 List<StaffClassDTO> staffClassDTOList = studentTranslator.convertToStaffClassDTOList(response.getBody());
		 return studentTranslator.translateToClassList(staffClassDTOList);
		} catch (RestClientException | IOException e) {
			throw e;
		}
		
	}

	public Object getClassesListByStaff(HttpSession session) throws Exception {
		ResponseEntity<Object> response = null;
		try {
			HttpEntity<String> requestEntity = prepareGet(session); 
		
		 response = restTemplate.exchange( getAPIBaseURL()
							+ CommonConstants.STUDENTS_BASE_URL + CommonConstants.STAFF_CLASSES_URL ,
							HttpMethod.GET, requestEntity, Object.class);
			
		} catch (RestClientException | IOException e) {
			e.printStackTrace();
			throw e;
		}
		return response.getBody();
	}

	

	public List<Student> getStudentsByStaffClass(String staffId, HttpSession session) throws Exception {
		try {
		 ResponseEntity<Object> response = null;
		 HttpEntity<String> requestEntity = prepareGet(session); 
		 List<UserDTO> studentDTOList = null; 
		 List<Student> studentList = null; 
		 response = restTemplate.exchange( getAPIBaseURL()
							+ CommonConstants.STUDENTS_BASE_URL + CommonConstants.STAFF_URL
							+ CommonConstants.SLASH + staffId,
							HttpMethod.GET, requestEntity, Object.class);
			
		 studentDTOList =  studentTranslator.convertToListOfStudentDTO(response.getBody()); 
		 studentList = studentTranslator.translateToStudentList(studentDTOList);
		 return studentList;
		} catch (RestClientException | IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Object createStudent(Student student, HttpSession session) throws Exception {
		UserDTO studentDTO = studentTranslator.translateToStudentDTO(student);
		String postString = gson.toJson(studentDTO);
		ResponseEntity<Object> response = null;
		try {
			HttpEntity<String> entity = preparePost(postString, session);
			System.out.println("postString"+gson.toJson(postString));
			response = restTemplate.exchange( getAPIBaseURL()
					+ CommonConstants.STUDENTS_BASE_URL + CommonConstants.CREATE,
					HttpMethod.POST, entity, Object.class);

			return response.getStatusCode();
		}catch (IOException e) {
			throw e;
		}catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}  
	}
}
