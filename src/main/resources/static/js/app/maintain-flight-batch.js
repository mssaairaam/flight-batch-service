/**
 * Flight Batch Details Processing.
 */
var flightBatchGrid = null;
var flightSelectedRowDetails = {};
var columns = [ {
	title : "View / Update",
	width : '10%',
	data : 'selection',
	render : function(data, type, row, meta) {
		return '<a href="javascript:void(0)">' + data + '</a>';
	}
}, {
	title : "Flight Number",
	data : 'flightNumber'
}, {
	title : "Departure Flight Date",
	data : 'departureDateTime'
}, {
	title : "Departure City",
	data : 'departureCity'
}, {
	title : "Arrival City",
	data : 'arrivalCity'
}, {
	title : "Arrival Flight Date",
	data : 'arrivalDateTime'
}, {
	title : "Flight Closed",
	data : 'flightClosed'
}, {
	data : 'awbId',
	visible : false
}, {
	data : 'awb',
	visible : false
} ]

/**
 * Create and configure Flight Details Grid.
 * @returns
 */
function createFlightDetailsGrid() {

	flightBatchGrid = $('#flight-details-display-grid').DataTable({
		"columns" : columns,
		"processing" : true,
		"serverSide" : true,
		"searching" : false,
		"lengthChange" : false,
		"pageLength" : 10,
		"order" : [ [ 1, 'desc' ] ],
		"ajax" : {
			"url" : '/flight-batch-service/flight-batch-results',
			"type" : "GET",
			"data" : flatten
		},
		"dom" : '<"top"flp<"clear">>rt<"bottom"ifp<"clear">>'
	});

	$('#flight-details-display-grid tbody').on(
			'click',
			'tr td:nth-child(1)',
			function() {
				flightSelectedRowDetails = flightBatchGrid.row(
						$(this).parents('tr')).data();
				configureModalDialog(flightSelectedRowDetails);
				
				// enable and disable buttons
				$("#flight-detail-modal-create-button").prop('disabled', true);
				$("#flight-detail-modal-update-button").prop('disabled', false);
				$("#flight-detail-modal-delete-button").prop('disabled', false);
				
				// show the modal dialog
				$("#flight-detail-modal").modal('show');
			});
}

function configureModalDialog(flightSelectedRowDetails) {
	$("#flight-detail-modal-awb-text").val(
			flightSelectedRowDetails.awb);
	$("#flight-detail-modal-flight-number-text1").val(
			flightSelectedRowDetails.flightNumber.substring(0, 2));
	$("#flight-detail-modal-flight-number-text2").val(
			flightSelectedRowDetails.flightNumber.substring(2,
					flightSelectedRowDetails.flightNumber.length));
	$("#flight-detail-modal-departure-city-text").val(
			flightSelectedRowDetails.departureCity);
	$("#flight-detail-modal-arrival-city-text").val(
			flightSelectedRowDetails.arrivalCity);
	$("#flight-detail-modal-departure-date-text").val(
			flightSelectedRowDetails.departureDateTime.split(" ")[0]);
	$("#flight-detail-modal-departure-time-text").val(
			flightSelectedRowDetails.departureDateTime.split(" ")[1]);
	$("#flight-detail-modal-arrival-date-text").val(
			flightSelectedRowDetails.arrivalDateTime.split(" ")[0]);
	$("#flight-detail-modal-arrival-time-text").val(
			flightSelectedRowDetails.arrivalDateTime.split(" ")[1]);
}

function flatten(params) {

	// set custom parameters
	setCustomParameters(params);
	
	// update datatable parameters
	params.columns.forEach(function(column, index) {
		params['columns[' + index + '].data'] = column.data;
		params['columns[' + index + '].name'] = column.name;
		params['columns[' + index + '].searchable'] = column.searchable;
		params['columns[' + index + '].orderable'] = column.orderable;
		params['columns[' + index + '].search.regex'] = column.search.regex;

		// set search values
		//	    params['columns[' + index + '].search.value'] = column.search.value;
		params['columns[' + index + '].search.value'] = setColumnFilters(index,
				column.search.value);
	});
	delete params.columns;

	params.order.forEach(function(order, index) {
		params['order[' + index + '].column'] = order.column;
		params['order[' + index + '].dir'] = order.dir;
	});
	delete params.order;

	params['search.regex'] = params.search.regex;
	params['search.value'] = params.search.value;
	delete params.search;

	return params;
}

function setCustomParameters(params) {
	params['awbId'] = flightSelectedRowDetails.awbId;
	params['awb'] = flightSelectedRowDetails.awb;
	
	return params;
}

function setColumnFilters(index, defaultSearchValue) {
	var filterSearchValue = defaultSearchValue;

	// check if filter options
	if (index === 1) {
		var flightNumber = $.trim($("#flight-number-text1").val() + ' '
				+ $("#flight-number-text2").val());
		if (flightNumber && flightNumber.length > 0) {
			filterSearchValue = flightNumber;
		}
	} else if (index === 2) {
		var departureDate = $.trim($("#departure-date-text").val());
		var formattedDepartureDate = '';
		if (departureDate.length > 0) {
			formattedDepartureDate = formatDate(departureDate);
		}
		var departureDateAndTime = $.trim(formattedDepartureDate + ' '
				+ $("#departure-time-text").val());
		if (departureDateAndTime && departureDateAndTime.length > 0) {
			filterSearchValue = departureDateAndTime;
		}
	} else if (index === 3) {
		var departureCity = $.trim($("#departure-city-text").val());
		if (departureCity && departureCity.length > 0) {
			filterSearchValue = departureCity;
		}
	} else if (index === 4) {
		var arrivalCity = $.trim($("#arrival-city-text").val());
		if (arrivalCity && arrivalCity.length > 0) {
			filterSearchValue = arrivalCity;
		}
	} else if (index === 5) {
		var arrivalDate = $.trim($("#arrival-date-text").val());
		var formattedArrivalDate = '';
		if (arrivalDate.length > 0) {
			formattedArrivalDate = formatDate(arrivalDate);
		}
		var arrivalDateAndTime = $.trim(formattedArrivalDate + ' '
				+ $("#arrival-time-text").val());
		if (arrivalDateAndTime && arrivalDateAndTime.length > 0) {
			filterSearchValue = arrivalDateAndTime;
		}
	}

	return filterSearchValue;
}

/**
 * Searches for Flight Batch Details.
 * @returns
 */
function searchFlightBatchDetails() {
	flightBatchGrid.page(0);
	flightBatchGrid.ajax.reload(null, false);
}

/**
 * Clears the data in the UI.
 * @returns
 */
function clearFlightBatchDetails() {
	$("#flight-number-text1").val('');
	$("#flight-number-text2").val('');
	$("#departure-date-text").val('');
	$("#departure-time-text").val('');
	$("#departure-city-text").val('');
	$("#arrival-city-text").val('');
	$("#arrival-date-text").val('');
	$("#arrival-time-text").val('')
}

/**
 * Clears the data in the Modal Dialog UI.
 * @returns
 */
function clearFlightBatchDetailsInModal() {
	$("#flight-detail-modal-awb-text").val('');
	$("#flight-detail-modal-flight-number-text1").val('');
	$("#flight-detail-modal-flight-number-text2").val('');
	$("#flight-detail-modal-departure-date-text").val('');
	$("#flight-detail-modal-departure-time-text").val('');
	$("#flight-detail-modal-departure-city-text").val('');
	$("#flight-detail-modal-arrival-city-text").val('');
	$("#flight-detail-modal-arrival-date-text").val('');
	$("#flight-detail-modal-arrival-time-text").val('')
}

/**
 * Displays the Create Modal to add new Flight Batch Details.
 * @returns
 */
function showCreateFlightModal() {
	// clear all field values
	clearFlightBatchDetailsInModal();
	
	// enable and disable buttons
	$("#flight-detail-modal-create-button").prop('disabled', false);
	$("#flight-detail-modal-update-button").prop('disabled', true);
	$("#flight-detail-modal-delete-button").prop('disabled', true);
	$("#flight-detail-modal").modal('show');
}

/**
 * Create Flight Details
 * @returns
 */
function createFlightBatchDetails() {
	var awb = $.trim($("#flight-detail-modal-awb-text").val());
	
	var flightNumber = $.trim($("#flight-detail-modal-flight-number-text1").val() + ' '
			+ $("#flight-detail-modal-flight-number-text2").val());
	
	var departureDate = $.trim($("#flight-detail-modal-departure-date-text").val());
	var formattedDepartureDate = '';
	if (departureDate.length > 0) {
		formattedDepartureDate = formatDate(departureDate);
	}
	var departureDateTime = $.trim(formattedDepartureDate + ' '
			+ $("#flight-detail-modal-departure-time-text").val());

	var departureCity = $.trim($("#flight-detail-modal-departure-city-text").val());

	var arrivalCity = $.trim($("#flight-detail-modal-arrival-city-text").val());

	var arrivalDate = $.trim($("#flight-detail-modal-arrival-date-text").val());
	var formattedArrivalDate = '';
	if (arrivalDate.length > 0) {
		formattedArrivalDate = formatDate(arrivalDate);
	}
	var arrivalDateTime = $.trim(formattedArrivalDate + ' '
			+ $("#flight-detail-modal-arrival-time-text").val());
	
	// form request body
	var requestData = {
	        "flightNumber" : flightNumber,
	        "departureCity" : departureCity,
	        "arrivalCity" : arrivalCity,
	        "departureDateTime" : departureDateTime,
	        "arrivalDateTime" : arrivalDateTime,
	        "awbId" : flightSelectedRowDetails.awbId,
	        "awb" : awb
	}
	
	// call REST API to update the details
	$.ajax({
		url: '/flight-batch-service/flight-batch-results/create',
		type: 'POST',
		contentType: 'application/json',
        data: JSON.stringify(requestData),
        dataType: 'json',
        success: function(data){
        	alert(data);
        	
        	// update the grid
        	searchFlightBatchDetails();
        },
        error: function(e){
        	alert(e.responseText);
        }
	});
}

/**
 * Update Flight Details
 * @returns
 */
function updateFlightBatchDetails() {
	var awb = $.trim($("#flight-detail-modal-awb-text").val());
	
	var flightNumber = $.trim($("#flight-detail-modal-flight-number-text1").val() + ' '
			+ $("#flight-detail-modal-flight-number-text2").val());
	
	var departureDate = $.trim($("#flight-detail-modal-departure-date-text").val());
	var formattedDepartureDate = '';
	if (departureDate.length > 0) {
		formattedDepartureDate = formatDate(departureDate);
	}
	var departureDateTime = $.trim(formattedDepartureDate + ' '
			+ $("#flight-detail-modal-departure-time-text").val());

	var departureCity = $.trim($("#flight-detail-modal-departure-city-text").val());

	var arrivalCity = $.trim($("#flight-detail-modal-arrival-city-text").val());

	var arrivalDate = $.trim($("#flight-detail-modal-arrival-date-text").val());
	var formattedArrivalDate = '';
	if (arrivalDate.length > 0) {
		formattedArrivalDate = formatDate(arrivalDate);
	}
	var arrivalDateTime = $.trim(formattedArrivalDate + ' '
			+ $("#flight-detail-modal-arrival-time-text").val());
	
	// form request body
	var requestData = {
	        "flightNumber" : flightNumber,
	        "departureCity" : departureCity,
	        "arrivalCity" : arrivalCity,
	        "departureDateTime" : departureDateTime,
	        "arrivalDateTime" : arrivalDateTime,
//	        "flightClosed" : flightClosed,
	        "awbId" : flightSelectedRowDetails.awbId,
	        "awb" : awb
	}
	
	// call REST API to update the details
	$.ajax({
		url: '/flight-batch-service/flight-batch-results/update',
		type: 'POST',
		contentType: 'application/json',
        data: JSON.stringify(requestData),
        dataType: 'json',
        success: function(data){
        	alert(data);
        	
        	// update the grid
        	searchFlightBatchDetails();
        },
        error: function(e){
        	alert(e.responseText);
        }
	});
}

/**
 * Delete Flight Details
 * @returns
 */
function deleteFlightBatchDetails() {
	
	// form request body
	var requestData = {
	        "awbId" : flightSelectedRowDetails.awbId	
	}
	
	// call REST API to update the details
	$.ajax({
		url: '/flight-batch-service/flight-batch-results/delete',
		type: 'POST',
		contentType: 'application/json',
        data: JSON.stringify(requestData),
        dataType: 'json',
        success: function(data){
        	alert(data);
        	
        	// update the grid
        	searchFlightBatchDetails();
        },
        error: function(e){
        	alert(e.responseText);
        }
	});
}