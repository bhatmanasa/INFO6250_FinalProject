<%-- 
    Document   : post-page
    Created on : Apr 16, 2021, 8:39:31 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .topnav input{
   float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
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
.topnav input{
   float: left;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
    background-color: #ddd;
  color: black;
}
        </style>
    </head>
    <body>
                              <div class="topnav">
            <form action="LoginBackPage.htm" method="post">
   <input type='submit' value="Homepage" />
    </form>
  <a href="About.htm">About</a>
</div>
            <form:form method="post" modelAttribute="post" action="PostPage.htm" enctype="multipart/form-data">
<table border="0" width="90%" class="table-style">
    <tr>Post something Now!</tr>
    <tr>Description:</tr><tr><form:input type="text" path="description"/></tr>
        <tr><td>Upload a Photo:<form:input type="file" path="postFile" name="postFile"/></td></tr>
        <tr><input type="submit" value="Post!"></tr>
</table>
                 </form:form>
    </body>
</html>
