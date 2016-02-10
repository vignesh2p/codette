package com.codette.apps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.UserDAO;
import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.StaffAddressDTO;
import com.codette.apps.dto.StaffPhoneNumberDTO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.mapper.SessionMapper;
import com.codette.apps.mapper.StaffAddressResultsetExtractor;
import com.codette.apps.mapper.StaffPhoneNumberResultsetExtractor;
import com.codette.apps.mapper.UserListRowMapper;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO{
	
/*	@Resource
	EmailService emailService;*/
	
	@Resource
	CommonUtil commonUtil;
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
   
    public  String stringFeilds(String str){
		return "'"+str+"'";
	}
  
	private void insertAddressses(List<AddressDTO> addresses,Integer idStaff,Integer accessId) {
		// TODO Auto-generated method stub
		for(AddressDTO address:addresses){
			String ADDRESS = "INSERT INTO `staff_address`(";
			ADDRESS= ADDRESS+"`ID_STAFF`,";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ " `ADDRESS`,";
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ " `IS_PRIMARY`, ";
		}
		ADDRESS= ADDRESS+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY`, ) ";
		ADDRESS= ADDRESS+ "VALUES ";
		ADDRESS= ADDRESS+ "("+idStaff+" , ";
		if(address.getAddress() != null){
			ADDRESS= ADDRESS+ address.getAddress() ;
		}
		if(address.getIsPrimary()!= null){
			ADDRESS= ADDRESS+ address.getIsPrimary() ;
		}
		ADDRESS= ADDRESS+"0,NOW(),"+accessId+")";
		getJdbcTemplate().update(ADDRESS);

		}
	}
	private void insertPhoneNumber(List<PhoneNumberDTO> phoneNumbers,Integer idStaff,Integer accessId) {
		// TODO Auto-generated method stub
		for(PhoneNumberDTO phoneNumber: phoneNumbers){
			String PhoneNumber = "INSERT INTO `staff_phone`(";
				PhoneNumber= PhoneNumber+"`ID_STAFF`,";
			if(phoneNumber.getPhoneNumber() != null){
				PhoneNumber= PhoneNumber+ " `PHONE_NUMBER`,";
			}
			if(phoneNumber.getIsPrimary()!= null){
				PhoneNumber= PhoneNumber+ " `IS_PRIMARY`, ";
			}
			PhoneNumber= PhoneNumber+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY`, ) ";
			PhoneNumber= PhoneNumber+ "VALUES ";
			PhoneNumber= PhoneNumber+ "("+idStaff+" , ";
			if(phoneNumber.getPhoneNumber() != null){
				PhoneNumber= PhoneNumber+ phoneNumber.getPhoneNumber() ;
			}
			if(phoneNumber.getIsPrimary()!= null){
				PhoneNumber= PhoneNumber+ phoneNumber.getIsPrimary() ;
			}
			PhoneNumber= PhoneNumber+"0,NOW(),"+accessId+")";
			getJdbcTemplate().update(PhoneNumber);
		}
	}
	
	
	
	
	@Override
	public ResponseBean updateUser(UserDTO user, Integer acessId, Integer userId){
		ResponseBean responseBean= new ResponseBean();
		String UPDATE_USER = "UPDATE `user` SET ";
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
			   UPDATE_USER = UPDATE_USER+ "`ID_DESIGNATION`"+user.getDesignation().getId()+",";
			   }
		   if(user.getGender()!= null){
		   UPDATE_USER = UPDATE_USER+ "`ID_GENDER`"+user.getGender().getId()+",";
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
		   UPDATE_USER = UPDATE_USER+ "`UPDATED_BY` ="+acessId;
		   UPDATE_USER = UPDATE_USER+ " WHERE `ID` ="+userId;
		   UPDATE_USER = UPDATE_USER+ " AND `IS_DELETED` = 0 ";
		   System.out.println("UPDATE_USER>>>>>>>>>>>."+UPDATE_USER);
		   try{
			  
			   if(user.getEmailAddresses()!=null){
				  // insertPassword(authentication);
			   }
			   if(user.getAddresses()!= null){
					updateAddress(user.getAddresses(),userId,acessId);
					}
			   if(user.getPhoneNumbers()!= null){
					updatePhone(user.getPhoneNumbers(),userId,acessId);
					 }
			   getJdbcTemplate().execute(UPDATE_USER );
				responseBean.setStatus("SUCCESS");
				responseBean.setMessage("The new user is added successfully");

		   }
		   catch(DuplicateKeyException dup){
			   responseBean.setStatus("FAILED");
				responseBean.setMessage("This email Id is aleady used by other user");
		   }
		   return responseBean;
	}
	
	private void updateAddress(List<AddressDTO> addresses,Integer idStaff,
			Integer accessId) {
        	String UPDATE_ADDRESS = "UPDATE `staff_address` SET ";
        	for(AddressDTO address : addresses ){
        		if (address.getId() != null){
        		if(address.getAddress()!= null){
        			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`ADDRESS`="+address.getAddress()+",";
        		}
        		if(address.getIsPrimary()!= null){
        			UPDATE_ADDRESS=UPDATE_ADDRESS+ "`IS_PRIMARY`="+address.getIsPrimary()+",";
        		}
        		UPDATE_ADDRESS=UPDATE_ADDRESS+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+address.getId();
        		getJdbcTemplate().update(UPDATE_ADDRESS );
        		}else{
        			String ADDRESS = "INSERT INTO `staff_address`(";
        			ADDRESS= ADDRESS+"`ID_STAFF`,";
        		if(address.getAddress() != null){
        			ADDRESS= ADDRESS+ " `ADDRESS`,";
        		}
        		if(address.getIsPrimary()!= null){
        			ADDRESS= ADDRESS+ " `IS_PRIMARY`, ";
        		}
        		ADDRESS= ADDRESS+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY`, ) ";
        		ADDRESS= ADDRESS+ "VALUES ";
        		ADDRESS= ADDRESS+ "("+idStaff+" , ";
        		if(address.getAddress() != null){
        			ADDRESS= ADDRESS+ address.getAddress() ;
        		}
        		if(address.getIsPrimary()!= null){
        			ADDRESS= ADDRESS+ address.getIsPrimary() ;
        		}
        		ADDRESS= ADDRESS+"0,NOW(),"+accessId+")";
        		getJdbcTemplate().update(ADDRESS);

        		}
		}
	}

	private void updatePhone(List<PhoneNumberDTO> phoneNumbers,Integer idStaff,
			Integer accessId) {
		  String UPDATE_PHONE = "UPDATE `staff_phone` SET ";
       	for(PhoneNumberDTO phone : phoneNumbers ){
       		if(phone.getId()!= null){
       	if(phone.getPhoneNumber() != null){
       		UPDATE_PHONE = UPDATE_PHONE+ "`PHONE_NUMBER`="+phone.getPhoneNumber()+",";
       	}if(phone.getIsPrimary() != null){
       		UPDATE_PHONE = UPDATE_PHONE+ "`IS_PRIMARY`="+phone.getIsPrimary()+",";
       	}
       		UPDATE_PHONE = UPDATE_PHONE+ "`UPDATED_BY`="+accessId+"  WHERE ID = "+phone.getId();
			getJdbcTemplate().update(UPDATE_PHONE );
       }
       	else{
       		String PhoneNumber = "INSERT INTO `staff_phone`(";
			PhoneNumber= PhoneNumber+"`ID_STAFF`,";
		if(phone.getPhoneNumber() != null){
			PhoneNumber= PhoneNumber+ " `PHONE_NUMBER`,";
		}
		if(phone.getIsPrimary()!= null){
			PhoneNumber= PhoneNumber+ " `IS_PRIMARY`, ";
		}
		PhoneNumber= PhoneNumber+ "`IS_DELETED`, `CREATED_ON`, `CREATED_BY`, ) ";
		PhoneNumber= PhoneNumber+ "VALUES ";
		PhoneNumber= PhoneNumber+ "("+idStaff+" , ";
		if(phone.getPhoneNumber() != null){
			PhoneNumber= PhoneNumber+ phone.getPhoneNumber() ;
		}
		if(phone.getIsPrimary()!= null){
			PhoneNumber= PhoneNumber+ phone.getIsPrimary() ;
		}
		PhoneNumber= PhoneNumber+"0,NOW(),"+accessId+")";
		getJdbcTemplate().update(PhoneNumber);
       	}
       	}
	}


	@Override
	public ResponseBean deleter(Integer userId, Integer phoneNumberId,Integer addressId, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String DELETE_USER ="UPDATE `user` SET IS_DELETED = 1,UPDATED_BY ="+accessId +" WHERE USER ="+userId;
		String DELETE_PHONE = "UPDATE `STUDENT_PHONE` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID = "+phoneNumberId;
		String DELETE_ADDRESS = "UPDATE `STUDENT_ADDRESS` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID ="+addressId;
		try{
				if(phoneNumberId!= null){
				getJdbcTemplate().update(DELETE_PHONE);
				}if(addressId != null){
				getJdbcTemplate().update(DELETE_ADDRESS);
				}if(userId != null){
			    getJdbcTemplate().update(DELETE_USER );
		        }
				responseBean.setStatus("SUCCESS");
				responseBean.setMessage("The user is deleted sccessfully");
			
		   }catch(Exception e){
			   responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		   }
		   return responseBean;
	}

	@Override
	public UserDTO getUser(Integer userId) {
		ResponseBean responseBean = new ResponseBean();
		final UserDTO user = new UserDTO();
		 
		 System.out.println(" idddddd "+userId);
		 
		String GET_USER = "SELECT * FROM USER A"
				+ " LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
				+ "LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
				+ "LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
				+ "LEFT OUTER JOIN RELIGION REL ON A.ID_RELIGION = REL.ID "
				+ "LEFT OUTER JOIN COMMUNITY C ON A.ID_COMMUNITY = C.ID"
				+ " WHERE A.ID = "+userId+" and A.IS_DELETED = 0";
		try{
		 UserDTO staff =  getJdbcTemplate().query(GET_USER,new ResultSetExtractor<UserDTO>(){

			@Override
			public UserDTO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				

			    List<UserDTO> users = new ArrayList<UserDTO>();
			    List<StaffAddressDTO> addresses = new ArrayList<StaffAddressDTO>();
				List<StaffPhoneNumberDTO> phones = new ArrayList<StaffPhoneNumberDTO>(); 
			    if(rs.next()){
				user.setId(rs.getInt("ID"));
			    RoleDTO role = new RoleDTO();
				role.setId(rs.getInt("ID"));
			   role.setRole(rs.getString("ROLE"));
			   user.setRole(role);
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
				user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
				user.setExperience(rs.getInt("EXPERIENCE"));
				user.setBioGraphy(rs.getString("BIO_GRAPHY"));
				user.setDateOfJoining(rs.getDate("DATE_OF_JOINING").toString());
				user.setFatherName(rs.getString("FATHER_NAME"));
				user.setAge(rs.getInt("AGE"));
					DesignationDTO designation = new DesignationDTO();
					designation.setId(rs.getInt("ID"));
					designation.setDesignation(rs.getString("DESIGNATION"));
				    user.setDesignation(designation);
					GenderDTO gender = new GenderDTO();
					gender.setId(rs.getInt("ID"));
					gender.setGender(rs.getString("GENDER"));
					user.setGender(gender);
					CommunityDTO community = new CommunityDTO();
					community.setId(rs.getInt("ID"));
					community.setCommunity("COMMUNITY");
					user.setCommunity(community);
					ReligionDTO religion = new ReligionDTO();
					religion.setId(rs.getInt("ID"));
					religion.setReligion("RELIGION");
					user.setReligion(religion);
					String GET_ADDRESSES = "SELECT * FROM STUDENT_ADDRESS S "
							+ " WHERE S.IS_DELETED = 0 AND S.ID_STUDENT = "+user.getId();
					addresses = getJdbcTemplate().query(GET_ADDRESSES,new StaffAddressResultsetExtractor());
					user.setAddresses(addresses);
					String GET_PHONES = "SELECT * FROM STUDENT_PHONE_NUMBER S "
							+ "WHERE S.IS_DELETED = 0 AND S.ID_STUDENT = "+user.getId();
					phones = getJdbcTemplate().query(GET_PHONES,new StaffPhoneNumberResultsetExtractor());
					user.setPhoneNumbers(phones);
			    }
				return user;
			}
			
			  
		  });
		}
		catch (Exception e){
			 responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		}
		return user;
	}
	@Override
	public List<UserDTO> getUsers(String role, Integer stdId, Integer secId) {
		// TODO Auto-generated method stub
		List<UserDTO> userList = new ArrayList<UserDTO>();
	    String GET_USERS = "SELECT * FROM user ";
		String ID_ROLE = "SELECT ID FROM role WHERE ROLE = "+commonUtil.stringFeilds(role);
	try {
	Integer idRole =  getJdbcTemplate().queryForObject(
                ID_ROLE, Integer.class);
		if(idRole != null){
			GET_USERS = GET_USERS+ "WHERE ID_ROLE = "+idRole;
		userList = getJdbcTemplate().query(GET_USERS, new UserListRowMapper());
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return userList;
}
	@Override
	public ResponseBean createUser(UserDTO user, String orgId,
			Integer accessId) {
		{
			   ResponseBean responseBean = new ResponseBean();
			   System.out.println("Insert............");
			   String INSERT_USER = "INSERT INTO user(";
					   if(user.getRole() != null){
						   INSERT_USER = INSERT_USER+ "`ID_ROLE`,";
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
					   if(user.getDesignation() !=null && user.getDesignation().getId()!= null){
						   INSERT_USER = INSERT_USER+ "`ID_DESIGNATION`, ";
						   }
					   if(user.getGender()!= null){
					   INSERT_USER = INSERT_USER+ "`ID_GENDER`,";
					   }
					   if(user.getFatherName()!= null){
					   INSERT_USER = INSERT_USER+ "`FATHER_NAME`,";
					   }
					   if(user.getAge() != null){
					   INSERT_USER = INSERT_USER+ " `AGE`,";
					   }
					   if(user.getReligion() != null){
					   INSERT_USER = INSERT_USER+ "`ID_RELIGION`,";
					   }
					   if(user.getCommunity() != null){
					   INSERT_USER = INSERT_USER+ "`ID_COMMUNITY`,";
					   }
					   INSERT_USER = INSERT_USER+ "`IS_DELETED`,`CREATED_ON`,`CREATED_BY`)";
			   		
					   INSERT_USER = INSERT_USER+ " VALUES (";
					   if(user.getRole() != null){
						   INSERT_USER = INSERT_USER+ user.getRole().getId()+",";
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
					   if(user.getGender() != null){
					   INSERT_USER = INSERT_USER+ user.getGender().getId()+",";
					   }
					   if(user.getFatherName()!= null){
					   INSERT_USER = INSERT_USER+commonUtil.stringFeilds(user.getFatherName())+",";
					   }
					   if(user.getAge() != null){
					   INSERT_USER = INSERT_USER+ user.getAge()+",";
					   }
					   if(user.getReligion() != null){
					   INSERT_USER = INSERT_USER+user.getReligion().getId()+",";
					   }
					   if(user.getCommunity() != null){
					   INSERT_USER = INSERT_USER+user.getCommunity().getId()+",";
					   }
					   INSERT_USER = INSERT_USER+"0,NOW(),";
					   INSERT_USER = INSERT_USER+ accessId;
					   INSERT_USER = INSERT_USER+ ")";
					   System.out.println("INSERT_USER>>>...."+INSERT_USER);
			   try{
				   KeyHolder keyHolder = new GeneratedKeyHolder();
				   SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
						   user);
				if(user != null)
				getJdbcTemplate().update(INSERT_USER, namedParameters, keyHolder );
				Number idStaff = keyHolder.getKey();
				user.setId(idStaff.intValue());
				System.out.println("id ------------>"+idStaff);
				if(idStaff != null){
					Integer id = idStaff.intValue();
					if(user.getPhoneNumbers() != null){
					insertPhoneNumber(user.getPhoneNumbers(),id,accessId);
					}
					if(user.getAddresses() != null){
					insertAddressses(user.getAddresses(),id,accessId);	
					}
					UserAuthenticationDTO authentication = new UserAuthenticationDTO();
					authentication.setStaff(user);
					authentication.setUserSecret(user.getFatherName());
					authentication.setUserName(user.getEmailAddresses());
					insertPassword(authentication);
	/*				String msgBody = CommonConstants.USERNAME + authentication.getUserName()
							+ CommonConstants.PASSWORD +authentication.getUserSecret(); 
					emailService.readyToSendEmail(authentication.getUserName(), CommonConstants.CREDENTIALS, msgBody);*/
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
	public ResponseBean deleteUser(Integer orgId, Integer userId,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String DELETE_USER ="UPDATE `user` SET IS_DELETED = 1,UPDATED_BY ="+accessId +" WHERE USER ="+userId +"AND ID_ORGANIZATION = "+orgId;
		String DELETE_PHONE = "UPDATE `PHONE` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID_USER = "+userId  +"AND ID_ORGANIZATION = "+orgId;
		String DELETE_ADDRESS = "UPDATE `ADDRESS` SET IS_DELETED = 0,UPDATED_BY ="+accessId +" WHERE ID_USER ="+userId +"AND ID_ORGANIZATION = "+orgId;
		try{
				
				getJdbcTemplate().update(DELETE_PHONE);
				getJdbcTemplate().update(DELETE_ADDRESS);
				if(userId != null){
			    getJdbcTemplate().update(DELETE_USER );
		        }
				responseBean.setStatus("SUCCESS");
				responseBean.setMessage("The user is deleted sccessfully");
			
		   }catch(Exception e){
			   responseBean.setStatus("FAILED");
			   String eStr = e.getMessage();
				responseBean.setMessage(eStr);
		   }
		   return responseBean;
	}


}
