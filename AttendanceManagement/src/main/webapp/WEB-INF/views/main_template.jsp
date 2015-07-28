<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE> -->


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
</title>
	<!-- --Include transition of tabs, main layout design -- -->

</head>

<body>
<!--  This is loading image -->
<%-- <div id='mask' style="display:none;"></div>
	<div id='popup' style="display:none;">
	   <img id="loading" src="${pageContext.request.contextPath}/resources/images/loading3.gif" />
</div> --%>
	
<table cellpadding="2" cellspacing="2" width="100%" height="100%" style="border-spacing: 0;">
    <tr>
        <td colspan="2" align="left" id="header_section">
       		 <div id="header_style"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
        </td>
        
    </tr>
    <tr>
        <td width="100%" valign="top">
        	<div id="mainBorder"><tiles:insertAttribute name="body"></tiles:insertAttribute></div>
        </td>
    </tr>
    <tr>
        <td colspan="2"  align="center">
        	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
        </td>
    </tr>
</table>

</body>
</html>