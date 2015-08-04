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

<title>View Attendees Report</title>

</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">View Attendees Report</h1>
			
			 <!-- View for All -->
			<c:if test="${GENDER eq 'all' && GROUP_ID eq 0 && MINISTRY_ID eq 0}">
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all attendees: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_ALL}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB MALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB FEMALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM MALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM FEMALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children Male: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children Female: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Men: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_MEN}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Women: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_WOMEN}</span><br/>
			</c:if>
			<!-- Otherwise Show this -->
			<c:if test="${GENDER ne 'all' || GROUP_ID ne 0 || MINISTRY_ID ne 0}">
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all records: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_ALL}</span><br/>
			</c:if>
			<!-- Gender Male -->
			<c:if test="${GENDER eq 'true' && GROUP_ID eq 0 && MINISTRY_ID eq 0}">
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB MALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM MALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children Male: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN_MALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Men: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_MEN}</span><br/>	
			</c:if>
			
			<!-- Gender Female -->
			<c:if test="${GENDER eq 'false' && GROUP_ID eq 0 && MINISTRY_ID eq 0}">
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all KKB FEMALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_KKB_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all YAM FEMALE: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_YAM_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Children Female: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_CHILDREN_FEMALE}</span><br/>
				<span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px;">Total of all Women: </span> <span style="font-size:18px;   padding-bottom: 9px;  margin: 40px 0 20px; color:green;">${TOTAL_OF_WOMEN}</span><br/>
			</c:if>
			
			
			
			
			<label for="date-picker-2" class="control-label">List of All Attendees</label>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>Gender</th>
							<th>Contact Number</th>
							<th>Address</th>
							<th>Birthday</th>
							<th>Group</th>
							<th>Ministry</th>
							
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty attendeesList}">
							<c:forEach items="${attendeesList}" var="attendeesList">
					
								<tr>
									<th><c:out value='${attendeesList.firstName}' /> <c:out value='${attendeesList.lastName}' /></th>
									<th>
									<c:if test="${attendeesList.gender eq true}">
									<c:out value='Male' />
									</c:if>
									<c:if test="${attendeesList.gender eq false}">
									<c:out value='Female' />
									</c:if>
									</th>
									<th><c:out value='${attendeesList.contactNumber}' /></th>
									<th><c:out value='${attendeesList.address}' /></th>
									<th><c:out value='${attendeesList.birthday}' /></th>
									<th><c:out value='${attendeesList.groupName}' /></th>
									<th><c:out value='${attendeesList.ministryName}' /></th>
								</tr>
								</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${empty attendeesList}">
					<center>No Record Found</center>
				</c:if>
			</div>


		</div>
	</div>
</body>
</html>