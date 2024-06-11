package com.mlpt.bulkregistration.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rib_user")
public class RIBUser extends BaseEntityUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column
	private Date lastLogout;

	@Column
	private Date lastTry;

	@Column
	private String validatorImageid;

	@Column
	private int counterBlock;

	@Column
	private Date blockDate;

	@Column
	private String password;

	@Column(nullable = false)
	private String regUserId;

	@Transient
	@Override
	public boolean isRIBUser() {
		return true;
	}

	@Transient
	@Override
	public RIBUser asRIBUser() {
		return this;
	}
	
	@Transient
	@Override
	public String getChannelType() {
		return CHANNEL.RIB.toString();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogout() {
		return lastLogout;
	}

	public void setLastLogout(Date lastLogout) {
		this.lastLogout = lastLogout;
	}

	public Date getLastTry() {
		return lastTry;
	}

	public void setLastTry(Date lastTry) {
		this.lastTry = lastTry;
	}

	public String getValidatorImageid() {
		return validatorImageid;
	}

	public void setValidatorImageid(String validatorImageid) {
		this.validatorImageid = validatorImageid;
	}

	public int getCounterBlock() {
		return counterBlock;
	}

	public void setCounterBlock(int counterBlock) {
		this.counterBlock = counterBlock;
	}

	public Date getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}

}