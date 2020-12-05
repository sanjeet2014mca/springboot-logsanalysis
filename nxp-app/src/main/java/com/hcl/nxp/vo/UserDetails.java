package com.hcl.nxp.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDetails implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String fName;
	private String mName;
	private String lName;
	private String mobile;
	private String email;
	private String gender;
	private String dob;
	private String status;
	private String fileName;

	public UserDetails() {}

	public UserDetails(long id, String fName, String mName, String lName, String mobile, String email, String gender,
			String dob, String status, String fileName) {
		super();
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.status = status;
		this.fileName = fileName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", mobile="
				+ mobile + ", email=" + email + ", gender=" + gender + ", dob=" + dob + ", status=" + status
				+ ", fileName=" + fileName + "]";
	}

}
