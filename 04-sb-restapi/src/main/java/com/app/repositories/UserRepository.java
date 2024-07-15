package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.User;

@Repository // It is optional to write when we are using JPA
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * "select * from user where id=1"; -----> findById(id)
	 * 
	 * DSL Methods: "select * from user where username='rahul';" OR "select * from
	 * user where email='rahul@gmail.com'"
	 */

	User findByUsername(String username);

	User findByEmail(String email);

	/**
	 * Here inputs will be Username and Email to fetch User record
	 */
	User findByUsernameOrEmail(String username, String email);

	/**
	 * Here inputData will be Username And Password both to fetch User record
	 */
	User findByUsernameAndPassword(String username, String password);
}
