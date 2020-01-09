package com.flightservices.flight.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.flightservices.flight.model.User;

@Component
public class LoginRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public User fetchUser(String userId, String password) {
		User loginUser = null;
		String queryToFetchUser = "SELECT * FROM USER WHERE NAME = ? AND PASSWORD = ?";
		
		List<User> userList =  jdbcTemplate.query(queryToFetchUser, 
				new Object[] {userId, password}, 
				new UserRowMapper());
		Iterator<User> userListIterator = userList.iterator();
		while (userListIterator.hasNext()) {
			loginUser = userListIterator.next();
			break;
		}
		return loginUser;
	}
}
