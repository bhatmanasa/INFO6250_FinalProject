<%-- 
    Document   : about-page
    Created on : Apr 30, 2021, 10:21:00 AM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Page</title>
        <style>
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
}
.topnav input{
   float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
.component{
    width:100%;
    height:100%;
}
table {
  table-layout: fixed;
  width: 100%;
  height: 100%;
  align: center;
  border-collapse: collapse;
  border: 1px solid purple;
}


th, td {
  padding: 20px;
}
.column {
  float: left;
  height:100%;
  margin-top: 25px;
}

.left, .right {
  width: 25%;
  background-color:#96ded1;
}

.middle {
  width: 50%;
}
        
        </style>
    </head>
    <body>
                     <div class="topnav">
            <form action="${pageContext.request.contextPath}/LoginBackPage.htm" method="post">
           <input type='hidden' name="userLogged" value="${sessionScope.user.userId}" />     
   <input type='submit' value="Homepage" />
    </form>
  <a href="About.htm">About</a>
</div>
        <br><br><br><!-- comment -->
        <p> WeConnect Social networking web application is created as a part of Web development Tools and Method final project</p>
        <br><br><br><p> This is created with Spring MVC and hibernate along with DAO implementation </p><!-- comment -->
        <br><br><br><p>Created By: Manasa Bhat </p>
        
        <br><br><p>Contact: bhat.ma@northeastern.edu </p>
    </body>
</html>
