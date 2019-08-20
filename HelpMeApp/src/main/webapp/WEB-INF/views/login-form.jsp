<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
</head>
<body>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<h1>Enter your email and password</h1>
<form method="post" autocomplete="off">
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
</body>
</html>