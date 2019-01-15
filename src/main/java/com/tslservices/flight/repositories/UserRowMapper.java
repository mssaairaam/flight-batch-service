package com.tslservices.flight.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tslservices.flight.model.User;


public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User userDetail = new User();
		userDetail.setId(rs.getString("ID"));
		userDetail.setLastLoginTime(rs.getString("LAST_LOGIN"));
		userDetail.setLastLogoutTime(rs.getString("LAST_LOGOUT"));
		userDetail.setPassword(rs.getString("PASSWORD"));
		userDetail.setUsername(rs.getString("NAME"));
		return userDetail;
	}

}
