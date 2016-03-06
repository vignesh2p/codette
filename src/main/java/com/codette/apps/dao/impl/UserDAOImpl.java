package com.codette.apps.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.ResultSetExtractor.UserExtractor;
import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dao.AuthenticationDAO;
import com.codette.apps.dao.ClassRoomDAO;
import com.codette.apps.dao.UserDAO;
import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.EmailSenderService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;
import com.codette.apps.util.FiedValidationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO{
	
/*	@Resource
	EmailService emailService;*/
	
	@Resource
	private CommonUtil commonUtil;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserExtractor userExtractor;
	
	@Resource
	private AuthenticationDAO authenticationDAO;
	
	@Resource
	private AttendanceDAO attendanceDAO;
	
	@Resource
	private ClassRoomDAO classRoomDAO;
		
	@Resource
	private EmailSenderService emailSenderService;
	
	final static Logger logger = Logger.getLogger(UserDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	
   
    public  String stringFeilds(String str){
		return "'"+str+"'";
	}
  
	@Override
	public Object createUser(UserDTO user, Integer orgId,
			Integer accessId) throws Exception{
			       ResponseBean responseBean = new ResponseBean();
				   KeyHolder keyHolder = new GeneratedKeyHolder();
				 
				if(user != null){
					 Integer classId = null;
					if(user.getRole() != null && user.getRole().getRole().equalsIgnoreCase(CommonConstants.ROLE_STUDENT)){
						  classId = (Integer)classRoomDAO.createNewClassRoom(orgId,user.getClassRoom().getStandard().getId(),user.getClassRoom().getSection().getId(),
									 user.getId(),  accessId);
						}
					
					// create a new user
					SqlParameterSource sql = null ;
					getNamedParameterJdbcTemplate().update(createNewUser(user,orgId,classId,accessId), sql,keyHolder );
					Number idUser = keyHolder.getKey();
					System.out.println(" >>>>> iduser  " + keyHolder.getKey());
					user.setId(idUser.intValue());
				
				   if(idUser != null){
					Integer id = idUser.intValue();
									
					
					//save phone numbers for the user
					if(user.getPhoneNumbers() != null){
					insertPhoneNumber(user.getPhoneNumbers(),orgId,id,accessId);
					}
					
					//save  address for the user
					if(user.getAddresses() != null){
					insertAddressses(user.getAddresses(),orgId,id,accessId);	
					}
					
					//Create Attendance for the profile
					attendanceDAO.createAttendanceProfile(orgId, id, accessId);
					
					
					// provide authentication for the user
					UserAuthenticationDTO authentication = new UserAuthenticationDTO();
					user.setId(id);
					System.out.println(" >>>>> id  " + user.getId());
					authentication.setUser(user);
					authentication.setUserSecret(commonService.generateRandomString());
					authentication.setUserName(user.getEmailAddresses());
					authentication.setOrgId(orgId);
					authenticationDAO.createPassword(authentication,accessId);
					
					// Notify the user through mail
					/*String msgBody = CommonConstants.USERNAME + authentication.getUserName()
							+ CommonConstants.PASSWORD +authentication.getUserSecret(); 
					emailSenderService.emailNotification(authentication.getUserName(), CommonConstants.CREDENTIALS, msgBody);*/
				}
			 
				}
			return responseBean;
		}
	
	

	@Override
	public Object updateUser(UserDTO user, Integer orgId, Integer accessId, Integer userId) throws Exception{
		ResponseBean responseBean= new ResponseBean();
			   getJdbcTemplate().update(getUpdateUser(user,orgId,accessId) );
			   
			   
			   if(user.getEmailAddresses()!=null){
				  // insertPassword(authentication);
				   UserAuthenticationDTO authentication = new UserAuthenticationDTO();
					authentication.setUser(user);
					authentication.setUserSecret(commonService.generateRandomString());
					authentication.setUserName(user.getEmailAddresses());
					authentication.setOrgId(orgId);
					authenticationDAO.resetPassword(authentication,accessId); 
					
					
					String msgBody = CommonConstants.USERNAME + authentication.getUserName()
							+ CommonConstants.PASSWORD +authentication.getUserSecret(); 
					emailSenderService.emailNotification(authentication.getUserName(), CommonConstants.CREDENTIALS, msgBody);
			   }
			   
			   
			   if(user.getAddresses()!= null){
					updateAddress(user.getAddresses(),orgId,userId,accessId);
					}
			   
			   
			   if(user.getPhoneNumbers()!= null){
					updatePhone(user.getPhoneNumbers(),orgId,userId,accessId);
					 }
			   
		   return responseBean;
	}
	
	

	


	@Override
	public Object getUser(Integer orgId, Integer userId) throws Exception{
		
		Object[] inputs = new Object[]{userId,orgId};
		
		List<AddressDTO> addresses = getJdbcTemplate().query(getAddressListOfUser(),inputs,userExtractor.setAddressList());
	
		
		List<PhoneNumberDTO> phones = getJdbcTemplate().query(getPhoneNumbers(),inputs,userExtractor.setPhonenumber());
		
		
		
		 UserDTO user =  getJdbcTemplate().query(getUserDetails(),inputs,userExtractor.setUserDetails(addresses,phones));
		 
		
		return user;
	}
	

	@Override
	public Object getUsers(Integer orgId, String role, Integer classId,boolean includeDetails,String search) throws Exception {
		Object object = null;
		if(includeDetails){
	       object = getJdbcTemplate().query(getDetailListOfUser(orgId,classId,search,commonService.getId(role, CommonConstants.ROLE)),  userExtractor.setUserListDetails(null, null));
		}else{
	      object = getJdbcTemplate().query(getListOfUser(orgId,classId,search,commonService.getId(role, CommonConstants.ROLE)), userExtractor.setUserList(null, null));
		}	           
	  return object;
}

	
	@Override
	public Object deleteUser(Integer orgId, Integer userId,
			Integer accessId) throws Exception{
				Object[] inputs = new Object []{accessId,userId,orgId};
				getJdbcTemplate().update(DELETE_PHONE,inputs);
				getJdbcTemplate().update(DELETE_ADDRESS,inputs);
				if(userId != null){
			    getJdbcTemplate().update(DELETE_USER,inputs );
			    getJdbcTemplate().update(DELETE_USER_CREDENTIALS,inputs );
			    getJdbcTemplate().update(DELETE_ATTENDENCE,inputs );
		        }
			
		   return "success";
	}


	private Object insertAddressses(List<AddressDTO> addresses,
			Integer orgId,Integer idUser,Integer accessId)throws Exception {
		// TODO Auto-generated method stub
		ResponseBean responseBean= new ResponseBean();
		Object [] inputs = new Object[] {orgId,idUser,accessId};
		for(AddressDTO address:addresses){
			try{
			getJdbcTemplate().update(createAddress(address),inputs); 
			}catch(DataIntegrityViolationException ex){
				throw new FiedValidationException("The address :"+address.getAddress()+" given is too long");
			}
		}
		return responseBean;
	}
	
	
	private Object insertPhoneNumber(List<PhoneNumberDTO> phoneNumbers, Integer orgId,
			Integer idUser,Integer accessId)throws Exception {
		ResponseBean responseBean= new ResponseBean();
		Object [] inputs = new Object[] {orgId,idUser,accessId};
		for(PhoneNumberDTO phoneNumber: phoneNumbers){
			try{
			getJdbcTemplate().update(createPhoneNumber(phoneNumber),inputs);
			}catch(DataIntegrityViolationException ex){
				throw new FiedValidationException("The phone number : "+phoneNumber.getPhoneNumber()+" given is not 10 digit");
			}
		}
		return responseBean;
	}


	private Object updateAddress(List<AddressDTO> addresses, Integer orgId,Integer idUser,
			Integer accessId) throws DataIntegrityViolationException{
        	
		ResponseBean responseBean= new ResponseBean();
        	for(AddressDTO address : addresses ){
        		
        		getJdbcTemplate().update(getUpdateAddress(address,orgId,idUser,accessId));
        		}
			return responseBean;
		}
	
	

	private Object updatePhone(List<PhoneNumberDTO> phoneNumbers,Integer orgId,Integer idUser,
			Integer accessId) throws DataIntegrityViolationException {
		ResponseBean responseBean= new ResponseBean();
       	for(PhoneNumberDTO phone : phoneNumbers ){
       		
		getJdbcTemplate().update(getUpdatePhone(phone,orgId,idUser,accessId));
       	}
		return responseBean;
       	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*==============================================sql====================================================*/


	
	
	
	
	private String getPhoneNumbers() {
		String GET_PHONES = "SELECT * FROM PHONE S "
				+ "WHERE S.IS_DELETED = 0 AND S.ID_USER = ? AND S.ID_ORGANIZATION = ?";
		return GET_PHONES;
	}

	private String getAddressListOfUser() {
		String GET_ADDRESSES = "SELECT * FROM ADDRESS S "
				+ " WHERE S.IS_DELETED = 0 AND S.ID_USER = ? AND S.ID_ORGANIZATION = ?";
		return GET_ADDRESSES;
	}

	private String getUserDetails() {
		String GET_USER = "SELECT * FROM USER A "
				+ "LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
				+ "LEFT OUTER JOIN ORGANIZATION ORG ON A.ID_ORGANIZATION = ORG.ID "
				+ "LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
				+ "LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
				+ "LEFT OUTER JOIN RELIGION REL ON A.ID_RELIGION = REL.ID "
				+ "LEFT OUTER JOIN COMMUNITY C ON A.ID_COMMUNITY = C.ID "
				+ "LEFT OUTER JOIN IMAGE I ON A.ID_IMAGE = I.ID "
				+ "LEFT OUTER JOIN CLASSES CLS ON A.ID_CLASS = CLS.ID "
				+ "LEFT OUTER JOIN STANDARD STD ON CLS.ID_STANDARD = STD.ID "
				+ "LEFT OUTER JOIN SECTION SEC ON CLS.ID_SECTION = SEC.ID "
				+ "LEFT OUTER JOIN YEAR Y ON A.ID_YEAR = Y.ID "
				+ "LEFT OUTER JOIN BLOOD_GROUP BG ON A.ID_BLOOD_GROUP = BG.ID "
				+ " WHERE A.ID = ? AND A.ID_ORGANIZATION = ? AND A.IS_DELETED = 0";
		return GET_USER;
	}
	
	
	private String createPhoneNumber(PhoneNumberDTO phoneNumber) {
		String PhoneNumber = "INSERT INTO `phone`(";
		PhoneNumber= PhoneNumber+"`ID_ORGANIZATION`,`ID_USER`, ";
	if(phoneNumber.getPhoneNumber() != null){
		PhoneNumber= PhoneNumber+ " `PHONE_NUMBER`,";
	}
	if(phoneNumber.getIsPrimary()!= null){
		PhoneNumber= PhoneNumber+ " `IS_PRIMARY`, ";
	}
	PhoneNumber= PhoneNumber+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY` ) ";
	PhoneNumber= PhoneNumber+ "VALUES ";
	PhoneNumber= PhoneNumber+ "(? , ? ,";
	if(phoneNumber.getPhoneNumber() != null){
		PhoneNumber= PhoneNumber+ commonUtil.stringFeilds(phoneNumber.getPhoneNumber())+",";
	}
	if(phoneNumber.getIsPrimary()!= null){
		PhoneNumber= PhoneNumber+ phoneNumber.getIsPrimary() ;
	}
	PhoneNumber= PhoneNumber+",0,NOW(),?)";
		
		return PhoneNumber;
	}

	
	private String createAddress(AddressDTO address) {
			String ADDRESS = "INSERT INTO `address`(";
			ADDRESS= ADDRESS+"`ID_ORGANIZATION`,`ID_USER`, ";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ " `ADDRESS`,";
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ " `IS_PRIMARY`, ";
		}
		ADDRESS= ADDRESS+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY` ) ";
		ADDRESS= ADDRESS+ "VALUES ";
		ADDRESS= ADDRESS+ "(?, ?, ";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ commonUtil.stringFeilds(address.getAddress())+"," ;
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ address.getIsPrimary()+"," ;
		}
		ADDRESS= ADDRESS+"0,NOW(),?)";
		return ADDRESS;
	}

	static String DELETE_USER ="UPDATE `user` SET IS_DELETED = 1,UPDATED_BY = ? WHERE ID = ? AND ID_ORGANIZATION = ?";
	static String DELETE_PHONE = "UPDATE `PHONE` SET IS_DELETED = 1,UPDATED_BY = ? WHERE ID_USER = ? AND ID_ORGANIZATION = ?";
	static String DELETE_ADDRESS = "UPDATE `ADDRESS` SET IS_DELETED = 1,UPDATED_BY =? WHERE ID_USER = ? AND ID_ORGANIZATION = ?";
	static String DELETE_USER_CREDENTIALS = "UPDATE `user_authentication` SET `IS_DELETED`=1,`UPDATED_ON`=NOW(),`UPDATED_BY`=? WHERE ID_USER = ? AND ID_ORGANIZATION = ?";
	static String DELETE_ATTENDENCE = "UPDATE `attendence` SET `IS_DELETED`= 1 ,`UPDATED_ON`= NOW(),`UPDATED_BY`= ? WHERE `ID_USER` = ? AND `ID_ORGANIZATION` = ?";
	
	private String getListOfUser(Integer orgId, Integer classId, String search, Integer idRole) {

		 String GET_USERS = "SELECT `ID`, "
		 		+ "`ID_ORGANIZATION`, "
		 		+ "`ID_ROLE`, "
		 		+ "`REGISTRATION_ID`, "
		 		+ "`FIRST_NAME`, `LAST_NAME`, "
		 		+ "`DATE_OF_BIRTH`, "
		 		+ "`EMAIL_ADDRESS`, "
		 		+ "`EXPERIENCE`, "
		 		+ "`BIO_GRAPHY`, "
		 		+ "`DATE_OF_JOINING`, "
		 		+ "`ID_CLASS`, "
		 		+ "`ID_DESIGNATION`,"
		 		+ " `QUALIFICATION`, "
		 		+ "`ID_GENDER`, "
		 		+ "`ID_YEAR`, "
		 		+ "`FATHER_NAME`, "
		 		+ "`MOTHER_NAME`, "
		 		+ "`AGE`, "
		 		+ "`ID_IMAGE`, "
		 		+ "`ID_RELIGION`, "
		 		+ "`ID_BLOOD_GROUP`,"
		 		+ " `ID_COMMUNITY`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY` FROM user ";
		 if(idRole != null){
				GET_USERS = GET_USERS+ "WHERE ID_ROLE = "+idRole+ " AND ID_ORGANIZATION = "+orgId;
			if(classId != null){
				GET_USERS = GET_USERS+" AND ID_CLASS = "+classId;
			}
		 }
		return GET_USERS;
	}

	private String getDetailListOfUser(Integer orgId, Integer classId, String search, Integer idRole) {
	String GET_USERS = "SELECT * FROM USER A "
			+ "LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
			+ "LEFT OUTER JOIN ORGANIZATION ORG ON A.ID_ORGANIZATION = ORG.ID "
			+ "LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
			+ "LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
			+ "LEFT OUTER JOIN RELIGION REL ON A.ID_RELIGION = REL.ID "
			+ "LEFT OUTER JOIN COMMUNITY C ON A.ID_COMMUNITY = C.ID "
			+ "LEFT OUTER JOIN IMAGE I ON A.ID_IMAGE = I.ID "
			+ "LEFT OUTER JOIN CLASSES CLS ON A.ID_CLASS = CLS.ID "
			+ "LEFT OUTER JOIN STANDARD STD ON CLS.ID_STANDARD = STD.ID "
			+ "LEFT OUTER JOIN SECTION SEC ON CLS.ID_SECTION = SEC.ID "
			+ "LEFT OUTER JOIN YEAR Y ON A.ID_YEAR = Y.ID "
			+ "LEFT OUTER JOIN BLOOD_GROUP BG ON A.ID_BLOOD_GROUP = BG.ID "
			+ " WHERE A.ID_ORGANIZATION = "+orgId+" AND A.IS_DELETED = 0";
				 if(idRole != null){
						GET_USERS = GET_USERS+ " AND A.ID_ROLE = "+idRole;
					if(classId != null){
						GET_USERS = GET_USERS+" AND A.ID_STANDARD = "+classId;
					}
	 }
	return GET_USERS;
	
	}
	private String createNewUser(UserDTO user,Integer orgId,Integer classId, Integer accessId) {
		
		 String INSERT_USER = "INSERT INTO user(";
		 if(user.getOrgId() != null){
			   INSERT_USER = INSERT_USER+ "`ID_ORGANIZATION`,";
		     }
		   if(user.getRole() != null){
			   INSERT_USER = INSERT_USER+ "`ID_ROLE`,";
		     }
		   if(user.getRegistrationNumber()!= null){
			   INSERT_USER = INSERT_USER+ " `REGISTRATION_ID`, ";
	         }
		   if(user.getFirstName()!= null){
		   INSERT_USER = INSERT_USER+ " `FIRST_NAME`, ";
        }
		   if(user.getLastName()!=null){
		   INSERT_USER = INSERT_USER+ "`LAST_NAME`, ";
		   }
		   if(user.getDateOfBirth()!=null){
		   INSERT_USER = INSERT_USER+ "`DATE_OF_BIRTH`, ";
		   }
		   if(user.getEmailAddresses()!=null){
		      INSERT_USER = INSERT_USER+ "`EMAIL_ADDRESS`, ";
		   }
		   if(user.getExperience()!=null){
			   INSERT_USER = INSERT_USER+ "`EXPERIENCE`, ";
			   }
		   if(user.getBioGraphy()!=null){
			   INSERT_USER = INSERT_USER+ "`BIO_GRAPHY`, ";
			   }
		   if(user.getDateOfJoining() !=null){
			   INSERT_USER = INSERT_USER+ "`DATE_OF_JOINING`, ";
			   }
		   if(user.getDesignation() !=null && user.getDesignation().getId()!= null ){
			   INSERT_USER = INSERT_USER+ "`ID_DESIGNATION`, ";
			   }
		   if(user.getQualification() != null){
			   INSERT_USER = INSERT_USER+ "`QUALIFICATION`, ";
			   }
		   if(user.getClassRoom()!= null){
			   INSERT_USER = INSERT_USER+ "`ID_CLASS`,";   
		   }
		   if(user.getGender()!= null){
		   INSERT_USER = INSERT_USER+ "`ID_GENDER`,";
		   }
		   if(user.getIdImage()!= null){
			   INSERT_USER = INSERT_USER+ "`ID_IMAGE`,";
		   }
		   if(user.getFatherName()!= null){
		   INSERT_USER = INSERT_USER+ "`FATHER_NAME`,";
		   }
		   if(user.getMotherName()!= null){
		   INSERT_USER = INSERT_USER+ "`MOTHER_NAME`,";
		   }
		   if(user.getAge() != null){
		   INSERT_USER = INSERT_USER+ " `AGE`,";
		   }
		   if(user.getYear() != null){
			   INSERT_USER = INSERT_USER+ "`ID_YEAR`,";
		   }
		   if(user.getReligion() != null){
		   INSERT_USER = INSERT_USER+ "`ID_RELIGION`,";
		   }
		   if(user.getBloodGroup() != null){
			   INSERT_USER = INSERT_USER+ "`ID_BLOOD_GROUP`,";
			   }
		   if(user.getCommunity() != null){
		   INSERT_USER = INSERT_USER+ "`ID_COMMUNITY`,";
		   }
		   INSERT_USER = INSERT_USER+ "`IS_DELETED`,`CREATED_ON`,`CREATED_BY`)";
		
		   INSERT_USER = INSERT_USER+ " VALUES (";
		   if(user.getOrgId() != null){
			   INSERT_USER = INSERT_USER+ user.getOrgId()+",";
		     }
		   if(user.getRole() != null){
			   INSERT_USER = INSERT_USER+ user.getRole().getId()+",";
		     }
		   if(user.getRegistrationNumber()!= null){
			   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getRegistrationNumber())+",";
	         }
		   if(user.getFirstName()!= null){
		   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getFirstName())+",";
        }
		   if(user.getLastName()!=null){
		   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getLastName())+",";
		   }
		   if(user.getDateOfBirth()!=null){
		   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getDateOfBirth())+",";
		   }
		   if(user.getEmailAddresses()!=null){
			      INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getEmailAddresses())+",";
			   }
		   if(user.getExperience()!=null){
				   INSERT_USER = INSERT_USER+ user.getExperience()+",";
				   }
		    if(user.getBioGraphy()!=null){
				   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getBioGraphy())+",";
				   }
		    if(user.getDateOfJoining() !=null){
				   INSERT_USER = INSERT_USER+ commonUtil.stringFeilds(user.getDateOfJoining())+",";
				   }
		    if(user.getDesignation() !=null && user.getDesignation().getId()!= null){
				   INSERT_USER = INSERT_USER+ user.getDesignation().getId()+",";
				   }
		    if(user.getQualification() != null){
				   INSERT_USER = INSERT_USER+commonUtil.stringFeilds(user.getQualification()) +", ";
				   }
		    if(user.getClassRoom()!= null ){
				   INSERT_USER = INSERT_USER+ classId+",";   
			   }
		   if(user.getGender() != null){
		      INSERT_USER = INSERT_USER+ user.getGender().getId()+",";
		   }
		   if(user.getIdImage()!= null){
			   INSERT_USER = INSERT_USER+ user.getIdImage()+",";
		   }
		   if(user.getFatherName()!= null){
		   INSERT_USER = INSERT_USER+commonUtil.stringFeilds(user.getFatherName())+",";
		   }
		   if(user.getMotherName()!= null){
		    INSERT_USER = INSERT_USER+commonUtil.stringFeilds(user.getMotherName())+",";
			}
		   if(user.getAge() != null){
		   INSERT_USER = INSERT_USER+ user.getAge()+",";
		   }
		   if(user.getYear() != null && user.getYear().getId() != null){
		   INSERT_USER = INSERT_USER+ user.getYear().getId()+",";
		   }
		   if(user.getReligion() != null){
		   INSERT_USER = INSERT_USER+user.getReligion().getId()+",";
		   }
		   if(user.getBloodGroup() != null){
			   INSERT_USER = INSERT_USER+user.getBloodGroup().getId()+",";
			   }
		   if(user.getCommunity() != null){
		   INSERT_USER = INSERT_USER+user.getCommunity().getId()+",";
		   }
		   INSERT_USER = INSERT_USER+"0,NOW(),";
		   INSERT_USER = INSERT_USER+ accessId;
		   INSERT_USER = INSERT_USER+ ")";
	
		return INSERT_USER;
	}
	
	private String getUpdateUser(UserDTO user, Integer orgId,
			Integer accessId) {
		String UPDATE_USER = "UPDATE `user` SET ";
		 if(user.getOrgId() != null){
			 UPDATE_USER = UPDATE_USER+ "`ID_ORGANIZATION` = "+user.getOrgId()+",";
		     }
		   if(user.getRole()!= null){
			   UPDATE_USER = UPDATE_USER+ "`ID_ROLE`="+user.getRole().getId()+",";
		     }
		   if(user.getFirstName()!= null){
		   UPDATE_USER = UPDATE_USER+ " `FIRST_NAME`= "+ commonUtil.stringFeilds(user.getFirstName())+",";
            }
		   if(user.getLastName()!=null){
		   UPDATE_USER = UPDATE_USER+ "`LAST_NAME`="+commonUtil.stringFeilds(user.getLastName())+",";
		   }
		   if(user.getDateOfBirth()!=null){
		   UPDATE_USER = UPDATE_USER+ "`DATE_OF_BIRTH`="+commonUtil.stringFeilds(user.getDateOfBirth())+",";
		   }
		   if(user.getEmailAddresses()!=null){
		      UPDATE_USER = UPDATE_USER+ "`EMAIL_ADDRESS`="+commonUtil.stringFeilds(user.getEmailAddresses())+",";
		   }
		   if(user.getExperience()!=null){
			   UPDATE_USER = UPDATE_USER+ "`EXPERIENCE`="+user.getExperience()+",";
			   }
		   if(user.getBioGraphy()!=null){
			   UPDATE_USER = UPDATE_USER+ "`BIO_GRAPHY`="+commonUtil.stringFeilds(user.getBioGraphy())+",";
			   }
		   if(user.getDateOfJoining() !=null){
			   UPDATE_USER = UPDATE_USER+ "`DATE_OF_JOINING`="+commonUtil.stringFeilds(user.getDateOfJoining())+",";
			   }
		   if(user.getDesignation() !=null){
			   UPDATE_USER = UPDATE_USER+ "`ID_DESIGNATION` = "+user.getDesignation().getId()+",";
			   }
		   if(user.getQualification() != null){
			   UPDATE_USER = UPDATE_USER+ "`QUALIFICATION` = "+commonUtil.stringFeilds(user.getQualification())+",";
			   }
		   if(user.getGender()!= null){
		   UPDATE_USER = UPDATE_USER+ "`ID_GENDER` = "+user.getGender().getId()+",";
		   }
		   if(user.getFatherName()!= null){
		   UPDATE_USER = UPDATE_USER+ "`FATHER_NAME`="+commonUtil.stringFeilds(user.getFatherName())+",";
		   }
		   if(user.getAge() != null){
		   UPDATE_USER = UPDATE_USER+ " `AGE`="+user.getAge()+",";
		   }
		   if(user.getReligion() != null){
		   UPDATE_USER = UPDATE_USER+ "`ID_RELIGION`="+user.getReligion().getId()+",";
		   }
		   if(user.getCommunity() != null){
		   UPDATE_USER = UPDATE_USER+ "`ID_COMMUNITY`="+user.getCommunity().getId()+",";
		   }

		   UPDATE_USER = UPDATE_USER+ "`UPDATED_ON` = NOW() , ";
		   UPDATE_USER = UPDATE_USER+ "`UPDATED_BY` ="+accessId;
		   UPDATE_USER = UPDATE_USER+ " WHERE `ID` ="+user.getId();
		   UPDATE_USER = UPDATE_USER+ " AND `IS_DELETED` = 0 ";
		return UPDATE_USER;
	}

	private String getUpdateAddress(AddressDTO address,Integer orgId, Integer idUser,
			Integer accessId) {
		
		
		if (address.getId() != null){
			String UPDATE_ADDRESS = "UPDATE `address` SET ";
		if(address.getAddress()!= null){
			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`ADDRESS`="+address.getAddress()+",";
		}
		if(address.getIsPrimary()!= null){
			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`IS_PRIMARY`="+address.getIsPrimary()+",";
		}
		UPDATE_ADDRESS=UPDATE_ADDRESS+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+address.getId();
		getJdbcTemplate().update(UPDATE_ADDRESS );
       return UPDATE_ADDRESS;
		}else{
			String ADDRESS = "INSERT INTO `address`(";
			ADDRESS= ADDRESS+"`ID_ORGANIZATION`,";
			ADDRESS= ADDRESS+"`ID_USER`,";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ " `ADDRESS`,";
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ " `IS_PRIMARY`, ";
		}
		ADDRESS= ADDRESS+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY`) ";
		ADDRESS= ADDRESS+ "VALUES ";
		ADDRESS= ADDRESS+ "("+orgId+","+idUser+" , ";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ address.getAddress()+"," ;
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ address.getIsPrimary() ;
		}
		ADDRESS= ADDRESS+",0,NOW(),"+accessId+")";
		return ADDRESS;
		}
		
	}
	
	private String getUpdatePhone(PhoneNumberDTO phone,Integer orgId ,Integer idUser,Integer accessId) {
		if(phone.getId()!= null){
      		 String UPDATE_PHONE = "UPDATE `phone` SET ";
      	if(phone.getPhoneNumber() != null){
      		UPDATE_PHONE = UPDATE_PHONE+ "`PHONE_NUMBER`="+phone.getPhoneNumber()+",";
      	}if(phone.getIsPrimary() != null){
      		UPDATE_PHONE = UPDATE_PHONE+ "`IS_PRIMARY`="+phone.getIsPrimary()+",";
      	}
      		UPDATE_PHONE = UPDATE_PHONE+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+phone.getId();
            return UPDATE_PHONE;      		
      }
      	else{
      		String PhoneNumber = "INSERT INTO `phone`(";
      		PhoneNumber= PhoneNumber+"`ID_ORGANIZATION`,";
      		PhoneNumber= PhoneNumber+"`ID_USER`,";
		if(phone.getPhoneNumber() != null){
			PhoneNumber= PhoneNumber+ " `PHONE_NUMBER`,";
		}
		if(phone.getIsPrimary()!= null){
			PhoneNumber= PhoneNumber+ " `IS_PRIMARY`, ";
		}
		PhoneNumber= PhoneNumber+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY` ) ";
		PhoneNumber= PhoneNumber+ "VALUES ";
		PhoneNumber= PhoneNumber+ "("+orgId+","+idUser+" , ";
		if(phone.getPhoneNumber() != null){
			PhoneNumber= PhoneNumber+ phone.getPhoneNumber() ;
		}
		if(phone.getIsPrimary()!= null){
			PhoneNumber= PhoneNumber+ phone.getIsPrimary() ;
		}
		PhoneNumber= PhoneNumber+",0,NOW(),"+accessId+")";
		return PhoneNumber;
      	}
	}
}
