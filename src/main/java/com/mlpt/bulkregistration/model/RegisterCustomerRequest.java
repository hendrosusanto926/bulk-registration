package com.mlpt.bulkregistration.model;

import java.util.Date;
import java.util.List;

public class RegisterCustomerRequest extends BaseBackofficeApprovalRequest {
	private String address;
	
	private Date birthDate;
		
	private String channel;
	
	private String channelMobileNumber;
	
	private String cif;
	
	private String customerName;
	
	private String lastname;
	
	private String mother;
	
	private String phoneNumber;
	
	private String sex;
	
	private String shortName;
	
	private String mobilePin;
	
	private String branchId;
	
	private String atmCardNo;
	
	private List<WritableAccountMessage> accountsSelected;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the channelMobileNumber
	 */
	public String getChannelMobileNumber() {
		return channelMobileNumber;
	}

	/**
	 * @param channelMobileNumber the channelMobileNumber to set
	 */
	public void setChannelMobileNumber(String channelMobileNumber) {
		this.channelMobileNumber = channelMobileNumber;
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the mother
	 */
	public String getMother() {
		return mother;
	}

	/**
	 * @param mother the mother to set
	 */
	public void setMother(String mother) {
		this.mother = mother;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the mobilePin
	 */
	public String getMobilePin() {
		return mobilePin;
	}

	/**
	 * @param mobilePin the mobilePin to set
	 */
	public void setMobilePin(String mobilePin) {
		this.mobilePin = mobilePin;
	}

	/**
	 * @return the accountsSelected
	 */
	public List<WritableAccountMessage> getAccountsSelected() {
		return accountsSelected;
	}

	/**
	 * @param accountsSelected the accountsSelected to set
	 */
	public void setAccountsSelected(List<WritableAccountMessage> accountsSelected) {
		this.accountsSelected = accountsSelected;
	}

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
	 * @return the atmCardNo
	 */
	public String getAtmCardNo() {
		return atmCardNo;
	}

	/**
	 * @param atmCardNo the atmCardNo to set
	 */
	public void setAtmCardNo(String atmCardNo) {
		this.atmCardNo = atmCardNo;
	}
	
	
}
