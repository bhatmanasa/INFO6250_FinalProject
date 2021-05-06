<%-- 
    Document   : user-login
    Created on : Mar 31, 2021, 3:26:31 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WeConnect Page</title>
                <style>
.error-message {
   color: red;
   font-size:90%;
   font-style: italic;
}
.table-style {
  text-align: center;
  color: darkblue;
}
.topnav {
  background-color: #333;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: #4f46a5;
  color: white;
}
* { box-sizing: border-box; }

.topnav input{
   float: left;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
    background-color: #ddd;
  color: black;
}
body {
  font-family: 'Rubik', sans-serif;
}

.container {
  display: flex;
  height: 100vh;
}

.left {
  overflow: hidden;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  animation-name: left;
  animation-duration: 1s;
  animation-fill-mode: both;
  animation-delay: 1s;
}

.right {
  flex: 1;
  background-color: black;
  transition: 1s;
  background-image: url(https://images.unsplash.com/photo-1550745165-9bc0b252726f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}



.header > h2 {
  margin: 0;
  color: #4f46a5;
}

.header > h4 {
  margin-top: 10px;
  font-weight: normal;
  font-size: 15px;
  color: rgba(0,0,0,.4);
}

.form {
  max-width: 80%;
  display: flex;
  flex-direction: column;
}

.form > p {
  text-align: right;
}

.form > p > a {
  color: #000;
  font-size: 14px;
}

.form-field {
  height: 46px;
  padding: 0 16px;
  border: 2px solid #ddd;
  border-radius: 4px;
  font-family: 'Rubik', sans-serif;
  outline: 0;
  transition: .2s;
  margin-top: 20px;
}

.form-field:focus {
  border-color: #0f7ef1;
}

.form > button {
  padding: 12px 10px;
  border: 0;
  background: linear-gradient(to right, #de48b5 0%,#0097ff 100%); 
  border-radius: 3px;
  margin-top: 10px;
  color: #fff;
  letter-spacing: 1px;
  font-family: 'Rubik', sans-serif;
}

.animation {
  animation-name: move;
  animation-duration: .4s;
  animation-fill-mode: both;
  animation-delay: 2s;
}

.a1 {
  animation-delay: 2s;
}

.a2 {
  animation-delay: 2.1s;
}

.a3 {
  animation-delay: 2.2s;
}

.a4 {
  animation-delay: 2.3s;
}

.a5 {
  animation-delay: 2.4s;
}

.a6 {
  animation-delay: 2.5s;
}
table{
    align: center;
}
@keyframes move {
  0% {
    opacity: 0;
    visibility: hidden;
    transform: translateY(-40px);
  }

  100% {
    opacity: 1;
    visibility: visible;
    transform: translateY(0);
  }
}

@keyframes left {
  0% {
    opacity: 0;
    width: 0;
  }

  100% {
    opacity: 1;
    padding: 20px 40px;
    width: 500px;
  }
}
</style>
    </head>
    
    <body>
<!--           <div class="topnav">
            <form action="LoginBackPage.htm" method="post">
   <input type='submit' value="Homepage" />
    </form>
  <a href="About.htm">About</a>
</div>-->

<div class="container">
  <div class="left">
    <div class="header">
      <h2 class="animation a1">Welcome to WeConnect!</h2>
      <h4 class="animation a2">Log in to your account using email and password</h4>
    </div>
      
    <form:form action="LoginPage.htm" modelAttribute="userLoginForm">
        <table>
        <tr><td><form:input path="email" class="form-field animation a3" placeholder="Email Address"/></td>
            <td><form:errors path="email" class="error-message"/></td></tr>
        <tr><td><form:input type="password" path="password" class="form-field animation a4" placeholder="Password" /></td>
            <td><form:errors path="password" class="error-message"/></td></tr>
        
       
      </table>
        <table>
             <tr><button class="animation a6" type="submit" value="Login!">LOGIN</button></tr>
        </table>
          <p class="animation a5">New to WeConnect? <a href="StartPage.htm">Register Now!</a></p>
      </form:form>  
    </div>
  </div>
  <div class="right"></div>
</div>

    <br><br>New to WeConnect? <a href="StartPage.htm">Register Now!</a>
    </body>
</html>
