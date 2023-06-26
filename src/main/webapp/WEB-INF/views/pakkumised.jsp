<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Storage station</title>
</head>
<body>
	<h1>Storage Power Station Application</h1>
	<div align="center">
		<h1>Pakkumine List</h1>
		<h3><a href="newpakkumine">New Pakkumine</a></h3>
		<table border="1" cellpadding="5">
			<tr>
				<th>No</th>
				<th>Nimetus</th>
				<th>Hind</th>
				<th>Kirjeldus</th>
				<th>Periood</th>
			</tr>
			<c:forEach items="${listPakkumineView}" var="pakkumine" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${pakkumine.nimetus}</td>
				<td>${pakkumine.hind}</td>
				<td>${pakkumine.kirjeldus}</td>
				<td>${pakkumine.periood}</td>
				<td>
					<a href="editpakkumine?id=${pakkumine.id}">Edit</a>
					&nbsp;&nbsp;
					<a href="deletepakkumine?id=${pakkumine.id}">Delete</a>
				</td>	
			</tr>
			
			
			</c:forEach>
		</table>
	</div>

</body>
</html>