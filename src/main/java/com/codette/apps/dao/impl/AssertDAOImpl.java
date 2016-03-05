package com.codette.apps.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.dao.AssertDAO;
import com.codette.apps.dto.ImageDTO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AssertDAOImpl extends NamedParameterJdbcDaoSupport implements AssertDAO{

	

	@Override
	public Object createDisplayPicture(ImageDTO image, Integer orgId,
			Integer accessId) throws SQLException, IOException {
		   KeyHolder keyHolder = new GeneratedKeyHolder();
	       SqlParameterSource sps =null ;
		   Object object =  null;
		   InputStream fis = null;
		   
		   File file = new File(image.getName());
		   fis = new FileInputStream(file);
		   
		   Blob blob = null;
			  byte[] content = IOUtils.toByteArray(fis);
			  try {
			      blob = new SerialBlob(content);//debugger says content is empty here
			      image.setImage(blob);//debugger says blob is empty here.
			  }catch (SerialException e) {e.printStackTrace();}
			  catch (SQLException e) {e.printStackTrace();}
			  
		   String INSERT_PICTURE = "INSERT INTO `image`(`NAME`, `IMAGE`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
		   		+ " VALUES ("+image.getName()+","+image.getImage()+",0,NOW(),"+accessId+")";
		   getNamedParameterJdbcTemplate().update(INSERT_PICTURE,sps,keyHolder);
		   object = keyHolder.getKey().intValue();
		return object;
	}

	

}
