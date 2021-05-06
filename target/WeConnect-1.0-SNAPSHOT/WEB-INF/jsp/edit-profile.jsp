<%-- 
    Document   : edit-profile
    Created on : Apr 1, 2021, 6:47:32 AM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>          
<script>      
    $(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;

    // or instead:
    // var maxDate = dtToday.toISOString().substr(0, 10);

    $('#txtDate').attr('max', maxDate);
});       
</script>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
   /*  Submit form using Ajax */
   $('button[type=submit]').click(function(e) {
   
      //Prevent default submission of form
      e.preventDefault();
      
      //Remove all errors
      $('input').next().remove();
      
  $(function() {
   /*  Submit form using Ajax */
   $('button[type=submit]').click(function(e) {
   
      //Prevent default submission of form
      e.preventDefault();
      
      //Remove all errors
      $('input').next().remove();
      
      $.post({
         url : 'EditPage.htm',
         data : $('form[name=user]').serialize(),
         success : function(res) {
         
            if(res.validated){
               //Set response
               $('#resultContainer pre code').text(JSON.stringify(res.user));
               $('#resultContainer').show();
            
            }else{
              //Set error messages
              $.each(res.errorMessages,function(key,value){
  	            $('input[name='+key+']').after('<span class="error">'+value+'</span>');
              });
            }
         }
      })
   });
});
</script>-->


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

body {
  font-family: 'Rubik', sans-serif;
}

.container {
  display: block;
  height: 100vh;
}

.left {
  overflow: hidden;
  display: block;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  animation-name: left;
  animation-duration: 1s;
  animation-fill-mode: both;
  animation-delay: 1s;
}
.privacy{
   flex-direction: column;
   display: block;
   animation-name: left;
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
  max-width: 100%;
  display: block;
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
  padding: 0 8px;
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
  padding: 6px 5px;
  border: 0;
  background: linear-gradient(to right, #de48b5 0%,#0097ff 100%); 
  border-radius: 3px;
  margin-top: 10px;
  color: #fff;
  letter-spacing: 1px;
  font-family: 'Rubik', sans-serif;
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
    width:100%;
  }
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

 <form:form method="post" modelAttribute="user" enctype="multipart/form-data">
<table class="table-style">
    <tr>    <div class="header">
          <h2>Edit Your Profile:</h2>
    </div></tr>
    <tr><td>First Name:<form:input  path="firstName" class="form-field animation a3" value="${user.firstName}"/></td>
        <td><form:errors path="firstName" class="error-message"/></td></tr>
    <tr><td>Last Name:<form:input  path="lastName" class="form-field animation a3" value="${user.lastName}"/></td>
        <td><form:errors path="lastName" class="error-message"/></td></tr>
    <tr><td>Gender:<form:input  path="gender" class="form-field animation a3" value="${user.gender}"/></td>
        <td> <form:errors path="gender" class="error-message"/></td></tr>
    <tr><td> Date of Birth:<form:input type="date" id="txtDate" path="dob" value="${user.dob}" class="form-field animation a3"/></td>
        <td><form:errors path="dob" class="error-message"/></td></tr>
    <tr><td>Contact:<form:input  path="contact" class="form-field animation a3" value="${user.contact}"/></td>
        <td><form:errors path="contact" class="error-message"/></td></tr>
    <tr><td>Select Country:<form:select path="country" class="form-field animation a3">  
        <form:option  value="Select Country" label="select" />  
        <form:option value="China" label="China"/>                 
        <form:option value="India" label="India"/>  
        <form:option value="France" label="France"/>  
        <form:option value="England" label="England"/>  
        <form:option value="United States of America" label="United States of America"/>  
        </form:select></td><td><form:errors path="country" class="error-message"/>
        <td></tr>
    <tr><td>State:<form:input  path="state" class="form-field animation a3" value="${user.state}"/></td>
        <td><form:errors path="state" class="error-message"/></td></tr>
    
    
    <tr><td>Contact Access:<input type="checkbox"  class="form-field animation a3" path="contactAccess" <c:if test="${user.contactAccess eq 'yes'}">checked="checked"</c:if> /></td>
        <td><form:errors path="contactAccess" class="error-message"/></td></tr>
    
    
    <tr><td>PhotoAccess:<form:radiobutton path="photoAccess" value="private" />Private <form:radiobutton
            path="photoAccess" value="public" />Public</td><td><form:errors path="photoAccess" class="error-message"/></td> </tr> 
    <tr><td>DOB Access:<input type="checkbox" path="dobAccess" <c:if test="${user.dobAccess eq 'yes'}">checked="checked"</c:if> /></td>
        <td><form:errors path="dobAccess" class="error-message"/></td></tr>
    <tr><td>Gender Access:<input type="checkbox" path="genderAccess" <c:if test="${user.genderAccess eq 'yes'}">checked="checked"</c:if> /></td>
        <td><form:errors path="genderAccess" class="error-message"/></td> </tr>
    <tr><td>Friendlist Access:<input type="checkbox" path="friendAccess" <c:if test="${user.friendAccess eq 'yes'}">checked="checked"</c:if> /></td>
           <td><form:errors path="friendAccess" class="error-message"/></td> </tr>
<tr>

 <img width="300" src="<c:url value="/images/"/>${user.photo}"/></tr>
<tr><td>Photo:<form:input type="file" class="form-field animation a3" name="file" path="file" value="${user.photo}" /></td></tr>
   


   
 

       
             </table>
             <table>
            <input type="submit" value="Update Profile!">
             </table>
        </form:form>  
      <br><br><br>

    </body>
</html>
