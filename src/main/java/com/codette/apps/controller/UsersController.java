package com.codette.apps.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.service.UsersService;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Resource
	UsersService usersService;
	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
    @RequestMapping(value = "/authentication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?>  Authentication(@RequestBody UserAuthenticationDTO userAuthenticationDTO) throws Exception {
        UserDTO user = new UserDTO();
        System.out.println("user>>>>>>>"+gson.toJson(userAuthenticationDTO));
        user = usersService.aurthentication(userAuthenticationDTO);
        System.out.println("user>>>>>>>"+gson.toJson(user));
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }


	@RequestMapping(value = "/users/{role}", method = RequestMethod.GET )
	@ResponseBody
	public List<UserDTO> getUsers(@PathVariable( value="role") String role, HttpServletRequest request, HttpSession session)  {
		//String role = request.getHeader(CommonConstants.SESSION_USERROLE);
		if(role != null){
			System.out.println(role);
			return usersService.getUsers(role);
		}
		return null;
	}
	
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUser(@PathVariable Integer userId) throws Exception {
		System.out.println("Get User>>>>>>>>>>");
		return usersService.getUser(userId);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Object saveUsers(@ModelAttribute("user") UserDTO user,
            Map<String, Object> model) throws Exception {
		System.out.println("saveUsers>>>>>>>>>>");
		return "success";
	}
	@RequestMapping(value = "/{accessId}/createUser", method = RequestMethod.POST)
	@ResponseBody
	public Object addUsers( @PathVariable(value ="accessId") String accessId, HttpEntity<String> entity) throws Exception {
		 String postString = entity.getBody();
		
	        UserDTO userDTO = gson.fromJson(postString, UserDTO.class);
	       System.out.println("userDTO>>>>>dfsf>>>>>>>>>."+gson.toJson(userDTO));
		Integer userid = null ;
		if(accessId != null && !accessId.isEmpty()){
			System.out.println("accessId>>>>>>>"+accessId);
			 userid = Integer.valueOf(accessId);
			 usersService.insertUser(userDTO, userid);
		}
		return "SUCESS";
	}


	@RequestMapping(value = "updateUser/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean updateBills(@PathVariable Integer userId, @RequestBody UserDTO userDTO, HttpSession session, HttpServletRequest request) throws Exception {
		Integer acessId = Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));
		String role = String.valueOf(request.getHeader(CommonConstants.SESSION_USERROLE));
		ResponseBean responseBean = new ResponseBean();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRole(role);
		userDTO.setRole(roleDTO);
		responseBean = usersService.updateUser(userDTO, acessId, userId);
		return responseBean;
	}

	@RequestMapping(value = "deleteBill/{userId}/{phoneNumberid}/{addressId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseBean deleteBill(@PathVariable Integer userId,Integer phoneNumberId,Integer addressId, HttpSession session) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		responseBean = usersService.deleteUser(userId,phoneNumberId, addressId, userId);
		return responseBean;
	}

}
