<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HelpMeApp Home</title>
</head>
<body>
<h1>Welcome to HelpMe! </h1>

<c:forEach items="${organizations}" var="org">

${org.email}

</c:forEach>
</body>
</html>