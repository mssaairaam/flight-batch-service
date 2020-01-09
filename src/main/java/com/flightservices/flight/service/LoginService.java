package com.flightservices.flight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightservices.flight.model.User;
import com.flightservices.flight.repositories.LoginRepository;

@Service
public class LoginService {

	Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginRepository loginRepository;
	
	public boolean validateUser (String username, String password) {
		boolean validationFlag = false;
		User user = loginRepository.fetchUser(username, password);
		if (user != null) {
			validationFlag = true;
			logger.info("Validation successful for the User : " + username + ".");
		}
		logger.info("Validation failed for the User : " + username + ".");
		return validationFlag;
	}
}
