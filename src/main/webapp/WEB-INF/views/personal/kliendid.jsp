<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<input type="submit" value="Log out" />	
	</form:form>

	<div align="center">	    
	
		<h1>Klientide loend</h1>
		<h3><a href="new">Uus klient</a></h3>		
		<table border="1" cellpadding="5">
			<tr>
				<th>Nr</th>
				<th>Nimi</th>
				<th>Tegevus</th>
				<th>Tarbitud energia</th>
				<th>Tellimused</th>
			</tr>
			<c:forEach items="${listContact}" var="contact" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${contact.name}</td>
				<td>
					<a href="edit?id=${contact.id}">Muuda</a>
					&nbsp;&nbsp;
					<a href="delete?id=${contact.id}">Kustuta</a>
				</td>
				<td>
				    <a href="energiaarvutuslaud?id=${contact.id}">Arvuta</a>
					&nbsp;&nbsp;
				    <a href="perioodenergialeForm?id=${contact.id}">Vaata</a>									
				</td>
				<td>				 
				    <a href="tellimusedLeft?id=${contact.id}&rollid=2&ajalugu=0">Vaata</a>									
				</td>	
			</tr>			
			
			</c:forEach>
		</table>
	</div>

</body>
</html>