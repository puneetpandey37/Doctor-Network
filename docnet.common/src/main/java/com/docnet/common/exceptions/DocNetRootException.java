package com.docnet.common.exceptions;

public abstract class DocNetRootException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	abstract String getExceptionMessage();
	abstract void setExceptionMessage(String exceptionMessage);
	
}
