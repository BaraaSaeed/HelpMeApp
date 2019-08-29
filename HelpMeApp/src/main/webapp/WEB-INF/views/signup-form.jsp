<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


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
					
					<li class="nav-item"><a class="nav-link"
						href="/helplist?city=${user.city}&state=${user.state}&service=${service}&orgSelection${orgSelection}">Organizations</a></li>
				</c:if>

			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		id="navbarColor01">
		<div class="container">


			<c:if test="${ not empty user }">

				<b style="color: white;">Welcome ${ user.firstName }</b>



			</c:if>
		</div>
	</nav>
</header>

<body>

<p style="background-color:LightSalmon;">${message }</p>

	<h3 style="padding-top: 2%; text-align: center;">Please fill the
		form to register!</h3>
	



	<div class="container" style="width: 600px">

		<form action="/signup-confirmation" autocomplete="off">

			<div class="form-group">
				<label for="firstName">First Name:</label> <label style="color:orange; font-size: 20px;"> *</label><input type="text"
					class="form-control" id="firstName" placeholder="Martha"
					name="firstName" required>

			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label><label style="color:orange; font-size: 20px;"> *</label> <input type="text"
					class="form-control" id="lastName" placeholder="Kent"
					name="lastName" required>

			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone:</label> <input type="text"
					class="form-control" id="phoneNumber" placeholder=""
					name="phoneNumber">

			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					class="form-control" id="address" placeholder=""
					name="address">

			</div>
			

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="city">City</label><label style="color:orange; font-size: 20px;"> *</label> <input type="text"
						class="form-control" id="city" name="city" required>
				</div>
				<div class="form-group col-md-4">
					<label for="inputState">State</label><label style="color:orange; font-size: 20px;"> *</label> <select name="state" id="inputState"
						class="form-control " required>
						<option selected> </option>
						<option> Alabama </option>
						<option>Alaska   </option>
						<option> Arizona  </option>
						<option> Arkansas  </option>
						<option> California  </option>
						<option>Colorado  </option>
						<option> Connecticut </option>
						<option> Delaware  </option>
						<option> Florida  </option>
						<option> Georgia  </option>
						<option> Hawaii  </option>
						<option> Idaho  </option>
						<option> Illinois </option>
						<option> Iowa  </option>
						<option> Kansas  </option>
						<option> Kentucky  </option>
						<option> Maine  </option>
						<option> Maryland  </option>
						<option> Massachusetts  </option>
						<option> Michigan  </option>
						<option> Minnesota  </option>
						<option> Mississippi  </option>
						<option> Missouri  </option>
						<option> Montana </option>
						<option> Nebraska  </option>
						<option>  Nevada  </option>
						<option> New Hampshire  </option>
						<option>  New Jersey  </option>
						<option>  New Mexico  </option>
						<option> New York  </option>
						<option>  North Carolina </option>
						<option>  North Dakota  </option>
						<option> Ohio  </option>
						<option>  Oklahoma  </option>
						<option>  Oregon  </option>
						<option> Pennsylvania </option>
						<option> Rhode Island  </option>
						<option> South Carolina  </option>
						<option> South Dakota  </option>
						<option> Tennessee  </option>
						<option> Texas  </option>
						<option>  Utah </option>
						<option> Vermont  </option>
						<option> Virginia   </option>
						<option> Washington  </option>
						<option> West Virginia  </option>
						<option> Wisconsin  </option>
						<option>Wyoming</option>
					</select>
					
				</div>
				<div class="form-group col-md-2">
					<label for="zip">Zip</label> <input type="text"
						class="form-control" name="zip" id="zip" >
				</div>
			</div>
	
	<div class="form-group">

		<label for="email">Email:</label> <label style="color:orange; font-size: 20px;"> *</label> <input type="text"
			class="form-control" id="email" placeholder="username@gc.com"
			name="email" pattern=".+@.+" required> 
		<div class="valid-feedback">Valid.</div>
		<div class="invalid-feedback">Please provide only a e-mail
			address.</div>
	</div>

	<div class="form-group">
		<label for="password">Password:</label> <label style="color:orange; font-size: 20px;"> *</label><input type="password"
			class="form-control" id="password" placeholder="Enter password"
			name="password" required>

	</div>


	<button type="submit" class="btn btn-secondary">Submit</button>

	</form>
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