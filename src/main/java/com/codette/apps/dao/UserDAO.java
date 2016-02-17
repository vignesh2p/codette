package com.codette.apps.dao;


import com.codette.apps.dto.UserDTO;

public interface UserDAO {

	public Object createUser(UserDTO userDTO, String orgId, Integer accessId);

	public Object updateUser(UserDTO userDTO, Integer acessId, Integer userId);

	public Object deleteUser(Integer orgId, Integer userId, Integer accessId);

	public Object getUser(Integer orgId, Integer userId);

	public Object getUsers(Integer orgId, String role, Integer stdId, Integer secId);

	
}
