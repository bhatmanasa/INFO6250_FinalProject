<%-- 
    Document   : user-homepage
    Created on : Mar 19, 2021, 12:11:12 PM
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

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
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
  height:100vh;
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
            <form action="LoginBackPage.htm" method="post">
   <input type='submit' value="Homepage" />
    </form>
  <a href="About.htm">About</a>
</div>
        <div class="component">
        <c:if test="${sessionScope.loginId ne null}">   
        <div class="column left">
            <br><br>${sessionScope.user.firstName}<br><br>
            ${sessionScope.user.country}
            <br><br><br>
            <a href="ProfPage.htm">Go to Profile</a>
            <br><br>
            <a href="RequestPage.htm">View Requests(${requestScope.friendRequests})</a>
            <br><br>
            <a href="FriendPage.htm">View Friends(${requestScope.friends})</a><br><br>
            <br><br>
            <a href="ViewPosts.htm">View Your Posts</a>
            <br><br>
            <a href="ViewTimeline.htm">View Your Timeline</a>
            <br><br>
            <a href="Messages.htm">View Your Messages</a>
            
        </div>
       <div class="column middle">
           <h1><i>Hey ${sessionScope.user.firstName}, </i></h1><br><h3><i>Welcome Back to WeConnect HomePage!</i></h3>

            <br><br>
                <a href="PostPage.htm">Upload a POST Now!</a>
                <br><br>

        <table>
            <c:forEach items="${requestScope.postList}" var="posts">  
                <tr><td>${posts.user.firstName} ${posts.user.lastName}</td><td>${posts.description}</td><td>${posts.dateTime}</td></tr>
    <tr>
    <td><img width="300" src="<c:url value="/images/"/>${posts.fileName}"/><br></td>
    <td></td>
    <td>
      <table>
        <tr><td>
                <form action="CommentAdd.htm" method="post">
                    <input type="text" name="commentDesc" /><br>
                    <input type="hidden" name="postId" value="${posts.postId}" /><br>
                    <input type='submit' value="Add Comment" />
                </form>
       </td></tr>
        <c:forEach items="${posts.commentSet}" var="comments">   
        <tr><td>${comments.userProfile.firstName} ${comments.userProfile.lastName}</td></tr>
        <tr><td>${comments.commentDesc}</td></tr>
        </c:forEach>
      </table>
     </td>
   </tr>
            </c:forEach>

        </table>
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
<c:if test="${sessionScope.loginId eq null}">
     <br><br><br>Access  HomePage after logging into WeConnect
     <br><br><br><a href="LoginPage.htm">Return to Login Page</a>
<br><br><br> <a href="StartPage.htm">Click Here for New User Registration!</a>
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
