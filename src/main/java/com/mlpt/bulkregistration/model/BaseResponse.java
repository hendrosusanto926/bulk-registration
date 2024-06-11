package com.mlpt.bulkregistration.model;

public class BaseResponse {
	
	private String rc;

	public BaseResponse() {
		super();
	}

	public BaseResponse(String rc) {	
		super();
		this.rc = rc;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

}
