<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perioodi valik</title>
</head>
<body>
	<div align="center">
		<h1>Perioodi valik Võimsuste plaanidele</h1>
		<form:form action="valiperioodvoimsusele" method="get" modelAttribute="jaamaVoimsus">
		<table cellpadding="5">
			

			
			<form:select path="perioodid">
				<form:option value="-"
				 label="--Vali periood"/>
				<form:options items="${ listPeriood }"
				itemValue="id" itemLabel="perNimetus"/>					
			</form:select></td></tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Kinnita valik"/></td>
			
			</tr>
		</table>
		</form:form>
	</div>

</body>
</html>