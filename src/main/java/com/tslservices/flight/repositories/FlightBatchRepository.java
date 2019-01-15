package com.tslservices.flight.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tslservices.flight.model.FlightBatchDetail;
import com.tslservices.flight.repositories.util.FlightBatchRepositoryUtil;

@Component
public class FlightBatchRepository {

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
		queryToFetchAllBatchDetails.append("SELECT FLIGHT_NUMBER, RECEIVED_DATE, ORIGIN, DESTINATION, ARRIVAL_DATE, ARRIVAL_TIME, ROW_NUMBER() OVER (ORDER BY ");
		
		// find order-by column
		Order columnToSort = input.getOrder().get(0);
		int columnIndex = columnToSort.getColumn();
		String columnName = FlightBatchRepositoryUtil.getColumnNameFromIndex(columnIndex);
		String direction = columnToSort.getDir().toUpperCase();
		
		// append the order by value
		queryToFetchAllBatchDetails.append(columnName + " " + direction);
		queryToFetchAllBatchDetails.append(") RNUM FROM TSLNotificationEngine.dbo.T001_AWB_DTL WHERE 1=1 ");
		
		// append filters if present
		String flightNumber = input.getColumn("flightNumber").getSearch().getValue().trim();
		if (flightNumber.length() > 0) {
			queryToFetchAllBatchDetails.append(" AND FLIGHT_NUMBER='").append(flightNumber).append("'");
		}
		String departureFlightDateTime = input.getColumn("departureDateTime").getSearch().getValue().trim();
		if (departureFlightDateTime.length() > 0) {
			String[] flightDepartureDetails = departureFlightDateTime.split(" ");
			try {
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				Date date = format1.parse(flightDepartureDetails[0]);
				
				// departure date
				queryToFetchAllBatchDetails.append(" AND DATEPART(yy, RECEIVED_DATE) = ").append(yearFormat.format(date)).append("");
				queryToFetchAllBatchDetails.append(" AND DATEPART(mm, RECEIVED_DATE) = ").append(monthFormat.format(date)).append("");
				queryToFetchAllBatchDetails.append(" AND DATEPART(dd, RECEIVED_DATE) = ").append(dayFormat.format(date)).append("");
				
				// departure time
				String departureTime = flightDepartureDetails[1];
				String[] departureTimeDetailsArray = departureTime.split(":");
				queryToFetchAllBatchDetails.append(" AND DATEPART(HOUR, RECEIVED_DATE) = ").append(departureTimeDetailsArray[0]).append("");
				queryToFetchAllBatchDetails.append(" AND DATEPART(MINUTE, RECEIVED_DATE) = ").append(departureTimeDetailsArray[1]).append("");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			String[] flightArrivalDetails = arrivalFlightDateTime.split(" ");
			try {
				SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
				SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
				SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
				SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				Date date = format1.parse(flightArrivalDetails[0]);
				// arrival date
				queryToFetchAllBatchDetails.append(" AND DATEPART(yy, ARRIVAL_DATE) = ").append(yearFormat.format(date)).append("");
				queryToFetchAllBatchDetails.append(" AND DATEPART(mm, ARRIVAL_DATE) = ").append(monthFormat.format(date)).append("");
				queryToFetchAllBatchDetails.append(" AND DATEPART(dd, ARRIVAL_DATE) = ").append(dayFormat.format(date)).append("");
				// arrival time
				queryToFetchAllBatchDetails.append(" AND ARRIVAL_TIME='").append(flightArrivalDetails[1]).append("'");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		
		System.out.println("Total Record Count = " + totalRecordCount);
		System.out.println("Populating Records From " + startRow + " - To " + endRow);
		
		return jdbcTemplate.query(queryToFetchLimitedBatchDetails.toString(), new Object[] {}, new FlightBatchRowMapper());
	}
}
