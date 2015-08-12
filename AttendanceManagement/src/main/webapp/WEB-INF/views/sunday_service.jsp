<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Custom built online life group management for JIL or Jesus Is Lord">
<meta name="author" content="Andrei Centeno">
<link rel="icon" href="images/favicon.ico">
<script src="${pageContext.request.contextPath}/resources/js/customize/notification_message.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notification.css">

<title>Sunday Service</title>


</head>
<body>
	<div id="maintenance_success" style="display:none;">${response}</div>
	<div id="maintenance_error" style="display:none;">${response}</div>
	
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Sunday service</h1>

			<div class="row">
				<div class="date-form">
					<div class="form-horizontal">

						<a href="add_sunday_service.html"><button type="submit"
								class="btn btn-default">Add Sunday Service</button></a><br />

						<div class="col-md-4">
							<label for="date-picker-3" class="control-label">From</label>
							<div class="control-group">
								<div class="controls">
									<div class="input-group">
										<label for="date-picker-1" class="input-group-addon btn"><span
											class="glyphicon glyphicon-calendar"></span> </label> <input
											id="date-picker-1" type="text"
											class="date-picker form-control" />
									</div>
								</div>
							</div>
							<br />
						</div>

						<div class="col-md-4">
							<label for="date-picker-3" class="control-label">to</label>
							<div class="control-group">
								<div class="controls">
									<div class="input-group">
										<label for="date-picker-2" class="input-group-addon btn"><span
											class="glyphicon glyphicon-calendar"></span> </label> <input
											id="date-picker-2" type="text"
											class="date-picker form-control" />
									</div>
								</div>
							</div>
							<br />
						</div>

						<div class="col-md-4">
							<label for="date-picker-3" class="control-label">Name</label>
							<div class="control-group">
								<div class="controls">
									<div class="input-group">
										<input type="text" class="form-control"
											placeholder="Search name..."> <span
											class="input-group-btn">
											<button class="btn btn-default" type="button">Go!</button>
										</span>
									</div>
								</div>
							</div>
							<br />
						</div>


					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Date</th>
							<th>Title</th>
							<th>Service</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty sundayServiceList}">
							<c:forEach items="${sundayServiceList}" var="sundayServiceList">
								<tr>
									<th><c:out value='${sundayServiceList.createTime}' /></th>
									<th><a href="sunday_service_profile.html?sundayServiceId=${sundayServiceList.sundayServiceId}"><c:out value='${sundayServiceList.serviceTitle}' /> </a></th>
									<th><c:out value='${sundayServiceList.serviceBean.serviceName}' /></th>
									<th><a href="sunday_service_attendees.html?sundayServiceId=${sundayServiceList.sundayServiceId}">Add Attendees</a> &nbsp;
										| &nbsp; <a href="update_sunday_service.html?sundayServiceId=${sundayServiceList.sundayServiceId}">Update</a> &nbsp; |
										&nbsp; <a href="delete_sunday_service.html?sundayServiceId=${sundayServiceList.sundayServiceId}">Delete</a></th>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${empty sundayServiceList}">
					<center>No record found</center>
				</c:if>
			</div>


		</div>
	</div>
</body>
</html>