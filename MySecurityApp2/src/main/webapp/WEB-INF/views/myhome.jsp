<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Hello ${username} Welcome to Home Page and have role ${roles}</h1>

<sec:authorize access='hasAuthority("Trainer")'>
<a href="${pageContext.request.contextPath}/trainer">Trainer Dash-board</a>
</sec:authorize>
 <br>

<sec:authorize access='hasAuthority("Coder")'>
<a href="${pageContext.request.contextPath}/coder">Coder Dash-board</a>
</sec:authorize>

<br> 


<a href="/MySecurityApp/deleteUser?username=${username}"> Delete Account</a> <br> <br>

<a href="/MySecurityApp/editUser?username=${username}"> Edit Account Details</a> <br> <br>

<a href="/MySecurityApp/changePassword"> Change Password</a> <br>

<form:form action="logout" method="post">
 <input type="submit" value="logout"/>
</form:form>
</body>
</html>