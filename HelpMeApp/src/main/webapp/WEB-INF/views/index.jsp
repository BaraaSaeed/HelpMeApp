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
      
      
    </ul>
    </div>
</nav>
<c:if test="${ not empty notFound }">
	<p>${notFound }</p>

</c:if>
		
<<<<<<< HEAD
	<p>
		 <input 	type="email"	name="email"	placeholder="email" required>
	
			<input 	type="password"	name="password"	placeholder="password" required>


		<button type="submit">Log in</button>
	</p>
</form>
	</c:if>	


=======

<nav>
>>>>>>> 3bd5fdfba9fdda2a29d278905348ea866d481128
	<c:if test="${ empty user }">
		<p><a href="/login">Log in!</a></p>
		<p>
		<a href="https://accounts.google.com/o/oauth2/v2/auth?scope=profile email&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&response_type=code&client_id=855747263310-23km4ggb7ckj25amm0hvpaedag4t6ur6.apps.googleusercontent.com">Sign in with Google</a>
	</p>
		<p><a href="/signup">Sign up!</a></p>

	</c:if>
	
	<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		
		<p><a href="/logout" id="logout">Log out</a></p>
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