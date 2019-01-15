package com.tslservices.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tslservices.flight.model.User;
import com.tslservices.flight.repositories.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public boolean validateUser (String username, String password) {
		boolean validationFlag = false;
		User user = loginRepository.fetchUser(username, password);
		if (user != null) {
			validationFlag = true;
		}
		return validationFlag;
	}
}
