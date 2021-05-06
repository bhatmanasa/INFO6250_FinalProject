<%-- 
    Document   : profile-page
    Created on : Mar 31, 2021, 10:05:20 PM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!--    <script  type="text/javascript"> comment 
$(function() {
   /*  Submit form using Ajax */
   $('button[type=submit]').click(function(e) {
        $.ajax({
   url: 'ReportPage.htm',
   type: 'post',
   data: {id,'${session.user}',description, '#desc',toUser: '${requestScope.otherUser.userId}'},
   success: function(response){
           alert("Insert successfully.");
   }
   error: function(response){
           alert("error!");
   }
});
</script>-->
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
.topnav input{
   float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
.emp-profile{
    padding: 1%;
    margin-top: 3%;
    margin-bottom: 3%;
    border-radius: 0.5rem;
    background: #fff;
}
.profile-img{
    text-align: center;
}
.profile-img img{
    width: 70%;
    height: 100%;
}
.profile-img .file {
    position: relative;
    overflow: hidden;
    margin-top: -20%;
    width: 70%;
    border: none;
    border-radius: 0;
    font-size: 15px;
    background: #212529b8;
}
.profile-img .file input {
    position: absolute;
    opacity: 0;
    right: 0;
    top: 0;
}
.profile-head h5{
    color: #333;
}
.profile-head h6{
    color: #0062cc;
}
.profile-edit-btn{
    border: none;
    border-radius: 1.5rem;
    width: 70%;
    height: 50px;
    color: black;
    padding: 2%;
    font-weight: 600;
    cursor: pointer;
}
.proile-rating{
    font-size: 12px;
    color: #818182;
    margin-top: 5%;
}
.proile-rating span{
    color: #495057;
    font-size: 15px;
    font-weight: 600;
}
.profile-head .nav-tabs{
    margin-bottom:5%;
}
.profile-head .nav-tabs .nav-link{
    font-weight:600;
    border: none;
}
.profile-head .nav-tabs .nav-link.active{
    border: none;
    border-bottom:2px solid #0062cc;
}
.profile-work{
    margin-top: -15%;
}
.profile-work p{
    font-size: 12px;
    color: #818182;
    font-weight: 600;
    margin-top: 10%;
}
.profile-work a{
    text-decoration: none;
    color: #495057;
    font-weight: 600;
    font-size: 14px;
}
.profile-work ul{
    list-style: none;
}
.profile-tab label{
    font-weight: 600;
}
.profile-tab p{
    font-weight: 600;
    color: #0062cc;
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

  <!--    //color: #6c757d;     <table border="0" width="100%" class="table-style">
            <tr><td>Photo</td><td>
                    <img src='${sessionScope.user.photo}'></td> <td>Access</td><td>
                    ${sessionScope.user.photoAccess}</td> </tr> 
                 <tr><td>First Name: </td><td>
                        ${sessionScope.user.firstName}</td> </tr>

            <tr><td>Last Name: </td><td>
                      ${sessionScope.user.lastName}</td> </tr>
            <tr><td>Email </td><td>
                   ${sessionScope.user.email}</td> </tr>
            <tr><td>Date of Birth</td><td>
                    ${sessionScope.user.dob}</td> <td>Access</td><td>
                    ${sessionScope.user.dobAccess}</td>   </tr>
            <tr><td>Country   </td><td>${sessionScope.user.country} </td></tr>
  <tr><td>Access</td><td>
                    ${sessionScope.user.contactAccess}</td> </tr>   
   <tr><td>Contact</td><td>
                    ${sessionScope.user.contact}</td> <td>Access</td><td>
                    ${sessionScope.user.contactAccess}</td> </tr>  </tr>   
    <tr><td>State</td><td>
                    ${sessionScope.user.state}</td> </tr>   
             </table>
             <a href="EditPage/${sessionScope.user.userId}.htm">Click to Update Profile!</a><!-- comment -->
             
       <!--      <a href="DeletePage/${sessionScope.user.userId}.htm" color ="red" >Click here to Delete Account!</a>-->

  <c:if test="${sessionScope.loginId ne null}">  
      
        <c:if test="${requestScope.otherUser eq null}"> 

    <div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
               <img width="300" src="<c:url value="/images/"/>${user.photo}"/>
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                        ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                                    </h5>
                                    <h6>
                                        ${sessionScope.user.state}, ${sessionScope.user.country}
                                    </h6>
                                    <p class="proile-rating">Email : ${sessionScope.user.email}</p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
              
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">

  <a href="EditPage.htm" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>Click to Update Profile!</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date of Birth</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${sessionScope.user.dob}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Gender</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${sessionScope.user.gender}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Contact</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${sessionScope.user.contact}</p>
                                            </div>
                                        </div>  
                                                  <div class="row">
                                            <div class="col-md-6">
                                                <label>Country</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${sessionScope.user.country}</p>
                                            </div>
                                        </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </form>           
        </div>
        </c:if>
      
        <c:if test="${requestScope.otherUser ne null}">  
   <div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                 
                                <img width="300" src="<c:url value="/images/"/>${requestScope.otherUser.photo}"/>
      
                                <br>
  <a href="${pageContext.request.contextPath}/ReportPage/${requestScope.otherUser.userId}.htm" class="profile-edit-btn" name="btnReport" value="Report Profile"/>Report Profile!</a>


                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                        ${requestScope.otherUser.firstName} ${requestScope.otherUser.lastName}
                                    </h5>
                                    <h6>
                                        ${requestScope.otherUser.state}, ${requestScope.otherUser.country}
                                    </h6>
                                        <c:if test="${requestScope.otherUser.contactAccess ne 'no'}"> <p>Private Email</p> </c:if>
                                    <c:if test="${requestScope.otherUser.contactAccess eq 'no'}"><p class="proile-rating">Email : ${requestScope.otherUser.email}</p>
                                    </c:if>
                                   
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                              
                            </ul>
                        </div>
                    </div>
                     <div class="col-md-2">
                     <c:if test="${requestScope.requestFlag  ne null}"> 
                         <div class="col-md-2">
                         <c:if test="${requestScope.requestFulfill  eq null}">     
                        <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/Accept.htm" class="profile-edit-btn" name="btnConnect" value="Accept Request"/>Accept Request!</a>
                          <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/Reject.htm" class="profile-edit-btn" name="btnConnect" value="Reject Request"/>Reject Request!</a>
                 
                               

                     </c:if>
                     <c:if test="${requestScope.requestFulfill  ne null}"> 
                        <c:if test="${requestScope.isFriend  eq null}"> 
                             <c:if test="${requestScope.isRequest  eq null}">
                     <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/ConnectNow.htm" class="profile-edit-btn" name="btnConnect" value="ConnectNow"/>Connect Now!</a>
                    </div>
                             </c:if>                     
                         </c:if>
                         <c:if test="${requestScope.isFriend ne null}"> 
                                 <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/Unfriend.htm" class="profile-edit-btn" name="btnConnect" value="Unfriend"/>Unfriend!</a>
                    </div>           
                         </c:if>                     </c:if>
                         </div>
                     </c:if>   
                     <c:if test="${requestScope.requestFlag  eq null}"> 
                         <c:if test="${requestScope.isFriend  eq null}"> 
                             <c:if test="${requestScope.isRequest  eq null}">
                     <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/ConnectNow.htm" class="profile-edit-btn" name="btnConnect" value="ConnectNow"/>Connect Now!</a>
                    </div>
                             </c:if>
                             <c:if test="${requestScope.isRequest  ne null}">
                     <div class="col-md-2">
                                                 <label>RequestSent</label>                   
                     </div>
                             </c:if>
                
                       
                         </c:if>
                         <c:if test="${requestScope.isFriend ne null}"> 
                                 <div class="col-md-2">
                        <a href="${pageContext.request.contextPath}/ConnectRequest/${requestScope.otherUser.userId}/Unfriend.htm" class="profile-edit-btn" name="btnConnect" value="Unfriend"/>Unfriend!</a>
                    </div>           
                         </c:if>
                         </c:if>
                    </div>  
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                        
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Date of Birth</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.otherUser.dob}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Gender</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.otherUser.gender}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Contact</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.otherUser.contact}</p>
                                            </div>
                                        </div>
                               
           
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <c:forEach items="${requestScope.timelineList}" var="timeline">    
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>${timelineList.description}</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${timelineList.time}</p>
                                            </div>
                                        </div>
                                    </c:forEach>   
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>           
        </div>         
            
        </c:if>
            
                                            </c:if>

<c:if test="${sessionScope.loginId eq null}">
     Access  HomePage after logging into WeConnect<a href="LoginPage.htm">Return to Login Page</a>
    <a href="StartPage.htm">Click Here for New User Registration!</a>
</c:if>
    </body>
</html>
