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



<div align="center">
<h1> Reset Password</h1>

<c:if test="${param.notMatched !=null}">
 <i style="color:red;"> New password and Confirm password don't match</i>
</c:if>

<c:if test="${param.invalidPassword !=null}">
 <i style="color:red;"> Invalid old password</i>
</c:if>

<form:form modelAttribute="password-chng" method="POST" action="save-password"> 

 <label> Old Password</label>
 <form:input path="oldPassword"/>
  <br/>
  <label> New Password</label>
 <form:input path="newPassword"/>
 <br/>
  <label> Confirm Password</label>
 <form:input path="confirmPasword"/>
 <br/>
 <input type="submit" value="Update Password"/>
</form:form>

</div>
</body>
</html>