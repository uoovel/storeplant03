<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tellimuse esitamine</title>
<s:url var="url_css" value="/static/css/style.css"/>
<link href="${url_css}" rel="stylesheet" type="text/css"/>
<link href="static/css/style.css"/>
</head>
<body>
	<div align="center">
	<a class="linkk" href="userWelcomeSec?user=${userName}" 
	   style="background-color:lightgreen">|Front page</a>
		<h1>Ordering form</h1>
		<form:form action="tellimusForm2" method="post" 
		modelAttribute="tellimus">
		<table cellpadding="5">
		 	<tr><td>Client</td>
		 	<td>
			<!--<form:hidden path="klientid" />-->
			<form:hidden path="userName" />
			<form:hidden path="klientid" value="${klient.id}"/>
				    ${klient.name} ${klient.lastName}	
			</td>
			</tr>
		    <tr><td>Period</td>
		    <td>
			<!--<form:hidden path="klientid" />-->
			<form:hidden path="userName" />
			<form:hidden path="perioodid" value="${ periood.id }"/>
					${ periood.perNimetus }	
			</td>
			</tr>
			
		    <tr><td>Offer</td>
		    <td>
			<!--<form:hidden path="klientid" />-->
			<form:hidden path="userName" />
			<form:select path="offerid">
						<form:option value="0" label="--choose"/>
						<form:options items="${ listOffer }"
						itemValue="id" itemLabel="nimetus"/>					
			</form:select>
			</td>
			<td style="color:red"><form:errors path="offerid" /></td>
			</tr>
			<tr><td>Meter</td>
			<td>
			<!--<form:hidden path="klientid" />-->
			<form:hidden path="userName" />
			<form:select path="meterid">
					    <form:option value="0" label="--choose"/>
						<form:options items="${ listMeter }"
						itemValue="id" itemLabel="number"/>										
			</form:select>	
			</td>
			<td style="color:red"><form:errors path="meterid" /></td>
			</tr>
			
			
						
			<tr>
				<td colspan="2" align="center"><input
				 type="submit" value="Send Order"/></td>			
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>