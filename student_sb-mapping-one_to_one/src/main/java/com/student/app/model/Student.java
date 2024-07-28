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
	
	
	/**
	 * allocationSize  is the incremental value of Sequence Generator
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "stud_gen")
	@SequenceGenerator(name = "stud_gen",sequenceName = "stud_seq",initialValue = 1,allocationSize = 1)
	private Long studentId;
	private String studentName;
	private String department;
	private Long studentIdCardNumber;
	
	/**
	 * One instance of Student have One instance of Address
	 * */
	@OneToOne
	private Address address;

	
	
}
