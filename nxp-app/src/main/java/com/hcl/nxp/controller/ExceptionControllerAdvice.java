package com.hcl.nxp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hcl.nxp.exception.ServiceException;
import com.hcl.nxp.vo.ErrorResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerGeneric(final Exception ex) {
		LOGGER.error(ex);
		final ErrorResponse error = new ErrorResponse();
		final String message = ex.getMessage();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(message.substring(0, message.lastIndexOf(';')));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerService(final ServiceException ex) {
		ResponseEntity<ErrorResponse> response = null;
		final ErrorResponse errorResp = new ErrorResponse();
		errorResp.setErrorMessage(ex.getMessage());
		errorResp.setErrorCode(ex.getCode());
		response = new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);

		return response;
	}
}