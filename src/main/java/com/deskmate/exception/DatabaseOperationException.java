package com.deskmate.exception;

public class DataBaseOperationException {
	public class DatabaseOperationException extends RuntimeException {
	    public DatabaseOperationException(String message, Throwable cause) { super(message, cause); }
	}


}
