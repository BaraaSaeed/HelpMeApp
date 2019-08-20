<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<header >
<nav  class="navbar navbar-expand-lg navbar-light bg-light"> 
<a class="navbar-brand" href="/">Help me App</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
   <div class="collapse navbar-collapse" id="navbarColor03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/userpro">Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/helplist">Big List</a>
      </li>
      
    </ul>
    </div>
</nav>
<c:if test="${ not empty notFound }">
	<p>${notFound }</p>

</c:if>

<nav  class="navbar navbar-expand-lg navbar-dark bg-primary" id="navbarColor01" >
<c:if test="${ empty user }">
		<form action="/" method="post" autocomplete="off" class="form-inline my-2 my-lg-0">
		
	<p>
		 <input 	type="email"	name="email"	placeholder="email" required>
	
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
<div class="container" style="padding-top:5%; text-align: center;"> 
<h1>Welcome to Help Me App! </h1>

<c:if test="${ empty user }">
<h2><a href="/signup">Sign up!</a></h2>

</c:if>
</div>






</body>
<footer></footer>
</html>