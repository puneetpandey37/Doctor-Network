package com.docnet.common.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocNetNoContentException extends DocNetRootException{
	
	private static final long serialVersionUID = 1L;
	
	private String exceptionMessage;
	public DocNetNoContentException(String exceptionMessage){
		this.exceptionMessage=exceptionMessage;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
