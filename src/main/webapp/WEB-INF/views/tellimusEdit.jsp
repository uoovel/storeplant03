<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muuda tellimus </title>
<s:url var="url_css" value="/static/css/style.css"/>
<link href="${url_css}" rel="stylesheet" type="text/css"/>
<link href="static/css/style.css"/>
</head>
<body>
<div align="center">
<a class="linkk" href="userWelcomeSec?user=${userName}" style="background-color:lightgreen">|Avalehele</a>
<h1>Muuda tellimus, klient</h1>
<form:form action="tellimusForm2" method="post" modelAttribute="tellimus">
<table cellpadding="5">
<form:hidden path="id" />
	<tr>
		<td>Klient</td>		<td>
		    <form:hidden path="klientid" value="${klient.id}"/>
		    ${klient.name} ${klient.lastName}</td></tr>
	<tr>
		<td>Periood</td>	<td>
		    <form:hidden path="perioodid" value="${periood.id}"/>
			${periood.perNimetus}	</td></tr>
	<tr>
		<td>Staatus</td>	<td>
		    <form:hidden path="staatusid" value="${staatus.id}"/>
			${staatus.nimetus}	</td></tr>	
	<tr>
		<td>Pakkumine</td>	<td>										
			<form:select path="pakkumineid">
				<form:option value="${pakkumine.id}"
				 label="${ pakkumine.nimetus }"/>
				<form:options items="${listPakkumine}"
				itemValue="id" itemLabel="nimetus"/>					
			</form:select></td></tr>
	<tr>
		<td>Arvesti</td>	<td>
			<form:select path="arvestiid">
			    <form:option value="${arvesti.id}" label="${arvesti.number}"/>
				<form:options items="${listArvesti}"
				itemValue="id" itemLabel="number"/>							
			</form:select>		</td>	</tr>
	<tr>	
		<td colspan="2" align="center">
			<input type="submit" value="Kinnita"/></td></tr>	
</table>
</form:form>
</div>
</body>
</html>