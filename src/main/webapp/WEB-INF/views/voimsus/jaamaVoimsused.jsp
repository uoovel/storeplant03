<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">

<a><b>Elektrijaama võimsuste plaan </b>       </a><a style="padding-left:30px">${periood.perNimetus}</a>
		<table border="1" style="font-size:90%">
			<tr>
				<th>Tund</th>
				<th>Alates</th>
				<th>Pakutav võimsus <br>kW</th>
				<th>Töövõimsus <br>kW</th>
				<th><a href="arvutaToovoimsus?perioodid=${perioodid}" style="background-color:lightgreen">Arvuta Võimsused</a></th>
			</tr>
			<c:forEach items="${listJaamaVoimsus}" var="voimsus" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${voimsus.aeg}</td>
				<td style="text-align:center">${voimsus.pakutav}</td>				
				<td style="text-align:center">${voimsus.too}</td>			
			</tr>
			</c:forEach>
			
		</table>
</div>		
</body>
</html>