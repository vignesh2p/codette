package com.codette.apps.dto;

import java.util.List;

public class NewOrganizationDTO {

private OrganizationDTO organization ;

private List<String> standards;

private List<String> sections;

private List<String> subjects;

private List<YearDTO> years;

private UserDTO adminUser;

private List<String> communitys;

private List<String> religons;

private ImageDTO image;

public OrganizationDTO getOrganization() {
	return organization;
}

public void setOrganization(OrganizationDTO organization) {
	this.organization = organization;
}

public List<String> getStandards() {
	return standards;
}

public void setStandards(List<String> standards) {
	this.standards = standards;
}

public List<String> getSections() {
	return sections;
}

public void setSections(List<String> sections) {
	this.sections = sections;
}

public List<String> getSubjects() {
	return subjects;
}

public void setSubjects(List<String> subjects) {
	this.subjects = subjects;
}

public List<YearDTO> getYears() {
	return years;
}

public void setYears(List<YearDTO> years) {
	this.years = years;
}

public UserDTO getAdminUser() {
	return adminUser;
}

public void setAdminUser(UserDTO adminUser) {
	this.adminUser = adminUser;
}

public List<String> getCommunitys() {
	return communitys;
}

public void setCommunitys(List<String> communitys) {
	this.communitys = communitys;
}

public List<String> getReligons() {
	return religons;
}

public void setReligons(List<String> religons) {
	this.religons = religons;
}

public ImageDTO getImage() {
	return image;
}

public void setImage(ImageDTO image) {
	this.image = image;
}

}
