package com.flightservices.flight.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Input fields for Flight Batch Detail.")
@JsonIgnoreProperties(ignoreUnknown = false)
public class FlightBatchDetail {

	@NotNull (message = "Selection cannot be null.")
	private String selection;
	
	@NotNull (message = "Flight Number cannot be null.")
	private String flightNumber;
	
	@NotNull (message = "Departure City cannot be null.")
	private String departureCity;
	
	@NotNull (message = "Arrival City cannot be null.")
	private String arrivalCity;
	
	@NotNull (message = "Departure Date Time cannot be null.")
	private String departureDateTime;
	
	@NotNull (message = "Arrival Date Time cannot be null.")
	private String arrivalDateTime;
	
	@NotNull (message = "Flight Closed status cannot be null.")
	private String flightClosed;
	
	@NotNull (message = "Airway Bill Number cannot be null.")
	private String awbId;
	
	@NotNull (message = "AWB cannot be null.")
	private String awb;
	
	@ApiModelProperty (name = "selection"
			, allowEmptyValue = false
			, required = true
			, value = "Selection.")
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	
	@ApiModelProperty (name = "flightNumber"
			, allowEmptyValue = false
			, required = true
			, value = "Flight Number.")
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	@ApiModelProperty (name = "departureCity"
			, allowEmptyValue = false
			, required = true
			, value = "Departure City.")
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	
	@ApiModelProperty (name = "arrivalCity"
			, allowEmptyValue = false
			, required = true
			, value = "Arrival City.")
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	
	@ApiModelProperty (name = "departureDateTime"
			, allowEmptyValue = false
			, required = true
			, value = "Departure DateTime.")
	public String getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	
	@ApiModelProperty (name = "arrivalDateTime"
			, allowEmptyValue = false
			, required = true
			, value = "Arrival DateTime.")
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	@ApiModelProperty (name = "flightClosed"
			, allowEmptyValue = false
			, required = true
			, value = "Flight Closed Status.")
	public String getFlightClosed() {
		return flightClosed;
	}
	public void setFlightClosed(String flightClosed) {
		this.flightClosed = flightClosed;
	}
	
	@ApiModelProperty (name = "awbId"
			, allowEmptyValue = false
			, required = true
			, value = "Flight Airway Bill Number.")
	public String getAwbId() {
		return awbId;
	}
	public void setAwbId(String awbId) {
		this.awbId = awbId;
	}
	
	@ApiModelProperty (name = "awb"
			, allowEmptyValue = false
			, required = true
			, value = "Flight AWB.")
	public String getAwb() {
		return awb;
	}
	public void setAwb(String awb) {
		this.awb = awb;
	}
}
