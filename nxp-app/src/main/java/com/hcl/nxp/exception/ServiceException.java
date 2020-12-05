package com.hcl.nxp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	private int code;

	public ServiceException() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public ServiceException(final String message) {
		super(message);
		this.message = message;
	}

	public ServiceException(final Throwable clause) {
		super(clause);
	}

	public ServiceException(final String message, final Throwable clause) {
		super(message, clause);
		this.message = message;
	}

	public ServiceException(final int errorCode, final String message) {
		code = errorCode;
		this.message = message;
	}

}
