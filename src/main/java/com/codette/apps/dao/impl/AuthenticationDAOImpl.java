package com.codette.apps.dao.impl;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.dao.AuthenticationDAO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.mapper.SessionMapper;
import com.codette.apps.util.CommonUtil;
import com.codette.apps.util.FiedValidationException;

public class AuthenticationDAOImpl extends NamedParameterJdbcDaoSupport implements AuthenticationDAO{
	
	@Resource
	private CommonUtil commonUtil;

	@Override
	public Object authentication(UserAuthenticationDTO userAuthenticationDTO) throws Exception  {
     Object object = null;
		try {
            if (userAuthenticationDTO != null) {
               
                Object[] inputs = new Object[] {userAuthenticationDTO.getUserName(),
                		userAuthenticationDTO.getUserSecret()};
                Integer staffId =  getJdbcTemplate().queryForObject(
                		getAuthentication(), inputs, Integer.class);
                if (staffId!=null) {
                 
                     Object[] input = new Object[] {staffId};
               	       object = getJdbcTemplate().queryForObject(getSessionparams(), input, 
            			new SessionMapper());           	
                }
            }
        }
		catch(EmptyResultDataAccessException ex){
			logger.error("Error in authentication -- " + ex.getMessage());
        	throw new FiedValidationException("Invalid Username / Password");
        } 
		return object;
		
}

	

	@Override
	public Object createPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId)throws Exception {
		// TODO Auto-generated method stub
		KeyHolder key = new GeneratedKeyHolder();
			SqlParameterSource sps = null;
			getNamedParameterJdbcTemplate().update(getCreatePassword(userAuthenticationDTO,accessId),sps ,key);
			if(key.getKey()  != null){
				return "success";
			}
			return "problem in creating authentication for this User";
					
	}



	@Override
	public Object changePassword(UserAuthenticationDTO userAuthenticationDTO,String newPassword, Integer accessId) throws Exception {
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
                getJdbcTemplate().update(getUpdatePassword(),input);
                return "success";
        }else{
        	return "your user name or old password is wrong";
        }
			
			
	}

	@Override
	public Object resetPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) throws Exception  {
		// TODO Auto-generated method stub
		 Object[] input = new Object[] {
                userAuthenticationDTO.getUserName(),
                userAuthenticationDTO.getUserSecret(),
                 accessId,          
                 userAuthenticationDTO.getUser().getId()
               };
               getJdbcTemplate().update(getUpdatePassword(),input);
		 return "success";
	}
	
	
	
	
/*================================================Sql==================================================*/
	
	
	
	
	
	
	
	private String getCreatePassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) throws Exception{
		try{
			String createPassword = "INSERT INTO `user_authentication`( "
					+ "`USER_NAME`, `USER_SECRET`, `ID_USER` ,"
					+ " `ID_ORGANIZATION`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+"VALUES ('"+userAuthenticationDTO.getUserName().trim()+"','"+userAuthenticationDTO.getUserSecret().trim()+"', "
						+ +userAuthenticationDTO.getUser().getId()+","+ userAuthenticationDTO.getOrgId()+",0,NOW(),"+accessId+")";
			return createPassword;
		}catch(NullPointerException ex){
			throw ex;
		}
		
		}

	private String getSessionparams() throws Exception{
		String sessionParams = "SELECT A.ID_ORGANIZATION,O.NICK_NAME,O.ORGANIZATION_NAME,A.ID,A.FIRST_NAME,A.ID_ROLE,A.LAST_NAME, "
         		+ " R.ROLE,A.ID_GENDER,G.ID,G.GENDER,A.EMAIL_ADDRESS,D.ID,A.ID_DESIGNATION,D.DESIGNATION "
         		+ " FROM USER A "
         		+ " LEFT OUTER JOIN ORGANIZATION O ON A.ID_ORGANIZATION = O.ID"
         		+ " LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
         		+ " LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
         		+ " LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
         		+ " WHERE A.ID = ?";
		return sessionParams;
	}

	private String getAuthentication() throws Exception{
		 String authenticate  = "SELECT ID_USER FROM user_authentication WHERE USER_NAME =?"
		           + " AND USER_SECRET =? ";
		return authenticate;
	}
	private String getUpdatePassword() throws Exception{
		String updatePassword = "UPDATE `user_authentication` "
				+ "SET "
				+ "`USER_NAME`='?',`USER_SECRET`='?'"
				+ "`IS_DELETED`=0,`UPDATED_ON`= NOW(),`UPDATED_BY`=? WHERE `ID_USER`=?";
		return updatePassword;
	}

	
}
