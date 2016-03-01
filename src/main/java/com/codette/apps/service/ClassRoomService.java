package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.ClassRoomDAO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ClassRoomService {
	@Resource
	private ClassRoomDAO classRoomDAO;
	
	@Resource
	private CommonService commonService;
	
	final static Logger logger = Logger.getLogger(ClassRoomService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	public Object getClassList(Integer orgId, Integer userId,
			String role) {
		// TODO Auto-generated method stub
		return classRoomDAO.getClassList(orgId, userId,role);
	}

	public Object getAllClassList(Integer orgId) {
		// TODO Auto-generated method stub
		return classRoomDAO.getAllClassList(orgId);	
		}
	
	@Transactional
	public Object createNewClassRoom(Integer orgId,Integer standardId,Integer sectionId ,Integer userId , Integer accessId  ) {
		// TODO Auto-generated method stub
		return  classRoomDAO.createNewClassRoom(orgId, standardId, sectionId,userId,accessId );
	}
    
	@Transactional
	public Object createHandlingClassforStaff(List<StaffClassDTO> staffClasses, Integer orgId, Integer userId,
			String role, Integer accessId) {
		staffClasses = commonService.getBasicIds(staffClasses);
		return  classRoomDAO.createHandlingClassforStaff(staffClasses,orgId,userId,role,accessId);
	}


}
