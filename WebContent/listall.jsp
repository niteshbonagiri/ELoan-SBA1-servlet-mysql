<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	-->
<jsp:include page="header.jsp"/>
<div align="right"><a href="adminhome1.jsp">Home</a></div>
<div align="right"><a href="admin?action=logout">Logout</a></div>
<h3>All Loan Applications</h3>
<br/>
	<% 
		ArrayList<LoanInfo> list = (ArrayList<LoanInfo>)request.getAttribute("LoanInfo");
	%>
	<table border="1" cellspacing="5px" cellpadding="5px">
		<tr>
			<td><label>Loan Application Number</label>  </td>
			<td><label>Loan Name </label> </td>
			<td><label> Loan Amount Requested </label></td>
			<td><label> Loan Application Date </label></td>
			<td><label> Business Structure </label></td>
			<td><label> Billing Indicator </label></td>
			<td><label> Tax Indicator </label></td>
			<td><label> Address </label></td>
			<td><label> Mobile </label></td>
			<td><label> Email </label></td>
			<td><label> Status </label></td>
		</tr>
	</table>
	<table border="1" cellspacing="5px" cellpadding="5px">
	<%if(list.isEmpty()){%>
		<h3 align="center">No loan applications</h3>
	<%} else {
	 for (LoanInfo info:list){%>
		<tr>
			<td><%=info.getApplno()%></td>
			<td><%=info.getPurpose()%></td>
			<td><%=info.getAmtrequest()%></td>
			<td><%=info.getDoa() %> </td>
			<td><%=info.getBstructure() %> </td>
			<td><%=info.getBindicator() %> </td>
			<td><%=info.getTaxindicator() %> </td>
			<td><%=info.getAddress() %> </td>
			<td><%=info.getMobile() %> </td>
			<td><%=info.getEmail() %> </td>
			<td><%=info.getStatus() %> </td>
		</tr>	
	<%}} %>
	</table>
<jsp:include page="footer.jsp"/>
</body>
</html>