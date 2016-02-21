package com.codette.apps.dao;

import org.springframework.stereotype.Component;

@Component
public interface ClassRoomDAO {

	public Object getClassList(Integer orgId, Integer userId, String role);


	public Object createNewClassRoom(Integer orgId,Integer standardId, Integer sectionId,
			Integer userId, Integer accessId);

}
