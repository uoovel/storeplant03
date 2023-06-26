<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salvestuselektrijaam</title>
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
	    <a style="background-color:lightgreen;">Salvestuselektrijaama Veebirakendus</a>
		<h1>Tellimuste loend, personal</h1>		

		<table border="1" cellpadding="5">
			<tr>
				<th>Nr</th>
				<th>Pakkumine</th>
				<th>Klient</th>
				<th>Periood</th>				
				<th>Staatus</th>				
			</tr>
			<c:forEach items="${listTellimusViewL}" var="tellimusview" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><b>Pakett:</b> ${tellimusview.pakNimetus}<br>
				    <b>Hind:</b> ${tellimusview.hind}<br>
				    <b>Kirjeldus:</b> ${tellimusview.kirjeldus}</td>
				<td>${tellimusview.eesnimi} ${tellimusview.perenimi}</td>					
				<td>${tellimusview.perNimetus}</td>
				<td>${tellimusview.staNimetus}</td>				
				<td>
					<a href="edittellimus?id=${tellimusview.id}&rollid=${rollid}">Muuda</a>
					&nbsp;&nbsp;				
					<!-- <a href="deletetellimus?id=${tellimusview.id}">Kustuta</a> -->
				</td>	
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>