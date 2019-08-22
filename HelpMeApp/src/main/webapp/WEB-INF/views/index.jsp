<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="help.css"> 

<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<header>

	<nav class="navbar navbar-expand-lg navbar-light bg-light " >
		<a class="navbar-brand" href="/">Help me App</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor03" aria-controls="navbarColor03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor03">
			<ul class="navbar-nav mr-auto" >
				<li class="nav-item "><a class="nav-link" href="/">Home
					<!-- 	<span class="sr-only">(current)</span> -->
				</a></li>
				<c:if test="${ not empty user }">
				<li class="nav-item"><a class="nav-link" href="/userpro">Profile</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="/logout" >Log out</a>
				</li>


				<li class="nav-item"> <a class="nav-link" href="/helplist">Organizations</a>
	
				</li>
</c:if>

			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary "  id="navbarColor01">
<div class="form-inline" >

	<c:if test="${ empty user }" >
	
	<a href="https://accounts.google.com/o/oauth2/v2/auth?scope=profile email&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&response_type=code&client_id=855747263310-23km4ggb7ckj25amm0hvpaedag4t6ur6.apps.googleusercontent.com"
	 class="btn btn-secondary" role="button" style="margin-right:2%;">
			
				Sign in with Google
				</a>
			
		</c:if>
		
	<c:if test="${ empty user }">

	<form method="post" action="/"  >
		<p>
			<input type="email" name="email" placeholder="email" required>

			<input type="password" name="password" placeholder="password"
				required>
			<button type="submit" class="btn btn-secondary">Log in</button>
		</p>
	</form>
</c:if>

</div>
</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="navbarColor01">
<div class="container">
	

		<c:if test="${ not empty user }">

	<b style="color:white;">Welcome ${ user.firstName }</b>
		


		</c:if>
		</div>
	</nav>
</header>
<body>
	<div class="container" style="padding-top: 5%; text-align: center;">
		<h1>Welcome to Help Me App!</h1>

		<c:if test="${ empty user }">
			<h2>
				<a href="/signup">Sign up!</a>
			</h2>

		</c:if>
	</div>
	

<form action="/helplist?selection=${selection}">
			
			<select name="selection" value="All Services">
			
					<option value="All Services">All Services</option>
					<option value="Budgeting and Credit Repair">Budgeting and Credit Repair</option>
					<option value="Homeless Assistance">Homeless Assistance</option>
					<option value="Mortgage Payment Workshop">Mortgage Payment Workshop</option>	
			</select>
		
			<button type="submit" class="btn btn-primary">Find Services</button>

		</form>	

<!-- <!-- 	<div id="selection form">
	<div class="dropdown">
<form action="/helplist"  >
 <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown">
     Refine Search
  </button>
  
  <div class="dropdown-menu" >
  	<a class="dropdown-item" href="helplist?selection=All Services"> All Services </a>
    <a class="drdopdown-item" href="helplist?selection=Budgeting and Credit Repair"> Budgeting and Credit Repair </a>
    <a class="dropdown-item" href="helplist?selection=Budgeting and Credit Repair"> Homeless Assistance </a>
    <a class="dropdown-item" href="helplist?selection=Budgeting and Credit Repair"> Mortgage Payment Workshop </a>
    </div>
  
			<button type="submit" class="btn btn-secondary">Log in</button>
		
	</form> -->
</div>
</div>
	
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
<footer></footer>
</html>