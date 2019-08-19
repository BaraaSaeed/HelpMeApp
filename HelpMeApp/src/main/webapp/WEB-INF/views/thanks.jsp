<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanks</title>
</head>
<body>
	<h1>Thanks for signing up</h1>
	${ user } /  ${ session.user }
	<p>Welcome ${ user.firstName }!</p>
	<p><a href="/">Home</a></p>
</body>
</html>