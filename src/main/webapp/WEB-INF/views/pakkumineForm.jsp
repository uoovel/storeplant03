<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New/Edit Pakkumine</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Pakkumine</h1>
		<form:form action="savepakkumine" method="post" modelAttribute="pakkumine">
		<table cellpadding="5">
			<form:hidden path="id" />
			<tr>
				<td>Nimetus:</td>
				<td><form:input path="nimetus" /></td>
			</tr>
			<tr>
				<td>Hind:</td>
				<td><form:input path="hind" /></td>
			</tr>
			<tr>
				<td>Kirjeldus:</td>
				<td><form:input path="kirjeldus" /></td>
			</tr>
			<tr>
				<td>Periood:</td>
			<td>
			<form:select path="perioodid">
				<form:option value="-"
				 label="--Vali periood"/>
				<form:options items="${ listPeriood }"
				itemValue="id" itemLabel="perNimetus"/>					
			</form:select></td></tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"/></td>
			
			</tr>
		</table>
		</form:form>
	</div>

</body>
</html>