/**
 * 
 */
package com.codette.apps.frontend.model;

import java.util.List;

/**
 * @author bashelu
 *
 */
public class Evaluations {

	private List<Status> evaluationStatus;
	private List<Status> documentationStatus;
	
	/**
	 * @return the evaluationStatus
	 */
	public List<Status> getEvaluationStatus() {
		return evaluationStatus;
	}
	/**
	 * @param evaluationStatus the evaluationStatus to set
	 */
	public void setEvaluationStatus(List<Status> evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}
	/**
	 * @return the documentationStatus
	 */
	public List<Status> getDocumentationStatus() {
		return documentationStatus;
	}
	/**
	 * @param documentationStatus the documentationStatus to set
	 */
	public void setDocumentationStatus(List<Status> documentationStatus) {
		this.documentationStatus = documentationStatus;
	}
	
	
	
	
}
