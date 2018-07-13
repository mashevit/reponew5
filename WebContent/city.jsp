<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cities Page</title>
<link rel="stylesheet" type="text/css" href="mywebp.css">
</head>
<body>
<c:if test="${empty arim}"><jsp:forward page="/CityController?myaction=listOfCities&init=true"></jsp:forward></c:if>
<H1>רשימת ערים</H1>
<div class='container'>
    <div>
<table>
   <thead>
	  <tr><TH>ID</TH><TH>שם עיר</TH>
	  <TH>פעולה</TH></tr>
	</thead>
	<tbody>
	<c:forEach items="${arim}" var="ir">
	  <tr>
		  <td><c:out  value="${ir.id}"/></td>
		  <td><c:out  value="${ir.cname}"/></td>
		    <td>
    		  <a href="CityController?myaction=delete&id=<c:out value="${ir.id}"/>">מחיקה</a>
		    <a href="CityController?myaction=update&id=<c:out value="${ir.id}"/>">עדכון</a>  
		  </td> 
	 </tr>
	  </c:forEach>

	</tbody>
</table>
</div>
<div class='button-wrapper'>
<a href="formcity.jsp?action=new"><input type="button" value="new"  /></a>
    </div>
</div>
<!-- <a href="formcity.jsp?action=new">new</a> -->
</body>
</html>
