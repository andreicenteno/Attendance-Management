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

<title>Sunday Service</title>


</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h1 class="page-header">Update Sunday Service</h1>
			<div class="row">

				<div class="col-md-8">
					<form:form method="POST" class="form-horizontal"
						action="save_sunday_service.html" modelAttribute="sunday_services">
						<form:input path="sundayServiceId" type="hidden" class="form-control" value="${updateSundayService.sundayServiceId}" ></form:input>
						<div class="form-group">
							<label class="col-sm-2 control-label">Title:</label>
							<div class="col-sm-10">
								<form:input path="serviceTitle" type="text" class="form-control" value="${updateSundayService.serviceTitle}" placeholder="Ministry Name"></form:input>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Description</label>
							<div class="col-sm-10">
								<form:input path="description" type="text" class="form-control"
									value="${updateSundayService.description}"
									placeholder="Description"></form:input>
								<br />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Service</label>
							<div class="col-sm-10">
								<form:select path="serviceBean.serviceId" id="leave_type" class="form-control">
								<c:forEach items="${servicesList}" var="servicesList">
									<c:if test="${!empty servicesList}">
										<c:choose>
											<c:when test="${servicesList.serviceId eq updateSundayService.serviceBean.serviceId}">
												<form:option value="${servicesList.serviceId}" path="${serviceBean.serviceId}" selected="true">
													<c:out value='${servicesList.serviceName}' />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${servicesList.serviceId}" path="serviceBean.serviceId">
													<c:out value='${servicesList.serviceName}' />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${empty servicesList}">
										<form:option path="sundayServiceId" value="0">
											<c:out value='No Services! Please add first services' />
										</form:option>
									</c:if>
									</c:forEach>
								</form:select>
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