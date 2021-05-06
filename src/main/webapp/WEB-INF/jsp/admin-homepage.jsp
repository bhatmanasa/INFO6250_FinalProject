<%-- 
    Document   : admin-homepage
    Created on : Apr 29, 2021, 8:45:39 PM
    Author     : Manasa
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WeConnect-HomePage</title>
  
        <style>
            body{
                height:100%;
                width:100%;
            }
            .topnav {
  background-color: #333;
  overflow: hidden;
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

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
#pageNav{
    display:none;
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
.newpost {
  background-color:yellow;
}
table {
  table-layout: fixed;
  width: 100%;
  height: 100%;
  align: center;
  border-collapse: collapse;
  border: 1px solid purple;
}


td {
  padding: 20px;
}
tr{
    padding: 5px;
}
notif{
    background-color:pink;
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
        <div class="component">
        <c:if test="${sessionScope.user ne null}">   
        <div class="column left">
            <br><br>${sessionScope.user.firstName}<br><br>
            ${sessionScope.user.country}
            <br><br><br><br>
            <a href="ProfPage.htm">Go to Profile</a><br><br>
            <a href="ViewReport.htm">View Report Profiles</a><br><br>
            <a href="ViewStats.htm">View Statistics</a><br><br><!-- <a href="Messages.htm">View Your Messages</a> -->

            
            
        </div>
       <div class="column middle">
                   <h1>Hey ${sessionScope.user.firstName}, Welcome Back to WeConnect HomePage!</h1>
             <c:if test="${requestScope.displayPage eq 'reports'}">
                     <c:forEach items="${requestScope.reportList}" var="reports">    
      
    
      <table>
          <tr><td>Reporter: ${reports.reporterProfile.firstName}  ${reports.reporterProfile.lastName}</td><td>Time:${reports.time} </td></tr>
        <tr><td>Reporting Profile:${reports.reportedProfile.firstName} ${reports.reportedProfile.lastName} </td></tr>
        <tr><td>Statement:${reports.statement}</td></tr>
      </table><!-- comment -->
      <table>
        <form>
                <input type='hidden' name="userLogged" value="${sessionScope.user.userId}" />  
   <input type='submit' value="Send Email" />
    </form>
      </table>
<!--               <form action="/EmailReport.htm" method="post">
                   <input type="hidden" name="reporterId" value="${reports.reportedId}" />
   <input type='submit' value="Send Warning" />
    </form>-->
   

           
            </c:forEach>
             </c:if>     
            <c:if test="${requestScope.displayPage eq 'stats'}">
             
      
                <p> Shows County wise - user statistics
      <table>
          <tr><td>India:</td><td> ${requestScope.inCount}</td></tr>
           <tr><td>China</td><td> ${requestScope.chCount}</td></tr>
            <tr><td>England</td><td> ${requestScope.enCount}</td></tr>
             <tr><td>USA</td><td> ${requestScope.amCount}</td></tr>
              <tr><td>France:</td><td> ${requestScope.frCount}</td></tr>

<!--               <form action="/EmailReport.htm" method="post">
                   <input type="hidden" name="reporterId" value="${reports.reportedId}" />
   <input type='submit' value="Send Warning" />
    </form>-->
      </table>
     </td>
   </tr>
           
         
             </c:if>    
            
       </div>
         <div class="column right">

             <a href="Logout.htm">Logout</a><br><br><br>
                 <form action="SearchPage.htm" method='POST'>
     First Name: <input type="text" name="name"/>
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
        </c:if>
        </div>
<c:if test="${sessionScope.user eq null}">
    <br>Access  HomePage after logging into WeConnect<br><br><a href="LoginPage.htm">Return to Login Page</a>
    <br><br><a href="StartPage.htm">Click Here for New User Registration!</a>
</c:if>
<!--          <script>
        $("#hide").on('click',function(){
    $("#pageNav").css('display','none');  // $('#pageNav').hide();
});
$("#show").on('click',function(){
    $("#pageNav").css('display','block');  // $('#pageNav').show();
});
</script>-->
    </body>
</html>
