package com.mlpt.bulkregistration.model;

import javax.persistence.Transient;

public class WritableAccountMessage {
	private String accountNo;
	private boolean active;
	private boolean visible;
	private String type;
	@Transient
	private String newAccountNo;
	private String accountCurrency;
	
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	/**
	 * Check if prefix = 0 then remove 0, else do nothing
	 * @return the newAccountNo
	 */
	public String getNewAccountNo() {
		if (accountNo.substring(0,1).equals("0")){
			newAccountNo = accountNo.substring(1);
		} else {
			newAccountNo = accountNo;
		}
		return newAccountNo;
	}
	/**
	 * @param newAccountNo the accountNo to set
	 */
	public void setNewAccountNo(String newAccountNo) {
		this.newAccountNo = newAccountNo;
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the accountCurrency
	 */
	public String getAccountCurrency() {
		return accountCurrency;
	}
	/**
	 * @param accountCurrency the accountCurrency to set
	 */
	public void setAccountCurrency(String accountCurrency) {
		this.accountCurrency = accountCurrency;
	}
	
}
