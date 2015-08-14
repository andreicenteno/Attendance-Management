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
<title>Sunday Service First Timer</title>
<script src="${pageContext.request.contextPath}/resources/js/customize/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/customize/notification_message.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notification.css">
<script src="${pageContext.request.contextPath}/resources/js/customize/autoComplete.js" type="text/javascript"></script>

</head>
<body>
	<div id="div_success" style="display:none;">${response}</div>
	<div id="div_error" style="display:none;">${response}</div>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Sunday Service First Timer</h1>
			<div class="row">
				<div class="col-md-8">
					<span
						style="font-size: 24px; padding-bottom: 9px; margin: 40px 0 20px;">Title:
					</span> <span
						style="font-size: 28px; font-style: bold; padding-bottom: 9px; margin: 40px 0 20px; color: green;"><c:out
							value='${sundayServiceDetails.serviceTitle}' /></span><br /> <span
						style="font-size: 24px; padding-bottom: 9px; margin: 40px 0 20px;">Date:
					</span> <span
						style="font-size: 24px; padding-bottom: 9px; margin: 40px 0 20px; color: green;"><c:out
							value='${sundayServiceDetails.createTime}' /></span><br /> <span
						style="font-size: 24px; padding-bottom: 9px; margin: 40px 0 20px;">Service:
					</span> <span
						style="font-size: 24px; padding-bottom: 9px; margin: 40px 0 20px; color: green;"><c:out
							value='${sundayServiceDetails.serviceBean.serviceName}' /></span><br />
				</div>
				<br /> <a
					href="sunday_service_profile.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button
						type="submit" class="btn btn-default">View Service
						Profile</button></a> <a
					href="sunday_service_attendees.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button
						type="submit" class="btn btn-default">Service Attendees</button></a> <a
					href="service_first_timer.html?sundayServiceId=${SUNDAY_SERVICE_ID}"><button
						type="submit" class="btn btn-default">Service First Timer</button></a>
				<a href="sunday_service.html"><button type="submit"
						class="btn btn-default">View All Sunday Service</button></a>
				<hr />
			</div>
			<br />
			
			<form:form method="POST" style="align-items: center;" class="form-horizontal" action="insert_first_timer.html"
						modelAttribute="first_timer">
				<form:input path="sundayServiceBean.sundayServiceId" type="hidden" class="form-control" value="${SUNDAY_SERVICE_ID}"></form:input>
				<div class="form-group">
					<label class="col-sm-2 control-label">First Name</label>
					<div class="col-sm-10">
						<form:input path="guestBean.firstName" type="text" class="form-control"
							placeholder="First Name"></form:input>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Middle Name</label>
					<div class="col-sm-10">
						<form:input path="guestBean.middleName" type="text" class="form-control"
							placeholder="Middle Name"></form:input>
						<br />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Last Name</label>
					<div class="col-sm-10">
						<form:input path="guestBean.lastName" type="text" class="form-control"
							placeholder="Last Name"></form:input>
						<br />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<form:input path="guestBean.address" type="text" class="form-control"
							placeholder="Address"></form:input>
						<br />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Contact Number</label>
					<div class="col-sm-10">
						<form:input path="guestBean.contactNumber" type="text" class="form-control"
							placeholder="Contact Number"></form:input>
						<br />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Gender</label>
					<div class="col-sm-10">
						<form:select path="guestBean.gender" id="leave_type" class="form-control">
							<form:option path="guestBean.gender" value="true">Male</form:option>
							<form:option path="guestBean.gender" value="false">Female</form:option>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Birthday</label>
					<div class="col-sm-10">
						<div class="input-group">
							<label for="date-picker-2" class="input-group-addon btn"><span
								class="glyphicon glyphicon-calendar"></span> </label>
							<form:input path="guestBean.birthday" id="date-picker-2" type="text"
								class="date-picker form-control"></form:input>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Group</label>
					<div class="col-sm-10">
						<form:select path="guestBean.groupBean.groupId" id="leave_type"
							class="form-control">
							<c:if test="${!empty groupList}">
								<c:forEach items="${groupList}" var="groupList">
									<form:option path="guestBean.groupBean.groupId"
										value="${groupList.groupId}">
										<c:out value='${groupList.groupName}' />
									</form:option>
								</c:forEach>
							</c:if>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Ministry</label>
					<div class="col-sm-10">
						<form:select path="guestBean.ministryBean.ministryId" id="leave_type"
							class="form-control">
							<c:if test="${!empty ministryList}">
								<c:forEach items="${ministryList}" var="ministryList">
									<form:option path="guestBean.ministryBean.ministryId"
										value="${ministryList.ministryId}">
										<c:out value='${ministryList.ministryName}' />
									</form:option>
								</c:forEach>
							</c:if>
						</form:select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">Remarks</label>
					<div class="col-sm-10">
						<form:select path="remarks" id="remarks_change" class="form-control" onchange="return changeReasonChange();">
							<form:option path="remarks" value="Walk-in">Walk-In</form:option>
							<form:option path="remarks" value="Invited">Invited</form:option>
						</form:select>
						<br/><p id="others_invited" style="display:none;">Invited by:</p>
						<form:select style="display:none;" path="attendeesBean.attendeesId" id="invited_by" class="form-control">
							<form:option path="attendeesBean.attendeesId" value="700">Pearlyn Tan</form:option>
							<form:option path="attendeesBean.attendeesId" value="700">Pearlyn Tan</form:option>
						</form:select>
					</div>
				</div>
				

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Add</button>
						<button class="btn btn-default" type="reset">Cancel</button>
					</div>
				</div>
			</form:form>

		</div>
	</div>

</body>
</html>