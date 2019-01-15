package com.tslservices.flight.model;

public class FlightBatchDetail {

	private String flightNumber;
	private String departureCity;
	private String arrivalCity;
	private String departureDateTime;
	private String arrivalDateTime;
	private String flightClosed;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getFlightClosed() {
		return flightClosed;
	}
	public void setFlightClosed(String flightClosed) {
		this.flightClosed = flightClosed;
	}
}
