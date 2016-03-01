package com.codette.apps.dto;


public class StaffClassDTO extends BaseDTO{
    private UserDTO user;
    private Integer isClassTeacher; 
    private ClassesDTO classRoom;
    private SubjectDTO subject;
    private YearDTO year;
    
    

	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public Integer getIsClassTeacher() {
		return isClassTeacher;
	}
	public void setIsClassTeacher(Integer isClassTeacher) {
		this.isClassTeacher = isClassTeacher;
	}

	public ClassesDTO getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(ClassesDTO classRoom) {
		this.classRoom = classRoom;
	}
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	public YearDTO getYear() {
		return year;
	}
	public void setYear(YearDTO year) {
		this.year = year;
	}
    
    
    
}
