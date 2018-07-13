<%-- <%@page import="com.travel1.TripBeLocal"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.travel1.TripBeLocal"%>
<%@page import="tr.model.City"%>
<%@page import="javax.naming.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<%/*
final Properties p = new Properties();

// jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

/* p.put(Context.INITIAL_CONTEXT_FACTORY,        "org.jnp.interfaces.NamingContextFactory");
p.put(Context.URL_PKG_PREFIXES, " org.jboss.naming:org.jnp.interfaces");
p.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");

	InitialContext ctx = new InitialContext(p); 
//	TellerLocal teller = (TellerLocal) ctx.lookup("EJBEARProject/Teller/local"); 
	
//Context ejbCtx = (Context) ctx.lookup("java:comp/env/ejb");

			try {
/*  InitialContext iniCtx = new InitialContext();
	TripBeLocal TBL = (TripBeLocal) iniCtx
			.lookup("java:app/DBEJB/TripBe!com.travel1.TripBeLocal");
/* 			 @EJB
			    TripBeLocal TBL= new TripBeLocal(); 
			    
			   
List<City> cities = TBL.getAllCities();
HttpSession s=request.getSession();
s.setAttribute("cities",cities); 
%>


<jsp:useBean id="listBuilder" class="com.example.ELListBuilder"/>

<ul>
  <c:forEach var="item" items="${listBuilder['red']['yellow']['green'].build}">
      <li>${item}</li>
  </c:forEach>
</ul>

*/
	<%	HttpSession s=request.getSession();
s.setAttribute("cities",request.getParameterValues("city")); %>
		<c:forEach items = "${paramValues.city}" var="i1">	
<c:out value="${TBean.removeCity(i1)}"/></c:forEach>
		
		<form name='form1' action='removemul.jsp'>
			<select multiple class="form-control" id='city4' name='city'
				style="width: 250px;">
				<option selected='selected' value="-1">Select city</option>
				<c:forEach items="${TBean.getAllCities()}" var="city">

					<option value="<c:out value="${city.idcities}" />"><c:out
							value="${city.cityName}" /></option>
				</c:forEach>
			</select>
			<input type="submit" name="submit" value="submit">
		</form>
		
	<%
		String[] citymul = request.getParameterValues("citymul");

		if (citymul != null) {
			for (int i = 0; i < citymul.length; i++) {
				String[] tosplit = citymul[i].split("#");
				out.println("city index + " + tosplit[0] + " name: " + tosplit[1]);
			}

		}

		/* 	} catch (NamingException ex) {
				ex.printStackTrace();
			}       */
	%>
</body>
</html> --%>