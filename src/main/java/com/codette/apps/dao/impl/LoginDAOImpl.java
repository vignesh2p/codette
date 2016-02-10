package com.codette.apps.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.LoginDAO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.mapper.SessionMapper;
import com.codette.apps.util.CommonUtil;

public class LoginDAOImpl extends NamedParameterJdbcDaoSupport implements LoginDAO{
	
	@Resource
	CommonUtil commonUtil;

	@Override
	public UserDTO authentication(UserAuthenticationDTO userAuthenticationDTO)  {
    	UserDTO user = new UserDTO();
        try {
            if (userAuthenticationDTO != null) {
                String sql = "SELECT ID_USER FROM user_authentication WHERE USER_NAME =?"
           + "AND USER_SECRET =?";
                Object[] inputs = new Object[] {userAuthenticationDTO.getUserName(),userAuthenticationDTO.getUserSecret()};
                Integer staffId =  getJdbcTemplate().queryForObject(
                        sql, inputs, Integer.class);
                if (staffId!=null) {
                 String USER_INFO = "SELECT A.ID,A.FIRST_NAME,A.ID_ROLE,A.LAST_NAME,"
                 		+ "R.ID,R.ROLE,A.ID_GENDER,G.ID,G.GENDER,A.EMAIL_ADDRESS,D.ID,A.ID_DESIGNATION "
                 		+ "FROM USER A "
                 		+ "LEFT OUTER JOIN ROLE R ON A.ID_ROLE = R.ID "
                 		+ " LEFT OUTER JOIN DESIGNATION D ON A.ID_DESIGNATION = D.ID "
                 		+ "LEFT OUTER JOIN GENDER G ON A.ID_GENDER = G.ID "
                 		+ "WHERE A.ID = ?";
                     Object[] input = new Object[] {staffId};
               	       user = getJdbcTemplate().queryForObject(USER_INFO, input, 
            			new SessionMapper());
            	
                }
            }
        } catch (Exception ex) {
            logger.error("Exception in authentication -- " + ex);
           
        }
		return user;
}

	@Override
	public UserDTO resetPassword(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
			String AUTHENTICATION = "INSERT INTO `user_authentication`( "
					+ "`USER_NAME`, `USER_SECRET`, `ID_USER`)"
				+"VALUES ("+
				commonUtil.stringFeilds(userAuthenticationDTO.getUserName())+","+
						commonUtil.stringFeilds(userAuthenticationDTO.getUserSecret())+","+
						userAuthenticationDTO.getStaff().getId()+")";
			getJdbcTemplate().update(AUTHENTICATION);
			return null;
			
	}

	@Override
	public UserDTO changePassword(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
