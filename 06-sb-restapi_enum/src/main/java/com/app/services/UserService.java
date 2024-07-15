package com.app.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * LOGGING:
	 * 
	 * Lombok given a annotation for Logging i.e @Slf4j with the help of this we can
	 * write Log for our code. for logging how many data we want to print that much
	 * {} bracse we have to write
	 * 
	 * Ex: log.info("UserService ::updateUser {} {} ", id, userInput.toString());
	 * Here I am printing two values "id" & "User" info so 2 {} I have written
	 * 
	 */

	@Transactional
	public void createUser(User user) {
		log.info("UserService :: createUser");
		userRepository.save(user);
		log.info("UserService :: createUser :: User saved Successfully in Database {}", user.getUsername());
	}

	public User fetchUserById(Long id) {
		log.info("UserService :: fetchUserById {}", id);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		log.info("UserService :: fetchUserById :: User fetched Successfully", user.getUsername());
		return user;
	}

	public List<User> fetchAllUsers() {
		List<User> userList = userRepository.findAll();
		log.info("UserService :: fetchAllUsers {} ", userList);
		List<User> result = userList.stream().sorted(Comparator.comparing(User::getUsername)).toList();
		log.info("UserService :: fetchAllUsers:: Sorted User List according Username {} ", result);
		return result;
	}

	@Transactional
	public void updateUser(Long id, User inputUser) {
		log.info("UserService ::updateUser {} {} ", id, inputUser.getUsername());
		// User dbUser = userRepository.findById(id).get();
		try {
			User dbUser = fetchUserById(id);
			dbUser.setUsername(inputUser.getUsername());
			dbUser.setPassword(inputUser.getPassword());
			dbUser.setEmail(inputUser.getEmail());
			log.info("UserService ::updateUser:: User update successfully ");
			userRepository.save(dbUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in Updating the User");
		}
	}

	@Transactional
	public void updatePassword(Long id, User userInput) {
		log.info("UserService ::updateUser {} ", userInput.getUsername());
		User user = userRepository.findById(id).get();
		user.setPassword(userInput.getPassword());
		log.info("UserService ::updateUser:: User password update successfully ");
	}

	// Hard Delete: Permanently Delete the User from DB
	// Soft Delete: We can also do soft delete, just we have to change the Status as
	// Active or Inactive
	@Transactional
	public void deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			log.info("UserService :: DeleteUser {}", id);
		} else {
			log.error("User with given ID is not Found");
			throw new ResourceNotFoundException("User Not Found");
		}
	}

	@Transactional
	public void deleteUser_another(Long id) {
		log.info("UserService :: DeleteUser {}", id);
		if (userRepository.existsById(id)) {
			User user = fetchUserById(id);
			userRepository.delete(user);
			log.info("UserService :: DeleteUser :: User with given ID is deleted successfully");
		} else {
			log.error("User with given ID is not Found");
			throw new ResourceNotFoundException("User Not Found");
		}
	}

	/**
	 * Fetching User data using DSL methods--> 1.) Get User Data Using Username and
	 * Password
	 */
	public User fetchUserData(String username, String password) {
		log.info("UserService :: fetchUserData {}", username);
		return userRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * Fetching User data using DSL methods -->2.) Get User Data Using Username
	 */
	public User fetchUserByUsername(String username) {
		log.info("UserService :: findUserByUsername {}", username);
		return userRepository.findByUsername(username);
	}

	/**
	 * Fetching User data using DSL methods -->3.) Get User Data Using Email
	 */
	public User fetchUserByEmail(String email) {
		log.info("UserService :: findUserByEmail {}", email);
		return userRepository.findByEmail(email);
	}

	/**
	 * Fetching User data using DSL methods -->4.) Get User Data Using Username and
	 * Email
	 */
	public User fetchByUsernameOrEmail(String username, String email) {
		log.info("UserService :: findUserByEmail {} {}", username, email);
		return userRepository.findByUsernameOrEmail(username, email);
	}

}
