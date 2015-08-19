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
<script src="${pageContext.request.contextPath}/resources/js/customize/notification_message.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notification.css">
<script src="${pageContext.request.contextPath}/resources/js/customize/autoComplete.js" type="text/javascript"></script>
<title>Sunday Service First Timer</title>


</head>
<body>
	<div id="div_success" style="display:none;">${response}</div>
	<div id="div_error" style="display:none;">${response}</div>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Sunday Service First Timer</h1>
			<div class="row">
				<div class="col-md-8">
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Title: </span> <span style="font-size:28px; font-style:bold;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceTitle}' /></span><br/>
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Date: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.createTime}' /></span><br/> 
			    <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Service: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceBean.serviceName}' /></span><br/>
			    </div>
			    <br/>
			      <a href="sunday_service.html"><button type="submit" class="btn btn-default">View All Sunday Service</button></a>
			    <a href="sunday_service_attendees.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit" class="btn btn-default">Service Attendees</button></a>
				<a href="service_first_timer.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit" class="btn btn-default">Service First Timer</button></a>
				<a href="sunday_service_profile.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit" class="btn btn-default">View Service Profile</button></a>
			<hr/>
			 </div>
			 <label><a href="add_first_timer.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit"
								class="btn btn-default">Add First Timer</button></a><br /></label>
			<br/>
			 
				<div class="row">
					<div class="date-form">
						<div class="form-horizontal">
							<label for="date-picker-2" class="control-label">Name</label>
							
							<div class="control-group">
								<form:form method="GET" style="align-items: center;" class="form-horizontal" 
											action="search_first_timer.html" modelAttribute="sunday_services">
									<form:input path="sundayServiceId" value="${SUNDAY_SERVICE_ID}" type="hidden"/>		
									<div class="controls">
										<div class="input-group">
											<form:input id="attendeesFirstTimer" path="keywords" type="text" class="form-control"
												placeholder="Search name..."></form:input>
											<span class="input-group-btn">
												<button class="btn btn-default" type="submit">Go!</button>
											</span>
										</div>
									</div>
								</form:form>
							</div>
							
							
						</div>
					</div>
			</div>
		
			<label for="date-picker-2" class="control-label">List of All First Timers</label>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th class="hidden-xs hidden-sm">Address</th>
							<th class="hidden-xs hidden-sm">Status</th>
							<th>Remarks</th>
							<th>Invited by</th>
							
						</tr>
					</thead>
					<tbody>

					<c:if test="${!empty firstTimerList}">
							<c:forEach items="${firstTimerList}" var="firstTimerList">
								<tr>
									<th><c:out value='${firstTimerList.guestBean.firstName}' /> &nbsp;
									<c:out value='${firstTimerList.guestBean.lastName}' /></th>
									<th class="hidden-xs hidden-sm"><c:out
											value='${firstTimerList.guestBean.address}' /></th>
									<th class="hidden-xs hidden-sm"><c:out
											value='${firstTimerList.firstTimerStatusBean.firstTimerStatus}' /></th>
									<th><c:out value='${firstTimerList.remarks}' /></th>
									<th><c:out value='${firstTimerList.attendeesBean.firstName}' /> &nbsp; <c:out value='${firstTimerList.attendeesBean.lastName}' /></th>
								</tr>
							</c:forEach>
					</c:if>

					</tbody>
				</table>
				<c:if test="${empty firstTimerList}">
					<center>No record found</center>
				</c:if>
			</div>
			
		</div>
	</div>

</body>
</html>