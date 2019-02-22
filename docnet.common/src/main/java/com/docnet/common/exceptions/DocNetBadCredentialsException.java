package com.docnet.common.exceptions;


public class DocNetBadCredentialsException  extends DocNetAuthenticationException{

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	public DocNetBadCredentialsException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * 
	 */
	String getExceptionMessage() {
		return exceptionMessage;
	}

	void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
