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
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/">Help me App</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor03" aria-controls="navbarColor03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="/">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/userpro">Profile</a>
				</li>
				

			</ul>
		</div>
	</nav>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		id="navbarColor01">


		<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		<p>
				<a href="/helplist">Display Organizations</a>
			</p>
			<p>
				<a href="/logout">Log out!</a>
			</p>
		</c:if>
	</nav>
</header>

<body>
	<h3 style="padding-top: 2%; text-align: center;">Please fill the
		form to register!</h3>
	<div class="container" style="width: 600px">
	
   <form   action="/signup-confirmation" autocomplete="off"  >
	<div class="form-group">
    <label for="">First Name:</label>
    <input type="text" class="form-control" id="firstname" placeholder="Martha" name="firstName" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
	<div class="form-group">
    <label for="">Last Name:</label>
    <input type="text" class="form-control" id="lastname" placeholder="Kent" name="lasrname" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
	<div class="form-group">
    <label for="">Phone:</label>
    <input type="text" class="form-control" id="phone" placeholder="" name="phone" >
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
	
	
	
	
	
	
		
			<p>First Name:
				<input type="text" name="firstName" required />
				Last Name:
				<input type="text" name="lastName" required />
			</p>
			<p>Phone Number:
				<input type="text" name="phoneNumber" />
			
			Address:
		
				<input type="text" name="address" required />
			</p>
			<p>Zip Code:
				<input type="text" name="zip" />
			
			City:
		
				<input type="text" name="city" required />
			</p>
			<p>Email:</p>
			<p>
			<input type="email" name="email" placeholder="username@gc.com"
				pattern=".+@.+"
				title="Please provide only a Best Startup Ever corporate e-mail address" />
			</p>
			<p>
				Password:</p> <p> <input type="password" name="password" required />
			</p>
			<p>
				<button type="submit">Submit</button>
			</p>
		</form>
	</div>

</body>
<footer> </footer>
</html>