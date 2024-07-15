package com.consumer.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {

	private Long id;
	private String username;
	private String password;
	private String email;
}
