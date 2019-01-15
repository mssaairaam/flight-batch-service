package com.tslservices.flight.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.tslservices.flight.model.FlightBatchDetail;
import com.tslservices.flight.repositories.FlightBatchRepository;

@Service
public class FlightBatchService {

	@Autowired
	private FlightBatchRepository flightBatchRepository;

	/**
	 * Forms batch details and sends to front-end
	 * 
	 * @param input
	 * @return
	 */
//	public DataTablesOutput<FlightBatchDetail> fetchFlightBatchResults(@Valid DataTablesInput input) {
//		return flightBatchRepository.findAll(input);
//	}

	/**
	 * Forms batch details and sends to front-end
	 * 
	 * @param input
	 * @return
	 */
	public DataTablesOutput<FlightBatchDetail> fetchBatchDetails(DataTablesInput input) {
		DataTablesOutput<FlightBatchDetail> datatablesOutput = new DataTablesOutput<>();
		datatablesOutput.setDraw(input.getDraw());

		// set data
		List<FlightBatchDetail> flightBatchDetailList = new ArrayList<>();
		flightBatchDetailList = flightBatchRepository.fetchBatchDetails(input);
		datatablesOutput.setData(flightBatchDetailList);

		// set record count
		datatablesOutput.setRecordsTotal(flightBatchRepository.getTotalRecordCount());
		datatablesOutput.setRecordsFiltered(flightBatchRepository.getTotalRecordCount());

		return datatablesOutput;
	}

}
