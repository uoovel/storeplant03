<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perioodi valik</title>
<s:url var="url_css" value="/static/css/style.css"/>
<link href="${url_css}" rel="stylesheet" type="text/css"/>
<link href="static/css/style.css"/>
</head>
<body>
	<div align="center">
	<a class="linkk" href="userWelcomeSec?user=${userName}" 
	   style="background-color:lightgreen">|Avalehele</a>
		<h1>Perioodi valik tellimusele</h1>
		<form:form action="perioodtellimuseleForm" method="post" 
		modelAttribute="tellimus">
		<table cellpadding="5">
		    <tr><td>
			<!--<form:hidden path="klientid" />-->
			<form:hidden path="userName" />
			<form:select path="perioodid">
				<form:option value="0"
				 label="--Vali periood"/>
				<form:options items="${ filtListPeriood }"
				itemValue="id" itemLabel="perNimetus"/>					
			</form:select></td>
			<td style="color:red"><form:errors path="perioodid" /></td>
			</tr>			
			<tr>
				<td colspan="2" align="center"><input
				 type="submit" value="Kinnita valik"/></td>			
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>