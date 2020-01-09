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
<script type="text/javascript" src="js/app/util.js"></script>
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
					<a href="/flight-batch-service/v1/logout">Logout</a>
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
						step="60" required="required">
				</td>
				<td style="text-align: right;"><label
					for="scheduled-arrival-time-text">ARR :
				</label> &nbsp;&nbsp;&nbsp;</td>
				<td colspan="2">
					<input type="text" id="arrival-date-text" placeholder="DD-Mon-YYYY">
					<input type="time" id="arrival-time-text"
						step="60" required="required">
				</td>
			</tr>
			<tr style="height: 30px;"></tr>
			<tr>
				<td colspan="6" style="text-align: center;">
					<input
					type="button" value="Search Flight" id="search-flight-button" class="btn btn-primary"
					style="height: 35px; width: 120px; font-size: 18px; font-family: Calibri;"
					onclick="searchFlightBatchDetails()">
					&nbsp;&nbsp;
					<input
					type="button" value="Create Flight" id="create-flight-button" 
					class="btn btn-success" style="height: 35px; width: 120px; font-size: 18px; font-family: Calibri;"
					onclick="showCreateFlightModal()">
					&nbsp;&nbsp;
					<input
					type="button" value="Clear" id="clear-details-button" 
					class="btn btn-default" style="height: 35px; width: 120px; font-size: 18px; font-family: Calibri;"
					onclick="clearFlightBatchDetails()">
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
	<!-- Modal -->
	<div id="flight-detail-modal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Create / Update / Delete Flight Details</h4>
	      </div>
	      <div class="modal-body">
	       <table>
			<tr>
				<td style="text-align: right;">
					<label>AWB Number : </label>
				</td>
				<td width="20px">
				</td>
				<td colspan="2"><input type="text" id="flight-detail-modal-awb-text"
					 maxlength="11">
				</td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;">
					<label>Flight Number : </label>
				</td>
				<td width="20px">
				</td>
				<td>
					<input type="text" id="flight-detail-modal-flight-number-text1"
					maxlength="2" style="width:30px">&nbsp;-&nbsp;
					<input type="text" id="flight-detail-modal-flight-number-text2"
					maxlength="4" style="width:60px">
				</td>
				<td width="10px">
				<td>
				</td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;">
					<label>Departure City : </label>
				</td>
				<td width="20px">
				</td>
				<td colspan="2"><input type="text" id="flight-detail-modal-departure-city-text"
					 maxlength="3">
				</td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;">
					<label>Arrival City : </label> 
				</td>
				<td width="20px">
				</td>
				<td colspan="2"><input type="text" id="flight-detail-modal-arrival-city-text"
					maxlength="3"></td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;">
					<label>DEP : </label>
				</td>
				<td width="20px">
				</td>
				<td>
					<input type="text" id="flight-detail-modal-departure-date-text">
				</td>
				<td width="10px">
				<td>
					<input type="time" id="flight-detail-modal-departure-time-text"
						step="60" >
				</td>
			</tr>
			<tr style="height: 23px;"></tr>
			<tr>
				<td style="text-align: right;">
					<label>ARR :</label> 
				</td>
				<td width="20px">
				</td>
				<td>
					<input type="text" id="flight-detail-modal-arrival-date-text">
				</td>
				<td width="10px">
				</td>
				<td>
					<input type="time" id="flight-detail-modal-arrival-time-text"
						step="60">
				</td>
			</tr>
	       </table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="flight-detail-modal-create-button" class="btn btn-success" data-dismiss="modal" onclick="createFlightBatchDetails()">Create</button>
	        <button type="button" id="flight-detail-modal-update-button" class="btn btn-primary" data-dismiss="modal" onclick="updateFlightBatchDetails()">Update</button>
	        <button type="button" id="flight-detail-modal-delete-button" class="btn btn-danger" data-dismiss="modal" onclick="deleteFlightBatchDetails()">Delete</button>
	        <button type="button" id="flight-detail-modal-close-button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	<div id="flight-details-display-div">
		<script type="text/javascript">
			$(document).ready(function() {
				// configure date pickers
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
				
				// create and configure the grid
				createFlightDetailsGrid();
				
				// configure datepicker in update modal
				$("#flight-detail-modal-departure-date-text").datepicker({ 
					dateFormat: 'dd-M-yy',
					changeMonth: true,
		            changeYear: true,
		            showButtonPanel: true
				});
				$("#flight-detail-modal-arrival-date-text").datepicker({ 
					dateFormat: 'dd-M-yy',
					changeMonth: true,
		            changeYear: true,
		            showButtonPanel: true
				});
			});
		</script>
	</div>
</body>
</html>