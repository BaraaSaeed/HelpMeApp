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
<meta charset="ISO-8859-1">
<title>User Profile</title>
</head>
<header>

	<nav class="navbar navbar-expand-lg navbar-light bg-light  ">
		<a class="navbar-brand" href="/"> <img alt="Help Me App"
			src="Help-Me-App-logo_02.png" style="width: 60px;"></a>
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
						href="/helplist?city=${user.city}&state=${user.state}&service=${service}&orgSelection${orgSelection}">Organizations</a></li>
				</c:if>
			</ul>


		</div>
	</nav>
	<div class="shadow-lg p-1 mb-3 bg-primary">
		<nav class="navbar navbar-expand-sm navbar-dark   bg-primary  "
			id="navbarColor01" style=""></nav>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
			id="navbarColor01">
			<div class="container">

				<c:if test="${ not empty user }">
					<div class="container-fluid text-center text-lg-left ">
						<p style="color: white;">Welcome ${ user.firstName }</p>

					</div>

				</c:if>

			</div>
			<div class="container-fluid text-center text-sm-right ">
				<div class="mx-auto form-inline float-right  "
					style="color: white; font-size: 12px;">

					<p class=" col-auto">
						<strong>Connecting you with people who can help</strong>
					</p>
				</div>
			</div>
		</nav>
	</div>
</header>

<body>
	<div class=" waraper" style="padding-top: 5%;">
		<div class="container">
			<div class="row">

				<div class="col-sm-4">
					<div class="card bg-default" style="width: inherit;">
						<div class="card-body">
							<h3>${user.firstName}</h3>
							<img alt=" user img "
								src=" https://image.flaticon.com/icons/svg/0/126.svg  "
								style="width: 80%">
							<p>Phone: ${user.phoneNumber}
							<p>
							<p>Address: ${user.address}
							<p>
							<p>City: ${user.city}
							<p>
						</div>


					</div>

				</div>

				<div class="col-sm-8">

					<div class="messagehist" class="overflow-auto">


						<c:forEach var="each" items="${messageHistory}">

							<div class="table-primary shadow-sm p-3 mb-5  rounded">
								<p>Message Sent: ${each.date }</p>
								<p>Subject: ${each.issue }</p>
								<p>Message:</p>
								<p>${each.content }</p>
							</div>
						</c:forEach>



					</div>

					<div class="messages">

						<form
							action="/autorepo?apiId=${apiId}&selection=${issue}&content=${contentString}"
							method="post">
							<textarea name="contentString" rows="5 " cols="60">
 
</textarea>
							<br>
							<button class="btn btn-outline-primary" type="submit">Send</button>


						</form>

					</div>

				</div>
			</div>

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


<footer class="page-footer font-small bg-primary pt-2">
	<div class="container-fluid text-center text-sm-right">


		<div class="row">


			<div class=" container ">




				<div class="mx-auto form-inline float-right  "
					style="color: white; font-size: 12px;">
					<p class=" mr-2">
						<strong> Contact Us:</strong>
					</p>
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

	<div class="footer-copyright text-center py-1 bg-primary"></div>

</footer>
</html>