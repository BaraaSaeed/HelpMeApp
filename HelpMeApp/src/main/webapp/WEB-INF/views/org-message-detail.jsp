<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class=" waraper" style="padding-top: 5%;">
		<div class="container">
			<div class="row">

				<div class="col-sm-4">
					<div class="card bg-default" style="width: inherit;">
						<div class="card-body">
							<h3>Org Name</h3>
							<img alt="org logo" src="https://www.designevo.com/res/templates/thumb_small/overlapping-hand-and-charity.png">
							<p>
							<p>
						</div>


					</div>

				</div>

				<div class="col-sm-8">
					<c:forEach var="messageHistory" items="${message}">
					<div class="messagehist"></div>
					${messageHistory }
					</c:forEach>
					<div class="messages">
				
					


						<form action="/org-message-detail"  method="post">
							<input type=hidden value="${lastMessage.messageId}" name="messageId"/>
								<input type=hidden value="${orgId}" name="orgId"/>
						
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

</body>
</html>