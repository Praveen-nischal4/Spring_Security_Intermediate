<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.6.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>

<style>

#form1
{
  width :500px;
  margin:auto;
}

table {
 width:450px;
 margin:auto;
}
</style>

</head>
<body class="container-fluid text-center" style="background-color: #DAD3BE">

<!-- Error in custom login form  -->

<c:if test="${param.error !=null}">
 <i class="text-danger"> Invalid login or password</i>
</c:if>


<h1 class="mt-5 h1 fw-bold"> My Custom Login Window</h1>

<form:form action="process-login" method="post" class="mt-5" id="form1">

<table>

    <tr>
<td> Username :</td>
<td><input type="text" name="username" placeholder="Your Username" class="form-control p-2"/>  </td>
   </tr>
   
   <tr>
   <td> Password :</td>
   <td> <input type="password" name="password" placeholder="Your Password" class="form-control p-2  "/> </td>
   </tr>
   
   <tr> 
   <td colspan="2">  <input type="submit" value="Login Here" class="btn btn-warning btn-lg mt-3 p-1"/> </td>
   </tr>
</table>


 
 

</form:form> 

<!-- After successful logout custom message  -->

<c:if test="${param.logout !=null}">
 <i class="text-success"> Successfully Logout!!!!!!! You can sign in again.</i>
</c:if>


</body>
</html>