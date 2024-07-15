package com.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * New Way
	 * */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorAPI handleException(ResourceNotFoundException exception) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setDetails(exception.toString());
		errorApi.setTitle("Resource Not Found in Database");
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorApi.setLocalDateTime(LocalDateTime.now());
		return errorApi;
				
	}
	
	/**
	 * Old Way
	 * */
	//@ExceptionHandler(ResourceNotFoundException.class)
	//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorAPI> handleException_old(ResourceNotFoundException exception) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setDetails(exception.toString());
		errorApi.setTitle("Resource Not Found in Database");
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorApi.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorAPI handleException(Exception exception) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setDetails(exception.toString());
		errorApi.setTitle("There is some problem, something went wrong");
		errorApi.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorApi.setLocalDateTime(LocalDateTime.now());
		return errorApi;
				
	}
	
}
