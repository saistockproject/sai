<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html  xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
	<title>Home</title>
</head>
<body>
<h1> 
	Stock Prediction
</h1>   
<P>  The time on the server is ${serverTime}. </P>
<div> <c:redirect url="/dashboard"></c:redirect> </div>
</body>
</html>
