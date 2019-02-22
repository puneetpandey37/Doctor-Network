package com.docnet.common.exceptions;

public class DocNetUserDataExistsException extends DocNetRootException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;

	public DocNetUserDataExistsException(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	@Override
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
