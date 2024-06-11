package com.mlpt.bulkregistration.model;

public class RegisterCustomerResponse extends BaseResponse{

	private String reffNum;
	
	public RegisterCustomerResponse(){
		super();
	}
	
	
	public RegisterCustomerResponse(String rc, String reffNum) {
		this.reffNum = reffNum;
		this.setRc(rc);
	}

	/**
	 * @return the reffNum
	 */
	public String getReffNum() {
		return reffNum;
	}

	/**
	 * @param reffNum the reffNum to set
	 */
	public void setReffNum(String reffNum) {
		this.reffNum = reffNum;
	}
}
