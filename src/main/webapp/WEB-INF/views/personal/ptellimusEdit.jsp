<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New/Edit Tellimus </title>
</head>
<body>
<div align="center">
<h1>Muuda Tellimus, Personal</h1>
<form:form action="psavetellimus" method="post" modelAttribute="tellimus">
<table cellpadding="5">
	<form:hidden path="id" />
	<tr>
		<td>Periood</td>
		<td>										
		<form:select path="perioodid">
			<form:option value="${ periood.id }"
			 label="${ periood.perNimetus }"/>								
		</form:select></td></tr>
	<tr>	
	<tr>
		<td>Pakkumine</td>
		<td>										
		<form:select path="pakkumineid">
			<form:option value="${ pakkumine.id }"
			 label="${ pakkumine.nimetus }"/>
			<form:options items="${ listPakkumine }"
			itemValue="id" itemLabel="nimetus"/>					
		</form:select></td></tr>
	<tr>
		<td>Klient</td>
		<td>
		<form:select path="klientid">
			<form:option value="${klient.id}" label="${klient.name}"/>
			<form:options items="${ listKlient }"
			itemValue="id" itemLabel="name"/>					
		</form:select></td>
	</tr>
	<tr>
		<td>Staatus</td>
		<td>
		<form:select path="staatusid">
			<form:option value="${staatus.id}" label="${staatus.nimetus}"/>
			<form:options items="${ listStaatus }"
			itemValue="id" itemLabel="nimetus"/>					
		</form:select></td>
	</tr>
	<tr>
		<td>Arvesti</td>
		<td>
		<form:select path="arvestiid">
		    <form:option value="${arvesti.id}" label="${arvesti.number}"/>
			<form:options items="${ listArvesti }"
			itemValue="id" itemLabel="number"/>							
		</form:select>	
		</td>
	</tr>	
				
	<tr>
	
	
		<td colspan="2" align="center">
		<input type="submit" value="Save"/></td></tr>

				
	
</table>
</form:form>
</div>
</body>
</html>