<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<title>Attendees</title>

</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Attendees</h1>

			<div class="row">
				<div class="date-form">
					<div class="form-horizontal">

						<a href="add_attendees.html"><button type="submit"
								class="btn btn-default">Add Attendees</button></a><br /> <label
							for="date-picker-3" class="control-label">Name</label>
						
						<div class="control-group">
							<form:form method="GET" style="align-items: center;" class="form-horizontal" 
										action="search_attendees.html" modelAttribute="attendees">
								<div class="controls">
									<div class="input-group">
										<form:input path="keywords" type="text" class="form-control"
											placeholder="Search name..."></form:input>
										<span class="input-group-btn">
											<button class="btn btn-default" type="button">Go!</button>
										</span>
									</div>
								</div>
							</form:form>
						</div>
						
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Contact Number</th>
							<th>Gender</th>
							<th>Group</th>
							<th>Ministry</th>
							<th>Birthday</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<c:if test="${!empty attendeesList}">
							<c:forEach items="${attendeesList}" var="attendeesList">
								<tr>
									<th><c:out value='${attendeesList.firstName}' /> &nbsp; <c:out
											value='${attendeesList.lastName}' /></th>
									<th><c:out value='${attendeesList.contactNumber}' /></th>
									<th><c:if test="${attendeesList.gender eq true}">
											<c:out value='Male' />
										</c:if> <c:if test="${attendeesList.gender eq false}">
											<c:out value='Female' />
										</c:if></th>
									<th><c:out value='${attendeesList.groupBean.groupName}' /></th>
									<th><c:out value='${attendeesList.ministryBean.ministryName}' /></th>
									<th><c:out value='${attendeesList.birthday}' /></th>
									<th><a
										href="update_attendees.html?attendeesId=${attendeesList.attendeesId}">Update</a>
										&nbsp;&nbsp; | &nbsp;&nbsp;<a
										href="delete_ministry.html?ministryId=${attendeesList.attendeesId}">Delete</a></th>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
				<c:if test="${empty attendeesList}">
					<center>No record found</center>
				</c:if>
			</div>


		</div>
	</div>
</body>
</html>