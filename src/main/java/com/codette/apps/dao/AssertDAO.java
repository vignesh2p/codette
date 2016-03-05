package com.codette.apps.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ImageDTO;

@Component
public interface AssertDAO {

	public Object createDisplayPicture(ImageDTO image, Integer orgId, Integer accessId) throws FileNotFoundException, SQLException, IOException;

}
