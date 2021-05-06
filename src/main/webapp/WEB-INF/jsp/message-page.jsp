<%-- 
    Document   : message-page
    Created on : Apr 22, 2021, 2:01:50 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message Page</title>
        <style>
         .table-style {
  text-align: center;
  color: darkblue;
}  
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
  <a href="About.htm">About</a>
</div>
        <h1>Start Messages</h1>

        
                
             
              <c:forEach items="${requestScope.friends}" var="users"> 
                  <table border="1" width="90%" class="table-style">
                   <form action="AddMessage.htm" method="post">
                  <tr><td>
                  <label>${users.firstName} ${users.lastName} (${fn:length(users.messages)}) </label>
                      </td><td>
                              <input type="hidden" name="fId" value="${users.userId}" />
                  <input type="hidden" name="searchId" value="${users.userId}" /><br>
                    <input type='submit' value="Start Messaging" /></td>
                    </tr>
                         </table>
                     </form>
              </c:forEach>  
     
                           
           

 
    </body>
</html>
