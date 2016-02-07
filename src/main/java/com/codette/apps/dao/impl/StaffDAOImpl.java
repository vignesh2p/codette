package com.codette.apps.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

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
import com.codette.apps.dto.UserDTO;
import com.codette.apps.mapper.StudentAddressResultsetExtractor;
import com.codette.apps.mapper.StudentPhoneNumberResultsetExtractor;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StaffDAOImpl extends NamedParameterJdbcDaoSupport implements StaffDAO{
  @Resource
  CommonUtil commonUtil;
  public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
  
	@Override
	/*@Transactional ("transactionManager")*/
	public ResponseBean updateStudent(StudentDTO student, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		
		String UPDATE_STUDENT = "UPDATE `student` SET ";
		if(student.getFirstName() !=null){
			UPDATE_STUDENT = UPDATE_STUDENT + "`FIRST_NAME` = "+student.getFirstName();
	        }
	        if(student.getLastName() != null){
	         UPDATE_STUDENT = UPDATE_STUDENT + "`LAST_NAME` = "+student.getLastName();
	        }
	        if(student.getDateOfBirth() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT +  "`DATE_OF_BIRTH`="+student.getDateOfBirth();
	        }
	        if(student.getStandard() != null && student.getStandard().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + " `ID_STANDARD`= "+student.getStandard().getId();
	        }
	        if(student.getSection() != null && student.getSection().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`ID_SECTION`= "+student.getSection().getId();
	        }
	        if(student.getYear() != null && student.getYear().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`ID_YEAR`= "+student.getYear().getId();
	        }
	        if(student.getAge() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`AGE`= "+student.getAge();
	        }
	        if(student.getFatherName() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`FATHER_NAME`= "+student.getFatherName();
	        }
	        if(student.getBloodGroup() != null && student.getBloodGroup().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`ID_BLOOD_GROUP`= "+student.getBloodGroup().getId();
	        }
	        if(student.getCommunity() != null && student.getCommunity().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + "`ID_COMMUNITY` = "+student.getCommunity().getId();
	        }
	        if(student.getReligion() != null && student.getReligion().getId() != null){
	        UPDATE_STUDENT = UPDATE_STUDENT + " `ID_RELIGION` = "+student.getReligion().getId();
	        }
	        UPDATE_STUDENT = UPDATE_STUDENT + " `IS_DELETED` = 0";
	        UPDATE_STUDENT = UPDATE_STUDENT + "`UPDATED_BY`="+accessId;
	        UPDATE_STUDENT = UPDATE_STUDENT +") WHERE ID = "+student.getId();
	      
	      
	 	   try{
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					   student);
			if(student != null)
			getJdbcTemplate().update(UPDATE_STUDENT, namedParameters, keyHolder );
			//if(student.getAddresses()!= null){
			updateAddress(student.getAddresses(),accessId);
			//}
			// if(student.getPhoneNumbers()!= null){
			updatePhone(student.getPhoneNumbers(),accessId);
			// }
			Number idStudent = keyHolder.getKey();
			student.setId(idStudent.intValue());
			System.out.println("id ------------>"+idStudent);
			if(idStudent != null){
				responseBean.setStatus("SUCCESS");
		        responseBean.setMessage("The new student is added successfully");
			}
		   }catch(Exception e){
			   e.printStackTrace();
			   responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		   }
		return responseBean;
	}
	
	@Override
	public ResponseBean updateAttendence(List<Integer> idStudents, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "UPDATE `attendence` SET IS_ABSENT = 1,UPDATED_BY ="+accessId +" WHERE ID_STUDENT = ?";
		try{
			for(Integer id : idStudents){
				 getJdbcTemplate().update(ATTENDENCE, id);
			}
			responseBean.setStatus("SUCCESS");
	        responseBean.setMessage("The new student is added successfully");
		}catch(Exception e){
			 e.printStackTrace();
			   responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		}
		return responseBean;
	}

	private void updateAddress(List<StudentAddressDTO> addresses,
			Integer accessId) {
        	String UPDATE_ADDRESS = "UPDATE `student_address` SET ";
        	for(StudentAddressDTO address : addresses ){
        		if(address.getAddress()!= null){
        			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`ADDRESS`="+address.getAddress()+",";
        		}
        		if(address.getIsPrimary()!= null){
        			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`IS_PRIMARY`="+address.getIsPrimary()+",";
        		}if(address.getStudentRelation().getId()!= null){
        			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`ID_STUDENT_RELATION`="+address.getStudentRelation().getId()+",";
        		}
        		UPDATE_ADDRESS=UPDATE_ADDRESS+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+address.getId();
        		getJdbcTemplate().update(UPDATE_ADDRESS );
		}
	}

	private void updatePhone(List<StudentPhoneNumberDTO> phoneNumbers,
			Integer accessId) {
		  String UPDATE_PHONE = "UPDATE `student_phone` SET ";
       	for(StudentPhoneNumberDTO phone : phoneNumbers ){
       	if(phone.getPhoneNumber() != null){
       		UPDATE_PHONE = UPDATE_PHONE+ "`PHONE_NUMBER`="+phone.getPhoneNumber()+",";
       	}if(phone.getIsPrimary() != null){
       		UPDATE_PHONE = UPDATE_PHONE+ "`IS_PRIMARY`="+phone.getIsPrimary()+",";
       	}if(phone.getStudentRelation() != null){
       		UPDATE_PHONE = UPDATE_PHONE+ "`ID_STUDENT_RELATION`="+phone.getStudentRelation().getId()+",";
       	}
       		UPDATE_PHONE = UPDATE_PHONE+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+phone.getId();
			getJdbcTemplate().update(UPDATE_PHONE );

       }		
	}
	

	@Override
	public ResponseBean deleteStudent(Integer studentId,Integer phoneNumberId,Integer addressId, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String DELETE_STUDENT = "UPDATE `student` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID ="+studentId;
		String DELETE_ATTENDENCE = "UPDATE `attendence` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID_STUDENT ="+studentId;
		String DELETE_PHONE = "UPDATE `STUDENT_PHONE` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID = "+phoneNumberId;
		String DELETE_ADDRESS = "UPDATE `STUDENT_ADDRESS` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID ="+addressId;
		try{
	   if(studentId != null){
	        int success = getJdbcTemplate().update(DELETE_STUDENT);
           if(success != 0){
		    getJdbcTemplate().update(DELETE_ATTENDENCE);
           }
		}
		if(phoneNumberId!= null){
		getJdbcTemplate().update(DELETE_PHONE);
		}if(addressId != null){
		getJdbcTemplate().update(DELETE_ADDRESS);
		}
		responseBean.setStatus("SUCCESS");
	    responseBean.setMessage("The student is deleted successfully");
		
		}catch(Exception e){
			   e.printStackTrace();
			   responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		   }
		return responseBean;
	}

	@Override
/*	@Transactional ("transactionManager")*/
	public ResponseBean createStudent(StudentDTO student, Integer accessId) {
		
		ResponseBean responseBean = new ResponseBean();
		String CREATE_STUDENT = "INSERT INTO `student`(";
		        if(student.getFirstName() !=null){
				CREATE_STUDENT = CREATE_STUDENT + "`FIRST_NAME`, ";
		        }
		        if(student.getLastName() != null){
		         CREATE_STUDENT = CREATE_STUDENT + "`LAST_NAME`, ";
		        }
		        if(student.getDateOfBirth() != null){
		        CREATE_STUDENT = CREATE_STUDENT +  "`DATE_OF_BIRTH`,";
		        }
		        if(student.getStandard() != null && student.getStandard().getId() != null){
		        CREATE_STUDENT = CREATE_STUDENT + " `ID_STANDARD`, ";
		        }
		        if(student.getSection() != null && student.getSection().getId() != null){
		        CREATE_STUDENT = CREATE_STUDENT + "`ID_SECTION`, ";
		        }
		        if(student.getYear() != null && student.getYear().getId() != null){
		        CREATE_STUDENT = CREATE_STUDENT + "`ID_YEAR`, ";
		        }
		        if(student.getGender() != null ){
			        CREATE_STUDENT = CREATE_STUDENT + "`ID_GENDER`, ";
			        }
		        if(student.getEmailAddress() != null ){
			        CREATE_STUDENT = CREATE_STUDENT + "`EMAIL_ADDRESS`, ";
			        }
		        if(student.getDateOfJoining() != null ){
			        CREATE_STUDENT = CREATE_STUDENT + "`DATE_OF_JOINING`, ";
			        }
		        if(student.getAge() != null){
		        CREATE_STUDENT = CREATE_STUDENT + "`AGE`, ";
		        }
		        if(student.getFatherName() != null){
		        CREATE_STUDENT = CREATE_STUDENT + "`FATHER_NAME`, ";
		        }
		        if(student.getMotherName() != null){
			        CREATE_STUDENT = CREATE_STUDENT + "`MOTHER_NAME`, ";
			        }
		        if(student.getBloodGroup() != null && student.getBloodGroup().getId() != null){
		        		CREATE_STUDENT = CREATE_STUDENT + "`ID_BLOOD_GROUP`, ";
		        }
		        if(student.getCommunity() != null && student.getCommunity().getId() != null){
		        		CREATE_STUDENT = CREATE_STUDENT + "`ID_COMMUNITY`,";
		        }
		        if(student.getReligion() != null && student.getReligion().getId() != null){
		        	CREATE_STUDENT = CREATE_STUDENT + " `ID_RELIGION`,";
		        }
		        CREATE_STUDENT = CREATE_STUDENT + " `IS_DELETED`,";
		        CREATE_STUDENT = CREATE_STUDENT + " `CREATED_ON`, ";
		        CREATE_STUDENT = CREATE_STUDENT + "`CREATED_BY`) VALUES (";
		        
		        		 if(student.getFirstName() !=null){
		     				CREATE_STUDENT = CREATE_STUDENT + commonUtil.stringFeilds(student.getFirstName())+",";
		     		        }
		     		        if(student.getLastName() != null){
		     		         CREATE_STUDENT = CREATE_STUDENT + commonUtil.stringFeilds(student.getLastName())+",";
		     		        }
		     		        if(student.getDateOfBirth() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT +  commonUtil.stringFeilds(student.getDateOfBirth())+",";
		     		        }
		     		        if(student.getStandard() != null && student.getStandard().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getStandard().getId()+",";
		     		        }
		     		        if(student.getSection() != null && student.getSection().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getSection().getId()+",";
		     		        }
		     		        if(student.getYear() != null && student.getYear().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getYear()+",";
		     		        }
		     		       if(student.getGender() != null ){
		   			        CREATE_STUDENT = CREATE_STUDENT + student.getGender().getId()+", ";
		   			        }
		   		           if(student.getEmailAddress() != null ){
		   			        CREATE_STUDENT = CREATE_STUDENT +  commonUtil.stringFeilds(student.getEmailAddress())+", ";
		   			        }
		   		            if(student.getDateOfJoining() != null ){
		   			        CREATE_STUDENT = CREATE_STUDENT + commonUtil.stringFeilds(student.getDateOfJoining())+", ";
		   			        }
		     		        if(student.getAge() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getAge()+",";
		     		        }
		     		        if(student.getFatherName() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + commonUtil.stringFeilds(student.getFatherName())+",";
		     		        }
		     		       if(student.getMotherName() != null){
			     		        CREATE_STUDENT = CREATE_STUDENT + commonUtil.stringFeilds(student.getMotherName())+",";
			     		        }
		     		        if(student.getBloodGroup() != null && student.getBloodGroup().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getBloodGroup()+",";
		     		        }
		     		        if(student.getCommunity() != null && student.getCommunity().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getCommunity().getId() +",";
		     		        }
		     		        if(student.getReligion() != null && student.getReligion().getId() != null){
		     		        CREATE_STUDENT = CREATE_STUDENT + student.getReligion().getId()+",";
		     		        }	
		     		       CREATE_STUDENT = CREATE_STUDENT + "0,NOW(),"+accessId+")";
		     		       
		     		      String ATTENDENCE = "INSERT INTO `attendence`(`ID_STUDENT`, `IS_ABSENT`,`IS_ENABLE`,`IS_DELETED`,`CREATED_ON`,`CREATED_BY`) "
		     		      		+ "VALUES (?,0,0,0,NOW(),"+accessId+")";
		     		      try{
		     		    	  KeyHolder keyHolder = new GeneratedKeyHolder();
		     				   SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
		     						   student);
		     					System.out.println("query   "+CREATE_STUDENT);
		     				   getJdbcTemplate().update(CREATE_STUDENT, namedParameters,keyHolder );
		     				   Number id = keyHolder.getKey();
		     				   if(id!= null){
		     					   student.setId(id.intValue());
		     					   getJdbcTemplate().update(ATTENDENCE, id.intValue());
		     					   Integer idStudent = id.intValue();
		     					   System.out.println("student.getPhoneNumbers()-----"+gson.toJson(student.getPhoneNumbers()));
		     				//	   if(!student.getPhoneNumbers().isEmpty()){
		     							System.out.println("Inside phone number");
		     						insertPhoneNumber(student.getPhoneNumbers(),idStudent,accessId);
		     					//	}
		     						//if(!student.getAddresses().isEmpty()){
		     							System.out.println("Inside address number");
		     						insertAddressses(student.getAddresses(),idStudent,accessId);	
		     						//}
		     				   }
		     					responseBean.setStatus("SUCCESS");
		     					responseBean.setMessage("The new student is added successfully");
		     				
		     			   }catch(Exception e){
		     				   responseBean.setStatus("FAILED");
		     				   String eStr = e.getMessage();
		     					responseBean.setMessage(eStr);
		     			   }
		     			   return responseBean;
	}
	private void insertAddressses(List<StudentAddressDTO> addresses,Integer idStudent,Integer accessId) {
		// TODO Auto-generated method stub
		for(StudentAddressDTO address:addresses){
			String ADDRESS = "INSERT INTO `student_address`(";
			ADDRESS= ADDRESS+"`ID_STUDENT`,";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ " `ADDRESS`,";
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ " `IS_PRIMARY`, ";
		}
		if(address.getStudentRelation()!= null){
			ADDRESS= ADDRESS+ " `ID_STUDENT_RELATION`, ";
		}
		ADDRESS= ADDRESS+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY` ) ";
		ADDRESS= ADDRESS+ "VALUES ";
		ADDRESS= ADDRESS+ "("+idStudent+" , ";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ commonUtil.stringFeilds(address.getAddress())+" , " ;
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ address.getIsPrimary() +" , ";
		}
		if(address.getStudentRelation()!= null){
			ADDRESS= ADDRESS+ +address.getStudentRelation().getId()+" , ";
		}
		ADDRESS= ADDRESS+"0,NOW(),"+accessId+")";
		getJdbcTemplate().update(ADDRESS);

		}
	}
	private void insertPhoneNumber(List<StudentPhoneNumberDTO> phoneNumbers,Integer idStudent,Integer accessId) {
		// TODO Auto-generated method stub
		for(StudentPhoneNumberDTO phoneNumber: phoneNumbers){
			String PhoneNumber = "INSERT INTO `student_phone`(";
				PhoneNumber= PhoneNumber+"`ID_STUDENT`,";
			if(phoneNumber.getPhoneNumber() != null){
				PhoneNumber= PhoneNumber+ " `PHONE_NUMBER`,";
			}
			if(phoneNumber.getIsPrimary()!= null){
				PhoneNumber= PhoneNumber+ " `IS_PRIMARY`, ";
			}
			PhoneNumber= PhoneNumber+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY` ) ";
			PhoneNumber= PhoneNumber+ "VALUES ";
			PhoneNumber= PhoneNumber+ "("+idStudent+" , ";
			if(phoneNumber.getPhoneNumber() != null){
				PhoneNumber= PhoneNumber+ commonUtil.stringFeilds(phoneNumber.getPhoneNumber()) +" , ";
			}
			if(phoneNumber.getIsPrimary()!= null){
				PhoneNumber= PhoneNumber+ phoneNumber.getIsPrimary() +" , ";
			}
			if(phoneNumber.getStudentRelation()!= null){
				PhoneNumber= PhoneNumber +phoneNumber.getStudentRelation().getId()+" , ";
			}
			PhoneNumber= PhoneNumber+"0,NOW(),"+accessId+")";
			getJdbcTemplate().update(PhoneNumber);
		}
	}

	@Override
	public StudentDTO getStudent(Integer studentId) {
		StudentDTO student = new StudentDTO(); 
		String GET_STUDENT_BY_ID = "SELECT * FROM student A "
				+ "LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID "
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID "
				+ "LEFT OUTER JOIN RELIGION REL ON A.ID_RELIGION = REL.ID "
				+ "LEFT OUTER JOIN COMMUNITY C ON A.ID_COMMUNITY = C.ID "
				+  " WHERE A.ID = "+studentId+" AND A.IS_DELETED = 0";		
		try
		{
			student = getJdbcTemplate().query(GET_STUDENT_BY_ID,new ResultSetExtractor<StudentDTO>(){

			@Override
			public StudentDTO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				StudentDTO student = new StudentDTO();
				List<StudentAddressDTO> addresses = new ArrayList<StudentAddressDTO>();
				List<StudentPhoneNumberDTO> phones = new ArrayList<StudentPhoneNumberDTO>(); 
             if(rs.next()){
				
				student.setId(rs.getInt("ID"));
			 
			    
				student.setFirstName(rs.getString("FIRST_NAME"));
				student.setLastName(rs.getString("LAST_NAME"));
				student.setDateOfBirth(rs.getString("DATE_OF_BIRTH").toString());
				student.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				student.setDateOfJoining(rs.getString("DATE_OF_JOINING").toString());
				student.setFatherName(rs.getString("FATHER_NAME"));
				student.setFatherName(rs.getString("MOTHER_NAME"));
				student.setAge(rs.getInt("AGE"));
				
					GenderDTO gender = new GenderDTO();
					gender.setId(rs.getInt("ID_GENDER"));
					gender.setGender(rs.getString("GENDER"));
					student.setGender(gender);
					
					CommunityDTO community = new CommunityDTO();
					community.setId(rs.getInt("ID_COMMUNITY"));
					community.setCommunity("COMMUNITY");
					student.setCommunity(community);
					
					ReligionDTO religion = new ReligionDTO();
					religion.setId(rs.getInt("ID_RELIGION"));
					religion.setReligion("RELIGION");
					student.setReligion(religion);
					
					StandardDTO standard = new StandardDTO();
					standard.setId(rs.getInt("ID_STANDARD"));
					standard.setStandard(rs.getString("STANDARD"));
					student.setStandard(standard);
					
					SectionDTO section = new SectionDTO();
					section.setId(rs.getInt("ID_SECTION"));
					section.setSection(rs.getString("SECTION"));
					
					String GET_ADDRESSES = "SELECT * FROM STUDENT_ADDRESS S LEFT OUTER JOIN STUDENT_RELATION SR"
							+ "ON S.ID_STUDENT_RELATION = SR.ID WHERE S.IS_DELETED = 0 AND S.ID_STUDENT = "+student.getId();
					addresses = getJdbcTemplate().query(GET_ADDRESSES,new StudentAddressResultsetExtractor());
					student.setAddresses(addresses);
					String GET_PHONES = "SELECT * FROM STUDENT_PHONE_NUMBER S LEFT OUTER JOIN STUDENT_RELATION SR"
							+ "ON S.ID_STUDENT_RELATION = SR.ID WHERE S.IS_DELETED = 0 AND S.ID_STUDENT = "+student.getId();
					phones = getJdbcTemplate().query(GET_PHONES,new StudentPhoneNumberResultsetExtractor());
					student.setPhoneNumber(phones);
					student.setSection(section);
             }
			return student;
			}
			
		});
		}catch(Exception e){
			
		}
		return student;
	}

	@Override
	public List<StudentDTO> getStudents(String role , Integer standardId, Integer sectionId) {
		System.out.println("**********role---"+role + "standardId--------"+standardId+"sectionId-------"+sectionId);
		List<StudentDTO> students = new ArrayList<StudentDTO>(); 
		String GET_STUDENTS = "SELECT * FROM student A " 
				+ " LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
				+ " LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID "
				+ " LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID"
				+ " LEFT OUTER JOIN RELIGION REL ON A.ID_RELIGION = REL.ID "
				+ " LEFT OUTER JOIN COMMUNITY C ON A.ID_COMMUNITY = C.ID "
				+ " WHERE A.IS_DELETED = 0 AND STD.ID ="+standardId+" AND SEC.ID ="+sectionId
				+ " ORDER BY A.FIRST_NAME ASC";		
		try
		{
			students = getJdbcTemplate().query(GET_STUDENTS,new ResultSetExtractor<List<StudentDTO>>(){

			@Override
			public List<StudentDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<StudentDTO> students = new ArrayList<StudentDTO>(); 
				while(rs.next()){
					
					StudentDTO student = new StudentDTO();
				student.setId(rs.getInt("ID"));
			 
			    
				student.setFirstName(rs.getString("FIRST_NAME"));
				student.setLastName(rs.getString("LAST_NAME"));
				student.setDateOfBirth(rs.getString("DATE_OF_BIRTH").toString());
				student.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				student.setDateOfJoining(rs.getString("DATE_OF_JOINING").toString());
				student.setFatherName(rs.getString("FATHER_NAME"));
				student.setAge(rs.getInt("AGE"));
				
					GenderDTO gender = new GenderDTO();
					gender.setId(rs.getInt("ID_GENDER"));
					gender.setGender(rs.getString("GENDER"));
					student.setGender(gender);
					
					CommunityDTO community = new CommunityDTO();
					community.setId(rs.getInt("ID_COMMUNITY"));
					community.setCommunity(rs.getString("COMMUNITY"));
					student.setCommunity(community);
					
					ReligionDTO religion = new ReligionDTO();
					religion.setId(rs.getInt("ID_RELIGION"));
					religion.setReligion(rs.getString("RELIGION"));
					student.setReligion(religion);
					
					StandardDTO standard = new StandardDTO();
					standard.setId(rs.getInt("ID_STANDARD"));
					standard.setStandard(rs.getString("STANDARD"));
					student.setStandard(standard);
					
					SectionDTO section = new SectionDTO();
					section.setId(rs.getInt("ID_SECTION"));
					section.setSection(rs.getString("SECTION"));
					student.setSection(section);
				
					students.add(student);
				     
			}
			     return students;
			}
		});
			
		}catch(Exception e){
			
		}
		return students;
	}
	@Override
	public List<StaffClassDTO> getClassList(Integer staffId) {
		System.out.println();
		List<StaffClassDTO> classList = new ArrayList<StaffClassDTO>();
		String CLASS_LIST = "SELECT * FROM `staff_class` A "
				+ "LEFT OUTER JOIN SUBJECT SUB ON A.ID_SUBJECT = SUB.ID "
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID "
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID "
				+ "LEFT OUTER JOIN YEAR Y ON A.ID_YEAR = Y.ID "
				+ "WHERE A.ID_STAFF = "+staffId ;	
	try
		{
			classList = getJdbcTemplate().query(CLASS_LIST, new ResultSetExtractor<List<StaffClassDTO>>(){

			@Override
			public List<StaffClassDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<StaffClassDTO> classes = new ArrayList<StaffClassDTO>(); 
				while(rs.next()){
					
					StaffClassDTO clases = new StaffClassDTO();
				clases.setId(rs.getInt("ID"));
				UserDTO user = new UserDTO();
				user.setId(rs.getInt("ID_STAFF"));
			    clases.setStaff(user);
				clases.setIsClassTeacher(rs.getInt("IS_CLASS_TEACHER"));
				StandardDTO standard = new StandardDTO();
				standard.setId(rs.getInt("ID_STANDARD"));
				standard.setStandard(rs.getString("STANDARD"));
				clases.setStandard(standard);
				SectionDTO section = new SectionDTO();
				section.setId(rs.getInt("ID_SECTION"));
				section.setSection(rs.getString("SECTION"));
				clases.setSection(section);
					
					classes.add(clases);
				     
			}
			     return classes;
			}
		});
			
		}catch(Exception e){
			
		}
		return classList;
	}

	@Override
	public List<ClassesDTO> getAllClassList() {
	String LIST_ALL_CLASS="SELECT * FROM `classes` A "
			+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID "
			+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID ORDER BY STD.ID , SEC.ID";
	
	List<ClassesDTO> classList = new ArrayList<ClassesDTO>();
	try
	{
		classList = getJdbcTemplate().query(LIST_ALL_CLASS, new ResultSetExtractor<List<ClassesDTO>>(){

		@Override
		public List<ClassesDTO> extractData(ResultSet rs) throws SQLException,
				DataAccessException {

			List<ClassesDTO> classes = new ArrayList<ClassesDTO>(); 
			while(rs.next()){
				
				ClassesDTO clases = new ClassesDTO();
				
			StandardDTO standard = new StandardDTO();
			standard.setId(rs.getInt("ID_STANDARD"));
			standard.setStandard(rs.getString("STANDARD"));
			clases.setStandard(standard);
			SectionDTO section = new SectionDTO();
			section.setId(rs.getInt("ID_SECTION"));
			section.setSection(rs.getString("SECTION"));
			clases.setSection(section);
				
				classes.add(clases);
			     
		}
		     return classes;
		}
	});
		
	}catch(Exception e){
		
	}
	return classList;
	}

	@Override
	public List<AttendenceDTO> getAttendence(Integer staffId) {
		List<AttendenceDTO> attendencesList = new ArrayList<AttendenceDTO>(); 
		String CLASS_LIST = "SELECT ID_STANDARD,ID_SECTION FROM `staff_class` "
				+ " WHERE IS_CLASS_TEACHER = 1 AND ID_STAFF = "+staffId;
		
		
		String GET_STUDENTS = "SELECT * FROM ATTENDENCE ATD " 
				+ " LEFT OUTER JOIN STUDENT A ON A.ID = ATD.ID_STUDENT"
				+ " LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID "
				+ " LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID"
				+ " WHERE A.IS_DELETED = 0 AND A.ID_STANDARD =? AND A.ID_SECTION =?"
				+ " ORDER BY A.FIRST_NAME ASC";		
		try
		{
			List<Integer> ids = getJdbcTemplate().query(CLASS_LIST, new ResultSetExtractor<List<Integer>>(){

				public List<Integer> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(rs.getInt("ID_STANDARD"));
					ids.add(rs.getInt("ID_SECTION"));
					return ids;
				}});
			Integer[] inputIds = {ids.get(0),ids.get(1)};
			attendencesList = getJdbcTemplate().query(GET_STUDENTS,inputIds,new ResultSetExtractor<List<AttendenceDTO>>(){

			@Override
			public List<AttendenceDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<AttendenceDTO> attendences = new ArrayList<AttendenceDTO>(); 
				while(rs.next()){		
					
					AttendenceDTO attencence = new AttendenceDTO();
					attencence.setIsAbsent(rs.getInt("IS_ABSENT"));
					attencence.setIsEnable(rs.getInt("IS_ENABLE"));
					
					
					StudentDTO student = new StudentDTO();
				student.setId(rs.getInt("ID")); 
				student.setFirstName(rs.getString("FIRST_NAME"));
				student.setLastName(rs.getString("LAST_NAME"));
				student.setDateOfBirth(rs.getString("DATE_OF_BIRTH").toString());
				student.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				
						
				
    				StandardDTO standard = new StandardDTO();
					standard.setId(rs.getInt("ID_STANDARD"));
					standard.setStandard(rs.getString("STANDARD"));
					student.setStandard(standard);
					
					SectionDTO section = new SectionDTO();
					section.setId(rs.getInt("ID_SECTION"));
					section.setSection(rs.getString("SECTION"));
					student.setSection(section);
				
					attencence.setStudent(student); 
					attendences.add(attencence);
			    }
			     return attendences;
			}
		});
			
		}catch(Exception e){
			
		}
		return attendencesList;
	}

	@Override
	public ResponseBean enableAttendence(Integer staffId) {
		ResponseBean responseBean = new ResponseBean();
		String CLASS_LIST = "SELECT ID_STANDARD,ID_SECTION FROM `staff_class` "
				+ " WHERE IS_CLASS_TEACHER = 1 AND ID_STAFF = "+staffId;
		String ENABLE = "UPDATE ATTENDENCE SET IS_ENABLE = 1 "
				+ " WHERE ID_STUDENT IN (SELECT `ID` FROM STUDENT WHERE ID_STANDARD = ? AND ID_SECTION = ?)";
		try
		{
			List<Integer> ids = getJdbcTemplate().query(CLASS_LIST, new ResultSetExtractor<List<Integer>>(){

				public List<Integer> extractData(ResultSet rs)
						throws SQLException, DataAccessException {
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(rs.getInt("ID_STANDARD"));
					ids.add(rs.getInt("ID_SECTION"));
					return ids;
				}});
			Object[] inputIds = {ids.get(0),ids.get(1)};
			getJdbcTemplate().update(ENABLE, inputIds);
			
			
		}catch(Exception e){
			
		}
		return responseBean;
	}
	



}
