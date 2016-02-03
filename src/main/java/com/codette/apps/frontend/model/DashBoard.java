/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.List;

/**
 * @author bashelu
 *
 */
public class DashBoard {

	private List<Inbox> inboxList;
	private List<Section> activityList;
	/**
	 * @return the inboxList
	 */
	public List<Inbox> getInboxList() {
		return inboxList;
	}

	/**
	 * @param inboxList the inboxList to set
	 */
	public void setInboxList(List<Inbox> inboxList) {
		this.inboxList = inboxList;
	}

	/**
	 * @return the activityList
	 */
	public List<Section> getActivityList() {
		return activityList;
	}

	/**
	 * @param activityList the activityList to set
	 */
	public void setActivityList(List<Section> activityList) {
		this.activityList = activityList;
	}
	
}
