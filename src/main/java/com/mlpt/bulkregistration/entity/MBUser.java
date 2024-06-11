package com.mlpt.bulkregistration.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "mb_user")
public class MBUser extends BaseEntityUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column
	private String address;

	/**
	 * may be mobile banking or mobile money
	 */

	@Column(nullable = false)
	private String salt;

	// tanggal registrasi
	@Column
	private Date registrationDate;
	
	@Column
	private String passcode;

	@Column
	private String photoProfile;

	@Transient
	@Override
	public boolean isMBUser() {
		return true;
	}

	@Transient
	@Override
	public MBUser asMBUser() {
		return this;
	}
	
	@Transient
	@Override
	public String getChannelType() {
		return CHANNEL.MB.toString();
	}

	public String getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	

}