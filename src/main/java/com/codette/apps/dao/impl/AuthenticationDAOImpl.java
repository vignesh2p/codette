package com.codette.apps.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.AuthenticationDAO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.mapper.SessionMapper;
import com.codette.apps.util.CommonUtil;

public class AuthenticationDAOImpl extends NamedParameterJdbcDaoSupport implements AuthenticationDAO{
	
	@Resource
	private CommonUtil commonUtil;

	@Override
	public Object authentication(UserAuthenticationDTO userAuthenticationDTO, Integer accessId)  {
    	UserDTO user = new UserDTO();
        try {
            if (userAuthenticationDTO != null) {
               
                Object[] inputs = new Object[] {userAuthenticationDTO.getUserName(),
                		userAuthenticationDTO.getUserSecret()};
                Integer staffId =  getJdbcTemplate().queryForObject(
                		getAuthentication(), inputs, Integer.class);
                if (staffId!=null) {
                 
                     Object[] input = new Object[] {staffId};
               	       user = getJdbcTemplate().queryForObject(getSessionparams(), input, 
            			new SessionMapper());           	
                }
            }
        } catch (Exception ex) {
            logger.error("Exception in authentication -- " + ex);
           
        }
		return user;
		
}

	

	@Override
	public Object createPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) {
		// TODO Auto-generated method stub
		   		
				Object[] input = new Object[] {
						                       userAuthenticationDTO.getUserName(),
						                       userAuthenticationDTO.getUserSecret(),
						                       accessId,
						                       userAuthenticationDTO.getOrgId(),	
						                       userAuthenticationDTO.getUser().getId()
				                              };
			getJdbcTemplate().update(getCreatePassword());
			return "success";
			
	}
	

	@Override
	public Object changePassword(UserAuthenticationDTO userAuthenticationDTO,String newPassword, Integer accessId) {
		// TODO Auto-generated method stub
		Object[] inputs = new Object[] {userAuthenticationDTO.getUserName(),
        		userAuthenticationDTO.getUserSecret()};
        Integer staffId =  getJdbcTemplate().queryForObject(
        		getAuthentication(), inputs, Integer.class);
        if (staffId!=null) {
   		 Object[] input = new Object[] {
                 userAuthenticationDTO.getUserName(),
                 newPassword,
                 accessId,
                 userAuthenticationDTO.getUser().getId()
                };
                getJdbcTemplate().update(getUpdatePassword());
                return "success";
        }else{
        	return "your user name or password is wrong";
        }
			
			
	}

	@Override
	public Object resetPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) {
		// TODO Auto-generated method stub
		 Object[] input = new Object[] {
                userAuthenticationDTO.getUserName(),
                userAuthenticationDTO.getUserSecret(),
                 accessId,          
                 userAuthenticationDTO.getUser().getId()
               };
               getJdbcTemplate().update(getUpdatePassword());
		 return "success";
	}
	
	
	
	
/*================================================Sql==================================================*/
	
	
	
	
	
	
	
	private String getCreatePassword() {
		String createPassword = "INSERT INTO `user_authentication`( "
				+ "`USER_NAME`, `USER_SECRET`, `ID_USER` ,"
				+ " `ID_ORGANIZATION`, `IS_DELETED`, `UPDATED_ON`, `UPDATED_BY`)"
			+"VALUES ('?','?',?,?,0,NOW(),?)";
		return createPassword;
	}

	private String getSessionparams() {
		String sessionParams = "SELECT O.ID,O.NAME,A.ID,A.FIRST_NAME,A.ID_ROLE,A.LAST_NAME, "
         		+ " R.ID,R.ROLE,A.ID_GENDER,G.ID,G.GENDER,A.EMAIL_ADDRESS,D.ID,A.ID_DESIGNATION "
         		+ " FROM USER A "
         		+ " LEFT OUTER JOIN ORGANIZATION O ON A.ID_ORGANIZATION = O.ID"
         		+ " LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
         		+ " LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
         		+ " LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
         		+ " WHERE A.ID = ?";
		return sessionParams;
	}

	private String getAuthentication() {
		 String authenticate  = "SELECT ID_USER FROM user_authentication WHERE USER_NAME =?"
		           + " AND USER_SECRET =? ";
		return authenticate;
	}
	private String getUpdatePassword() {
		String updatePassword = "UPDATE `user_authentication` "
				+ "SET "
				+ "`USER_NAME`='?',`USER_SECRET`='?'"
				+ "`IS_DELETED`=0,`UPDATED_ON`= NOW(),`UPDATED_BY`=? WHERE `ID_USER`=?";
		return updatePassword;
	}

	
}
