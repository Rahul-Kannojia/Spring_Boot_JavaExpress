package com.app.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorAPI {

	private Integer statusCode;
	private String status;
	private String title;
	private String details;
	private LocalDateTime localDateTime;
}
