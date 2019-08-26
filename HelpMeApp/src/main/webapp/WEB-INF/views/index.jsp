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

	<nav class="navbar navbar-expand-lg navbar-light bg-light ">
		<a class="navbar-brand" href="/">Help me App</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor03" aria-controls="navbarColor03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link" href="/">Home <!-- 	<span class="sr-only">(current)</span> -->
				</a></li>
				<c:if test="${ not empty user }">
					<li class="nav-item"><a class="nav-link" href="/userpro">Profile</a>
					</li>

					<li class="nav-item"><a class="nav-link" href="/logout">Log
							out</a></li>


					<li class="nav-item"><a class="nav-link"
						href="/helplist?selection=All Services">Organizations</a></li>
				</c:if>

			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary "
		id="navbarColor01">
		<div class="form-inline">

			<c:if test="${ empty user }">

				<a
					href="https://accounts.google.com/o/oauth2/v2/auth?scope=profile email&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&response_type=code&client_id=855747263310-23km4ggb7ckj25amm0hvpaedag4t6ur6.apps.googleusercontent.com"
					class="btn btn-secondary" role="button" style="margin-right: 2%;">

					Sign in with Google </a>

			</c:if>

			<c:if test="${ empty user }">

				<form method="post" action="/">
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
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		id="navbarColor01">
		<div class="container">


			<c:if test="${ not empty user }">

				<b style="color: white;"> Welcome ${ user.firstName }</b>



			</c:if>
		</div>
	</nav>
</header>
<body>
	<div class="container" style="padding-top: 1%; text-align: center;">
		<h1>Welcome to Help Me App!</h1>

		<c:if test="${ empty user }">
			<h2>
				<a href="/signup">Sign up!</a>
			</h2>

		</c:if>
	</div>


	<div class="container" style="padding-top: 1%; text-align: center;">
		<h3>Help me with</h3>
<div class="form-group">
		<form action="/helplist?selection=${selection}">

			<div class="mx-auto form-inline" style="width: 600px;">

				<p class="mr-2">
					City: <select name="selection" value="All Cities">

						<option value="All Cities">All Cities</option>
						<option value="Detroit">Detroit</option>
						<option value="Ann Arbor">Ann Arbor</option>
						<option value="lansing">Lansing</option>
						<option value="Kalamazoo">Kalamazoo</option>
						<option value="Dearborn">Dearborn</option>
						<option value="Bay City ">Bay City</option>
						<option value="Port Huron">Port Huron</option>
						<option value="Grand Rapids">Grand Rapids</option>
						<option value="Traverse City">Traverse City</option>
						<option value="Saginaw">Saginaw</option>
						<option value="Mackinaw City">Mackinaw City</option>
						<option value=" Muskegon">Muskegon</option>
						<option value=" Iron Mountain">Iron Mountain</option>
						<option value=" Sault Ste Marie">Sault Ste Marie</option>

					</select>
				</p>



				<p class="mr-2">
					Services: <select name="selection" value="All Services">

						<option value="All Services">All Services</option>
						<option value="Credit Repair">Credit Repair</option>
						<option value="Homelessness">Homelessness</option>
						<option value="Mortgage Payments">Mortgage Payments</option>
						<option value="Reverse Mortgages">Reverse Mortgages</option>
						<option value="Renting a Home">Renting a Home</option>
						<option value="Buying a Home">Buying a Home</option>
						<option value="Home Improvements">Home Improvements</option>
						<option value="Preditory Lending">Predatory Lending</option>
						<option value="CAA Services">CAA Services</option>
					</select>
					<button type="submit" class="btn btn-primary" class="ml-2">Search</button>
				</p>
			</div>
		</form>
		<p class="mr-2">Or</p>

		<form action="/helplist?selection=${selection}" class="form-inline">
			<div class="mx-auto form-inline" style="width: 600px;">
				<p class="mr-2">
					Search By: <select name="selection" value="Orgs">

						<option value="All Organiztions">All Organizations</option>
						<option value="Salvation Army">Salvation Army</option>
						<option value="Focus Hope">Focus Hope</option>

					</select>
					<button type="submit" class="btn btn-primary" class="ml-2">Search</button>
				</p>
			</div>
		</form>
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<br>
	<br>
</body>

<footer class="page-footer font-small bg-primary pt-4">
	<div class="container-fluid text-center text-sm-left">

		<!-- Grid row -->
		<div class="row">

			<!-- Grid column -->
			<div class="col-md-6 mt-md-0 mt-3">

				<!-- Content -->
				<h5 class="text-uppercase">Contact Us</h5>
				<p></p>

			</div>


		</div>


	</div>


	<!-- Copyright -->
	<div class="footer-copyright text-center py-3 bg-white">© 2019
		Copyright:</div>
	<!-- Copyright -->

</footer>
</html>