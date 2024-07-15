package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
import com.app.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		log.info("UserController :: createUser {}", user.getEmail());
		userService.createUser(user);
	}

	@GetMapping("{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public User getUserById(@PathVariable Long userId) {
		log.info("UserController :: getUserById {}", userId);
		User user = userService.fetchUserById(userId);
		return user;
	}

	@GetMapping
	public List<User> getAllUsers() {
		log.info("UserController :: getAllUsers");
		return userService.fetchAllUsers();
	}

	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser {} {}", userId, user.getUsername());
		userService.updateUser(userId, user);
	}

	@PatchMapping("{userId}")
	public void updateUserPassword(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUserPassword {} {}", userId, user.getUsername());
		userService.updatePassword(userId, user);
	}

	@DeleteMapping("{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable Long userId) {
		log.info("UserController :: deleteUserById {}", userId);
		userService.deleteUser(userId);
	}

	/**
	 * Fetching User data using DSL methods --> 1.) Get User Data Using Username and
	 * Password
	 */
	@GetMapping("/login/{username}/{password}")
	public User fetchUser(@PathVariable String username, @PathVariable String password) {
		log.info("UserController :: fetchUser {}", username);
		return userService.fetchUserData(username, password);
	}

	/**
	 * Fetching User data using DSL methods --> 2.) Get User Data Using Username
	 */
	@GetMapping("/login/{username}")
	public User fetchUserByUsername(@PathVariable String username) {
		log.info("UserController :: findUserByUsername {}", username);
		return userService.fetchUserByUsername(username);
	}

	/**
	 * Fetching User data using DSL methods --> 3.) Get User Data Using Email
	 */
	@GetMapping("/login/email/{email}")
	public User fetchUserByEmail(@PathVariable String email) {
		log.info("UserController :: findUserByUsername {}", email);
		return userService.fetchUserByEmail(email);
	}

	/**
	 * Fetching User data using DSL methods --> 4.) Get User Data Using Username and
	 * Email
	 */
	@GetMapping("/login/user/{username}/{email}")
	public User fetchByUsernameOrEmail(@PathVariable String username, @PathVariable String email) {
		log.info("UserController :: findUserByUsername {} {}", username, email);
		return userService.fetchByUsernameOrEmail(username, email);
	}

}
