<!-- https://developers.google.com/chart/interactive/docs/gallery/gauge -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
      //function graafik(){
    	  //var voolutugevus = 50;
    	  google.charts.load('current', {'packages':['gauge']});
          //google.charts.setOnLoadCallback(drawChart);
      //}      

      function drawChart(voolutugevus) {
    	  
        //var voolutugevus = 50;
        //console.log(voolutugevus);
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Vool[A]', voolutugevus]
          //['CPU', 55],
          //['Network', 68]
        ]);

        var options = {
          width: 200, height: 200,
          min: 0, max: 800,
          redFrom: 650, redTo: 800,
          yellowFrom:500, yellowTo: 650,          
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);

        //setInterval(function() {
        //  data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
        //  chart.draw(data, options);
        //}, 13000);
        //setInterval(function() {
        //  data.setValue(1, 1, 40 + Math.round(60 * Math.random()));
        //  chart.draw(data, options);
        //}, 5000);
        //setInterval(function() {
        //  data.setValue(2, 1, 60 + Math.round(20 * Math.random()));
        //  chart.draw(data, options);
        //}, 26000);
      }
    </script>
</head>
<body onload='drawChart(${ ameetriNait.vool })' style="background-color:lightblue">

<!-- onload='peida("${ pakkumisiPole }")' -->


<div align="center">
	<h1>Ampermeetri näidud</h1>
	<fmt:setLocale value="et_EE" scope="session"/>

	<table>
	    <tr>
	          <th>Reaalaeg</th>
			  <th>Mõõteseeria</th>
	    </tr>
		<tr>
		<td>
		<div id="chart_div">  </div>
		</td>		
		<td>
		<table border="1" cellpadding="5" style="background-color:lightgrey">
			<tr>				
				<th>Aeg  
				<br>
				<fmt:formatDate type="date" dateStyle="long" value="${ ameetriNait.aeg }"/> 
				</th>
				<th>Vool <br>A</th>
			</tr>
			<c:forEach items="${listAmeetriNait}" var="ameetrinait" varStatus="status">
			<tr>
				<td style="text-align:center" >
				<fmt:formatDate type="time" dateStyle="long" value="${ ameetrinait.aeg }"/>
				
				</td>
				<td>${ameetrinait.vool}</td>	
			</tr>
			</c:forEach>
		</table>
		</td>		
		</tr>	
	</table>
	
	
	








       		

		
</div>

</body>
</html>