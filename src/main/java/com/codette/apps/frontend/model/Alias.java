/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.UUID;

/**
 * @author bashelu
 *
 */
public class Alias {

	private DropDownValue entityType;
	private DropDownValue aliasType;
	private String value;
	private UUID entityId;
	/**
	 * @return the entityType
	 */
	public DropDownValue getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(DropDownValue entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return the aliasType
	 */
	public DropDownValue getAliasType() {
		return aliasType;
	}
	/**
	 * @param aliasType the aliasType to set
	 */
	public void setAliasType(DropDownValue aliasType) {
		this.aliasType = aliasType;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the entityId
	 */
	public UUID getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(UUID entityId) {
		this.entityId = entityId;
	}
}
