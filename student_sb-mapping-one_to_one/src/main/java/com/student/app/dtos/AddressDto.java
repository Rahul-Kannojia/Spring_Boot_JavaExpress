package com.student.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddressDto {

	private String city;
	private String state;
	private String country;
	private Integer pinCode;
	private String mobileNumber;
}
