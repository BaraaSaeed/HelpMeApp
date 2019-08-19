<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="help.css">
<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<header>
<nav>
	<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		<p><a href="/organizations-list">Display Organizations</a></p>
		<p><a href="/logout">Log out!</a></p>
	</c:if>
</nav>
</header>
<body>
<h1>Welcome to HelpMe! </h1>

<c:if test="${ empty user }">
		<form method="post" autocomplete="off">
		<h2>Login </h2>
	<p>
		<label>Email</label>	<input 	type="email"	name="email"	required>
	</p>
	<p>
		<label>Password</label>	<input 	type="password"	name="password"	required>
	</p>
	<p>
		<button type="submit">Log in</button>
	</p>
</form>
		<p><a href="/signup">Sign up!</a></p>
	</c:if>






<%-- <c:forEach items="${organizations}" var="org">
<ul>
<li><a href="${org.weburl }" >${org.nme}</a></li>
</ul>
</c:forEach>
 --%>




</body>
</html>