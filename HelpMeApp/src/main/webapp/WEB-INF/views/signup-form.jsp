<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/minty/bootstrap.min.css" />
<!-- Custom CSS goes below Bootstrap CSS -->
<link rel="stylesheet" href="/style.css" />
</head>
<header> </header>
<body>
<h3>Please fill the form to register!</h3>

<form action="/signup-confirmation" autocomplete="off">
		<p>First Name:	<input	type="text"			name="firstName"	required/> </p>
		<p>Last Name:	<input	type="text"			name="lastName"		required/> </p>
		<p>Phone Number:<input	type="text"			name="phoneNumber"	/> </p>
		<p>Address:<input	type="text"			name="address"	required/> </p>
		<p>Email:		<input	type="email"		name="email" 		
          				placeholder="username@grandcircus.com" pattern=".+@.+"
          				title="Please provide only a Best Startup Ever corporate e-mail address"/> </p>
		<p>Password:	<input	type="password"		name="password" required/> </p>
		<p><button type="submit">Submit</button></p>
</form>
</body>
<footer>
</footer>
</html>