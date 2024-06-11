package com.mlpt.bulkregistration.model;

public class BaseBackofficeApprovalRequest {

	private String backofficeUserId;
	
	/**
	 * Optional : userbank supervisor id 
	 */
	private String selectedApproverId;

	/**
	 * @return the backofficeUserId
	 */
	public String getBackofficeUserId() {
		return backofficeUserId;
	}

	/**
	 * @param backofficeUserId
	 *            the backofficeUserId to set
	 */
	public void setBackofficeUserId(String backofficeUserId) {
		this.backofficeUserId = backofficeUserId;
	}

	public String getSelectedApproverId() {
		return selectedApproverId;
	}

	public void setSelectedApproverId(String selectedApproverId) {
		this.selectedApproverId = selectedApproverId;
	}

}
