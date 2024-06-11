package com.mlpt.bulkregistration.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class MasterUserResponse extends BaseMasterUserResponse {
	private Date birthDate;
	private String cif;
	private Long id;
	private String motherName;
	private String mobileNumber;
	private String sex;
	private List<AccountMessage> accounts;
	@Transient
	private String maskMobileNumber;
	@Transient
	private String stringBirthDate;
	
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the maskMobileNumber
	 */
	public String getMaskMobileNumber() {

		if (mobileNumber == null){
			return mobileNumber;
		} else {
			if (!mobileNumber.isEmpty()){
				if (mobileNumber.equals("0")) {
					return mobileNumber;
				} else {
					if (mobileNumber.length() <= 3) {
						return mobileNumber;
					} else {
						String last3 = mobileNumber.substring(mobileNumber.length()-3, mobileNumber.length());
						String prefix = "";
						for (int i=0; i < mobileNumber.length()-3; i++){
							prefix = prefix+"*";
						}
						return prefix+last3;
					}
				}
			} else {
				return mobileNumber;
			}
		}
	}
	/**
	 * @param maskMobileNumber the maskMobileNumber to set
	 */
	public void setMaskMobileNumber(String maskMobileNumber) {
		this.maskMobileNumber = maskMobileNumber;
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
	 * @return the accounts
	 */
	public List<AccountMessage> getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<AccountMessage> accounts) {
		this.accounts = accounts;
	}
	
	public  String parseDate(Date date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public  Date formatDate(String s) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (s == null || s.isEmpty()) {
			return null;
		}
		try {
			Date date;
			date = sf.parse(s);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return the stringBirthDate
	 */
	public String getStringBirthDate() {
		stringBirthDate = parseDate(birthDate);
		return stringBirthDate;
	}
	/**
	 * @param stringBirthDate the stringBirthDate to set
	 */
	public void setStringBirthDate(String stringBirthDate) {
		birthDate = formatDate(stringBirthDate);
		this.stringBirthDate = stringBirthDate;
	}
	
}
