<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import='java.sql.*'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Trip Creator</title>
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



	<%-- <c:choose>
    <c:when test="${not empty ci}">
        <c:set var="cis" value="${ci}" scope="session"/>
        <fmt:parseNumber var = "cis" type = "number" value = "${ci}" />
    </c:when>
    <c:otherwise>
        var1 is empty or null.
    </c:otherwise>
</c:choose>
<c:set var="cis" value="${empty ci ? -1 : ci }" scope="session"/><c:out value="${cis}"/> --%>
		<c:if test="${empty cities}"><jsp:forward
			page="TripController?action=createtrip"></jsp:forward></c:if>
	
	Select trip city:
	<center>
		<label style="margin-right: 130px;" for='city5'>city name</label>
		<form name='form1' action='TripController'>
			<select class="form-control" id='city4' name='city'
				style="width: 250px;">
				<option value="-1">Select city</option>
				<c:forEach items="${cities}" var="city">
					<%--    <fmt:parseNumber var = "ids" type = "number" value = "${city.idcities}" /> --%>
					<option value="<c:out value="${city.idcities}" />"
						<c:if test="${ city.idcities eq ci }"><c:out value=" selected"/><c:set var="cityc" value="${city.cityName}" scope="session"/></c:if>><c:out
							value="${city.cityName}" />
					</option>
				</c:forEach>
			</select><input type="hidden" name="action" value="cityset"><input
				type="hidden" name="ti" value="<c:out value="${ti}"/>">
				<input type="hidden" name="sig" value="<c:out value="${sig}"/>">
				<input
				type="hidden" name="trt" value="<c:out value="${trt}"/>"> <input
				type="hidden" name="trid" value="<c:out value="${trid}"/>">
			<input type="hidden" name="triho" value="<c:out value="${triho}"/>">
			<input type="hidden" name="trm" value="<c:out value="${trm}"/>">
			<input type="hidden" name="trdy" value="<c:out value="${trdy}"/>">
			<input
				type="hidden" name="fromto" value="createtrip"><input
				type="submit" name="submit" value="submit">
		</form>
	</center>
	or add city: city name
	<form name="f2" action="TripController">
		<input type="hidden" name="action" value="addcity"><input
			type="hidden" name="fromto" value="create"><input type="text"
			name="cn"><input type="submit" value="submit">
	</form>

	<BR>Select Traveler:
	<center>
		<label style="margin-right: 130px;" for='city5'>traveler:</label>
		<form name='form1' action='TripController'>
			<select class="form-control" id='city4' name='traveller'
				style="width: 250px;">
				<option value="-1">Select traveler</option>
				<c:forEach items="${travellers}" var="trs">

					<option value="<c:out value="${trs.idtraveler}" />"
						<c:if test="${trs.idtraveler eq ti}"><c:out value=" selected"/></c:if>><c:out
							value="${trs.travelerName}" />
						<fmt:formatDate value="${trs.traveler_BirthDate}"
							var="formattedDate" type="date" pattern="dd-MM-yyyy" />
						${formattedDate}
					</option>
				</c:forEach>
			</select> <input type="hidden" name="action" value="settra"> <input
				type="hidden" name="trt" value="<c:out value="${trt}"/>"> <input
				type="hidden" name="trid" value="<c:out value="${trid}"/>">
			<input type="hidden" name="triho" value="<c:out value="${triho}"/>">
			<input type="hidden" name="trm" value="<c:out value="${trm}"/>">
			<input type="hidden" name="trdy" value="<c:out value="${trdy}"/>">
			<input type="hidden" name="ci" value="<c:out value="${ci}"/>">
			<input type="hidden" name="sig" value="<c:out value="${sig}"/>">
				<input type="hidden" name="ti" value="<c:out value="${ti}"/>">
			<input type="hidden" name="fromto" value="createtrip"><input
				type="submit" name="submit" value="submit">
		</form>
	</center>
	Or add traveler: traveler name
	<form name="f2" action="TripController">
		<input type="hidden" name="action" value="addtraveler"><input
			type="hidden" name="fromto" value="create"><input type="text"
			name="trn"><input type="text" name="bd">dd-mm-yyyy<input
			type="submit" value="submit">
	</form>
	<form name="f3" action="TripController">
		<BR>Enter num min transit:<input type="text" name="transit"
			<c:if test="${not empty trt}"><c:out value=" value = ${trt} "/></c:if>>
		<BR>Enter trip date:<input type="text" name="tripdate"
			<c:if test="${not empty trid}"><c:out value=" value = ${trid} "/></c:if>>dd-mm-yyyy
		<BR>Enter hotel name:<input type="text" name="hotel"
			<c:if test="${not empty triho}"><c:out value=" value = ${triho} "/></c:if>>
		<BR>Enter money spent:<input type="text" name="money"
			<c:if test="${not empty trm}"><c:out value=" value = ${trm}"/></c:if>>
		<BR>Enter num days:<input type="text" name="days"
			<c:if test="${not empty trdy}"><c:out value=" value = ${trdy} "/></c:if>>
		<input type="hidden" name="action" value="addatrr"><input
			type="hidden" name="fromto" value="createtrip"> <input
			type="hidden" name="ci" value="<c:out value="${ci}"/>"> <input
			type="hidden" name="ti" value="<c:out value="${ti}"/>"> <input
			type="hidden" name="ti" value="<c:out value="${ti}"/>">
			<input type="hidden" name="sig" value="<c:out value="${sig}"/>"><input
			type="submit" name="submit" value="submit">

	</form>
	<form name="clean1" action="TripController">
	
	<input type="hidden" name="action" value="clean"><input
				type="hidden" name="fromto" value="create"><!--  <input
				type="hidden" name="trt" value=""> <input
				type="hidden" name="trid" value="">
			<input type="hidden" name="triho" value="">
			<input type="hidden" name="trm" value="">
			<input type="hidden" name="trdy" value="">
			<input type="hidden" name="ci" value="">
			<input type="hidden" name="ti" value="">
			<input type="hidden" name="sig" value=""> -->
			<input type="submit" name="submit" value="clean">
	</form>

	
	
	
	
	
	<BR>select Sight:
	<center>
		<label style="margin-right: 130px;" for='city5'>sight:</label>
		<form name='f4' action='TripController'>
			<select class="form-control" id='sight' name='sight'
				style="width: 250px;">
				<option value="-1">Select sight</option>
				<c:forEach items="${sights}" var="st">

					<option value="<c:out value="${st.idSightSeeings}" />" <c:if test="${ st.idSightSeeings eq sig }"><c:out value=" selected"/></c:if>>
						<c:out value="${st.sightSeeingsName}" />
					</option>
				</c:forEach>
			</select> <input type="hidden" name="action" value="addsightf"><input
				type="hidden" name="fromto" value="create"> <input
				type="hidden" name="trt" value="<c:out value="${trt}"/>"> <input
				type="hidden" name="trid" value="<c:out value="${trid}"/>">
			<input type="hidden" name="triho" value="<c:out value="${triho}"/>">
			<input type="hidden" name="trm" value="<c:out value="${trm}"/>">
			<input type="hidden" name="trdy" value="<c:out value="${trdy}"/>">
			<input type="hidden" name="ci" value="<c:out value="${ci}"/>">
			<input type="hidden" name="ti" value="<c:out value="${ti}"/>">
			<input type="hidden" name="sig" value="<c:out value="${sig}"/>">
			<input type="submit" name="submit" value="submit">
		</form>
	</center>
	Or add sight: sight name
	<form name="f5" action="TripController">
		<input type="hidden" name="action" value="addsight"><input
			type="hidden" name="fromto" value="create"> <input
			type="hidden" name="action" value="settra"> <input
			type="hidden" name="trt" value="<c:out value="${trt}"/>"> <input
			type="hidden" name="trid" value="<c:out value="${trid}"/>"> <input
			type="hidden" name="triho" value="<c:out value="${triho}"/>">
		<input type="hidden" name="trm" value="<c:out value="${trm}"/>">
		<input type="hidden" name="trdy" value="<c:out value="${trdy}"/>">
		<input type="hidden" name="ci" value="<c:out value="${ci}"/>">
		<input type="hidden" name="ti" value="<c:out value="${ti}"/>"><input
			type="text" name="sign"> city is:
		<c:out value="${ci}" />
		select above city is: <input type="submit" value="submit">
	</form>
	<BR>
	<c:if
		test="${(not empty trt) && (not empty trid) && (not empty triho) && (not empty trm) && (not empty trdy) && (not empty ci) && (not empty sig) && (not empty ti)}">
		<c:out value=" a form will be created with above values "/><form name='final' action='TripController'>
		<input type="hidden" name="action" value="docreate"><input
				type="hidden" name="fromto" value="create"> <input
				type="hidden" name="action" value="settra"> <input
				type="hidden" name="trt" value="<c:out value="${trt}"/>"> <input
				type="hidden" name="trid" value="<c:out value="${trid}"/>">
			<input type="hidden" name="triho" value="<c:out value="${triho}"/>">
			<input type="hidden" name="trm" value="<c:out value="${trm}"/>">
			<input type="hidden" name="trdy" value="<c:out value="${trdy}"/>">
			<input type="hidden" name="ci" value="<c:out value="${ci}"/>">
			<input type="hidden" name="ti" value="<c:out value="${ti}"/>">
			<input type="hidden" name="sig" value="<c:out value="${sig}"/>"><input type="submit" name="submit" value="submit"></form>
	</c:if>
</body>
</html>