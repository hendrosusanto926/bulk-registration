package com.mlpt.bulkregistration.model;

public class ApproveRequest extends BaseBackofficeRequest {

	private String branchId;
	private String idApproval;
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the idApproval
	 */
	public String getIdApproval() {
		return idApproval;
	}
	/**
	 * @param idApproval the idApproval to set
	 */
	public void setIdApproval(String idApproval) {
		this.idApproval = idApproval;
	}
	
	
}