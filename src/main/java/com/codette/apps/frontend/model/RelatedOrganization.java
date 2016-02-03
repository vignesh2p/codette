/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author bashelu
 *
 */
/**
 * @author bashelu
 *
 */
@JsonInclude(value=Include.NON_EMPTY)
public class RelatedOrganization {

	private String id;
	private String startOrganizationId;
	private String endOrganizationId;
	private DropDownValue relationshipType;
	private boolean status;
	private String startDate;
	private String startOrganizationName;	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the startOrganizationId
	 */
	public String getStartOrganizationId() {
		return startOrganizationId;
	}
	/**
	 * @param startOrganizationId the startOrganizationId to set
	 */
	public void setStartOrganizationId(String startOrganizationId) {
		this.startOrganizationId = startOrganizationId;
	}
	/**
	 * @return the endOrganizationId
	 */
	public String getEndOrganizationId() {
		return endOrganizationId;
	}
	/**
	 * @param endOrganizationId the endOrganizationId to set
	 */
	public void setEndOrganizationId(String endOrganizationId) {
		this.endOrganizationId = endOrganizationId;
	}
	/**
	 * @return the relationshipType
	 */
	public DropDownValue getRelationshipType() {
		return relationshipType;
	}
	/**
	 * @param relationshipType the relationshipType to set
	 */
	public void setRelationshipType(DropDownValue relationshipType) {
		this.relationshipType = relationshipType;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the startOrganizationName
	 */
	public String getStartOrganizationName() {
		return startOrganizationName;
	}
	/**
	 * @param startOrganizationName the startOrganizationName to set
	 */
	public void setStartOrganizationName(String startOrganizationName) {
		this.startOrganizationName = startOrganizationName;
	}
	
	
	
}
