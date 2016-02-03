package com.codette.apps.frontend.model;

import java.util.List;

public class Staff extends User{

	
	private String classTeacherID ;
	private List<Class> classes;

	public String getClassTeacherID() {
		return classTeacherID;
	}

	public void setClassTeacherID(String classTeacherID) {
		this.classTeacherID = classTeacherID;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	
}
