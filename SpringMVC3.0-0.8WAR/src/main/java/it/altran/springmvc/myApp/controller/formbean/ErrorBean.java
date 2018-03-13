package it.altran.springmvc.myApp.controller.formbean;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorBean {
	
	private String codeError;
	private String messageError;
	private String stackTraceError;
	private Exception exception;
	
	public String getCodeError() {
		return codeError;
	}
	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	public String getStackTraceError() {
		
		if (exception==null) return stackTraceError;
		
		String stacktrace="";
		StringWriter writer = new StringWriter();
		exception.printStackTrace(new PrintWriter(writer));
		
		stacktrace=writer.toString();
		return stacktrace;
		
		
	}
	public void setStackTraceError(String stackTraceError) {
		this.stackTraceError = stackTraceError;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	
	
}
