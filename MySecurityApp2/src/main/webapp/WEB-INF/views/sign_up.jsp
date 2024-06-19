<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>    
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Sing up Here</h1>
<form:form action="process-signup" method="POST" modelAttribute="signupdto">

Username : <input type="text" path="username" name="username"/> <br>

Password : <input type="password" path="password" name="password"/> <br>

Enabled : <input type="text" path="enabled" name="enabled"/> <br>

<input type="submit" value="Signup"/>


</form:form>
</body>
</html>