package com.codette.apps.frontend.model;

import java.util.UUID;

public class Asset {
	
	private UUID id;
    private ParentModel parent;
   	private String externalId;
    private String externalLink;
    private String fileName;
    private DropDownValue assetType;
    private DropDownValue contentType;
    
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public ParentModel getParent() {
		return parent;
	}
	public void setParent(ParentModel parent) {
		this.parent = parent;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getExternalLink() {
		return externalLink;
	}
	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public DropDownValue getAssetType() {
		return assetType;
	}
	public void setAssetType(DropDownValue assetType) {
		this.assetType = assetType;
	}
	public DropDownValue getContentType() {
		return contentType;
	}
	public void setContentType(DropDownValue contentType) {
		this.contentType = contentType;
	}
	
}
