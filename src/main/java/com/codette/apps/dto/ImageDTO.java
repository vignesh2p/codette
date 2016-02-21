package com.codette.apps.dto;

import java.sql.Blob;

public class ImageDTO extends BaseDTO{

	private String caption;
	
	private Blob image;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
}
