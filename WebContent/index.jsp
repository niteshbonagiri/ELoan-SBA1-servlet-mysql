<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	<%@ include file="header.jsp" %>
	<h3>User Login Form</h3>
<br/>
	<%
		if (request.getAttribute("message") != null) {
		String str = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=str%></div>
	<%
		}
	%>
<form action="user?action=validate" method="post">
<table>
		<tr>
			<td><label>Username</label></td>
			<td><input type="text" name="username" required/></td>
		</tr>
		<tr>
			<td><label>Password</label></td>
			<td><input type="password" name="password" required/></td>
	    </tr>
	    <tr>
	    	<td><input type=Submit name=Submit Value=Submit></td>
	    	<td><input type=reset name=reset Value=clear></td>
	    </tr>
</table>
</form>
<table>
	<tr>
		<td>New user? Click here </td> 
		<td><a href="user?action=register">New User? register here</a></td>
	</tr>
</table>
<%@ include file="footer.jsp" %>
</body>
</html>