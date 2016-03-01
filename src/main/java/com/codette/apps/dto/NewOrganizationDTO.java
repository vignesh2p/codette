package com.codette.apps.dto;

import java.util.List;

public class NewOrganizationDTO {

private OrganizationDTO organization ;

private List<StandardDTO> standards;

private List<SectionDTO> sections;

private List<SubjectDTO> subjects;

private List<YearDTO> years;

private UserDTO adminUser;

private List<CommunityDTO> communities;

private List<ReligionDTO> religons;

private ImageDTO image;

public OrganizationDTO getOrganization() {
	return organization;
}

public void setOrganization(OrganizationDTO organization) {
	this.organization = organization;
}

public List<StandardDTO> getStandards() {
	return standards;
}

public void setStandards(List<StandardDTO> standards) {
	this.standards = standards;
}

public List<SectionDTO> getSections() {
	return sections;
}

public void setSections(List<SectionDTO> sections) {
	this.sections = sections;
}

public List<SubjectDTO> getSubjects() {
	return subjects;
}

public void setSubjects(List<SubjectDTO> subjects) {
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

public List<CommunityDTO> getCommunities() {
	return communities;
}

public void setCommunities(List<CommunityDTO> communities) {
	this.communities = communities;
}

public List<ReligionDTO> getReligons() {
	return religons;
}

public void setReligons(List<ReligionDTO> religons) {
	this.religons = religons;
}

public ImageDTO getImage() {
	return image;
}

public void setImage(ImageDTO image) {
	this.image = image;
}


}
