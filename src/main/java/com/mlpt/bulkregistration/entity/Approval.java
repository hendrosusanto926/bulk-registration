package com.mlpt.bulkregistration.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "approval")
public class Approval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private @Column String channel;
	private @Column Date requestDate;
	private @Column String requestedBy;
	private @Column Date approvalDate;
	private @Column String approvalBy;
	private @Column String status;
	private @Column String category;
	private @Column String type;
	private @Lob String patch;
	private @Column String branchId;
	private @Column String idEntity;
	private @Column String string1;
	private @Column String selectedApprover;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Approval() {
	}

	public Approval(Builder builder) {
		this.channel = builder.channel;
		this.requestedBy = builder.requestedBy;
		this.approvalBy = builder.approvalBy;
		this.category = builder.category;
		this.type = builder.type;
		this.patch = builder.patch;
		this.branchId = builder.branchId;
		this.idEntity = builder.idEntity;
		this.string1 = builder.string1;
		this.selectedApprover = builder.selectedApprover;
	}

	public enum APPROVAL_SPV { ALL }
	
	public enum APPROVAL_STATUS {
		NOT_YET, APPROVED, REJECTED, CANCELLED
	}

	public enum APPROVAL_CATEGORY {
		USER, MASTER_USER, ACCOUNT_SOURCE, CONFIG_PARAM_LITERAL, MESSAGE_DICTIONARY, BRANCH;
	}

	public enum APPROVAL_TYPE {
		REGISTRATION_USER(APPROVAL_CATEGORY.USER), BLOCK_USER(APPROVAL_CATEGORY.USER), UNBLOCK_USER(APPROVAL_CATEGORY.USER), RESET_PASSWORD_USER(APPROVAL_CATEGORY.USER), CHANGE_PHONE_NO(APPROVAL_CATEGORY.USER), RESET_MPIN(APPROVAL_CATEGORY.USER), RESET_REGISTERED_USER(APPROVAL_CATEGORY.USER), CLOSE_USER(APPROVAL_CATEGORY.USER),

		CHANGE_ATM(APPROVAL_CATEGORY.MASTER_USER),
		
		UPDATE_ATM_DATA(APPROVAL_CATEGORY.MASTER_USER),

		CHANGE_USER_ACCOUNTS(APPROVAL_CATEGORY.ACCOUNT_SOURCE),

		CHANGE_CONFIG(APPROVAL_CATEGORY.CONFIG_PARAM_LITERAL), CHANGE_LITERAL(APPROVAL_CATEGORY.CONFIG_PARAM_LITERAL),

		CREATE_MESSAGE_DICTIONARY(APPROVAL_CATEGORY.MESSAGE_DICTIONARY), CHANGE_MESSAGE_DICTIONARY(APPROVAL_CATEGORY.MESSAGE_DICTIONARY), DELETE_MESSAGE_DICTIONARY(APPROVAL_CATEGORY.MESSAGE_DICTIONARY),

		CREATE_BRANCH(APPROVAL_CATEGORY.BRANCH), CHANGE_BRANCH(APPROVAL_CATEGORY.BRANCH), DELETE_BRANCH(APPROVAL_CATEGORY.BRANCH);

		private APPROVAL_CATEGORY category;

		private APPROVAL_TYPE(APPROVAL_CATEGORY category) {
			this.category = category;
		}

		public APPROVAL_CATEGORY getCategory() {
			return this.category;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPatch() {
		return patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public String getSelectedApprover() {
		return selectedApprover;
	}

	public void setSelectedApprover(String selectedApprover) {
		this.selectedApprover = selectedApprover;
	}

	public static class Builder {

		private String channel;
		private String requestedBy;
		private String approvalBy;
		private String category;
		private String type;
		private String patch;
		private String branchId;
		private String idEntity;
		private String string1;
		private String selectedApprover;

		public Builder() {
		}

		public Builder(APPROVAL_TYPE type) {
			this.type = type.toString();
			this.category = type.getCategory().toString();
		}

		public Builder APPROVAL_TYPE(APPROVAL_TYPE type) {
			this.type = type.toString();
			this.category = type.getCategory().toString();
			return this;
		}

		public Builder requestedBy(String requestedBy) {
			this.requestedBy = requestedBy;
			return this;
		}

		public Builder approvalBy(String approvalBy) {
			this.approvalBy = approvalBy;
			return this;
		}

		public Builder patch(String patch) {
			this.patch = patch;
			return this;
		}

		public Builder channel(String channel) {
			this.channel = channel;
			return this;
		}

		public Builder branchId(String branchId) {
			this.branchId = branchId;
			return this;
		}

		public Builder idEntity(String idEntity) {
			this.idEntity = idEntity;
			return this;
		}

		public Builder string1(String string1) {
			this.string1 = string1;
			return this;
		}

		public Builder selectedApprover(String selectedApprover) {
			this.selectedApprover = selectedApprover;
			return this;
		}

		public Approval build() {
			return new Approval(this);
		}
	}

}