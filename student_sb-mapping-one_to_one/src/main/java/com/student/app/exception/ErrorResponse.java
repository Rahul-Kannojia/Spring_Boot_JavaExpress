package com.student.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

	private String errorTitle;
	private HttpStatus status;
	private HttpStatusCode statusCode;
	private String errorDescription;
	
}
