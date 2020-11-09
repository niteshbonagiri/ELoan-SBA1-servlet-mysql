<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Status</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->
	 <%@ include file="header.jsp" %>
	<% String str="";
		if (request.getAttribute("appno") != null) {
		str = request.getAttribute("appno").toString();
		}
	%>
	<div align="right"><a href="index.jsp">Logout</a></div>
	<h3>Loan Application Status</h3>
<br/>
		<% 
		LoanInfo info = (LoanInfo)request.getAttribute("LoanInfo");
	%>
	<table>
		<tr>
			<td><label>Loan Name: </label> </td>
			<td><%= info.getPurpose()    %></td>
	   </tr>
	    <tr>
			<td><label>Loan Application Number:</label>  </td>
			<td> <%= info.getApplno()   %></td>
	 	</tr>
	 	<tr>
			<td><label> Loan Amount Requested: </label></td>
			<td> <%= info.getAmtrequest()  %></td>
		</tr>
		<tr>
			<td><label> Loan Application Date: </label></td>
			<td> <%= info.getDoa()   %></td>
		</tr>
		<tr>
			<td><label> Business Structure: </label></td>
			<td>	<%= info.getBstructure()   %>		</td>
		</tr>
		<tr>
			<td><label> Billing Indicator: </label></td>
			<td><%= info.getBindicator()   %>	</td>
		</tr>
		<tr>
			<td><label> Tax payer: </label></td>
			<td><%= info.getTaxindicator()   %>		</td>
		</tr>
		<tr>
			<td><label> Address: </label></td>
			<td><%= info.getAddress()   %></td>
		</tr>
		<tr>
			<td><label> Mobile: </label></td>
			<td><%= info.getMobile()   %> </td>
		</tr>
		<tr>
			<td><label> Email: </label></td>
			<td><%= info.getEmail()  %> </td>
		</tr>
		<tr>
			<td><label> Status: </label></td>
			<td><%= info.getStatus()  %> </td>
		</tr>
	</table>
	
	<%@ include file="footer.jsp" %>
	 
	

</body>
</html>