package com.flightservices.flight;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flightservices.flight.controller.FlightBatchController;
import com.flightservices.flight.model.FlightBatchDetail;
import com.flightservices.flight.repositories.FlightBatchRepository;
import com.flightservices.flight.service.FlightBatchService;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlightBatchServiceApplication.class)
public class FlightBatchServiceApplicationTests {

	@Autowired
	FlightBatchController flightBatchController;

	@Autowired
	FlightBatchService flightBatchService;

	@Autowired
	FlightBatchRepository flightBatchRepository;
	
	@Test
	public void createBatchDetails_whenFlightNumberIsNull() {
		// custom test data
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setFlightNumber(null);
		
		Mockito.when(flightBatchRepository.createBatchDetails(flightBatchDetail))
			.thenReturn(-1);
		
		// test
		String actualCreateResult = flightBatchService.createBatchDetails(flightBatchDetail);
		Assert.assertEquals("Error in Creating Flight Batch Details.", actualCreateResult);
	}
	
	@Test
	public void createBatchDetails_whenInsufficientFlightDetailInRequest_AwbIsNull() {
		// custom test data
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setFlightNumber(null);
		flightBatchDetail.setArrivalCity("SYD");
		flightBatchDetail.setDepartureCity("JFK");
		flightBatchDetail.setAwb(null);
		
		Mockito.when(flightBatchRepository.createBatchDetails(flightBatchDetail))
			.thenReturn(-1);
		
		// test
		String actualCreateResult = flightBatchService.createBatchDetails(flightBatchDetail);
		Assert.assertEquals("Error in Creating Flight Batch Details.", actualCreateResult);
	}
	
	@Test
	public void createBatchDetails_whenInsufficientFlightDetailInRequest() {
		// custom test data
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setArrivalCity("MAA");
		flightBatchDetail.setDepartureCity("FRA");
		
		Mockito.when(flightBatchRepository.createBatchDetails(flightBatchDetail))
			.thenReturn(-1);
		
		// test
		String actualCreateResult = flightBatchService.createBatchDetails(flightBatchDetail);
		Assert.assertEquals("Error in Creating Flight Batch Details.", actualCreateResult);
	}
	
	@Test
	public void updateBatchDetails_whenInsufficientFlightDetailInRequest() {
		// custom test data
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setArrivalCity("MAA");
		flightBatchDetail.setDepartureCity("FRA");
		
		Mockito.when(flightBatchRepository.updateBatchDetails(flightBatchDetail))
			.thenReturn(-1);
		
		// test
		String actualCreateResult = flightBatchService.updateBatchDetails(flightBatchDetail);
		Assert.assertEquals("Error in Updating Flight Batch Details.", actualCreateResult);
	}
	
	@Test
	public void updateBatchDetails_whenInsufficientFlightDetailInRequest_AwbIsNull() {
		// custom test data
		FlightBatchDetail flightBatchDetail = new FlightBatchDetail();
		flightBatchDetail.setArrivalCity("MAA");
		flightBatchDetail.setDepartureCity("FRA");
		flightBatchDetail.setAwb(null);
		
		Mockito.when(flightBatchRepository.updateBatchDetails(flightBatchDetail))
			.thenReturn(-1);
		
		// test
		String actualCreateResult = flightBatchService.updateBatchDetails(flightBatchDetail);
		Assert.assertEquals("Error in Updating Flight Batch Details.", actualCreateResult);
	}
}
