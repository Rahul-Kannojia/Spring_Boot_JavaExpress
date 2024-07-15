package com.student.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "add_gen")
	@SequenceGenerator(name = "add_gen",initialValue = 1,allocationSize = 1,sequenceName = "add_seq")
	private Integer addressId;
	private String city;
	private String state;
	private String country;
	
	public Address(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	
}
