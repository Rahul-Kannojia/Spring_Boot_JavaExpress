package com.student.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(StudentAlreadyExistException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorTitle("Student Already Exist");
		errorResponse.setStatusCode(HttpStatusCode.valueOf(400));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setErrorDescription("Student is already exist so that we are not able to create Same student");
		return errorResponse;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(ResourceNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorTitle("Resouce Not Exist");
		errorResponse.setStatusCode(HttpStatusCode.valueOf(400));
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setErrorDescription("Resouce is not available in Database");
		return errorResponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorTitle("Resouce Not Exist");
		errorResponse.setStatusCode(HttpStatusCode.valueOf(500));
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setErrorDescription("Resouce is not available in Database");
		return errorResponse;
	}
}
