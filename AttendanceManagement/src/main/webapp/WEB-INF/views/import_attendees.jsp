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
<script src="${pageContext.request.contextPath}/resources/js/customize/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/customize/notification_message.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notification.css">
<title>Attendees</title>


</head>
<body>
	
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Import Attendees</h1>
			<div class="row">	
				 <b>Note:</b><br/>
				 <p class="note">Importing of attendees using .csv file are done here. <br/>
				Please check the format of header file: first_name, last_name, middle_name, address, contact_number, birthday, gender, group_id.<br/>
				Required Fields are: first_name, last_name, address, contact_number, birthday, gender and group_id <br/>
				Birthday field format accept only: 8/1/2015 AND 2015-08-01   - (MM/DD/YYYY AND YYYY-MM-DD) <br/>
				Gender Field format accept only: TRUE - for MALE | FALSE  - for FEMALE<br/>
				Group Field format: 1 - KKB/CYN | 2 - YAM | 3 - CHILDREN | 4 - MEN | 5 - WOMEN</p>
				
				<br/>
				<br/>
				<center>
				<form:form method="POST" action="validate_import_file.html" modelAttribute="attendees" enctype="multipart/form-data">
				<table width="50%">
					<tr>
						<td>UPLOAD CSV FILE:</td>
						<td><form:input path="attendeesFile" name="file" type="file"></form:input></td>
					</tr>
					
					<tr>
						<td></td>
						<td><br/><button type="submit" class="btn btn-default">VALIDATE</button></td>
					</tr>
				</table>
				</form:form>
				</center>
				<span><b>LOGS:</b> Checking of records according to the .csv file format </span><br/>
				
				<div id="div_success_import" style="display:none;">${response}<br/>
					<b>SUMMARY: </b><br/>
					${LOGS_SUMMARY}
					<center>
						<form:form method="POST" action="import_attendees_record.html" modelAttribute="attendees">
								<form:input path="keywords" type="hidden" value='${PATH_FILE}'></form:input>
								<br/><button type="submit" class="btn btn-default">IMPORT</button>
						</form:form>
					</center>
				</div>
				
				<div id="div_success_fixed" style="display:none;">${response}<br/>
				<b>SUMMARY: </b><br/>
					${LOGS_SUMMARY}
				</div>
				
				
				<div id="div_error_fixed" style="display:none;">${response} <br/>
				<b>ERROR: </b><br/>
				${LOGS_ERROR}</div><br/>
				
					<div class="scroll">
					${LOGS_INFORMATION}
					<span><b>ERRORS:</b></span><br/>
					<span style="color:red; font-style:bold;">${LOGS_ERROR}</span>
					<span><b>SUMMARY:</b></span><br/>
					<span style="color:green; font-style:bold;">${LOGS_SUMMARY}</span>
					
					<center>
					<span style="color:green; font-style:bold;">${LOGS_SUCCESS}</span>
					<span style="color:red; font-style:bold;">${LOGS_FAILED}</span>
					</center>
					</div>
				

			</div>
		</div>
	</div>

</body>
</html>