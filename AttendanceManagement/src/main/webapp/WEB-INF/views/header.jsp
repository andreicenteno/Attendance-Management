<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Custom built online life group management for JIL or Jesus Is Lord">
    <meta name="author" content="Andrei Centeno">
    <link rel="icon" href="images/favicon.ico">
    
    <title>Attendance Management</title>
    
      <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/js/fixed/jquery-ui-1.11.4/jquery-ui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/js/fixed/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/js/fixed/jquery-ui-1.11.4/jquery-ui.theme.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/js/fixed/jquery-ui-1.11.4/jquery-ui.structure.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/dashboard.css" rel="stylesheet">
    
    
</head>    
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"> </span>
            <span class="icon-bar"> </span>
            <span class="icon-bar"> </span>
          </button>
          <a class="navbar-brand" href="#"><img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/jiltemporarylogo.png" style="height: 43px; width:177px; "></a>
        </div>
		
		<div id="navbar" class="navbar-collapse collapse">
           <ul class="full_navbar nav navbar-nav navbar-right">
		   	<li><a href="dashboard.html">Dashboard</a></li>
		   	<li><a href="sunday_service.html">Sunday Service</a></li>
            <li><a href="attendees.html">Attendees</a></li>
            <li><a href="reports.html">Report</a></li>
            <li><a href="maintenance.html">Maintenance</a></li>
		   	<li><a href="#" style="color:white;"> | </a></li>
           	<li><a href="<c:url value="/j_spring_security_logout" />" style="color:white;">Logout</a></li>
           </ul>
         <ul class="mobile_navbar nav navbar-nav navbar-right">
         	<li class="active"><a href="dashboard.html">Dashboard<span class="sr-only">(current)</span></a></li>
         	<li><a href="sunday_service.html">Sunday Service</a></li>
            <li><a href="attendees.html">Attendees</a></li>
            <li><a href="reports.html">Report</a></li>
            <li><a href="maintenance.html">Maintenance</a></li>
            <li><a href="<c:url value="/j_spring_security_logout" />" style="color:white;">Logout</a></li>
          </ul>
         
          
          </div>
          
    
       
       </div>
        
    </nav>



 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/fixed/jquery-ui-1.11.4/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/fixed/bootstrap.min.js"></script>
    
    <!-- Custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/fixed/scripts.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/resources/js/fixed/ie10-viewport-bug-workaround.js"></script>

</body>
</html>


