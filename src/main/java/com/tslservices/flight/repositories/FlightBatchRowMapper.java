package com.tslservices.flight.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tslservices.flight.model.FlightBatchDetail;

public class FlightBatchRowMapper implements RowMapper<FlightBatchDetail> {

	@Override
	public FlightBatchDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setFlightNumber(rs.getString("FLIGHT_NUMBER"));
		flightBatchDetail.setArrivalCity(rs.getString("DESTINATION"));
		flightBatchDetail.setDepartureCity(rs.getString("ORIGIN"));
		flightBatchDetail.setDepartureDateTime(rs.getString("RECEIVED_DATE"));
		flightBatchDetail.setArrivalDateTime(rs.getString("ARRIVAL_DATE").split(" ")[0] 
				+ " " 
				+ rs.getString("ARRIVAL_TIME").split(" ")[0]);
		flightBatchDetail.setFlightClosed("N");
		return flightBatchDetail;
	}

}
