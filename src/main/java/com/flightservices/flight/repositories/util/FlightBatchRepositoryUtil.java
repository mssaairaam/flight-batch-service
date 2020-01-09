package com.flightservices.flight.repositories.util;

import com.flightservices.flight.util.Constants;

public class FlightBatchRepositoryUtil {

	private FlightBatchRepositoryUtil() {}
	
	public static String getColumnNameFromIndex (int columnIndex) {
		String columnName = null;
		switch(columnIndex) {
		case 1:
			columnName = Constants.SORT_BY_FLIGHT_NUMBER;
			break;
		case 2:
			columnName = Constants.SORT_BY_DEPARTURE_TIMESTAMP;
			break;
		case 3:
			columnName = Constants.SORT_BY_DEPARTURE_CITY;
			break;
		case 4:
			columnName = Constants.SORT_BY_ARRIVAL_CITY;
			break;
		case 5:
			columnName = Constants.SORT_BY_ARRIVAL_TIMESTAMP;
			break;
		default:
			columnName = Constants.SORT_BY_FLIGHT_NUMBER;
			break;
		}
		return columnName;
	}
}
