<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
				<li class="nav-item "><a class="nav-link" href="/">Home
					<!-- 	<span class="sr-only">(current)</span> -->
				</a></li>
				<c:if test="${ not empty user }">
				<li class="nav-item"><a class="nav-link" href="/userpro">Profile</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/logout" >Log out</a>
				</li>
				
				<li class="nav-item"> <a class="nav-link" href="/helplist?selection=All Services">Organizations</a>
	
				</li>
</c:if>
			</ul>
		</div>
	</nav>
	<div class="shadow-lg p-1 mb-5  bg-primary">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="navbarColor01">
	<div class="container">
	

		<c:if test="${ not empty user }">

	<b style="color:white;">Welcome ${ user.firstName }</b>
		


		</c:if>
		</div>
	</nav>
	</div>
</header>

<meta charset="ISO-8859-1">
<title>Address</title>
</head>
<body>
<p>The address is: ${address }</p>
</body>
<footer class="page-footer font-small bg-primary pt-4">
	<div class="container-fluid text-center text-sm-right">

		<!-- Grid row -->
		<div class="row">

			<!-- Grid column -->
			<div class=" container ">

				<!-- Content -->

				<h5 class="text-uppercase " style="color: white;">Contact Us:</h5>
				<div class="mx-auto form-inline float-right ">
					<p class="mr-2">
						<a href="https://www.linkedin.com/in/baraaali/"
							style="color: white;">Baraa Ali </a>
					</p>

					<p class="mr-2">
						<a href="https://www.linkedin.com/in/gerardbreitenbeck/"
							style="color: white;">Gerard Breitenbeck</a>
					</p>
					<p>
						<a href="https://www.linkedin.com/in/siennaharris/"
							style="color: white;">Sienna Harris </a>
					</p>
				</div>
			</div>


		</div>


	</div>


	<!-- Copyright -->
	<div class="footer-copyright text-center py-3 bg-white">© 2019
		Copyright:</div>
	<!-- Copyright -->

</footer>
</html>