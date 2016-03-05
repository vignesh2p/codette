package com.codette.apps.dao;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.UserAuthenticationDTO;

@Component
public interface AuthenticationDAO {

	public Object authentication(UserAuthenticationDTO userAuthenticationDTO) throws Exception;

	public Object resetPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId);

	public Object changePassword(UserAuthenticationDTO userAuthenticationDTO,String newPassword, Integer accessId);

	public Object createPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId);

}
