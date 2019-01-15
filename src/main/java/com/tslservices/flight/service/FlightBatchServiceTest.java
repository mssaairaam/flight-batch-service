package com.tslservices.flight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.tslservices.flight.model.FlightBatchDetail;;

@Service
public class FlightBatchServiceTest {

	/**
	 * Forms batch details and sends to front-end
	 * @param input
	 * @return
	 */
	public DataTablesOutput<FlightBatchDetail> fetchBatchDetails(DataTablesInput input) {
		DataTablesOutput<FlightBatchDetail> datatablesOutput = new DataTablesOutput<>();
		datatablesOutput.setDraw(input.getDraw());
		
		// set data
		List<FlightBatchDetail> flightBatchDetailList = new ArrayList<>();
		
		datatablesOutput.setData(flightBatchDetailList);
		
		
		// set record count
		datatablesOutput.setRecordsTotal(2);
		datatablesOutput.setRecordsFiltered(2);
		
		return datatablesOutput;
	}

}
