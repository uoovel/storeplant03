<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajaloo valik</title>
</head>
<body>
	<div align="center">
		<h1>Ajaketke valik ajaloo päringuks</h1>
		<form:form action="valiajaloodetailid" method="get" modelAttribute="ajaparingView">
		<table cellpadding="5">	
			<form:input path="aeg"/>			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Kinnita valik"/></td>			
			</tr>
		</table>
		</form:form>
	</div>

</body>
</html>