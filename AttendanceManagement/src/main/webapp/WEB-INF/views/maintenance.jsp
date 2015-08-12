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

<title>Maintenance</title>


</head>
<body>
	<div id="maintenance_success" style="display:none;">${response}</div>
	<div id="maintenance_error" style="display:none;">${response}</div>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Maintenance</h1>
			
			
			<h3>Church Ministry</h3>
			<div class="row">
				<div class="date-form">
					<div class="form-horizontal">

					 <a href="add_ministry.html"><button type="submit"
								class="btn btn-default">Add Ministry</button></a><br /> 
							<!--
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
						</div> -->
						
						
					</div>
				</div>
			</div>


			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Ministry</th>
							<th class="hidden-xs hidden-sm">Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<c:if test="${!empty ministryList}">
							<c:forEach items="${ministryList}" var="ministryList">
								<tr>
									<th><c:out value='${ministryList.ministryName}' /></th>
									<th class="hidden-xs hidden-sm"><c:out
											value='${ministryList.description}' /></th>
									<th><a
										href="update_ministry.html?ministryId=${ministryList.ministryId}">Update</a>
										&nbsp;&nbsp; | &nbsp;&nbsp;
										<a href="delete_ministry.html?ministryId=${ministryList.ministryId}">Delete</a></th>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
				<c:if test="${empty ministryList}">
					<center>No record found</center>
				</c:if>
			</div>
			
			<hr/>
			
			<h3>Life Group Network (LGN)</h3>

			<div class="row">
				<div class="date-form">
					<div class="form-horizontal">

						<!-- <a href="add_group.html"><button type="submit"
								class="btn btn-default">Add Group</button></a><br /> <label
							for="date-picker-3" class="control-label">Name</label>
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
						</div> -->
						
						
					</div>
				</div>
			</div>


			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Group</th>
							<th class="hidden-xs hidden-sm">Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<c:if test="${!empty groupList}">
							<c:forEach items="${groupList}" var="groupList">
								<tr>
									<th><c:out value='${groupList.groupName}' /></th>
									<th class="hidden-xs hidden-sm"><c:out
											value='${groupList.description}' /></th>
									<th><a
										href="update_group.html?groupId=${groupList.groupId}">Update</a>
										&nbsp;&nbsp; <%-- | &nbsp;&nbsp;<a
										href="delete_group.html?groupId=${groupList.groupId}">Delete</a> --%></th>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
				<c:if test="${empty groupList}">
					<center>No record found</center>
				</c:if>
			</div>
			
			
			
			

			<hr/>

			<h3>Services</h3>

			<div class="row">
				<div class="date-form">
					<div class="form-horizontal">

						<a href="add_service.html"><button type="submit"
								class="btn btn-default">Add Service</button></a><br /> <label
							for="date-picker-3" class="control-label">Name</label>
						<!-- <div class="control-group">
							<div class="controls">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search name..."> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>

								</div>
							</div>
						</div> -->
					</div>
				</div>
			</div>


			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Service</th>
							<th class="hidden-xs hidden-sm">Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

						<c:if test="${!empty serviceList}">
							<c:forEach items="${serviceList}" var="serviceList">
								<tr>
									<th><c:out value='${serviceList.serviceName}' /></th>
									<th class="hidden-xs hidden-sm"><c:out
											value='${serviceList.description}' /></th>
									<th><a
										href="update_service.html?serviceId=${serviceList.serviceId}">Update</a>
										&nbsp;&nbsp; | &nbsp;&nbsp;<a
										href="delete_service.html?serviceId=${serviceList.serviceId}">Delete</a></th>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
				<c:if test="${empty serviceList}">
					<center>No record found</center>
				</c:if>
			</div>



		</div>
	</div>
</body>
</html>