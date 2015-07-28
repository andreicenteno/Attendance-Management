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

			<h1 class="page-header">Add Ministry</h1>
			<div class="row">


				<div class="col-md-8">
						<form:form method="POST" class="form-horizontal" action="insert_ministry.html" modelAttribute="ministry">
							<div class="form-group">
								<label class="col-sm-2 control-label">Ministry Name</label>
								<div class="col-sm-10">
									<form:input path="ministryName" type="text" class="form-control" placeholder="Ministry Name"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">Description</label>
								<div class="col-sm-10">
									<form:input path="description" type="text" class="form-control"  placeholder="Description"></form:input><br/>
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


		</div>
	</div>

</body>
</html>