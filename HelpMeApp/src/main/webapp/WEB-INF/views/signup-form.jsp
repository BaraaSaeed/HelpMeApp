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
				<li class="nav-item "><a class="nav-link" href="/">Home
					<!-- 	<span class="sr-only">(current)</span> -->
				</a></li>
				<c:if test="${ not empty user }">
				<li class="nav-item"><a class="nav-link" href="/userpro">Profile</a>
				</li>
				<li class="nav-item"> <a class="nav-link" href="/helplist">Organizations</a>
	
				</li>
</c:if>
			</ul>
		</div>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary" id="navbarColor01">
	<div >
	

		<c:if test="${ not empty user }">
		<div>
		<b>Welcome ${ user.firstName }</b>
	    <a class="nav-link "  id="logout" href="/logout">Log out </a>
		</div>	

		</c:if>
		</div>
	</nav>
</header>

<body>

	<h3 style="padding-top: 2%; text-align: center;">Please fill the
		form to register!</h3>
	<div class="container" style="width: 600px">
	
   <form    action="/signup-confirmation" autocomplete="off"  >
   
	<div class="form-group">
    <label for="firstName">First Name:</label>
    <input type="text" class="form-control" id="firstName" placeholder="Martha" name="firstName" required>
    
  </div>
	<div class="form-group">
    <label for="lastName">Last Name:</label>
    <input type="text" class="form-control" id="lastName" placeholder="Kent" name="lastName" required>
   
  </div>
	<div class="form-group">
    <label for="phoneNumber">Phone:</label>
    <input type="text" class="form-control" id="phoneNumber" placeholder="" name="phoneNumber" >
    
  </div>
<div class="form-group">
    <label for="address">Address:</label>
    <input type="text" class="form-control" id="address" placeholder="" name="address" >
  
  </div>
	<div class="form-inline" class="mr-md-5 bg-warning">
    <label for="zip">Zip:</label>
    <input type="number" class="form-control" id="zip" placeholder="" name="zip" >
    
	
    <label for="city" >City:</label>
    <input type="text" class="form-control" id="city" placeholder="Smallville" name="city" >
    
  </div>
		
	<div class="form-group">
    <label for="email">Email:</label>
    <input type="text" class="form-control" id="email" placeholder="username@gc.com" name="email" pattern=".+@.+">
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please provide only a e-mail address.</div>
  </div>
	<div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
    
  </div>
	
	
	
			
				<button type="submit" class="btn btn-secondary">Submit</button>
	
		</form>
	</div>

</body>
<footer> </footer>
</html>