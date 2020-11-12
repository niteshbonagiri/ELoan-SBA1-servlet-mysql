<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
 <%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calculate EMI</title>
</head>
<body>
 <!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
  -->  
 <%@ include file="header.jsp" %>
	<% String str="";
		if (request.getAttribute("appno") != null) {
		str = request.getAttribute("appno").toString();
		}
	%>
	<div align="right"><a href="admin?action=logout">Logout</a></div>
	<h3>Calculate EMI</h3>
<br/>
		<% 
		LoanInfo info = (LoanInfo)request.getAttribute("LoanInfo");
		%>
		<%
		if (request.getAttribute("message") != null) {
		String message = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=message%></div>
	<%
		}
	%>
	<table>
		<tr>
			<td><label>Loan Name: </label> </td>
			<td><%= info.getPurpose()    %></td>
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
	<form action="admin?action=updatestatus" method="post">

	<table>
		 <tr>
			<td><label>Loan Application Number:</label>  </td>
			<td> <input type="text" name="appno" value=<%= info.getApplno()  %> readonly="readonly"></input></td>
	 	</tr>
		<tr>
			<td><label>Loan Amount Sanctioned: </label> </td>
			<td><input type="text" name="loanAmtSanctioned" required></input> </td>
	   </tr>
	    <tr>
			<td><label>Term of loan (Duration):</label>  </td>
			<td> <input type="text" name="term" required ></input> </td>
	 	</tr>
	 	<tr>
			<td><label> Payment Start date: </label></td>
			<td><input type="text" name="paymentstrtdate" value=<%= LocalDate.now().plusDays(30) %> readonly="readonly"></input> </td>
		</tr>
		<tr>
			<td><input type=Submit name=Submit Value=Approve></td>
			<td><input type=reset name=reset Value=clear></td>
		</tr>
	</table>
	</form>
	<%@ include file="footer.jsp" %>
	 

</body>
</html>