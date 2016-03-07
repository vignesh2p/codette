/**
 * 
 */
package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.DropDownValue;
import com.codette.apps.frontend.model.Organization;
import com.codette.apps.frontend.model.User;
import com.codette.apps.util.CommonConstants;


@Component
public class UserTranslator extends BaseTranslator{
	
	@Resource
	LoginTranslator loginTranslator;
	
	@Resource
	CommonTranslator commonTranslator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserTranslator.class);
	
	/**
	 * Translate json response to UserDTO
	 * @param object
	 * @return
	 */
	public List<UserDTO> translateToUserDTOList(Object object) {
		LOGGER.debug("Translate json to UserDTO");
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		List<UserDTO> userDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return userDTOList;
	}
	
	public UserDTO translateToUserDTO(Object object) {
		LOGGER.debug("Translate json to UserDTO");
		Type listType = new TypeToken<UserDTO>() {}.getType();
		UserDTO userDTO = gson.fromJson(translateObjectToJson(object), listType);
		return userDTO;
	}
	
	
    /**
     * Translating UserDTO List to User List
     * @param userDTOList
     * @param locale
     * @return
     * @throws ParseException 
     */
    public List<User> translateToUserList(List<UserDTO> userDTOList, String locale) throws ParseException{
    	LOGGER.debug("Translating UserDTO List to User List");
		List<User> userList = new ArrayList<User>(); 
		if(userDTOList != null && !userDTOList.isEmpty()){
			for(UserDTO userDTO : userDTOList){
				userList.add(translateToUser(userDTO, locale));
			}
		}
		return userList;
    }
    
    /**
	 * Converts to user
	 * @param userDTO
	 * @param locale
	 * @return user
     * @throws ParseException 
	 */
	public User translateToUser(UserDTO userDTO, String locale) throws ParseException{
		LOGGER.info("Converting UserDTO model to User");
		User user= new User();
		if(userDTO != null){
			BeanUtils.copyProperties(userDTO, user);
			user.setUserName(userDTO.getFirstName()+" "+userDTO.getLastName());
			if(userDTO.getDateOfBirth()!=null){
				user.setDateOfBirth(commonUtil.formatgivenStringToDate(userDTO.getDateOfBirth(), CommonConstants.DATE_FORMAT,CommonConstants.DATE_DD_MMMM_YYYY));
			}
			if(userDTO.getDateOfJoining()!=null){
				user.setDateOfJoining(commonUtil.formatgivenStringToDate(userDTO.getDateOfJoining(),  CommonConstants.DATE_FORMAT, CommonConstants.DATE_DD_MMMM_YYYY));
			}
			if(userDTO.getGender() != null){
				user.setGender(userDTO.getGender().getGender());
			}
			if(userDTO.getRole() != null){
				user.setUserRole(userDTO.getRole().getRole());
			}
			if(userDTO.getReligion() != null){
				user.setReligion(commonTranslator.translateToDropDown(userDTO.getReligion().getId().toString(), userDTO.getReligion().getReligion()));
			}
			if(userDTO.getDesignation() != null){
				user.setDesignation(commonTranslator.translateToDropDown(userDTO.getDesignation().getId().toString(), userDTO.getDesignation().getDesignation()));
			}
			if(userDTO.getCommunity() != null){
				user.setCommunity(commonTranslator.translateToDropDown(userDTO.getCommunity().getId().toString(), userDTO.getCommunity().getCommunity()));
			}
			if(userDTO.getOrganizationDTO() != null){
				Organization organization = new Organization();
				 if(userDTO.getOrganizationDTO().getOrgId() != null){
					 organization.setId(String.valueOf(userDTO.getOrganizationDTO().getOrgId()));
				 }
				 if(userDTO.getOrganizationDTO().getOrganizationName() != null){
					 organization.setOrgName(userDTO.getOrganizationDTO().getOrganizationName());
				 }
				 if(userDTO.getOrganizationDTO().getNickName() != null){
					 organization.setMnemonic(userDTO.getOrganizationDTO().getNickName());
				 }
				user.setOrganization(organization);
			}
			
		}
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws ParseException 
	 */
	public UserDTO translateToUserDTO(User user, String role) throws ParseException{
		UserDTO userDTO = null;
		if(user != null){
			userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			
			if(user.getDateOfBirth() != null) {
				userDTO.setDateOfBirth(commonUtil.formatgivenStringToDate(user.getDateOfBirth(), CommonConstants.DATE_DD_MMMM_YYYY, CommonConstants.DATE_FORMAT));
			}
			
			if(user.getDateOfJoining() != null) {
				userDTO.setDateOfJoining(commonUtil.formatgivenStringToDate(user.getDateOfJoining(), CommonConstants.DATE_DD_MMMM_YYYY, CommonConstants.DATE_FORMAT));
			}
			
			RoleDTO userRole = new RoleDTO();
			
			if(role.equals("teaching"))
				userRole.setRole(CommonConstants.ROLE_T_STAFF);
			else if(role.equals("nonTeaching"))
				userRole.setRole(CommonConstants.ROLE_NT_STAFF);
			else if(role.equals("ADMIN"))
				userRole.setRole(CommonConstants.ROLE_ADMIN);
			userDTO.setRole(userRole);
			
			if(user.getCommunity() != null) {
				CommunityDTO communityDTO = new CommunityDTO();
				communityDTO.setId(Integer.valueOf(user.getCommunity().getId()));
				communityDTO.setCommunity(user.getCommunity().getValue());
				userDTO.setCommunity(communityDTO);
			}
			
			if(user.getReligion() != null) {
				ReligionDTO religionDTO = new ReligionDTO();
				religionDTO.setId(Integer.valueOf(user.getReligion().getId()));
				religionDTO.setReligion(user.getReligion().getValue());
				userDTO.setReligion(religionDTO);
			}
			
			if(user.getDesignation() != null) {
				DesignationDTO designationDTO = new DesignationDTO();
				designationDTO.setId(Integer.valueOf(user.getDesignation().getId()));
				designationDTO.setDesignation(user.getDesignation().getValue());
				userDTO.setDesignation(designationDTO);
			}
			
			List<AddressDTO> addressList = new ArrayList<AddressDTO>();
			if(user.getAddress() != null) {
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setIsPrimary(1);
				addressDTO.setAddress(user.getAddress());
				addressList.add(addressDTO);
				userDTO.setAddresses(addressList);
			}
			
			List<PhoneNumberDTO> phoneNumbers = new ArrayList<PhoneNumberDTO>();
			if(user.getContact() != null) {
				PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
				phoneNumberDTO.setIsPrimary(1);
				phoneNumberDTO.setPhoneNumber(user.getContact());
				phoneNumbers.add(phoneNumberDTO);
				userDTO.setPhoneNumbers(phoneNumbers);
			}
			
			if(user.getGender() != null && !user.getGender().isEmpty()) {
				GenderDTO gender = new GenderDTO();
				gender.setGender(user.getGender());
				userDTO.setGender(gender);
			}
			
		}
		return userDTO;
	}
}
