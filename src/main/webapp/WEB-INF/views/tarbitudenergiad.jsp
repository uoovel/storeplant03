<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<a class="linkk" href="userWelcomeSec?user=${userName}">|Avalehele</a>
<h1>Tarbitud energiakogused</h1>
    <table>
    <tr><td>
        <fmt:setLocale value="et_EE" scope="session"/>
        <a style="background-color:light-blue"><b>Perioodi algus:</b> 
        <fmt:formatDate type="date" dateStyle="long" value="${perioodiAlgus}"/> 
				kell
				<fmt:formatDate type="time" dateStyle="long" value="${perioodiAlgus}"/>
        
        <a/>
        <br>
         <a style="background-color:light-blue"><b>Perioodi  lõpp:</b>
         <fmt:formatDate type="date" dateStyle="long" value="${perioodiLopp}"/> 
				kell
	     <fmt:formatDate type="time" dateStyle="long" value="${perioodiLopp}"/>
         
         </a> <br>
         <a style="background-color:light-blue"><b>Kogusumma: </b>
		<a id="kokkuSumma" >  </a>
		eurot</a>
		</td></tr>
     </table>   
		<table border="1" cellpadding="5">
			<tr>
				<th>Nr</th>
				<th>Alates</th>
				<th>Kuni</th>
				<th>Energia <br>kWh</th>
				<th>Hind <br>s/kWh</th>
				<th>Summa <br>s </th>
								
			</tr>
			<c:forEach items="${listTarbitudEnergia}" var="tarbitudenergia" varStatus="status">
			<tr style="font-size:18px">
				<td>${status.index + 1}</td>
				<td><fmt:formatDate type="date" dateStyle="long" value="${tarbitudenergia.alates}"/> 
				kell
				<fmt:formatDate type="time" dateStyle="long" value="${tarbitudenergia.alates}"/>				
				</td>
				<td><fmt:formatDate type="date" dateStyle="long" value="${tarbitudenergia.kuni}"/> 
				kell
				<fmt:formatDate type="time" dateStyle="long" value="${tarbitudenergia.kuni}"/>				
				</td>				
				<td>${tarbitudenergia.energia}</td>
				<td>${tarbitudenergia.hind}</td>	
				<td>${tarbitudenergia.summa}</td>			
				
			</tr>
			</c:forEach>
		</table>
		
</div>
<script>
	let num = ${kokkuSumma}
	let n = num.toFixed(2);
	document.getElementById("kokkuSumma").innerHTML = n;
</script>		
</body>
</html>