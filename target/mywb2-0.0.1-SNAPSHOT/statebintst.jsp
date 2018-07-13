<%-- <%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="javax.naming.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="simple.stateful.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<form action="statebintst.jsp" method="post">
		Enter a to do item: &nbsp; &nbsp;<INPUT type="text" name="item"><br>
		<INPUT type="submit" name="todo" value="AddItem"> <INPUT
			type="submit" name="todo" value="ListItems"> <INPUT
			type="submit" name="todo" value="ClearList">
			<br>
		<%
			String action = request.getParameter("todo");
			ToDoRemote td = (ToDoRemote) session.getAttribute("todo");
			if (td == null) {
				try {
					final Properties jndiProps = new Properties();

					jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
					InitialContext ctx = new InitialContext(jndiProps);
					td = (ToDoRemote) ctx.lookup("ejb:ww22-ear/ww22-web/ToDo!simple.stateful.ToDoRemote?stateful");
					session.setAttribute("todo", td);
				} catch (NamingException ex) {
					ex.printStackTrace();
				}
			}
			if (action != null) {
				if (action.equals("AddItem")) {
					String item = request.getParameter("item");
					if (!item.equals("")) {
						td.addItem(item);
						out.println("item " + item + " added");
					}
				} else if (action.equals("ListItems")) {
					Vector<String> items = td.listItems();
					out.println("items: ");
					for (int x = 0; x != items.size(); x++) {
						out.println("<br>" + (x + 1) + ". " + items.elementAt(x));
					}
				} else {
					td.clearItems();
					out.println("items Cleared");
				}
			}
		%>
	</form>
</body>
</html> --%>