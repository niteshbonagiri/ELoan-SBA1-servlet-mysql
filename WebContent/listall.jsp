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
	<table border="1" width=600 cellspacing="5px" cellpadding="5px">
		<tr>
			<th><label>Loan Application Number</label>  </th>
			<th><label>Loan Name </label> </th>
			<th><label> Loan Amount Requested </label></th>
			<th><label> Loan Application Date </label></th>
			<th><label> Business Structure </label></th>
			<th><label> Billing Indicator </label></th>
			<th><label> Tax Indicator </label></th>
			<th><label> Address </label></th>
			<th><label> Mobile </label></th>
			<th><label> Email </label></th>
			<th><label> Status </label></th>
		</tr>
	<%if(list.isEmpty()){%>
		<tr>
		<td colspan="11"><h3 align="center">No loan applications</h3></td>
		</tr>
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