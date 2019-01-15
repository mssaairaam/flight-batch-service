package com.tslservices.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tslservices.flight.model.FlightBatchDetail;
import com.tslservices.flight.service.FlightBatchService;

@Controller
public class FlightBatchController {

	@Autowired
	FlightBatchService flightBatchService;

	@RequestMapping("/")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/maintain-flight-batch")
	public String maintainFlightBatch(Model model) {
		model.addAttribute("message", "Maintain Flight Batch Page");
		return "maintain-flight-batch-view";
	}

//	@RequestMapping(value = "/flight-batch-results-test", method = RequestMethod.GET)
//	@ResponseBody
//	public DataTablesOutput<FlightBatchDetail> fetchFlightBatchResultsTest(DataTablesInput input) {
//		return flightBatchService.fetchBatchDetailsTest(input);
//	}

	@RequestMapping(value = "/flight-batch-results", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<FlightBatchDetail> fetchFlightBatchResults(DataTablesInput input) {
		return flightBatchService.fetchBatchDetails(input);
	}
}
