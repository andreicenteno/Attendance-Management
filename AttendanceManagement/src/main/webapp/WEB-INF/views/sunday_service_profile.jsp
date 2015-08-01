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
<title>Sunday Service Profile</title>


</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Sunday Service Profile</h1>
			<div class="row">
				<div class="col-md-8">
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Title: </span> <span style="font-size:28px; font-style:bold;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceTitle}' /></span><br/>
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Date: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.createTime}' /></span><br/> 
			    <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Service: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceBean.serviceName}' /></span><br/>
			    </div>
			    <br/>
			    <a href="sunday_service_attendees.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit" class="btn btn-default">Service Attendance</button></a>
				<hr/>
				<center><a href="#"><button type="submit" class="btn btn-default">GENERATE REPORT</button></a></center>
			 </div>
				
				<div class="row">
					<div class="date-form">
						<div class="form-horizontal">
							<label for="date-picker-2" class="control-label">Name</label>
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
						</div>
					</div>
			</div>
			
			<label for="date-picker-2" class="control-label">List of All Attendees</label>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Gender</th>
							<th>Contact Number</th>
							<th>Address</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty sundayServiceAttendeesList}">
							<c:forEach items="${sundayServiceAttendeesList}" var="sundayServiceAttendeesList">
					
								<tr>
									<th><c:out value='${sundayServiceAttendeesList.attendeesBean.firstName}' /></th>
									<th>
									<c:if test="${sundayServiceAttendeesList.attendeesBean.gender eq true}">
									<c:out value='Male' />
									</c:if>
									<c:if test="${sundayServiceAttendeesList.attendeesBean.gender eq false}">
									<c:out value='Female' />
									</c:if>
									</th>
									<th><c:out value='${sundayServiceAttendeesList.attendeesBean.contactNumber}' /></th>
									<th><c:out value='${sundayServiceAttendeesList.attendeesBean.address}' /></th>
									<th>
									<form:form method="POST" style="align-items: center;" class="form-horizontal" action="remove_service_attendees.html" modelAttribute="sunday_services_attendees">
										<form:input path="sundayServiceAttendeesId" type="hidden" value="${sundayServiceAttendeesList.sundayServiceAttendeesId}" class="form-control"></form:input>
										<form:input path="sundayServiceBean.sundayServiceId" type="hidden" value="${SUNDAY_SERVICE_ID}" class="form-control"></form:input>
										<form:input path="attendeesBean.attendeesId" type="hidden" value="${sundayServiceAttendeesList.attendeesBean.attendeesId}" class="form-control"></form:input>
										<button type="submit" class="btn btn-default">REMOVE</button>
									</form:form>
									</th>
								</tr>
								</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${empty sundayServiceAttendeesList}">
					<center>No Record Found</center>
				</c:if>
			</div>
			<br/>
			<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Attendees: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_ALL_ATTENDEES}</span><br/>
			<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB}</span><br/>
			<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM}</span><br/>
			<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Men: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_MEN}</span><br/>
			<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Women: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_WOMEN}</span><br/>
		<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN}</span>
		
		</div>
	</div>

</body>
</html>