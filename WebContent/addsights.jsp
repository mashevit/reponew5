<%-- <%@page import="com.travel1.TripBeLocal"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.travel1.TripBeLocal"%>
<%@page import="tr.model.City"%>
<%@page import="tr.model.Sightseeing"%>
<%@page import="javax.naming.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
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


<c:set var="snm" value="${param.namesi}" scope="session"/>
	<c:forEach items="${param.cn}" var="i1">
		<c:set var="q" value="${TBean.addCity(i1)}" scope="session" />${TBean.addSighti(snm,q) }</c:forEach>

	<form name='form1' action='addsights.jsp'>
		<select id='city4' name='city' style="width: 250px;">
			<option selected='selected' value="-1">Select city</option>
			<c:forEach items="${TBean.getAllCities()}" var="city">

				<option value="<c:out value="${city.idcities}" />"><c:out
						value="${city.cityName}" /></option>
			</c:forEach>
		</select>
		<c:forEach items="${param.city}" var="i1"><c:set var="snm" value="${param.namesi}" scope="session"/>
			<c:if test="${i1 ne -1}">${TBean.addSighti(snm,i1) }</c:if>
		</c:forEach>
		add city: city name <input type="hidden" name="action" value="addcity"><input
			type="hidden" name="fromto" value="create"><input type="text"
			name="cn">sight name: <input type="text" name="namesi">
		<input type="submit" name="submit" value="submit">
	</form>
</body>
</html> --%>