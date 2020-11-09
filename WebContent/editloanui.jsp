<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
 <%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h3>Edit Loan Application</h3>
<br/>
		<%
		if (request.getAttribute("message") != null) {
		String message = request.getAttribute("message").toString();
	%>
	 <div style="Color:red"><%=message%></div>
	<%
		}
	%>
	<% 
		LoanInfo info = (LoanInfo)request.getAttribute("LoanInfo");
	%>
	<form action="user?action=editLoanProcess" method="post">
	<table>
		<tr>
			<td><label>Loan Name: </label> </td>
			<td><input type="text" name="loanname" value=<%=info.getPurpose()%> required></input> </td>
	   </tr>
	    <tr>
			<td><label>Loan Application Number:</label>  </td>
			<td> <input type="text" name="loanappnumber" readonly="readonly" value=<%=info.getApplno()%> ></input> </td>
	 	</tr>
	 	<tr>
			<td><label> Loan Amount Requested: </label></td>
			<td><input type="text" name="loanamtrequested" value=<%=info.getAmtrequest()%> required></input> </td>
		</tr>
		<tr>
			<td><label> Loan Application Date: </label></td>
			<td><input type="text" name="loanapplicationdate" value=<%= LocalDate.now() %> readonly="readonly"></input> </td>
		</tr>
		<tr>
			<td><label> Business Structure: </label></td>
			<td><select name="businessstructure" id="bs">
				  <option value="Individual">Individual</option>
				  <option value="Organisation">Organisation</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td><label> Billing Indicator: </label></td>
			<td><select name="billingindicator" id="bi">
				  <option value="Salaried">Salaried</option>
				  <option value="Non-Salaried">Non-Salaried</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td><label> Tax payer: </label></td>
			<td><select name="taxpayer" id="tp">
				  <option value="Taxpayer">Taxpayer</option>
				  <option value="Non-Taxpayer">Non-Taxpayer</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td><label> Address: </label></td>
			<td><textarea id="address" name="address" row="8" cols="16" required><%= info.getAddress() %></textarea> </td>
		</tr>
		<tr>
			<td><label> Mobile: </label></td>
			<td><input type="text" name="mobile" value=<%= info.getMobile()%> required></input> </td>
		</tr>
		<tr>
			<td><label> Email: </label></td>
			<td><input type="text" name="email" value=<%= info.getEmail()%> required></input> </td>
		</tr>
		<tr>
			<td><input type=Submit name=Submit Value=Submit></td>
			<td><input type=reset name=reset Value=clear></td>
		</tr>
	</table>
	</form>
<jsp:include page="footer.jsp"/>
</body>
</html>