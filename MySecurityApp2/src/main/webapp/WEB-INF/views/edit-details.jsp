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

     <form:form method="post" action="${pageContext.request.contextPath}/updateDetails">
     <table>
     <tr> 
       <td> <form:label path="username"></form:label> </td>
       <td> <form:input path="username" name="username"/> </td>
     </tr>
     
     <tr> 
       <td> <form:label path="password"></form:label> </td>
       <td> <form:input path="password" name="password"/> </td>
     </tr>
     
     <tr> 
       <td> <form:label path="enabled"></form:label> </td>
       <td> <form:input path="enabled" name="enabled"/> </td>
     </tr>
     
      <tr> 
       <td colspan="2"> <input type="submit" value="Update Details"/> </td>
     </tr>
     </table>
     
     
     </form:form>
</body>
</html>