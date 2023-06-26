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

	
<div align="center">

	<h1>Tere ${klient.name}
			
	
	</h1>
	<table>
	<tr>
		<td>
		<a class="linkk" style="font-size:20px" href="perioodenergialeForm">|TARBIMINE</a>
		</td>
	    <td>		
		<td>
		<a class="linkk" style="font-size:20px" 
		href="tellimusedLeft?ajalugu=0&rollid=1&user=${userName}">TELLIMUSED|</a>
		</td>
		<td style="vertical-align:top">
		
		<form:form action="/Station01/" method="post">
			
			<input type="hidden"
				th:name="${_csrf.parameterName}"
				th:value="${_csrf.token}" />			
			<input type="submit" value="Välju" />
		</form:form>
		</td>
		</tr>
	</table>
	
		
	
	<div style="background-color:lightblue;width:300px;margin-top:50px;height:200px">
	
	<table align="left">
		<tr>
	    <td style="align:left;vertical-align:top"><b>Teated:</b></td>
		<td>		        </td>
		</tr>
		<tr>
		<td>          		</td>
		<td>-${teade}</td>
		</tr>	
	
	</table>
	
	
	
	</div>
</div>	
	

</body>
</html>