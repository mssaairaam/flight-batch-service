<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Batch - Login</title>
<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/lib/bootstrap.min.css" />

<script type="text/javascript" src="js/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/lib/jquery-ui.js"></script>
<script type="text/javascript" src="js/lib/bootstrap.min.js"></script>
</head>
<body>
	<div id="login-div" align="center">
		<form name="login" action="/authenticate" method="post">
			<table style="width: 30%; font-family: Calibri; font-size: 17px;">
				<tr style="height: 20px;"></tr>
				<tr height="25px" bordercolor="darkgreen"  class="table table-striped">
					<td align="center" style="background-color: #337ab7; border-color: #2e6da4;
					color: #fff; font-size: 30px;"
						colspan="6">Login</td>
				</tr>
				<tr style="height: 23px;"></tr>
				<tr>
					<td colspan="3" align="center" style="color: red">
						<div id="message">${message}</div>
					</td>
				</tr>
				<tr style="height: 23px;"></tr>
				<tr>
					<td style="text-align: right;">
						<label for="username">User Name : </label></td>
					<td width="20px">
					</td>
					<td>
						<input type="text" name="username" id="username"
							placeholder="Enter User Id" required="required"></td>
					<td width="20px">
					</td>
				</tr>
				<tr style="height: 23px;"></tr>
				<tr>
					<td style="text-align: right;">
						<label for="password">Password : </label></td>
					<td width="20px">
					</td>
					<td>
						<input type="password" name="password" id="password"
							placeholder="Enter Password" required="required"></td>
					<td width="20px">
					</td>
				</tr>
				<tr style="height: 30px;"></tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" id="submit" value="Login"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>