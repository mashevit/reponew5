<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import='java.sql.*'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mytripstyles.css">
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
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
			page="SightAddController?action=trinit"></jsp:forward></c:if>
	<center>
		<label style="margin-right: 130px;" for='city5'>trip: </label>
		<form name='form1' action='SightAddController'>
			<select class="form-control" id='city4' name='tripn'
				style="width: 250px;">
				<option value="-1">Select trip</option>
				<c:forEach items="${trl}" var="trip">
					<%--    <fmt:parseNumber var = "ids" type = "number" value = "${city.idcities}" /> --%>
					<option value="<c:out value="${trip.idtrip}" />"<c:if test="${ trip.idtrip eq tripn }"><c:out value=" selected"/><c:set var="city" value="${trip.city}" scope="session"/></c:if>>
						<c:out value="${trip}" />
					</option>
				</c:forEach>
			</select><input type="hidden" name="action" value="tslctd"> <input
				type="submit" name="submit" value="submit">
		</form>
	</center>
	<c:if test="${not empty tripn}">Current sights:<br> <c:forEach
			items="${tsig}" var="sight">
			<c:out value="${sight}" />
			<a
				href="SightAddController?tripn=<c:out value="${tripn}" />&action=deletes&sight=<c:out value="${sight.idtripSightseeing}" />&city=<c:out value="${city}" />">
				delete sight</a>
			<br>
		</c:forEach>

		<BR>select Sight:
	<center>
			<label style="margin-right: 130px;" for='city5'>new sight
				from <c:out value="${city}"/>
			</label>
			<form name='f4' action='SightAddController'>
				<select class="form-control" id='sight' name='sight'
					style="width: 250px;">
					<option value="-1">Select sight</option>
					<c:forEach items="${news}" var="st">
						<option value="<c:out value="${st.idSightSeeings}" />" >
							<c:out value="${st.sightSeeingsName}" />
						</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="addsightfn"> <input
					type="hidden" name="city" value="<c:out value="${city}"/>"><input
					name="tripn" type="hidden" value="${tripn}"> <input
					type="submit" name="submit" value="submit">
			</form>
		</center>
	Or add sight: sight name
	<form name="f5" action="SightAddController">
			<input type="hidden" name="action" value="addsight"><input
				type="text" name="sign"> city is:
			<c:out value="${city}" /> <input
				type="hidden" name="city" value="<c:out value="${city}"/>"> <input
				type="hidden" name="tripn" value="${tripn}"><input
				type="submit" name="whereto" value="tocity"> <input
				type="submit" name="whereto" value="totrip">
		</form>
	</c:if>

</body>
</html>