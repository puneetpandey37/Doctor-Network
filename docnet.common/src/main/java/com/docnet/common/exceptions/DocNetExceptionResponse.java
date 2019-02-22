package com.docnet.common.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;

@XmlRootElement
public class DocNetExceptionResponse {
	
	private HttpStatus status;
	private String exception;
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
