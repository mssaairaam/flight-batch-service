package com.tslservices.flight.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.tslservices.flight.model.User;

@Component
public class LoginRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public User fetchUser(String userId, String password) {
		User loginUser = null;
		StringBuilder queryToFetchUser = new StringBuilder();
		queryToFetchUser.append("SELECT [NAME]\r\n" + 
				"      ,[PASSWORD]\r\n" + 
				"      ,[LAST_LOGIN]\r\n" + 
				"      ,[LAST_LOGOUT]\r\n" + 
				"      ,[ID]\r\n" + 
				"  FROM [TSLNotificationEngine].[dbo].[USER]\r\n"); 
		queryToFetchUser.append(" WHERE NAME = '").append(userId).append("'");
		queryToFetchUser.append(" AND PASSWORD = '").append(password).append("'");
		
		List<User> userList =  jdbcTemplate.query(queryToFetchUser.toString(), new Object[] {}, new UserRowMapper());
		Iterator<User> userListIterator = userList.iterator();
		while (userListIterator.hasNext()) {
			loginUser = userListIterator.next();
			break;
		}
		return loginUser;
	}
}
