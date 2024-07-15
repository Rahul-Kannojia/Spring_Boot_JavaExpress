package com.consumer.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.app.model.User;
import com.consumer.app.service.UserServiceClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("consumer/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserServiceClient userServiceClient;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		log.info("Consumer :: UserController :: createUser {}",user.getEmail());
		userServiceClient.createUser(user);
	}
	
	@GetMapping("{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public User fetchUser(@PathVariable Long userId) {
		log.info("Consumer :: UserController :: getUser {}",userId);
		return userServiceClient.fetchUser(userId);
	}
	
	@PutMapping("{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateUser(@PathVariable Long userId, @RequestBody User user ) {
		log.info("Consumer :: UserController :: updateUser:  {} {} {}",userId, user.getUsername(), user.getEmail());
		userServiceClient.updateUser(userId, user);
	}
	
	@DeleteMapping("{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteUser(@PathVariable Long userId) {
		log.info("Consumer :: UserController :: deleteUser:  {}",userId);
		userServiceClient.deleteUser(userId);
	}
	
}
