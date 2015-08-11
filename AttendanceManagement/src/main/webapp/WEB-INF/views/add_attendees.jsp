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
<script
	src="${pageContext.request.contextPath}/resources/js/customize/common.js"
	type="text/javascript"></script>
<title>Attendees</title>


</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Add Attendees</h1>
			<div class="row">


				<div class="col-md-8">
					<form:form method="POST" style="align-items: center;"
						class="form-horizontal" action="insert_attendees.html"
						modelAttribute="attendees">
						<div class="form-group">
							<label class="col-sm-2 control-label">First Name</label>
							<div class="col-sm-10">
								<form:input path="firstName" type="text" class="form-control"
									placeholder="First Name"></form:input>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Middle Name</label>
							<div class="col-sm-10">
								<form:input path="middleName" type="text" class="form-control"
									placeholder="Middle Name"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Last Name</label>
							<div class="col-sm-10">
								<form:input path="lastName" type="text" class="form-control"
									placeholder="Last Name"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Address</label>
							<div class="col-sm-10">
								<form:input path="address" type="text" class="form-control"
									placeholder="Address"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Contact Number</label>
							<div class="col-sm-10">
								<form:input path="contactNumber" type="text"
									class="form-control" placeholder="Contact Number"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<form:select path="gender" id="leave_type" class="form-control">
									<form:option path="gender" value="true">Male</form:option>
									<form:option path="gender" value="false">Female</form:option>
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Birthday</label>
							<div class="col-sm-10">
								<div class="input-group">
									<label for="date-picker-2" class="input-group-addon btn"><span
										class="glyphicon glyphicon-calendar"></span> </label>
									<form:input path="birthday" id="date-picker-2" type="text"
										class="date-picker form-control"></form:input>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Group</label>
							<div class="col-sm-10">
								<form:select path="groupBean.groupId" id="leave_type" class="form-control">
									<c:if test="${!empty groupList}">
										<c:forEach items="${groupList}" var="groupList">
											<form:option path="groupBean.groupId" value="${groupList.groupId}"><c:out value='${groupList.groupName}' /></form:option>
										</c:forEach>
									</c:if>
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Ministry</label>
							<div class="col-sm-10">
								<form:select path="ministryBean.ministryId" id="leave_type" class="form-control">
									<c:if test="${!empty ministryList}">
										<c:forEach items="${ministryList}" var="ministryList">
											<form:option path="ministryBean.ministryId" value="${ministryList.ministryId}"><c:out value='${ministryList.ministryName}' /></form:option>
										</c:forEach>
									</c:if>
								</form:select>
							</div>
						</div>

						<%-- <div class="form-group">
							<label class="col-sm-2 control-label">First Timer?</label>
							<div class="col-sm-10">
								<form:checkbox path="isFirstTimer" id="isFirstTimer"
									type="checkbox" value="false"
									onchange="changeValueFirstTimer(this)"
									class="checkbox form-control"></form:checkbox>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Is Member?</label>
							<div class="col-sm-10">
								<form:checkbox path="isMember" id="isMember" type="checkbox"
									onchange="changeValueMember(this)" value="false"
									class="checkbox form-control"></form:checkbox>
							</div>
						</div> --%>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Add</button>
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