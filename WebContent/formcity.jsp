<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>City Update Form</title>
</head>
<body>
<form action="CityController" method="post" name="frm">

<c:if test="${action !='new'}" >
<div>
<label for="id_param">ID (readonly)</label>
<input readonly type="text" name="id_param" value="<c:out  value="${ir.id}"/>"/>
</div>
</c:if>
<div>
<label for="c_name_param">City name</label>
<input type="text" name="c_name_param" value="<c:out  value="${ir.cname}"/>"/>
</div>
<c:if test="${param.action != 'new'}" ><input type="submit" name="action" value="update"/></c:if>
<c:if test="${param.action == 'new'}" ><input type="submit" name="action" value="new"/></c:if>
<input type="submit" name="action" value="cancel">
</form>
</body>
</html>


