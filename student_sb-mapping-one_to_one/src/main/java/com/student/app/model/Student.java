package com.student.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "std_gen")
	@SequenceGenerator(name = "std_gen",initialValue = 1,allocationSize = 1,sequenceName = "std_seq")
	private Integer studentId;
	private String studentName;
	private String department;
	
	/**
	 * One instance of Student have One instance of Address
	 * */
	@OneToOne
	private Address address;

	
	
}
