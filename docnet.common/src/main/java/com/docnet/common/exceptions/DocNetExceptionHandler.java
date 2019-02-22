package com.docnet.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class DocNetExceptionHandler {


	@ExceptionHandler(DocNetUserDataExistsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)// 409
	public DocNetExceptionResponse elyxUserDatExistsException(
			DocNetUserDataExistsException dataAlreadyExistsException) {
		String errorMessage = dataAlreadyExistsException.getExceptionMessage();
		DocNetExceptionResponse exceptionResponse = new DocNetExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.CONFLICT);
		exceptionResponse.setException(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(DocNetInvalidRequestException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public DocNetExceptionResponse elyxInvalidRequestException(
			DocNetInvalidRequestException invalidRequestException) {
		String errorMessage = invalidRequestException.getExceptionMessage();
		DocNetExceptionResponse exceptionResponse = new DocNetExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
		exceptionResponse.setException(errorMessage);
		return exceptionResponse;
	} 
	
	@ExceptionHandler(DocNetUserNotExistsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public DocNetExceptionResponse elyxUserNotExistsException(
			DocNetUserNotExistsException userNotExistsException) {
		String errorMessage = userNotExistsException.getExceptionMessage();
		DocNetExceptionResponse exceptionResponse = new DocNetExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
		exceptionResponse.setException(errorMessage);
		return exceptionResponse;
	}
	
	@ExceptionHandler(DocNetBadCredentialsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public DocNetExceptionResponse elyxBadCredentialsException(DocNetBadCredentialsException badCredentialsException){
		String errorMessage = badCredentialsException.getExceptionMessage();
		DocNetExceptionResponse exceptionResponse = new DocNetExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
		exceptionResponse.setException(errorMessage);
		return exceptionResponse;
	}
}
