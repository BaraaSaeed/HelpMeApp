<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>User Profile</title>
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
				<li class="nav-item"><a class="nav-link" href="/logout">Log out</a>
				</li>
				
				<li class="nav-item"> <a class="nav-link" href="/helplist">Organizations</a>
	
				</li>
</c:if>
			</ul>
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
<div class=" waraper" style="padding-top: 5%;"> 
	<div class="container">
		<div class="row">

			<div class="col-sm-4">
			 <div class="card bg-default" style="width: inherit;">
			 <div class= "card-body">
			 <h3> ${user.firstName}</h3>
			 <img alt=" user img " src=" https://image.flaticon.com/icons/svg/0/126.svg ">
			 <p>Phone: ${user.phoneNumber} <p>
			 <p>Address: ${user.address} <p>
			 <p>City:  ${user.city}<p>
			 
			 </div>
			 
			 
			 </div>
			
			</div>

			<div class="col-sm-8">
			
			<div class="messagehist">
			
			<c:forEach var="each" items="${orgSet}">

					<a href="/user-message-detail?orgId=${each.key}" >${each.value}</a>

				</c:forEach>
		<div>
		<b>Welcome ${ user.firstName }</b>
	    <a class="nav-link "  id="logout" href="/logout">Log out </a>
		</div>	

	
			
			</div>
		
			<div class="messages">

						<form action="/autorepo?id=${id}" method="post" >
							<p></p>

							<textarea name="message" rows="12 " cols="20">
		



  
</textarea>

							<button class="btn btn-outline-secondary" type="submit">Send</button>
						</form>

					</div>
			
			</div>
			</div>

		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
<footer> </footer>
</html>