<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/Station01/" method="post">
		<input type="hidden"
			th:name="${_csrf.parameterName}"
			th:value="${_csrf.token}" />			
		<input type="submit" value="Välju" />	
	</form:form>
	<h1>Tere personal ${klient.name}</h1>
	<h3><a href="kliendid">Kliendid</a></h3>	
	<h3><a href="pakkumised">Pakkumised</a></h3>
	<h3><a href="ameetriNaidud">Mõõdikud</a></h3>
	<h3><a href="valiperioodvoimsuseleform">Võimsusgraafikud</a></h3>
	<h3><a href="ajalugu">Ajalugu</a></h3>
	<!-- <h3><a href="tellimused">Tellimused</a></h3>
    <h3><a href="tarbitudenergiad">Tarbitud Energiad</a></h3> -->
	
	

</body>
</html>