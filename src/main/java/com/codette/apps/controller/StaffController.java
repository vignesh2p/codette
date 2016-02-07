package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.service.StaffServices;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StudentDTO;
import com.codette.apps.util.CommonConstants;

/**
 * @author Vignesh
 * 
 */
@Controller
@RequestMapping(CommonConstants.STUDENTS_BASE_URL)
public class StaffController{

	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	StaffServices staffServices;

    @RequestMapping(value =  "/staffClasses", method = RequestMethod.GET)
	 @ResponseBody
	 public List<StaffClassDTO> getStudentsList( HttpServletRequest request, HttpSession session){
		request.getHeader(CommonConstants.SESSION_USERROLE);
		 String staffid= request.getHeader(CommonConstants.SESSION_USER_ID);
		 Integer staffId= Integer.valueOf(staffid); 
		 List<StaffClassDTO> staffClassList =  staffServices.getClassList(staffId);
		  return staffClassList;
	 }
	 
	 /**
	  * */
     @RequestMapping(value = "/classes", method = RequestMethod.GET)
	 @ResponseBody
	 public List<ClassesDTO> getAllClassList( HttpServletRequest request, HttpSession session){
		  return staffServices.getAllClassList();
	 }
	 
     @RequestMapping(value =  CommonConstants.STANDARD_URL+"/{standardId}"+ CommonConstants.SECTION_URL+"/{sectionId}",
	 method = RequestMethod.GET)
			@ResponseBody
			public List<StudentDTO> getStudents(@PathVariable(value="standardId")Integer standardId, @PathVariable(value="sectionId")Integer sectionId,
					 @RequestParam( value="search" , required = false) String search, HttpServletRequest requests)  {
						 String role = requests.getHeader(CommonConstants.SESSION_USERROLE);
				if(role != null){
					return staffServices.getStudents(role, search, standardId, sectionId);
				}return null;
			}
     
     
     
    @RequestMapping(value = "/staff/{staffId}", method = RequestMethod.GET)
	@ResponseBody
	public List<AttendenceDTO> getAttendence(@PathVariable( value="staffId") String staffId, HttpServletRequest requests)  {
		 if(staffId != null){
			 System.out.println("staffId======="+staffId);
			return staffServices.getAttendence(Integer.valueOf(staffId));
	   	}
	return null;
	}
     
     
			
			@RequestMapping(value = "/getStudent/{studentId}", method = RequestMethod.GET)
			@ResponseBody
			public StudentDTO getUser(@PathVariable(value ="studentId") Integer studentId) throws Exception {
				System.out.println("Get User>>>>>>>>>>");
				return staffServices.getStudent(studentId);
			}
			
			@RequestMapping(value = "/create", method = RequestMethod.POST)
			@ResponseBody
			public ResponseBean addUsers(@RequestBody StudentDTO studentDTO, HttpServletRequest requests) throws Exception {
				ResponseBean responseBean = null;
				String staffid = requests.getHeader(CommonConstants.SESSION_USER_ID);
				 Integer accessId= Integer.valueOf(staffid); 
				if(accessId != null ){
					System.out.println("accessId>>>>>>>"+accessId);
					accessId = Integer.valueOf(accessId);
					System.out.println("json>>>>>>>>>>>>"+gson.toJson(studentDTO));
					responseBean = staffServices.createStudent(studentDTO, accessId);
				}
				return  responseBean;
			}


			@RequestMapping(value = "updateStudent/{studentId}", method = RequestMethod.PUT)
			@ResponseBody
			public ResponseBean updateStudent(@PathVariable Integer studentId, @RequestBody StudentDTO studentDTO, HttpSession session, HttpServletRequest request) throws Exception {
				Integer accessId = Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));
				ResponseBean responseBean = new ResponseBean();
				responseBean = staffServices.updateStudent(studentDTO, accessId);
				return responseBean;
			}
			@RequestMapping(value = "enable/{staffId}", method = RequestMethod.PUT)
			@ResponseBody
			public ResponseBean enableAttendence(@PathVariable( value="staffId") String staffId, HttpServletRequest requests)  {
				 if(staffId != null){
					 System.out.println("staffId======="+staffId);
					return staffServices.enableAttendence(Integer.valueOf(staffId));
			   	}
			return null;
			}
			
			@RequestMapping(value = "updateAttendance/{studentIds}", method = RequestMethod.PUT)
			@ResponseBody
			public ResponseBean updateAttendence(@PathVariable List<Integer> studentIds, @RequestBody StudentDTO studentDTO, HttpSession session, HttpServletRequest request) throws Exception {
				Integer accessId = Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));
				ResponseBean responseBean = new ResponseBean();
				responseBean = staffServices.updateAttendence(studentIds, accessId);
				return responseBean;
			}

			@RequestMapping(value = "deleteStudent/{studentId}{phoneNumberId}/{addressId})", method = RequestMethod.DELETE)
			@ResponseBody
			public ResponseBean deleteStaff(@PathVariable Integer studentId,Integer phoneNumberId,Integer addressId,HttpServletRequest request, HttpSession session) throws Exception {
				Integer accessId = Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));
				ResponseBean responseBean = new ResponseBean();
				responseBean = staffServices.deleteStudent(studentId,phoneNumberId,addressId, accessId);
				return responseBean;
			}
	 
	 
}
