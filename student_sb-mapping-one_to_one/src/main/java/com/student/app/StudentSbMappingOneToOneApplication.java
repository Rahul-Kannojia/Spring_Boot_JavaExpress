package com.student.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentSbMappingOneToOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentSbMappingOneToOneApplication.class, args);
	}

	@Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
