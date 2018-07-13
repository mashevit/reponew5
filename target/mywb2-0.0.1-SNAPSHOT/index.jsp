<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <%--      <base href="${pageContext.request.contextPath}">  --%>
<link rel="stylesheet" type="text/css" href="mytripstyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<%-- <c:out value="${pageContext.request.contextPath}"/> --%>
<div class="container">
<!-- <img src="images/51.jpg"> -->
<table class="indext">
<tbody class="tbodyclass">
<tr>
<td class="tdind"><a href="./index.jsp"><img src="images/41.png" class="indeximages" ></a></td>
  <td class="tdind"><a href="./addsights.jsp"><img src="./images/51.png" class="indeximages"></a></td>
  <td class="tdind"><a href="./create trip.jsp"><img src="./images/61.png" class="indeximages"></a></td>
  <td class="tdind"><a href="add pics.jsp"><img src="./images/71.png" class="indeximages"></a></td>
  <td class="tdind"><a href="TripSightUpdate.jsp"><img src="./images/81.png" class="indeximages"></a></td>
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




</body>
</html>