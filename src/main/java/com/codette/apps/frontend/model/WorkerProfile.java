/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.List;

/**
 * user : Vernalis
 *
 */
public class WorkerProfile {
	private String serviceDescription;
	private String promoteBusiness;
	private List<BusinessProfile> businessProfile;
	private List<Employees> employees;
	/**
	 * @return the serviceDescription
	 */
	public String getServiceDescription() {
		return serviceDescription;
	}
	/**
	 * @param serviceDescription the serviceDescription to set
	 */
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	/**
	 * @return the promoteBusiness
	 */
	public String getPromoteBusiness() {
		return promoteBusiness;
	}
	/**
	 * @param promoteBusiness the promoteBusiness to set
	 */
	public void setPromoteBusiness(String promoteBusiness) {
		this.promoteBusiness = promoteBusiness;
	}
	/**
	 * @return the businessProfile
	 */
	public List<BusinessProfile> getBusinessProfile() {
		return businessProfile;
	}
	/**
	 * @param businessProfile the businessProfile to set
	 */
	public void setBusinessProfile(List<BusinessProfile> businessProfile) {
		this.businessProfile = businessProfile;
	}
	/**
	 * @return the employees
	 */
	public List<Employees> getEmployees() {
		return employees;
	}
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
	

}
