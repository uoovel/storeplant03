<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muuda Pakkumine </title>
</head>
<body>
<div align="center">
<h1>Muuda Pakkumine, Personal</h1>
<form:form action="savepakkumine" method="post" modelAttribute="pakkumine">
<table cellpadding="5">
	<form:hidden path="id" />
	
	<tr>
		<td>Nimetus</td>
		<td><form:input path="nimetus" value="${ pakkumine.nimetus }"/>										
		</td></tr>
	<tr>
	
	<tr>
		<td>Hind</td>
		<td><form:input path="hind" value="${ pakkumine.hind }"/>										
		</td></tr>
	<tr>	
	
	<tr>
		<td>Kirjeldus</td>
		<td><form:input path="kirjeldus" value="${ pakkumine.kirjeldus }"/>										
		</td></tr>
	<tr>
	
	<tr>
		<td>Periood</td>
		<td>										
		<form:select path="perioodid">
			<form:option value="${ periood.id }"
			 label="${ periood.perNimetus }"/>								
		</form:select></td></tr>
	<tr>	
	
				
	<tr>
	
	
		<td colspan="2" align="center">
		<input type="submit" value="Save"/></td></tr>

				
	
</table>
</form:form>
</div>
</body>
</html>