<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<s:url var="url_css" value="/static/css/style.css"/>
<link href="${url_css}" rel="stylesheet" type="text/css"/>
<link href="static/css/style.css"/>


</head>
<body>
<div id="Progress_Status"> 
	
    <div id="myprogressBar"></div> 
    <h1 align="center">SALVELEKTER</h1>
    <p align="center" style="color:red">${ teade }</p>
</div>
	<div align="center" style="background-color:lightblue">
		<h1>Sisene kontole</h1>
		<form:form  action="loginUserSec"
		 method="post" modelAttribute="loginCredentials">
		<table cellpadding="$" class="gfg">
			
			
			<tr>
				<td>Kasutaja:</td>
				<td><form:input  path="userName" style="border:1px solid black;"/></td>	
				<td><form:errors path="userName"/>    </td>		
			</tr>
			
			<tr>
				<td>Salasõna:</td>
				<td><form:input path="password" type="password" style="border:1px solid black;"/></td>			
			</tr>
			
			<tr>
				<td colspan="2" align="right" vertical-align="bottom">
				<input onclick="update()" type="submit" value="Sisene"/></td>
			
			</tr>
		</table>		
		</form:form>
	
	</div>

</body>
</html>