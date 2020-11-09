<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Process Loan</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
<jsp:include page="header.jsp"/>
<div align="right"><a href="adminhome1.jsp">Home</a></div>
<div align="right"><a href="admin?action=logout">Logout</a></div>
<h3>Process Loan Application</h3>
<br/>
	<%
		if (request.getAttribute("message") != null) {
		String str = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=str%></div>
	<%
		}
	%>
	<form action="admin?action=callemi" method="post">
	<table>
		<tr>
			<td><label>Enter Application Number: </label> </td>
			<td><input type="text" name="appno" required></input> </td>
	   </tr>
	   <tr>
			<td><input type=Submit name=Submit Value="Search"></td>
		</tr>
	</table>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>