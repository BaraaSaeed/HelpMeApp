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
<title>Message</title>
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

<body>
<div class="container" > 

<h3>${org.name}</h3><br> 

<p>Address: ${org.formattedAddress} </p>
<p>Phone: ${org.phone} </p>
<p>
	<c:forEach var = "i" begin = "1" end = "${org.rating}">
	<span class="fa fa-star checked"></span>
	</c:forEach>
</p>
</div>

Services: 
<ul class="list-group">
<c:forEach items="${serviceList}" var="service">
  <li class="list-group-item">${service}</li>
</c:forEach>
</ul>

 

<div class="container">
<c:if test="${not empty org.email }">
<c:if test="${ not empty user.name}">
	<form action="/autorepo?apiId=${org.apiId}&selection=${selection}" method="post">
	
						<textarea name="content" rows="5 " cols="60">
 
</textarea>

<br>
							<button class="btn btn-outline-primary" type="submit">Send</button>
						</form>
</c:if>				
</c:if>
<c:if test="${empty user.name }">
Sign-in to send direct messages to Orgs.
</c:if>
</div>

<c:url value="https://www.google.com/maps/embed/v1/search" var="iframeUrl">
	<c:param name="q" value="${org.name } ${org.formattedAddress }" />
	<c:param name="key" value="${geoKey}" />
	
</c:url>

<iframe width="250" height="250" frameborder="0" style="border:0" src="${iframeUrl }" allowfullscreen></iframe>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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