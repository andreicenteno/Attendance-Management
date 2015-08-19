<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Dashboard</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Dashboard</h1>
			<h3>Birthday Celebrant</h3>
			
			Select 
			<select id="leave_type" class="form-control">
				<option value="true">For Today</option>
				<option value="false">For this Week</option>
				<option value="false">For this Month</option>
			</select>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th class="hidden-xs hidden-sm">Age</th>
							<th class="hidden-xs hidden-sm">Remarks</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Andrei Centeno</th>
							<th class="hidden-xs hidden-sm">13</th>
							<th class="hidden-xs hidden-sm">Andrei is now KKB</th>
						</tr>
						<tr>
							<th>Arne Centeno</th>
							<th class="hidden-xs hidden-sm">25</th>
							<th class="hidden-xs hidden-sm"></th>
						</tr>
					</tbody>
				</table>
			</div>
			<hr/>
			<h3>Attendees Group Adjustment</h3>
			
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th class="hidden-xs hidden-sm">Remarks</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Andrei Centeno</th>
							<th class="hidden-xs hidden-sm">Andrei is already 15 years old.  He is now KKB</th>
							<th>Update</th>
						</tr>
						<tr>
							<th>Arne Centeno</th>
							<th class="hidden-xs hidden-sm">Andrei is already 25 years old.  He is now YAM</th>
							<th>Update</th>
						</tr>
					</tbody>
				</table>
			</div>
			

		</div>
	</div>

</body>
</html>