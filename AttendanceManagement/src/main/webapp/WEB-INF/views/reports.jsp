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
<title>Reports</title>

</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Reports</h1>

			<h3>ATTENDEES REPORT</h3>

			<div class="row">
				<div class="date-form">
					<form:form method="POST" class="form-horizontal" action="genreate_attendance.html" modelAttribute="attendees_report">
						<div class="form-horizontal">
							<div class="col-md-4">
								<label for="leave_type" class="control-label">Group</label>
								<form:select path="groupId" id="groupId" class="form-control"
									onchange="ddListGroup(this)">
									<c:if test="${!empty groupList}">
										<form:option path="groupId" value="0">
											<c:out value='All' />
										</form:option>
										<c:forEach items="${groupList}" var="groupList">
											<form:option path="groupId" value="${groupList.groupId}">
												<c:out value='${groupList.groupName}' />
											</form:option>
										</c:forEach>
									</c:if>
									<c:if test="${empty groupList}">
										<form:option path="groupId" value="-1">
											<c:out value='No Group Record' />
										</form:option>
									</c:if>
								</form:select>
								<br />
							</div>

							<div class="col-md-4">
								<label for="leave_type" class="control-label">Ministry</label>
								<form:select path="ministryId" id="ministryId"
									class="form-control"
									onchange="ddListMinistry(this)">
									<c:if test="${!empty ministryList}">
										<form:option path="ministryId" value="0">
											<c:out value='All' />
										</form:option>
										<c:forEach items="${ministryList}" var="ministryList">
											<form:option path="ministryId"
												value="${ministryList.ministryId}">
												<c:out value='${ministryList.ministryName}' />
											</form:option>
										</c:forEach>
									</c:if>
									<c:if test="${empty ministryList}">
										<form:option path="ministryId" value="0">
											<c:out value='No Ministry Record' />
										</form:option>
									</c:if>
								</form:select>
								<br />
							</div>

							<div class="col-md-4">
								<label for="leave_type" class="control-label">Gender</label>
								<form:select path="gender" id="gender" class="form-control"
								onchange="ddListGender(this)">
									<form:option path="gender" value="all">
										<c:out value='All' />
									</form:option>
									<form:option path="gender" value="true">
										<c:out value='Male' />
									</form:option>
									<form:option path="gender" value="false">
										<c:out value='Female' />

									</form:option>
								</form:select>
								<br />
							</div>
							<br /> <br />
						</div>
					</form:form>

					<div class="col-md-12">
						<center>
						<table>
							<tr>
								<td>
									<form:form method="POST" class="form-horizontal" action="generate_attendees.html" modelAttribute="attendees_report">
										<button class="btn btn-default" type="submit">GENERATE .PDF FILE</button>
										<form:input path="groupId" type="hidden" value="0" id="groupIdPdfFile"></form:input>
										<form:input path="ministryId" type="hidden" value="0" id="ministryIdPdfFile"></form:input>
										<form:input path="gender" type="hidden" value="all" id="genderPdfFile"></form:input>
									</form:form>
								</td>
								<td>
									<form:form method="GET" class="form-horizontal" action="view_attendees_report.html" modelAttribute="attendees_report">
									<button class="btn btn-default" type="submit">VIEW REPORT</button>
									<form:input path="groupId" type="hidden" value="0" id="groupIdView"></form:input>
									<form:input path="ministryId" type="hidden" value="0" id="ministryIdView"></form:input>
									<form:input path="gender" type="hidden" value="all" id="genderView"></form:input>
									</form:form>
								</td>
							</tr>
						</table>
							
						<%-- 	<form:form method="POST" class="form-horizontal" action="genreate_attendance.html" modelAttribute="attendees_report">
							<button class="btn btn-default" type="submit">GENERATE .CSV FILE</button>
							<input type="hidden" value="0" id="groupIdCsvFile">
							<input type="hidden" value="0" id="ministryIdCsvFile">
							<input type="hidden" value="all" id="genderCsvFile">
							</form:form> --%>
							
							
						</center>
					</div>
				 
				</div>

			</div>
		</div>

		<br /> <br /> <br />
		<br /> <br /> <br />
		<hr />
		<h3>ATTENDANCE REPORT</h3>
		<center><h1>ATTENDANCE REPORT IS COMING SOON</h1>
		<h3>You can generate report for sunday services by date. (MONTHLY, YEARLY, QUARTERLY)</h3>
		<%-- <div class="row">
			<div class="date-form">
				<div class="form-horizontal">
					<div class="col-md-4">
						<label for="leave_type" class="control-label">Group</label> <select
							id="leave_type" class="form-control">
							<option>Select leave here</option>
							<option>Casual leave</option>
							<option>Priviledge leave</option>
							<option>Medical leave</option>
							<option>Maternity leave</option>
							<option>Quarantine leave</option>
						</select> <br />
					</div>

					<div class="col-md-4">
						<label for="leave_type" class="control-label">Ministry</label> <select
							id="leave_type" class="form-control">
							<option>Select leave here</option>
							<option>Casual leave</option>
							<option>Priviledge leave</option>
							<option>Medical leave</option>
							<option>Maternity leave</option>
							<option>Quarantine leave</option>
						</select> <br />
					</div>

					<div class="col-md-4">
						<label for="leave_type" class="control-label">Gender</label> <select
							id="leave_type" class="form-control">
							<option>Select leave here</option>
							<option>Casual leave</option>
							<option>Priviledge leave</option>
							<option>Medical leave</option>
							<option>Maternity leave</option>
							<option>Quarantine leave</option>
						</select> <br />
					</div>

					<div class="col-md-12">
						<center>
							<button class="btn btn-default" type="submit">GENERATE
								.PDF FILE</button>
							<button class="btn btn-default" type="reset">GENERATE
								.CSV FILE</button>
							<button class="btn btn-default" type="reset">VIEW REPORT</button>
						</center>
					</div>

				</div>
			</div>
		</div> --%>


	</div>
	</div>
	</div>
</body>
</html>