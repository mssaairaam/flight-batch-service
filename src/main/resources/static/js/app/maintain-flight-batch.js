/**
 * Flight Batch Details Processing.
 */
var flightBatchGrid = null;

var columns = [ {
    orderable: false,
    className: 'select-checkbox',
    targets:   0
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
} ]

/**
 * Create and configure Flight Details Grid.
 * @returns
 */
function createFlightDetailsGrid () {
	
	flightBatchGrid = $('#flight-details-display-grid').DataTable( {
        "columns": columns,
        "select": {
            style:    'os',
            selector: 'td:first-child'
        },
        "processing": true,
        "serverSide": true,
        "searching": false,
        "lengthChange": false,
        "pageLength": 10,
        "order": [[1, 'desc']],
        "ajax": {
            "url": '/flight-batch-results',
            "type": "GET",
            "data": flatten
        },
        "dom": '<"top"flp<"clear">>rt<"bottom"ifp<"clear">>'
    } );
	
	$('#flight-details-display-grid tbody').on( 'click', 'tr', function () {
        console.log(flightBatchGrid.rows('.selected').data());
    } );
}

function flatten(params) {
	  params.columns.forEach(function (column, index) {
	    params['columns[' + index + '].data'] = column.data;
	    params['columns[' + index + '].name'] = column.name;
	    params['columns[' + index + '].searchable'] = column.searchable;
	    params['columns[' + index + '].orderable'] = column.orderable;
	    params['columns[' + index + '].search.regex'] = column.search.regex;
	    
	    // set search values
//	    params['columns[' + index + '].search.value'] = column.search.value;
	    params['columns[' + index + '].search.value'] = setColumnFilters(index, column.search.value);
	  });
	  delete params.columns;

	  params.order.forEach(function (order, index) {
	    params['order[' + index + '].column'] = order.column;
	    params['order[' + index + '].dir'] = order.dir;
	  });
	  delete params.order;

	  params['search.regex'] = params.search.regex;
	  params['search.value'] = params.search.value;
	  delete params.search;

	  return params;
	}

function setColumnFilters (index, defaultSearchValue) {
	var filterSearchValue = defaultSearchValue;
	
	// check if filter options
	if (index === 0) {
		var flightNumber = $("#flight-number-text1").val() + $("#flight-number-text2").val();
		if (flightNumber && flightNumber.length > 0) {
			filterSearchValue = flightNumber;
		}
	} else if (index === 1) {
		var departureDateAndTime = $("#departure-date-text").val() + ' ' + $("#departure-time-text").val();
		if (departureDateAndTime && departureDateAndTime.length > 0) {
			filterSearchValue = departureDateAndTime;
		}
	} else if (index === 2) {
		var departureCity = $("#departure-city-text").val();
		if (departureCity && departureCity.length > 0) {
			filterSearchValue = departureCity;
		}
	} else if (index === 3) {
		var arrivalCity = $("#arrival-city-text").val();
		if (arrivalCity && arrivalCity.length > 0) {
			filterSearchValue = arrivalCity;
		}
	} else if (index === 4) {
		var arrivalDateAndTime = $("#arrival-date-text").val() + ' ' + $("#arrival-time-text").val();
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