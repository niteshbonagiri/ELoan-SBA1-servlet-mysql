<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
	<!-- write the html code to display hyperlinks for user functionalities -->
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>User Dash Board</h4>
<a href="user?action=application">Apply for Loan</a><br>
<a href="trackloan.jsp">Track Loan Application</a><br>
<a href="editloan.jsp">Edit Loan Application</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>