<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Batch</title>
<link rel="stylesheet" type="text/css" href="css/lib/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/lib/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/lib/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" type="text/css" href="css/lib/select.dataTables.min.css" />

<script type="text/javascript" src="js/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/lib/jquery-ui.js"></script>
<script type="text/javascript" src="js/lib/bootstrap.min.js"></script>
<script type="text/javascript" src="js/lib/datatables.min.js"></script>
<script type="text/javascript" src="js/lib/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/app/maintain-flight-batch.js"></script>
</head>
<body>
	<div id="flight-details-search-div" align="center">
		<table style="width: 72%; font-family: Calibri; font-size: 17px;">
			<tr style="height: 20px;"></tr>
			<tr height="25px" bordercolor="darkgreen"  class="table table-striped">
				<td align="center" style="background-color: #337ab7; border-color: #2e6da4;
				color: #fff; font-size: 30px;"
					colspan="6">Maintain Flight Batch</td>
				<td width="20px">
				</td>
				<td>
					<a href="/logout">Logout</a>
				</td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;"><label for="flight-number-text">Flight
						Number : </label> &nbsp;&nbsp;&nbsp;</td>
				<td>
					<input type="text" id="flight-number-text1"
					required="required" maxlength="2" style="width:30px">
					&nbsp;-&nbsp;
					<input type="text" id="flight-number-text2"
					required="required" maxlength="4" style="width:60px">
				</td>
				<td style="text-align: right;"><label for="departure-city-text">Departure
						City : </label> &nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="departure-city-text"
					placeholder="Enter Departure City" required="required" maxlength="3"></td>
				<td style="text-align: right;"><label for="arrival-city-text">Arrival
						City : </label> &nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="arrival-city-text"
					placeholder="Enter Arrival City" required="required" maxlength="3"></td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;"><label
					for="scheduled-departure-time-text">DEP : </label> &nbsp;&nbsp;&nbsp;</td>
				<td colspan="2">
					<input type="text" id="departure-date-text" placeholder="DD-Mon-YYYY">
					<input type="time" id="departure-time-text"
						step="1" required="required">
				</td>
				<td style="text-align: right;"><label
					for="scheduled-arrival-time-text">ARR :
				</label> &nbsp;&nbsp;&nbsp;</td>
				<td colspan="2">
					<input type="text" id="arrival-date-text" placeholder="DD-Mon-YYYY">
					<input type="time" id="arrival-time-text"
						step="1" required="required">
				</td>
			</tr>
			<tr style="height: 30px;"></tr>
			<tr>
				<td colspan="6" style="text-align: center;">
					<input
					type="button" value="Search Flight" id="search-flight-button" class="btn btn-success"
					style="height: 35px; width: 120px; font-size: 18px; font-family: Calibri;"
					onclick="searchFlightBatchDetails()">
					&nbsp;&nbsp;
					<input
					type="button" value="Update Flight" id="update-flight-button" disabled="disabled"
					class="btn btn-warning" style="height: 35px; width: 120px; font-size: 18px; font-family: Calibri;">
				</td>
			</tr>
			<tr style="height: 26px;"></tr>
			<tr>
				<td colspan="6">
					<table id="flight-details-display-grid" class="table table-striped table-bordered" style="width:100%">
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="flight-details-display-div">
		<script type="text/javascript">
			$(document).ready(function() {
				$("#departure-date-text").datepicker({ 
					dateFormat: 'dd-M-yy',
					changeMonth: true,
		            changeYear: true,
		            showButtonPanel: true
				});

				$("#arrival-date-text").datepicker({ 
					dateFormat: 'dd-M-yy',
					changeMonth: true,
		            changeYear: true,
		            showButtonPanel: true
				});
				createFlightDetailsGrid();
			});
		</script>
	</div>
</body>
</html>