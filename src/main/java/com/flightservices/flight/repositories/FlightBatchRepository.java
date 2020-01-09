package com.flightservices.flight.repositories;

import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.flightservices.flight.model.FlightBatchDetail;
import com.flightservices.flight.repositories.util.FlightBatchRepositoryUtil;

@Component
public class FlightBatchRepository {

	Logger logger = LoggerFactory.getLogger(FlightBatchRepository.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private int totalRecordCount = 0;
	
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
	private int fetchTotoalRecordCount(String query) {
		StringBuilder queryToFetchTotalRecordCount = new StringBuilder();
		queryToFetchTotalRecordCount.append("SELECT COUNT(*) RECORD_COUNT FROM (");
		queryToFetchTotalRecordCount.append(query);
		queryToFetchTotalRecordCount.append(") TOTAL_ROWS");
		return jdbcTemplate.queryForObject(queryToFetchTotalRecordCount.toString(), new Object[] {}, Integer.class);
	}
	
	@Transactional(readOnly=true)
	public List<FlightBatchDetail> fetchBatchDetails(DataTablesInput input) {
		StringBuilder queryToFetchAllBatchDetails = new StringBuilder();
		queryToFetchAllBatchDetails.append("SELECT AWBID, AWB, FLIGHT_NUMBER, ARRIVAL_TIMESTAMP, DEPARTURE_TIMESTAMP, ORIGIN, DESTINATION, FLIGHT_CLOSED, (@ROW_NUMBER:=@ROW_NUMBER + 1) AS RNUM ");
		queryToFetchAllBatchDetails.append(" FROM T001_AWB_DTL T, (SELECT @ROW_NUMBER := 0) R WHERE 1=1 ");
		
		// append filters if present
		String flightNumber = input.getColumn("flightNumber").getSearch().getValue().trim();
		if (flightNumber.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND FLIGHT_NUMBER LIKE '%").append(flightNumber).append("%'");
		}
		String departureFlightDateTime = input.getColumn("departureDateTime").getSearch().getValue().trim();
		if (departureFlightDateTime.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND DEPARTURE_TIMESTAMP LIKE '%").append(departureFlightDateTime).append("%'");
		}
		String departureCity = input.getColumn("departureCity").getSearch().getValue().trim();
		if (departureCity.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND ORIGIN='").append(departureCity).append("'");
		}
		String arrivalCity = input.getColumn("arrivalCity").getSearch().getValue().trim();
		if (arrivalCity.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND DESTINATION='").append(arrivalCity).append("'");
		}
		String arrivalFlightDateTime = input.getColumn("arrivalDateTime").getSearch().getValue().trim();
		if (arrivalFlightDateTime.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND ARRIVAL_TIMESTAMP LIKE '%").append(arrivalFlightDateTime).append("%'");
		}
		
		// find order-by column
		Order columnToSort = input.getOrder().get(0);
		int columnIndex = columnToSort.getColumn();
		String columnName = FlightBatchRepositoryUtil.getColumnNameFromIndex(columnIndex);
		String direction = columnToSort.getDir().toUpperCase();
		
		// append the order by value
		queryToFetchAllBatchDetails.append(" ORDER BY ");
		queryToFetchAllBatchDetails.append(columnName + " " + direction);
				
		// find total record count
		totalRecordCount = fetchTotoalRecordCount(queryToFetchAllBatchDetails.toString());
		
		// from position and to position
		int startRow = input.getStart();
		int endRow = startRow + input.getLength();
		StringBuilder queryToFetchLimitedBatchDetails = new StringBuilder();
		queryToFetchLimitedBatchDetails.append("SELECT * FROM (");
		queryToFetchLimitedBatchDetails.append(queryToFetchAllBatchDetails);
		queryToFetchLimitedBatchDetails.append(") TOTAL_ROWS WHERE RNUM BETWEEN ");
		queryToFetchLimitedBatchDetails.append(startRow + 1);
		queryToFetchLimitedBatchDetails.append(" AND ");
		queryToFetchLimitedBatchDetails.append(endRow);
		
		logger.info("Total Flight Batch Record Count = " + totalRecordCount);
		logger.info("Populating Flight Batch Records From " + startRow + " - To " + endRow);
		
		return jdbcTemplate.query(queryToFetchLimitedBatchDetails.toString(), new Object[] {}, new FlightBatchRowMapper());
	}
	
	/**
	 * Create Flight Batch Details based on unique AWB Identifier.
	 * @param flightBatchDetail
	 * @return
	 */
	public int createBatchDetails(FlightBatchDetail flightBatchDetail) {
		StringBuilder createQuery = new StringBuilder();
		createQuery.append("INSERT INTO T001_AWB_DTL (AWB, FLIGHT_NUMBER, ARRIVAL_TIMESTAMP, DEPARTURE_TIMESTAMP, ORIGIN, DESTINATION, FLIGHT_CLOSED)");
		createQuery.append(" VALUES (?, ?, ?, ?, ?, ?, ?) ");
		
		Object[] parameters = { 
				flightBatchDetail.getAwb(),
				flightBatchDetail.getFlightNumber(),
				flightBatchDetail.getArrivalDateTime(),
				flightBatchDetail.getDepartureDateTime(),
				flightBatchDetail.getDepartureCity(),
				flightBatchDetail.getArrivalCity(),
				flightBatchDetail.getFlightClosed(),
				};
		
		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
		return jdbcTemplate.update(createQuery.toString(), parameters, types);
	}
	
	/**
	 * Update Flight Batch Details based on unique AWB Identifier.
	 * @param flightBatchDetail
	 * @return
	 */
	public int updateBatchDetails(FlightBatchDetail flightBatchDetail) {
		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("UPDATE T001_AWB_DTL ");
		updateQuery.append(" SET AWB=?, FLIGHT_NUMBER=?, ARRIVAL_TIMESTAMP=?, DEPARTURE_TIMESTAMP=?, ORIGIN=?, DESTINATION=?, FLIGHT_CLOSED=? ");
		updateQuery.append(" WHERE AWBID=?");
		
		Object[] parameters = { 
				flightBatchDetail.getAwb(),
				flightBatchDetail.getFlightNumber(),
				flightBatchDetail.getArrivalDateTime(),
				flightBatchDetail.getDepartureDateTime(),
				flightBatchDetail.getDepartureCity(),
				flightBatchDetail.getArrivalCity(),
				flightBatchDetail.getFlightClosed(),
				flightBatchDetail.getAwbId()
				};
		
		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		
		return jdbcTemplate.update(updateQuery.toString(), parameters, types);
	}
	
	/**
	 * Delete Flight Batch Details based on unique AWB Identifier.
	 * @param flightBatchDetail
	 * @return
	 */
	public int deleteBatchDetails(FlightBatchDetail flightBatchDetail) {
		StringBuilder deleteQuery = new StringBuilder();
		deleteQuery.append("DELETE FROM T001_AWB_DTL WHERE AWBID = ?");
		Object[] parameters = { flightBatchDetail.getAwbId() };
		int[] types = {Types.INTEGER};
		return jdbcTemplate.update(deleteQuery.toString(), parameters, types);
	}
}
