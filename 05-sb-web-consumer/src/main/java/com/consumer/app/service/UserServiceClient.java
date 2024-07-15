package com.consumer.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.consumer.app.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceClient {
	
	
	/**
	 * Autowiring are 2 types: 1) ByName 2)ByType
	 * Here we are doing ByType, because during Autowiring Container will check 
	 * First ByName, If name(method name) not matching(configuration class method name) where we are using @Bean
	 * Ex: Name of the Bean is "getRestTemplate" in Container, but Reference variable name here is "restTemplate"
	 * So name is not matching, Now container will Type of this, Here and Bean creation method Return Type is "RestTemplate"
	 * 
	 * */
	@Autowired
	private RestTemplate restTemplate;
	
	public void createUser(User user) {
		try {
			String url = "http://localhost:8081/api/v1/user";

			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			headers.set("Accept", "application/json");

			HttpEntity<User> entity = new HttpEntity<User>(user, headers);
			
			ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);
			if(response.getStatusCode()== HttpStatus.CREATED) {
				log.info("UserServiceClient :: createUser :: User created successfully");
			}else {
				log.error(" UserServiceClient :: createUser :: User not created");
			}
		} catch (RestClientException ex) {
			log.error("UserServiceClient :: createUser :: Failed to create User");
			ex.printStackTrace();
		}

	}
	
	public User fetchUser(Long userId) {
		log.info("UserServiceClient :: getUser: {}", userId);
		User user = null;
		try {
			String url = "http://localhost:8081/api/v1/user/" + userId;
			ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

			if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
				user = response.getBody();
			} else {
				log.error("UserServiceClient ::getUser ::Response do not have body");
			}

		} catch (RestClientException ex) {
			log.error("UserServiceClient ::getUser :: Failed to Fetch User");
			ex.printStackTrace();
		}
		return user;
	}
	
	public void updateUser(Long userId, User user) {
		log.info("UserServiceClient :: updateUser: {}", userId);
		try {
			String url = "http://localhost:8081/api/v1/user/"+userId;
			
//			Map<String, String> params = new HashMap<>();
//			params.put("id", );
			
			User updatedUser = new User();
			updatedUser.setUsername(user.getUsername());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setPassword(user.getPassword());
			
			restTemplate.put(url,updatedUser);
			log.info("UserServiceClient :: updateUser: User updated successfully");
			
		}catch(RestClientException ex) {
			log.error("UserServiceClient :: updateUser:: Failed to Update User");
			ex.printStackTrace();
		}
	}

	public void deleteUser(Long userId) {
		try {
			String url = "http://localhost:8081/api/v1/user/"+userId;
			log.info("UserServiceClient :: deleteUser::User Deleted Successfully ");
			restTemplate.delete(url);
		}catch(RestClientException ex){
			log.error("UserServiceClient :: deleteUser:: Failed to Delete User");
			ex.printStackTrace();
		}
	}
}
