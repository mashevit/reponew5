<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mytripstyles.css">
</head>
<body>


<div class="container">
<table class="indext">
<tbody class="tbodyclass">
<tr>
<td class="tdind"><a href="index.jsp"><img src="images/41.png" class="indeximages" ></a></td>
  <td class="tdind"><a href="addsights.jsp"><img src="images/51.png" class="indeximages"></a></td>
  <td class="tdind"><a href="create trip.jsp"><img src="images/61.png" class="indeximages"></a></td>
  <td class="tdind"><a href="add pics.jsp"><img src="images/71.png" class="indeximages"></a></td>
  <td class="tdind"><a href="TripSightUpdate.jsp"><img src="images/81.png" class="indeximages"></a></td>
</tr>
<tr><td colspan="5" class="tdindtxt"> <a href="tripbrowser.jsp">Trip Browser</a></td></tr>
<!-- <tr>
<td class="tdindtxt">index</td>
  <td class="tdindtxt">add sights</td>
  <td class="tdindtxt">create trip</td>
  <td class="tdindtxt">add pictures</td>
  <td class="tdindtxt">trip sights update</td>
</tr> -->
</tbody>
</table>

</div>



	<c:if test="${empty trl}"><jsp:forward
			page="/PicController?action=trinit"></jsp:forward></c:if>
	select trip
	<center>
		<label style="margin-right: 130px;" for='city5'>trip: </label>
		<form name='form1' action='PicController'>
			<select class="form-control" id='city4' name='tripn'
				style="width: 250px;">
				<option value="-1">Select trip</option>
				<c:forEach items="${trl}" var="trip">
					<option value="<c:out value="${trip.idtrip}" />"
						<c:if test="${ trip.idtrip eq tripn }"><c:out value=" selected"/><c:set var="city" value="${trip.city}" scope="session"/></c:if>>
						<c:out value="${trip}" />
					</option>
				</c:forEach>
			</select><input type="hidden" name="action" value="tslctd"> <input
				type="submit" name="submit" value="submit">
		</form>
	</center>


	<c:if test="${not empty tripn}">Current sights:<br>
		select sight:
		<center>
			<label style="margin-right: 130px;" for='city5'>sights: </label>
			<form name='form1' action='PicController'>
				<select class="form-control" id='city4' name='sights'
					style="width: 250px;">
					<option value="-1">Select sight</option>
					<c:forEach items="${tsig}" var="sight">
						<option value="<c:out value="${sight.idtripSightseeing}" />"
							<c:if test="${ sight.idtripSightseeing eq slctsi }"><c:out value=" selected"/><c:set var="csight" value="${sight.sightseeing}" scope="session"/></c:if>>
							<c:out value="${sight}" />
						</option>
					</c:forEach>
				</select><input type="hidden" name="action" value="sighslctd"> <input
					type="hidden" name="tripn" value="${tripn}"> <input
					type="submit" name="submit" value="submit">
			</form>
		</center>



		<c:if test="${not empty pics}">
	current pics:<br>
	<c:forEach items="${pics}" var="pic">
				<img src="<c:out value="${pic.picsAddr}" />" width="auto"
					height="200" alt="" />
					<a
					href="PicController?tripn=<c:out value="${tripn}" />&action=delpic&todel=<c:out value="${pic.idpics}" />&slctsi=<c:out value="${slctsi}" />">
					del </a>
			
			</c:forEach>
		</c:if>
		<br>
enter pic addr:
<form name="fpic" action="PicController">
			<input type="text" name="pic_addr"><input type="hidden"
				name="action" value="picadd"><input type="hidden"
				name="tripn" value="${tripn}"><input type="hidden"
				name="slctsi" value="${slctsi}"><input type="submit"
				name="submit" value="submit">
		</form>
<a href="PicController?action=trinit">clean</a>
	</c:if>
</body>
</html>