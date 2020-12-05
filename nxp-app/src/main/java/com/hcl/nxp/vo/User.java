package com.hcl.nxp.vo;

public class User {

	private String userName;

	private String password;

	private String passwordConfirm;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User() {

	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
