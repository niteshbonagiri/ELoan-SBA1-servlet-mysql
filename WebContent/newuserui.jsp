<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->
	<%@ include file="header.jsp" %>
	<h3>User Registration Form</h3>
<br/>
	<%
		if (request.getAttribute("message") != null) {
		String str = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=str%></div> <a href="index.jsp">click to go to home page </a>
	<%
		}
	%>
	
	<form action="user?action=registeruser" method="post">
	<table>
		<tr>
			<td><label>First Name: </label> </td>
			<td><input type="text" name="firstname" required></input> </td>
	   </tr>
	    <tr>
			<td><label>Last Name:</label>  </td>
			<td> <input type="text" name="lastname" required></input> </td>
	 	</tr>
	 	<tr>
			<td><label> Username: </label></td>
			<td><input type="text" name="username" required></input> </td>
		</tr>
		<tr>
			<td><label> Password: </label></td>
			<td><input type="password" name="password" required></input> </td>
		</tr>
		<tr>
			<td><input type=Submit name=Submit Value=Submit></td>
			<td><input type=reset name=reset Value=clear></td>
		</tr>
	</table>
	</form>
<%@ include file="footer.jsp" %>
</body>
</html>