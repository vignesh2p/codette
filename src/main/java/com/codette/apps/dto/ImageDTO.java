package com.codette.apps.dto;

import java.sql.Blob;

public class ImageDTO extends BaseDTO{

	private String name;
	
	private Blob image;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
}
