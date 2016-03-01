package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.StaffClassDTO;

@Component
public interface ClassRoomDAO {

	public Object getClassList(Integer orgId, Integer userId, String role);


	public Object createNewClassRoom(Integer orgId,Integer standardId, Integer sectionId,
			Integer userId, Integer accessId);


	public Object createHandlingClassforStaff(List<StaffClassDTO> staffClasses,
			Integer orgId, Integer userId, String role, Integer accessId);


	public Object getAllClassList(Integer orgId);

}
