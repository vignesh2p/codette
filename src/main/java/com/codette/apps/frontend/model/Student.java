	 
/**
 * 
 */
package com.codette.apps.frontend.model;


public class Student extends Person{
	
	private DropDownValue standard;
	private DropDownValue section;
	private Class clas;


	public Class getClas() {
		return clas;
	}

	public void setClas(Class clas) {
		this.clas = clas;
	}

	public DropDownValue getStandard() {
		return standard;
	}

	public void setStandard(DropDownValue standard) {
		this.standard = standard;
	}

	public DropDownValue getSection() {
		return section;
	}

	public void setSection(DropDownValue section) {
		this.section = section;
	}
	
	
	
}


