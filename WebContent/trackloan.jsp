<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track loan</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	<%@ include file="header.jsp" %>
	<div align="right"><a href="index.jsp">Logout</a></div>
	<h3>Track Loan</h3>
	<br/>
	<%
		if (request.getAttribute("message") != null) {
		String str = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=str%></div>
	<%
		}
	%>
	<form action="user?action=trackloan" method="post">
	<table>
		<tr>
			<td><label>Enter Application Number: </label> </td>
			<td><input type="text" name="appno" required></input> </td>
	   </tr>
	   <tr>
			<td><input type=Submit name=Submit Value="Check Status"></td>
		</tr>
	</table>
	</form>
	<%@ include file="footer.jsp" %>

</body>
</html>