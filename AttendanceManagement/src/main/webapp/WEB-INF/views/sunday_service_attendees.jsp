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

<title>Sunday Service Attendees</title>


</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Sunday Service Attendees</h1>
			<div class="row">
				<div class="col-md-8">
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Title: </span> <span style="font-size:28px; font-style:bold;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceTitle}' /></span><br/>
				<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Date: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.createTime}' /></span><br/> 
			    <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px;">Service: </span> <span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceDetails.serviceBean.serviceName}' /></span>
			    
				</div>
				<a href="sunday_service_profile.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button type="submit" class="btn btn-default">View Service Profile</button></a>
				<a href="sunday_service.html"><button type="submit" class="btn btn-default">View All Sunday Service</button></a>
			    <hr/>
			 </div>

				<div class="row">
					<div class="date-form">
						<div class="form-horizontal">
							<label for="date-picker-2" class="control-label">Name</label>
							
							<div class="control-group">
							<form:form method="GET" style="align-items: center;" class="form-horizontal" 
										action="search_service_attendees.html" modelAttribute="attendees">
								<div class="controls">
									<div class="input-group">
									<form:input path="sundayServiceId" type="hidden" class="form-control" value="${SUNDAY_SERVICE_ID}"></form:input>
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
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty sundayServiceAttendeesList}">
							<c:forEach items="${sundayServiceAttendeesList}" var="sundayServiceAttendeesList">
								<tr>
									<th>
										<span style="font-size:24px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;"><c:out value='${sundayServiceAttendeesList.lastName}' />, &nbsp; <c:out value='${sundayServiceAttendeesList.firstName}' /> &nbsp; <c:out value='${sundayServiceAttendeesList.middleName}' /></span><br/>
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Contact Number: </span> 
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:red; "><c:out value='${sundayServiceAttendeesList.contactNumber}' /></span><br/>
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Gender: </span> 
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; ">
											<c:if test="${sundayServiceAttendeesList.gender eq true}">
												<c:out value='Male' />
											</c:if>
											<c:if test="${sundayServiceAttendeesList.gender eq false}">
												<c:out value='Female' />
											</c:if>
										</span><br/>
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Address: </span> 
										<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;"><c:out value='${sundayServiceAttendeesList.address}' /></span><br/>
									</th>
									<th style="text-align:center;">
									<form:form method="POST" style="align-items: center;" class="form-horizontal" action="insert_service_attendees.html" modelAttribute="sunday_services_attendees">
										<form:input path="sundayServiceBean.sundayServiceId" type="hidden" value="${SUNDAY_SERVICE_ID}" class="form-control"></form:input>
										<form:input path="attendeesBean.attendeesId" type="hidden" value="${sundayServiceAttendeesList.attendeesId}" class="form-control"></form:input>
										<button type="submit" class="btn btn-default">ATTEND</button>
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
			
			

		</div>
	</div>

</body>
</html>