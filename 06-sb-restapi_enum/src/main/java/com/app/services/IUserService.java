package com.app.services;

import java.util.List;

import com.app.entities.User;

public interface IUserService {

	public void createUser(User user);

	public User fetchUserById(Long id);

	public List<User> fetchAllUsers();

	public void updateUser(Long id, User userInput);

	public void updatePassword(Long id, User userInput);

	public void deleteUser(Long id);

	public void deleteUser_another(Long id);

	/**
	 * Using DSL Methods
	 */
	public User fetchUserData(String username, String password);

	public User fetchUserByUsername(String username);

	public User fetchUserByEmail(String email);

	public User fetchByUsernameOrEmail(String username, String email);

}
