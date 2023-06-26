<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salvestuselektrijaam</title>
<s:url var="url_css" value="/static/css/style.css"/>
<link href="${url_css}" rel="stylesheet" type="text/css"/>
<link href="static/css/style.css"/>
<script>
        var rollid = 1;
        
		function peida(rollid){
			//console.log("x");
			//console.log(pakkumisiPole);
			if(rollid == 2){
				//document.getElementById("peiduproov").style.visibility="hidden";
				document.getElementById("kustuta").style.visibility="hidden";
				
			}
			
		}
</script>
</head>
<body onload='peida("${ rollid }")'>
	
	<div align="center">
	    <a class="linkk" href="userWelcomeSec?user=${userName}">|Avalehele</a>
	    <a class="linkk" href="tellimusedLeft?user=${userName}&rollid=1&ajalugu=1">|Ajalugu</a>
		<h1>Tellimuste loend</h1>		

		<table border="1" cellpadding="5">
			<tr>
				<th>Nr</th>
				<th>Pakkumine</th>
				<th>Klient</th>
				<th>Periood</th>				
				<th>Staatus</th>
				<th><form:form action="perioodtellimuseleForm" method="get"
		       		style="margin-top:0px">			
					<input onclick="update()" type="submit" value="Uus Tellimus"/>
					</form:form></th></tr>
			<c:forEach items="${listTellimusViewL}" var="tellimusview" varStatus="status">
			<tr style="font-size:18px">
				<td>${status.index + 1}</td>
				<td><b>Pakett:</b> ${tellimusview.pakNimetus}<br>
				    <b>Hind:</b> ${tellimusview.hind}<br>
				    <b>Kirjeldus:</b> ${tellimusview.kirjeldus}</td>
				<td>${tellimusview.eesnimi} ${tellimusview.perenimi}</td>					
				<td>${tellimusview.perNimetus}</td>
				<td>${tellimusview.staNimetus}</td>			
				<td><a class="linkk" style="font-size:18px" href="edittellimus?id=
					${tellimusview.id}&rollid=${rollid}">Muuda</a>	
					&nbsp;&nbsp;				
					<a class="linkk" style="font-size:18px"
					 href="deletetellimus?id=${tellimusview.id}">Kustuta</a></td></tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>