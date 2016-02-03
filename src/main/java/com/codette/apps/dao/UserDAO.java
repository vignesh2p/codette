package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;

public interface UserDAO {

	
	public UserDTO getUser(Integer userId);
	
    public UserDTO aurthentication(UserAuthenticationDTO userAuthenticationDTO);

	 public ResponseBean insertUser(UserDTO user, Integer accessId) throws Exception;

	 public ResponseBean updateUser(UserDTO user, Integer acessId, Integer userId);

	public ResponseBean deleteUser(Integer userId,Integer phoneNumberId,Integer addressId, Integer accessId);

	public List<UserDTO> getUsers(String role);
	
}
