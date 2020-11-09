<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
	<%@ include file="header.jsp" %>
	<div align="right"><a href="index.jsp">Logout</a></div>
	<h3>Edit Loan</h3>
	<br/>
	<%
		if (request.getAttribute("message") != null) {
		String str = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=str%></div>
	<%
		}
	%>
	<form action="user?action=editloan" method="post">
	<table>
		<tr>
			<td><label>Enter Application Number: </label> </td>
			<td><input type="text" name="appno" required></input> </td>
	   </tr>
	   <tr>
			<td><input type=Submit name=Submit Value="Edit Loan"></td>
		</tr>
	</table>
	</form>
	<%@ include file="footer.jsp" %>
	
	
</body>
</html>