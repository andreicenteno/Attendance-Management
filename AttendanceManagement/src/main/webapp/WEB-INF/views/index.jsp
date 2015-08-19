<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
      <div class="wrapper">
    <form class="form-signin" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">       
      <h4 class="form-signin-heading" style="">Attendance Management</h4>
      <img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/jil_logo_banner.jpg" style="height: 86px; width:293px; ">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
          </c:if>
          <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
      <input type="text" class="form-control" name="j_username" placeholder="Email Address" required="" autofocus="" />
      <input type="password" class="form-control" name="j_password" placeholder="Password" required=""/>      
      <label class="checkbox" style="padding-left: 20px;">
        <input type="checkbox" value="remember-me" id="rememberMe" name="_spring_security_remember_me"> Remember me
      </label>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
      <a href="forgot_password.html" style="color:white;">Forgot Password</a>
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
    </form>
  </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/resources/js/fixed/bootstrap.min.js" type="text/javascript"></script>
  </body>
</html>