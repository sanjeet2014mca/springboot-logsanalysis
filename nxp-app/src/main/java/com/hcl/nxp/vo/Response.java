package com.hcl.nxp.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private UserDetails userDetails;

	public Response(String message) {
		super();
		this.message = message;
	}

	public Response(String message, UserDetails userDetails) {
		super();
		this.message = message;
		this.userDetails = userDetails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
