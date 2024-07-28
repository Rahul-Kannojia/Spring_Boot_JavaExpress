package com.student.app.exception;

public class StudentAlreadyExistException extends RuntimeException{
	
	private static final long serialVersionUID = -449537667766040924L;

	public StudentAlreadyExistException(String msg) {
		super(msg);
	}

}
