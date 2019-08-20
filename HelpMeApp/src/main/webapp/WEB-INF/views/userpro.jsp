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

<nav  class="navbar navbar-expand-lg navbar-dark bg-primary" id="navbarColor01" >

	
	<c:if test="${ not empty user }">
		Welcome ${ user.firstName }
		<p><a href="/helplist" id="orglist"> Organizations</a></p>
		<p><a href="/logout" id="logout">Log out</a></p>
	</c:if>
</nav>
</header>
<body>
<div class=" waraper" style="padding-top: 5%;"> 
	<div class="container">
		<div class="row">

			<div class="col-sm-4">
			 <div class="card bg-default" style="width: inherit;">
			 <div class= "card-body">
			 <h3> User name here</h3>
			 <img alt=" user img " src=" ">
			 <p><p>
			 
			 </div>
			 
			 
			 </div>
			
			</div>

			<div class="col-sm-8">
			
			<div class="messagehist">
			
			
			</div>
		
			<div class="messages">

						<form action="/autorepo?id=${id}" method="post">
							<p></p>

							<textarea name="message" rows="12 " cols="20">
		



  
</textarea>

							<button class="btn btn-outline-info" type="submit">Send</button>
						</form>

					</div>
			
			</div>
			</div>

		</div>
	</div>

</body>
<footer> </footer>
</html>