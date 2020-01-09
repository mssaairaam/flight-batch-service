package com.flightservices.flight.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.flightservices.flight.model.FlightBatchDetail;
import com.flightservices.flight.repositories.FlightBatchRepository;

@Service
public class FlightBatchService {

	Logger logger = LoggerFactory.getLogger(FlightBatchService.class);

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

		logger.info("Get flight batch detail :: Total Records : ", flightBatchRepository.getTotalRecordCount());
		
		return datatablesOutput;
	}

	/**
	 * Create batch details and sends to front-end
	 * 
	 * @param input
	 * @return
	 */
	public String createBatchDetails(FlightBatchDetail flightBatchDetail) {
		String updateMessage = "Successfully Created Flight Batch Details.";
		int noOfRowsCreated = flightBatchRepository.createBatchDetails(flightBatchDetail);
		if (noOfRowsCreated < 0) {
			updateMessage = "Error in Creating Flight Batch Details.";
		}
		logger.info("No. of flight details created : " + noOfRowsCreated);
		logger.info(updateMessage);
		return updateMessage;
	}

	/**
	 * Update batch details and sends to front-end
	 * 
	 * @param input
	 * @return
	 */
	public String updateBatchDetails(FlightBatchDetail flightBatchDetail) {
		String updateMessage = "Successfully Updated Flight Batch Details.";
		int noOfRowsUpdated = flightBatchRepository.updateBatchDetails(flightBatchDetail);
		if (noOfRowsUpdated < 0) {
			updateMessage = "Error in Updating Flight Batch Details.";
		}
		logger.info("No. of flight details updated : " + noOfRowsUpdated);
		logger.info(updateMessage);
		return updateMessage;
	}

	/**
	 * Delete batch details and sends to front-end.
	 * 
	 * @param input
	 * @return
	 */
	public String deleteBatchDetails(FlightBatchDetail flightBatchDetail) {
		String message = "Successfully Deleted Flight Batch Details.";
		int noOfRowsDeleted = flightBatchRepository.deleteBatchDetails(flightBatchDetail);
		if (noOfRowsDeleted < 0) {
			message = "Error in Deleting Flight Batch Details.";
		}
		logger.info("No. of flight details deleted : " + noOfRowsDeleted);
		logger.info(message);
		return message;
	}
}
