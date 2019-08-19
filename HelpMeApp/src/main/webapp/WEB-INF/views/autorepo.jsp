<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="help.css">
<meta charset="ISO-8859-1">
<title>Message</title>
</head>
<header>
	<h2>send a message</h2>
</header>
<body>
<div class="container">
	<form action="/autorepo?id=${id}" method="post">
		<p>
		
		</p>

		<textarea name="message" rows="12 " cols="20">
		
<%-- ${message}  --%>


  
</textarea>

		<button type="submit">Send</button>
	</form>
</div>
</body>
<footer> </footer>
</html>