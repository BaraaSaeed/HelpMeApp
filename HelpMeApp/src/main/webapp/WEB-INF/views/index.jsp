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


			

				</c:if>

			</ul>


		</div>
	</nav>
	<div class="shadow-lg p-1 mb-3 bg-primary">
		<nav class="navbar navbar-expand-sm navbar-dark   bg-primary  "
			id="navbarColor01" style="">

			<div class="form-row align-items-center">
			<div class="form-inline">

					<c:if test="${ empty user }">

						<form method="post" action="/">
							<div class="form-row align-items-center">
								<div class="col-auto">
									<input class=" form-group " type="email" name="email"
										placeholder="email" required>
								</div>
								<div class="col-auto">
									<input class=" form-group " type="password" name="password"
										placeholder="password" required>
								</div>
								<div class="col-auto">
									<button type="submit" class="btn btn form-group " style=" color:white; background-color: #FFA500;">Log
										in</button>
								</div>

							</div>
							
						</form>
						
					</c:if>

					<c:if test="${ empty user }">
					
						<div class="col-auto">

							<c:url var="googleLoginUrl"
								value="https://accounts.google.com/o/oauth2/v2/auth">
								<c:param name="scope" value="profile email" />
								<c:param name="access_type" value="offline" />
								<c:param name="include_granted_scopes" value="true" />
								<c:param name="state" value="state_parameter_passthrough_value" />
								<c:param name="redirect_uri" value="${host}/callback" />
								<c:param name="response_type" value="code" />
								<c:param name="client_id"
									value="855747263310-23km4ggb7ckj25amm0hvpaedag4t6ur6.apps.googleusercontent.com" />
							</c:url>

							<a href="${ googleLoginUrl }" class="btn btn" style=" color:white; background-color: #FFA500;"
								role="button" > Google Sign-in</a>


						</div>

					</c:if>
			</div>
			</div>
		</nav>
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
	<div class="container" style="padding-top: 1%; text-align: center;">
		<h1>Welcome to HelpMe App!</h1>

		<c:if test="${ empty user }">
			<h2>
				<a href="/signup">Sign up!</a>
			</h2>

		</c:if>
	</div>



	<div class="container" style="padding-top: 1%; text-align: center;">
		<h3>Help me with....</h3>



			
			
			
 
 
 
			</form>

		<form
			action="/helplist?city=${city}&state=${state}&service=${service}&orgSelection${orgSelection}">

			<div class="form-row align-items-center ml-5">

				<div class="col-auto">
					<select name="city" id="city" class="form-control ">

						<c:if test="${ not empty user }">
							<option selected>${user.city}</option>
						</c:if>
						<!-- <option selected>City</option> -->
							<option>City</option>
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
						<option value="Muskegon">Muskegon</option>
						<option value="Iron Mountain">Iron Mountain</option>
						<option value="Sault Ste Marie">Sault Ste Marie</option>

					</select>
				</div>

				<div class="col-auto">
					<select name="state" id="inputState" class="form-control ">

						<c:if test="${ not empty user }">
							<option selected>${user.state}</option>
						</c:if>

						<!-- <option selected>State</option> -->
							<option>State</option>
						<option>Alabama</option>
						<option>Alaska</option>
						<option>Arizona</option>
						<option>Arkansas</option>
						<option>California</option>
						<option>Colorado</option>
						<option>Connecticut</option>
						<option>Delaware</option>
						<option>Florida</option>
						<option>Georgia</option>
						<option>Hawaii</option>
						<option>Idaho</option>
						<option>Illinois</option>
						<option>Iowa</option>
						<option>Kansas</option>
						<option>Kentucky</option>
						<option>Maine</option>
						<option>Maryland</option>
						<option>Massachusetts</option>
						<option>Michigan</option>
						<option>Minnesota</option>
						<option>Mississippi</option>
						<option>Missouri</option>
						<option>Montana</option>
						<option>Nebraska</option>
						<option>Nevada</option>
						<option>New Hampshire</option>
						<option>New Jersey</option>
						<option>New Mexico</option>
						<option>New York</option>
						<option>North Carolina</option>
						<option>North Dakota</option>
						<option>Ohio</option>
						<option>Oklahoma</option>
						<option>Oregon</option>
						<option>Pennsylvania</option>
						<option>Rhode Island</option>
						<option>South Carolina</option>
						<option>South Dakota</option>
						<option>Tennessee</option>
						<option>Texas</option>
						<option>Utah</option>
						<option>Vermont</option>
						<option>Virginia</option>
						<option>Washington</option>
						<option>West Virginia</option>
						<option>Wisconsin</option>
						<option>Wyoming</option>
					</select>

				</div>


				<div class="col-auto">
					<select name=service id="service" class="form-control ">
							
							<option value="All Services">All Services</option>
							<option value="clothing">Clothing</option>
							<option value="credit and debt">Credit and Debt</option>
							<option value="education">Education</option>	
							<option value="food">Food</option>
							<option value="health">Health</option>
							<option value="homelessness">Homelessness</option>
							<option value="housing">Housing</option>
							<option value="immigration">Immigration</option>
							<option value="jewish person">Jewish Person</option>
							<option value="legal issues">Legal</option>
							<option value="LGBTQ person">LGBTQ</option>
							<option value="parent">Parents</option>
							<option value="person of color">Persons of Color</option>
							<option value="senior">Seniors</option>
							<option value="veteran">Veterans</option>
							<option value="woman">Women</option>
							<option value="work">Work</option>
							<option value="young adult">Youth</option>

					
					</select>
				</div>


				<div class="col-auto">
					<select name="orgSelection" id="services" class="form-control ">

							<option value="All Organizations">All Organizations</option>
							<option value="american council of the Blind">American Council of the Blind</option>
							<option value="american veterans Relief Foundation">American Veterans Relief Foundation</option>
							<option value="assistance league">Assistance League</option>
							<option value="catholic charities">Catholic Charities</option>
							<option value="catholic social services">Catholic Social Services</option>
							<option value="children's health fund">Children's Health Fund</option>
							<option value="community action association">Community Action Associations</option>
							<option value="diaper distribution network">Diaper Distribution Network</option>
							<option value="focus hope">Focus Hope</option>
							<option value="friendship house">Friendship House</option>
							<option value="goodwill industries">Goodwill Industries</option>
							<option value="habitat for humanity">Habitat for Humanity</option>
							<option value="head start programs">Head Start</option>
							<option value="health and human services">HHS - Health and Human Services</option>
							<option value="housing and urban development">HUD - Housing and Urban Development</option>
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


				</div>


			</div>

			<div class="col-auto mt-2">
				<button type="submit" class="btn btn-primary">Search</button>

			</div>
	</div>

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