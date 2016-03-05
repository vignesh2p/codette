package com.codette.apps.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.AssertDAO;
import com.codette.apps.dto.ImageDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class AssertService {
        final static Logger logger = Logger.getLogger(AttendanceService.class);
		public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private AssertDAO assertDAO;
		
		
		public Object createDisplayPicture(ImageDTO image, Integer orgId, Integer accessId) throws SQLException, IOException {
			// TODO Auto-generated method stub
			return assertDAO.createDisplayPicture(image,orgId,accessId);
		}
}
