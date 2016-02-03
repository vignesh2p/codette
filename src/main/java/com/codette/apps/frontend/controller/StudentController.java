package com.codette.apps.frontend.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codette.apps.frontend.model.Student;
import com.codette.apps.frontend.service.StudentService;
import com.codette.apps.util.CommonConstants;

/**
 * @author Vignesh
 * 
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Resource
	StudentService studentService;

	 @RequestMapping(value = "/standard/{standardId}/section/{sectionId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> getStudentsList(@PathVariable(value="standardId")String standardId, 
			 @PathVariable(value="sectionId")String sectionId, HttpSession session){
		 List<Student> studentsList = null;
		try {
			studentsList = studentService.getStudentsList(standardId, sectionId, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return new ResponseEntity<List<Student>>(studentsList, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/classes", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> getClassesList(HttpSession session) {
		 Object classesList = null;
		try {
			String userRole = session.getAttribute(CommonConstants.SESSION_USERROLE).toString();
			System.out.println("userRole----------"+userRole);
			if(userRole.equalsIgnoreCase("ADMIN")){
				classesList = studentService.getClassesList(session);
			}else{
				classesList = studentService.getClassesListByStaff(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<Object>(classesList, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(value ="/create")
	 public ResponseEntity<?> createStudent(@RequestBody Student student, HttpSession session){
		 Object obj = null;
		 System.out.println("student--"+gson.toJson(student));
		 try {
			 obj = studentService.createStudent(student, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj , HttpStatus.ACCEPTED);
	 }

	 @RequestMapping(value ="/classTeacher")
	 public ResponseEntity<?> getStudentsByStaff(HttpSession session){
		 List<Student> studentList = null;
		 try {
			String staffId = session.getAttribute(CommonConstants.SESSION_USER_ID).toString();
			String role = session.getAttribute(CommonConstants.SESSION_USERROLE).toString();	 
			if(role.equalsIgnoreCase(CommonConstants.TEACHING_STAFF)){
				studentList = studentService.getStudentsByStaffClass(staffId, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Student>>(studentList , HttpStatus.ACCEPTED);
	 }
	 
	 
}
