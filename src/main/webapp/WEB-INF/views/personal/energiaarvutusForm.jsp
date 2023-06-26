<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Energiaarvutus</title>
</head>
<body>
	<div align="center">
		<h1>New Energiaarvutus</h1>
		<form:form action="arvutaenergiaraw" method="post" modelAttribute="tarbitudenergia">
		<table cellpadding="5">
			<form:hidden path="arvestiid" />
			
			<form:select path="perioodid">
				<form:option value="${ periood.id }"
				 label="${ periood.pernimetus }"/>
				<form:options items="${ listPeriood }"
				itemValue="id" itemLabel="perNimetus"/>					
			</form:select></td></tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Arvuta, Salvesta"/></td>
			
			</tr>
		</table>
		</form:form>
	</div>

</body>
</html>