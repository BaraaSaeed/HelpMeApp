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

	<nav class="navbar navbar-expand-lg navbar-light bg-light  ">
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
	<div class="shadow-lg p-0 mb-3 bg-primary">
		<nav class="navbar navbar-expand-md navbar-dark   bg-primary  "
			id="navbarColor01" style="">
			<div class="form-inline ">

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
	</div>
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
		<h3>Help me with....</h3>
		  <div class="row">
		  <div class=" col-sm"></div>
		<div class=" col-lg-8">
			<form
				action="/helplist?city=${city}&service=${service}&orgSelection${orgSelection}">

				<div class=" form-inline " >

					<p class="mr-2">
						City: <select name="city" value="All Cities">

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
						Services: <select name="service" value="All Services">

							<option value="All Services">All Services</option>
					<!-- 		<option value="Credit Repair">Credit Repair</option>
							
							<option value="Mortgage Payments">Mortgage Payments</option>
							<option value="Reverse Mortgages">Reverse Mortgages</option>
							<option value="Renting a Home">Renting a Home</option>
							<option value="Buying a Home">Buying a Home</option>
							<option value="Home Improvements">Home Improvements</option>
							<option value="Preditory Lending">Predatory Lending</option> -->
						
							<option value="Clothing">Clothing</option>
							<option value="DebtAndTemporaryAssistance">Debt</option>
							<option value="Education">Education</option>	
							<option value="Food">Food</option>
							<option value="Health">Health</option>
							<option value="Homeless">Homelessness</option>
							<option value="Housing">Housing</option>
							<option value="ImmigrantRefugee">Immigration</option>
							<option value="Jewish">Jewish</option>
							<option value="Legal">Legal</option>
							<option value="LGBTQ">LGBTQ</option>
							<option value="Parents">Parents</option>
							<option value="Senior">Seniors</option>
							<option value="Veteran">Veterans</option>
							<option value="Women">Women</option>
							<option value="Work">Work</option>
							<option value="Youth">Youth</option>
							
					
				
						</select>
					
				</p>
			
				<p class="mr-2"> 
						Search By: <select name="orgSelection" value="All Organizations">

							<option value="All Organizations">All Organizations</option>
							<option value="american council of the Blind">American Council of the Blind</option>
							<option value="american veterans Relief Foundation">American Veterans Relief Foundation</option>
							<option value="assistance league">Assistance League</option>
							<option value="catholic charities">Catholic Charities</option>
							<option value="catholic social Services">Catholic Social Services</option>
							<option value="children's Health Fund">Children's Health Fund</option>
							<option value="community action Association">Community Action Associations</option>
							<option value="community housing Network">Community Housing Network</option>
							<option value="dental lifeline Network">Dental Lifeline Network</option>
							<option value="diaper distribution network">Diaper Distribution Network</option>
							<option value="focus hope">Focus Hope</option>
							<option value="friendship house">Friendship House</option>
							<option value="goodwill industries">Goodwill Industries</option>
							<option value="habitat for humanity">Habitat for Humanity</option>
							<option value="head start programs">Head Start</option>
							<option value="health and human services">Health and Human Services</option>
							<option value="jewish family service">Jewish Family Service</option>
							<option value="jewish federation of north america">Jewish Federation of North America</option>
							<option value="legal aid and defender assoc">Legal Aid and Defender Association</option>
							<option value="lighthouse food and housing">Lighthouse Food and Housing</option>
							<option value="loveInc">Love INC</option>
							<option value="mission of mercy inc">Mission of Mercy Inc</option>
							<option value="my community dental care">My Community Dental Care</option>
							<option value="national jewish health">National Jewish Health</option>
							<option value="one-stop career center">One-Stop Career Center</option>
							<option value="open door outreach">Open Door Outreach</option>
							<option value="open hands pantry">Open Hands Pantry</option>
							<option value="operation homefront">Operation Homefront</option>
							<option value="rescue mission ministries">Rescue Mission Ministries</option>
							<option value="ruth ellis center">Ruth Ellis Center</option>
							<option value="salvation army">Salvation Army</option>
							<option value="senior alliance">Senior Alliance</option>
							<option value="st vincent de paul">St Vincent de Paul</option>
							<option value="the diaper bank">The Diaper Bank</option>
							<option value="the wellness plan">The Wellness Plan</option>
							<option value="united way">United Way</option>
							<option value="urban league">Urban League</option>
							<option value="veterans community resource and referral center">Veterans Community Resource and Referral Center</option>
							<option value="veterns services">Veterans Services</option>
							<option value="volunteers of america">Volunteers of America</option>
							<option value="ymca">YMCA</option>
							
							
						</select>
						
						</p>
						</div>
			
			
 <button type="submit" class="btn btn-primary" class="ml-2 p-7">Search</button>
 
 
			</form>
			</div>
			<div class=" col-sm"></div>
		
</div></div>

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
	
	<br>
	<br>
	<br>
	<br>
	
	
</body>


 <footer class="page-footer font-small bg-primary pt-2">
	<div class="container-fluid text-center text-sm-right">

	
		<div class="row">

		
			<div class=" container ">

			

				
				<div class="mx-auto form-inline float-right  " style="color: white; font-size: 12px;">
				<p class=" mr-2" > <strong> Contact Us:</strong></p>
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

	<div class="footer-copyright text-center py-3 bg-primary">

</footer> 
</html>