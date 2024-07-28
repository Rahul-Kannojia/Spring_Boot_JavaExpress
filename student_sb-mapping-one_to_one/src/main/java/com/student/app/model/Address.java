package com.student.app.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.student.app.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "add_gen")
	@SequenceGenerator(name = "add_gen", initialValue = 1, allocationSize = 1, sequenceName = "add_seq")
	private Long addressId;
	private String city;
	private String state;
	private String country;
	private Integer pinCode;
	private String mobileNumber;

}
