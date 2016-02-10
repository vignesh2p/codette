package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;

public interface UserDAO {

	ResponseBean createUser(UserDTO userDTO, String orgId, Integer accessId);

	ResponseBean updateUser(UserDTO userDTO, Integer acessId, Integer userId);

	ResponseBean deleteUser(Integer orgId, Integer userId, Integer accessId);

	UserDTO getUser(Integer userId);

	List<UserDTO> getUsers(String role, Integer stdId, Integer secId);

	
}
