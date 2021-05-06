<%-- 
    Document   : search-result
    Created on : Apr 10, 2021, 9:09:30 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
  color: white;
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

.topnav input{
   float: left;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
    background-color: #ddd;
  color: black;
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
  height:100vh;
}

.middle {
  width: 50%;
}
        
        </style>
    </head>
    <body>
                           <div class="topnav">
            <form action="${pageContext.request.contextPath}/LoginBackPage.htm" method="post">
                
   <input type='submit' value="Homepage" />
    </form>
  <a href="About.htm">About</a>
</div>
        <div class="column left">
            <br><br>${sessionScope.user.firstName}<br><br>
            ${sessionScope.user.country}
            <br><br><br><br>
            <a href="ProfPage.htm">Go to Profile</a><br><br>
           
        </div>
        <div class="column middle">  
                        <form action="SearchPage.htm" method='POST'>
     First Name: <input type="text" name="name"/>
     Place: <input type="text" name="place"/>
      <button  type="submit" value="Search!">Search!</button>
                        </form>
      
      
          <c:if test="${requestScope.users ne null}">  
             ${fn:length(requestScope.users)} Users found for the search...
        <table>
            <c:forEach items="${requestScope.users}" var="users">    
                <c:if test="${sessionScope.loginId ne $users.email}">  
                     <tr>
    <td><img width="300" src="<c:url value="/images/"/>${users.photo}"/><br></td>
    <td>
      <table>
        <tr><td>${users.firstName} ${users.lastName}</td></tr>
        <tr><td>${users.state}</td></tr>
        <tr><td>${users.country}</td></tr>
        <tr><td><a href='ViewOtherProfile/${users.userId}.htm'> View Profile </a></td></tr>
      </table>
     </td>
   </tr>
            </c:if>
            </c:forEach>
        </table>
       </c:if> 
        </div>
                          <div class="column right">

             <a href="Logout.htm">Logout</a><br><br><br>
                 <form action="SearchPage.htm" method='POST'>
     First Name: <input type="text" name="name"/>
     Place: <input type="text" name="place"/>
      <button  type="submit" value="Search People!">Search!</button>
      <form>  
            <div class="notif">
                <p>Notifications (${fn:length(requestScope.notifs)})</p>
                <table>
                    <c:if test="${fn:length(requestScope.notifs) ne 0}">
                 <c:forEach items="${requestScope.notifs}" var="notifs">  
                     <c:if test="${notifs.read eq 'no'}">
                     <tr><td>${notifs.description}</td><td>${notifs.dateTime}</td>
                     </tr>
                     </c:if>
                 </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(requestScope.notifs) eq 0}"> 
                        <p>No new notifications</p>
                    </c:if>    
                </table>
            </div>
            </div>
            
    </body>
</html>
