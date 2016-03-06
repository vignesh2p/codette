package com.codette.apps.frontend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codette.apps.frontend.model.Login;
import com.codette.apps.frontend.model.Roles;
import com.codette.apps.frontend.model.User;
import com.codette.apps.frontend.service.EmailService;
import com.codette.apps.frontend.service.LoginService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.MessageUtils;

@Controller
public class LoginController extends BaseController{
	

	@Autowired
	private BaseController baseController;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	EmailService emailService;
	
	
	/*
	 * Checking user authentication in login
	 * @param login
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/authentication",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authentication(@RequestBody Login login,HttpSession session,  HttpServletRequest request) throws IOException {
		User user = new User();
			try{ 
					if(login.getUserName()!=null && !login.getUserName().isEmpty()&&login.getUserSecret()!=null&&!login.getUserSecret().isEmpty()) {
						user = loginService.userAuthentication(login.getUserName(), login.getUserSecret(), session, locale, request);
						if(user.getId() != null )
							session.setAttribute(CommonConstants.SESSION_USER_ID, user.getId().toString());
						if(user.getUserName() != null && !user.getUserName().isEmpty())
							session.setAttribute(CommonConstants.SESSION_USERNAME, user.getUserName());
						if(user.getUserRole()!=null && !user.getUserRole().isEmpty())
							session.setAttribute(CommonConstants.SESSION_USERROLE, user.getUserRole());
						if(user.getFirstName() != null && !user.getFirstName().isEmpty())
							session.setAttribute(CommonConstants.SESSION_FIRSTNAME, user.getFirstName());
						if(user.getLastName() != null && !user.getLastName().isEmpty())
							session.setAttribute(CommonConstants.SESSION_LASTNAME, user.getLastName());
						if(user.getEmailAddresses() != null && !user.getEmailAddresses().isEmpty())
							session.setAttribute(CommonConstants.SESSION_EMAILADDRESS, user.getEmailAddresses());
						if(user.getOrganization() != null ){
							if(user.getOrganization().getId() != null){
								session.setAttribute(CommonConstants.SESSION_ORG_ID, user.getOrganization().getId());
							}
							if(user.getOrganization().getOrgName() != null){
								session.setAttribute(CommonConstants.SESSION_ORG_NAME, user.getOrganization().getOrgName());
							}
						}
					 }
					
			}catch(Exception exception){
				exception.printStackTrace();
				return setCustomExceptionHandler(exception);
			}
		 return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserRoles",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserRoles(HttpSession session) throws IOException {
		Roles roles = new Roles();
		try{ 
			roles = baseController.getRoleBasedPermission(session);
		}catch(Exception exception){
		//	return new ResponseEntity<>(setCustomExceptionHandler(exception, MessageUtils.getMessage("error.invalid.login")), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Roles>(roles,HttpStatus.OK);
	}
	
	/**
	 * User Logout 
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(HttpSession session) {
		session.removeAttribute(CommonConstants.SESSION_USER_ID);
    	session.removeAttribute(CommonConstants.SESSION_USERNAME);
    	session.removeAttribute(CommonConstants.SESSION_USERROLE);
    	session.removeAttribute(CommonConstants.SESSION_FIRSTNAME);
    	session.removeAttribute(CommonConstants.SESSION_LASTNAME);
    	session.invalidate();
    	return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("user.logout.success"),HttpStatus.OK.toString()),HttpStatus.OK);
	}
	
	/*
	 * Checking unauthorized and trigger
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/unauthorized",method = RequestMethod.GET,	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> unauthorized(HttpSession session) throws IOException {
		session.invalidate();
	return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.user.unauthorized"),HttpStatus.UNAUTHORIZED.toString()),HttpStatus.UNAUTHORIZED);
	}
} 