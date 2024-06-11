package com.mlpt.bulkregistration.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntityUser extends BaseEntity {

	public enum CHANNEL {
		MB, RIB, DEFAULT
	};

	@ManyToOne
	protected MasterUser masterUser;
	
	@Column(unique = true)
	protected String userId;

	@Column
	protected String email;

	@Column(nullable = false)
	protected String mobileNumber;

	@Column
	private int counter;
	
	@Column
	protected Date lastLogin;

	@Column
	protected String reffNumRegistration;

	// tanggal aktivasi
	@Column
	protected Date firstActivationDate;

	@Column
	protected String status;
	
	@Column(columnDefinition = "varchar(100) default 'in_ID'")
	private String language;

	public enum CUST_STATUS {
		BLOCKED, ACTIVATION_BLOCKED, ACTIVE, REGISTERED, DELETED, UNBLOCKED, RESET_PASSWORD, RESET_MPIN, CHANGE_PHONE_NO, EXPIRED
	}

	@Column
	protected String loginStatus;

	public enum CUST_LOGIN_STATUS {
		LOGGED, LOGOUT
	}

	@Column
	protected String otpStatus;

	public enum CUST_OTP_STATUS {
		OTP_ACTIVE, OTP_BLOCKED;
	}

	@Column
	protected int counterToken;

	@Column
	protected int counterAtmPin;

	@Column
	protected int counterActivation;

	@Column(nullable = false)
	protected String mobilePin;

	@Column
	private Date expiredActivateDate;

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// --- WARNING TRANSIENT ---
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@org.springframework.data.annotation.Transient
	@Column(name="counterOtpActivation",columnDefinition = "int default 0")
	protected int counterOtpActivation;
	
	@Transient
	public boolean isRIBUser() {
		return false;
	}

	@Transient
	public boolean isMBUser() {
		return false;
	}

	@Transient
	public RIBUser asRIBUser() {
		throw new IllegalStateException();
	}

	@Transient
	public MBUser asMBUser() {
		throw new IllegalStateException();
	}

	@Transient
	public abstract String getChannelType();
	
	public abstract String getId();
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// END --- WARNING TRANSIENT---
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	public String getStatus() {
		return status;
	}

	public int getCounterOtpActivation() {
		return counterOtpActivation;
	}

	public void setCounterOtpActivation(int counterOtpActivation) {
		this.counterOtpActivation = counterOtpActivation;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCounterToken() {
		return counterToken;
	}

	public void setCounterToken(int counterToken) {
		this.counterToken = counterToken;
	}

	public int getCounterAtmPin() {
		return counterAtmPin;
	}

	public void setCounterAtmPin(int counterAtmPin) {
		this.counterAtmPin = counterAtmPin;
	}

	public MasterUser getMasterUser() {
		return masterUser;
	}

	public void setMasterUser(MasterUser masterUser) {
		this.masterUser = masterUser;
	}

	public String getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(String mobilePin) {
		this.mobilePin = mobilePin;
	}

	public Date getExpiredActivateDate() {
		return expiredActivateDate;
	}

	public void setExpiredActivateDate(Date expiredActivateDate) {
		this.expiredActivateDate = expiredActivateDate;
	}

	public String getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}

	public int getCounterActivation() {
		return counterActivation;
	}

	public void setCounterActivation(int counterActivation) {
		this.counterActivation = counterActivation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getReffNumRegistration() {
		return reffNumRegistration;
	}

	public void setReffNumRegistration(String reffNumRegistration) {
		this.reffNumRegistration = reffNumRegistration;
	}

	public Date getFirstActivationDate() {
		return firstActivationDate;
	}

	public void setFirstActivationDate(Date firstActivationDate) {
		this.firstActivationDate = firstActivationDate;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getLanguage() {
		return language;
	}

	public String getProcessedLanguage() {
		String processedLanguage = language.toLowerCase().substring(0, 2);
		if (processedLanguage.equals("in")) {
			processedLanguage = "id";
		}
		return processedLanguage;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public String getSalt() {
		if (getChannelType().equals(CHANNEL.MB.toString())) {
			return this.asMBUser().getSalt();
		} else {
			return this.asRIBUser().getRegUserId();
		}
	}
}