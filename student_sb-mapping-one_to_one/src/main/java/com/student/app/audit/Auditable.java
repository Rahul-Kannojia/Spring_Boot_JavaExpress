package com.student.app.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Auditable {

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	
	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedBy
	@Column(name = "last_modified_by", insertable = false)
	private String lastModifiedBy;
	
	@LastModifiedDate
	@Column(name = "last_modified_date", insertable = false)
	private LocalDateTime lastModifiedDate;
	
}
