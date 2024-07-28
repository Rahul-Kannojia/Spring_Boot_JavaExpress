package com.student.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentDto {
    
	private String studentName;
	private String department;
	private Long studentIdCardNumber;
	private AddressDto addressDto;
	
}
