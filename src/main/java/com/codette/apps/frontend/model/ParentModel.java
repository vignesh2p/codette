/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.UUID;

/**
 * @author bashelu
 *
 */
public class ParentModel {

	private UUID entityId;
	private DropDownValue entityType;
	
	public UUID getEntityId() {
		return entityId;
	}
	public void setEntityId(UUID entityId) {
		this.entityId = entityId;
	}
	public DropDownValue getEntityType() {
		return entityType;
	}
	public void setEntityType(DropDownValue entityType) {
		this.entityType = entityType;
	}
}
