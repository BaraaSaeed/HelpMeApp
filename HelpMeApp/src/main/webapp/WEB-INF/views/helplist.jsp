<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Help List</title>
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
					<li class="nav-item"><a class="nav-link" href="/helplist">Organizations</a>
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
<div style="padding-top: 2%; text-align: center;"> 

<h2 > Here are the services near you  </h2>

<div class="dropdown">

 <button type="button" name= "selection" class="btn btn-primary dropdown-toggle " data-toggle="dropdown">
     Refine Search
  </button>
  
  <div class="dropdown-menu" >
  
    <a class="dropdown-item" name= "selection" href="helplist"> Budgeting and Credit Repair </a>
    <a class="dropdown-item" name= "selection" href=" helplist"> Homeless Assistance </a>
    <a class="dropdown-item" name= "selection" href="helplist "> Mortgage Payment Workshop </a>
    
  </div>
</div>
</div> 


<br>
<c:forEach items="${organizations}" var="org">
<ul >

<li ><a href="/autorepo?id=${org.agcid} " >${org.nme}</a> <br> 
<p>${org.city} </p>
<p>Address: ${org.adr1} ${org.adr2}</p>
<p>Phone: ${org.phone1} </p>

 <a href="/autorepo?id=${org.agcid}&nme=${org.nme } "><button type="button" class="btn btn-outline-info">Help</button></a></li>
		</ul>
	</c:forEach>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
<footer> </footer>
</html>