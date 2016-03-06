package com.codette.apps.dao;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.UserDTO;

@Component
public interface UserDAO {

	public Object createUser(UserDTO userDTO, Integer orgId, Integer accessId) throws DataIntegrityViolationException, DuplicateKeyException, Exception;

	public Object updateUser(UserDTO userDTO, Integer orgId, Integer accessId, Integer userId) throws Exception;

	public Object deleteUser(Integer orgId, Integer userId, Integer accessId)throws Exception ;

	public Object getUser(Integer orgId, Integer userId)throws Exception;

	public Object getUsers(Integer orgId, String role, Integer classId, boolean includeDetails,String search) throws Exception;

	
}
