<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form:form action="testhind" method="post" 
		modelAttribute="hind">
		
		 <form:input path="vaartus"></form:input>
		 <form:errors path="vaartus"></form:errors>
			
			<input type="submit" value="Esita tellimus"/>
		
				
	
		</form:form>
</body>
</html>