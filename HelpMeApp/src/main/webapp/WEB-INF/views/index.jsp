<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<link rel="stylesheet" href="help.css">
<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<header>
<nav style="">
<c:if test="${ empty user }">
		<form method="post" autocomplete="off" style="display: inline; float: right;">
		
	<p>
		<b>Login </b> <input 	type="email"	name="email"	placeholder="email" required>
	
			<input 	type="password"	name="password"	placeholder="password" required>

		<button type="submit">Log in</button>
	</p>
</form>
		
	</c:if>
	<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		<p><a href="/helplist">Display Organizations</a></p>
		<p><a href="/logout">Log out!</a></p>
	</c:if>
</nav>
</header>
<body>
<div class="container" style="padding-top:10%; text-align: center;"> 
<h1>Welcome to Help Me App! </h1>

<c:if test="${ empty user }">
<h2><a href="/signup">Sign up!</a></h2>

</c:if>
</div>

<%-- <c:forEach items="${organizations}" var="org">
<ul>
<li><a href="${org.weburl }" >${org.nme}</a></li>
</ul>
</c:forEach>
 --%>




</body>
</html>