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

<title>Maintenance</title>


</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Update Attendees</h1>
			<div class="row">
				<div class="col-md-8">
					<form:form method="POST" class="form-horizontal" action="save_attendees.html" modelAttribute="attendees">
								<form:input path="attendeesId" type="hidden" class="form-control" value="${updateAttendees.attendeesId}" ></form:input>
								<div class="form-group">
							<label class="col-sm-2 control-label">First Name</label>
							<div class="col-sm-10">
								<form:input path="firstName" type="text" class="form-control" value="${updateAttendees.firstName}" 
									placeholder="First Name"></form:input>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Middle Name</label>
							<div class="col-sm-10">
								<form:input path="middleName" type="text" class="form-control" value="${updateAttendees.middleName}"
									placeholder="Middle Name"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Last Name</label>
							<div class="col-sm-10">
								<form:input path="lastName" type="text" class="form-control" value="${updateAttendees.lastName}"
									placeholder="Last Name"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Address</label>
							<div class="col-sm-10">
								<form:input path="address" type="text" class="form-control" value="${updateAttendees.address}"
									placeholder="Address"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Contact Number</label>
							<div class="col-sm-10">
								<form:input path="contactNumber" type="text" value="${updateAttendees.contactNumber}"
									class="form-control" placeholder="Contact Number"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<form:select path="gender" id="gender" class="form-control">
								    <c:if test="${updateAttendees.gender eq true}">
								    <form:option path="gender" value="true" selected="selected">Male</form:option>
									<form:option path = "gender" value = "false">Female</form:option>
								    </c:if>
								    <c:if test="${updateAttendees.gender eq false}">
								    <form:option path="gender" value="true">Male</form:option>
									<form:option path = "gender" value = "false" selected="selected">Female</form:option>
								    </c:if>
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Group</label>
							<div class="col-sm-10">
								<form:select path="groupBean.groupId" id="groupBean.groupId" class="form-control">
									<c:forEach items="${groupList}" var="groupList">
										<c:if test="${!empty groupList}">
											<c:choose>
											<c:when test="${groupList.groupId eq updateAttendees.groupBean.groupId}">
												<form:option value="${groupList.groupId}" path="groupBean.groupId" selected="true">
													<c:out value='${groupList.groupName}' />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${groupList.groupId}" path="groupBean.groupId">
													<c:out value='${groupList.groupName}' />
												</form:option>
											</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${empty groupList}">
										<form:option path="groupBean.groupId" value="0">
											<c:out value='No Services! Please add first services' />
										</form:option>
										</c:if>
									</c:forEach>
								</form:select>
							</div>
						</div>
						

						<div class="form-group">
							<label class="col-sm-2 control-label">Birthday</label>
							<div class="col-sm-10">
								<div class="input-group">
									<label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span> </label>
									<form:input path="birthday" id="date-picker-2" type="text" value="${updateAttendees.birthday}"
										class="date-picker form-control"></form:input>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">First Timer?</label>
							<div class="col-sm-10">
							<c:if test="${updateAttendees.isFirstTimer eq true}">
							<form:checkbox path="isFirstTimer" id="isFirstTimer" checked="checked" type="checkbox" value="false" onchange="changeValueFirstTimer(this)" class="checkbox form-control"></form:checkbox>
							</c:if>
							<c:if test="${updateAttendees.isFirstTimer eq false}">
							<form:checkbox path="isFirstTimer" id="isFirstTimer" type="checkbox" value="false" onchange="changeValueFirstTimer(this)" class="checkbox form-control"></form:checkbox>
							</c:if>
								
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Is Member?</label>
							<div class="col-sm-10">
							<c:if test="${updateAttendees.isMember eq true}">
								<form:checkbox path="isMember" id="isMember" checked="checked" type="checkbox"  onchange="changeValueMember(this)" value="false" 
								class="checkbox form-control"></form:checkbox>
							</c:if>
							<c:if test="${updateAttendees.isMember eq false}">
							<form:checkbox path="isMember" id="isMember" type="checkbox"  onchange="changeValueMember(this)" value="false" 
								class="checkbox form-control"></form:checkbox>
							</c:if>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Update</button>
								<button class="btn btn-default" type="reset">Cancel</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>

		</div>
	</div>

</body>
</html>