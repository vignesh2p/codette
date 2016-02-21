package com.codette.apps.dto;

public class OrganizationDTO extends BaseDTO{
	
private String organizationName;
private String nickName;
private Integer idImage;
public String getOrganizationName() {
	return organizationName;
}
public void setOrganizationName(String organizationName) {
	this.organizationName = organizationName;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}
public Integer getIdImage() {
	return idImage;
}
public void setIdImage(Integer idImage) {
	this.idImage = idImage;
}


}
