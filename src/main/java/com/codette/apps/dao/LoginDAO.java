package com.codette.apps.dao;

import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;

public interface LoginDAO {

	UserDTO authentication(UserAuthenticationDTO userAuthenticationDTO);

	UserDTO resetPassword(UserAuthenticationDTO userAuthenticationDTO);

	UserDTO changePassword(UserAuthenticationDTO userAuthenticationDTO);

	UserDTO createPassword(UserAuthenticationDTO userAuthenticationDTO);

}
