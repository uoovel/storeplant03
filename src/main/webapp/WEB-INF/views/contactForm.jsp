<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
<div align="center">
	<h1>New/Edit Contact</h1>
	<form:form action="save" method="post" modelAttribute="contact">
	<table cellpadding="5">
		<form:hidden path="id" />
		<tr>
			<td>Name:</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="Save"/></td>			
		</tr>
	</table>
	</form:form>
</div>
</body>
</html>