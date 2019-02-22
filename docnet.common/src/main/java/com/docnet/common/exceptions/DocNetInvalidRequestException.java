package com.docnet.common.exceptions;

public class DocNetInvalidRequestException extends DocNetRootException{

	
	/**
	 * 
	 */
	private String exceptionMessage;
	private static final long serialVersionUID = 1L;
	public DocNetInvalidRequestException(String exceptionMessage){
		this.exceptionMessage=exceptionMessage;
	}
	@Override
	String getExceptionMessage() {
		return exceptionMessage;
	}

	@Override
	void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
