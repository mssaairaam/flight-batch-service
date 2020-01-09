package com.flightservices.flight.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.flightservices.flight.model.FlightBatchDetail;
import com.flightservices.flight.util.Constants;

public class FlightBatchRowMapper implements RowMapper<FlightBatchDetail> {

	@Override
	public FlightBatchDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setFlightNumber(rs.getString("FLIGHT_NUMBER"));
		flightBatchDetail.setArrivalCity(rs.getString("DESTINATION"));
		flightBatchDetail.setDepartureCity(rs.getString("ORIGIN"));
		flightBatchDetail.setDepartureDateTime(rs.getString("DEPARTURE_TIMESTAMP"));
		flightBatchDetail.setArrivalDateTime(rs.getString("ARRIVAL_TIMESTAMP"));
		flightBatchDetail.setFlightClosed(rs.getString("FLIGHT_CLOSED"));
		flightBatchDetail.setAwbId(rs.getString("AWBID"));
		flightBatchDetail.setAwb(rs.getString("AWB"));
		flightBatchDetail.setSelection(Constants.UPDATE);
		return flightBatchDetail;
	}
}
