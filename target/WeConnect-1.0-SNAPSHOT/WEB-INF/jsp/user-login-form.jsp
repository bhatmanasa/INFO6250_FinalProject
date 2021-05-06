<%-- 
    Document   : user-login-form
    Created on : Mar 19, 2021, 12:07:41 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LoginPage-WeConnect</title>
    </head>
    <body>
        <h1>Welcome to WeConnect</h1>
        <h3>Please login below:</h3>
        <h3>${requestScope.error}</h3>
        <form:form modelAttribute="user">
            Username(Email ID): <form:input path="email"/>
            <br>
            Password: <form:input path="password"/>
            <br>
            <br><br><!-- comment -->
            <input type="submit" value="ADD">
        </form:form>    
    </body>
</html>
