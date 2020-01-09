package com.flightservices.flight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightservices.flight.model.FlightBatchDetail;
import com.flightservices.flight.service.FlightBatchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value = "Flight Batch Service.")
public class FlightBatchController {

	Logger logger = LoggerFactory.getLogger(FlightBatchController.class);

	@Autowired
	FlightBatchService flightBatchService;

	@RequestMapping("/")
	public String login(ModelMap modelMap) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(ModelMap modelMap) {
		modelMap.addAttribute("message", "You are logged out Successfully !");
		return "login";
	}
	
	@RequestMapping("/maintain-flight-batch")
	public String maintainFlightBatch(Model model) {
		model.addAttribute("message", "Maintain Flight Batch Page");
		return "maintain-flight-batch-view";
	}

	@RequestMapping(value = "/flight-batch-results", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<FlightBatchDetail> fetchFlightBatchResults(DataTablesInput input) {
		logger.info("Request -> Get all flight batch results.");
		return flightBatchService.fetchBatchDetails(input);
	}

	@RequestMapping(value = "/flight-batch-results/create", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Creates a flight batch record.", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully created a flight batch."),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
    public String createFlightBatchResults(@RequestBody FlightBatchDetail flightBatchDetail) {
		logger.info("Request -> Create flight batch detail :: ", flightBatchDetail);
		return flightBatchService.createBatchDetails(flightBatchDetail);
	}

	@RequestMapping(value = "/flight-batch-results/update", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Updates a flight batch record.", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated the flight batch detail."),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public String updateFlightBatchResults(@RequestBody FlightBatchDetail flightBatchDetail) {
		logger.info("Request -> Update flight batch detail :: ", flightBatchDetail);
		return flightBatchService.updateBatchDetails(flightBatchDetail);
	}

	@RequestMapping(value = "/flight-batch-results/delete", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Deletes a flight batch record.", response = String.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully deleted a flight batch detail."),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public String deleteFlightBatchResults(@RequestBody FlightBatchDetail flightBatchDetail) {
		logger.info("Request -> Delete flight batch detail :: ", flightBatchDetail);
		return flightBatchService.deleteBatchDetails(flightBatchDetail);
	}
}
