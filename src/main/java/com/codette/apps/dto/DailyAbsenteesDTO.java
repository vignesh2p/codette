package com.codette.apps.dto;

import java.util.Date;

public class DailyAbsenteesDTO extends BaseDTO{
  private UserDTO user;
  private Date dateOfLeave;
 


public Date getDateOfLeave() {
	return dateOfLeave;
}
public void setDateOfLeave(Date dateOfLeave) {
	this.dateOfLeave = dateOfLeave;
}
public UserDTO getUser() {
	return user;
}
public void setUser(UserDTO user) {
	this.user = user;
}



}
