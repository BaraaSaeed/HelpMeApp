<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<body>
<h1>Welcome to HelpMe! </h1>

<c:forEach items="${organizations}" var="org">

${org.nme}

</c:forEach>

<nav>
	<c:if test="${ empty user }">
		<p><a href="/login">Log in!</a></p>
		<p><a href="/signup">Sign up!</a></p>
	</c:if>
	<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		<p><a href="/organizations-list">Display Organizations</a></p>
		<p><a href="/logout">Log out!</a></p>
	</c:if>
</nav>

</body>
</html>