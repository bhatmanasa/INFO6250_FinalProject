<%-- 
    Document   : user-registration
    Created on : Mar 30, 2021, 7:14:48 AM
    Author     : Manasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                    
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
  max-width: 100%;
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
  animation-delay: 1s;
}

.a1 {
  animation-delay: 1s;
}

.a2 {
  animation-delay: 1.1s;
}

.a3 {
  animation-delay: 1.2s;
}

.a4 {
  animation-delay: 1.3s;
}

.a5 {
  animation-delay: 1.4s;
}

.a6 {
  animation-delay: 1.5s;
  
}.topnav input{
   float: left;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
    background-color: #ddd;
  color: black;
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
    width:700px;
  }
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
<!--        <h3> Complete the below form for new User Registration:</h3>
   //    <form:form modelAttribute="userForm">
   //           <table border="0" width="90%" class="table-style">
   //              <tr><td>First Name: <form:input path="firstName"/></td><td>
   //                     <form:errors path="firstName" class="error-message"/></td> </tr>
//
//            <tr><td>Last Name: <form:input path="lastName"/></td><td>
//                        <form:errors path="lastName" class="error-message"/></td> </tr>
//            <tr><td>Email <form:input path="email"/></td><td>
//                    <form:errors path="email" class="error-message"/></td> </tr>
 //           <tr><td>Password <form:input path="password"/></td><td>
 //                   <form:errors path="password" class="error-message"/></td> </tr>
 //           <tr><td>Country <form:select path="country"> 
 <form:option  value="Select Country" label="select" />  
 //       <form:option value="China" label="China"/>                 
  //      <form:option value="India" label="India"/>  
   //     <form:option value="France" label="France"/>  
      //  <form:option value="England" label="England"/>  
    //    <form:option value="United States of America" label="United States of America"/>  
   //     </form:select>  </td> </tr>
 // <tr><td>Date of Birth <form:input name="dob" path="dob"/></td><td>
      //              <form:errors path="dob" class="error-message"/></td> </tr>           
    //         </table>
  //          <input type="submit" value="Register!">
//        </form:form> -->  

<div class="container">
  <div class="left">
    <div class="header">
      <h2 class="animation a1">Complete the below form for new User Registration:</h2>
    </div>
          <table>
             <form:form modelAttribute="userForm">
             
                     <tr><td>Enter your First Name:<form:input path="firstName" class="form-field animation a3" placeholder="First Name"/></td>
                         <td><form:errors path="firstName" class="error-message"/></td></tr>
    <tr><td>Enter your Last Name:<form:input path="lastName" class="form-field animation a4" placeholder="LastName" />
     <td><form:errors path="lastName" class="error-message"/></td></tr>
          <tr><td>Enter Email ID (This will be your username):<form:input path="email" class="form-field animation a3" placeholder="Email ID"/>
          <td><form:errors path="email" class="error-message"/></td></tr>
    <tr><td>Enter Password:<form:input path="password" class="form-field animation a4" placeholder="Password" />
     <td><form:errors path="password" class="error-message"/></td></tr>
<tr><td>Select Country:<form:select path="country" class="form-field animation a4">  
        <form:option label="Select Country" value="Select Country" />  
        <form:option value="China" label="China"/>                 
        <form:option value="India" label="India"/>  
        <form:option value="France" label="France"/>  
        <form:option value="England" label="England"/>  
        <form:option value="United States of America" label="United States of America"/>  
        </form:select></td><td><form:errors path="country" class="error-message"/></td></tr></tr>
    <tr><td>Enter Date of Birth:<form:input type="date" id="txtDate" path="dob" class="form-field animation a5" placeholder="Date of Birth" />
     <td><form:errors path="dob" class="error-message"/></td></tr>
                 </table>
      <table>
              <tr>  <button class="animation a6" type="submit">REGISTER</button></tr>
      </table>
                       </form:form>  
    </div>
  </div>
  <div class="right"></div>
</div>

            
            
            
            
            
            
            
    </body>
</html>
