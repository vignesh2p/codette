package com.codette.apps.dao;


import org.springframework.stereotype.Component;

import com.codette.apps.dto.UserDTO;

@Component
public interface UserDAO {

	public Object createUser(UserDTO userDTO, Integer orgId, Integer accessId);

	public Object updateUser(UserDTO userDTO, Integer orgId, Integer accessId, Integer userId);

	public Object deleteUser(Integer orgId, Integer userId, Integer accessId);

	public Object getUser(Integer orgId, Integer userId);

	public Object getUsers(Integer orgId, String role, Integer stdId, Integer secId,boolean includeDetails,String search);

	
}
