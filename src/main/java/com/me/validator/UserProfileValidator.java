/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.validator;

import com.me.dao.UserProfileDAO;
import com.me.pojo.UserProfile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Manasa
 */
@Component
public class UserProfileValidator implements Validator {
    
    // common-validator library.
  //  private EmailValidator emailValidator =   EmailValidator.get.getInstance();

    // The classes is supported to Validate
    @Override
    public boolean supports(Class<?> c) {
        return c == UserProfile.class;
    }
    
 
    @Override
    public void validate(Object target, Errors errors) {
        UserProfile userInfo = (UserProfile) target;
        System.out.println("userInfo="+userInfo);
        boolean found=false;
        Pattern namePattern = Pattern.compile("[0-9 ]");
//Validate name
System.out.println("Validating name");
            if(userInfo.getFirstName().equals("")){
                    errors.rejectValue("firstName", "NotEmpty.applicantForm.firstName","First Name cannot be empty");            
            }else{          
            Matcher matcher = namePattern.matcher(userInfo.getFirstName());
            while (matcher.find()) {
                found = true;

            }
            if(found){
                errors.rejectValue("firstName", "fname.string","First Name cannot have digits or spaces -need to have alphabets only");
            }
            }
             if(userInfo.getLastName().equals("")){
                    errors.rejectValue("lastName", "NotEmpty.applicantForm.lastName","Last Name cannot be empty");            
            }else{           
            found=false;
            Matcher matcher2 = namePattern.matcher(userInfo.getLastName());
            while (matcher2.find()) {
                found = true;

            }
            if(found){
                errors.rejectValue("lastName", "lname.string","Last Name cannot have digits or spaces - need to have alphabets only");
            }
             }
             System.out.println("Validating namedone");
 //Validate email
 System.out.println("Validating email");
              if(userInfo.getEmail().equals("")){
                    errors.rejectValue("email", "NotEmpty.applicantForm.email","Email cannot be empty");            
            }else{   
            Pattern emailPattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");
            Matcher emailMatcher = emailPattern.matcher(userInfo.getEmail());
            found= emailMatcher.find() ;

            if(!found){
                errors.rejectValue("email", "email.pattern","Invalid Email ID");
            }else{
                UserProfileDAO userProfileDAO = new UserProfileDAO();
                UserProfile profile = userProfileDAO.findAccount(userInfo.getEmail());
                if(profile!=null){
                   errors.rejectValue("email", "NotNew.applicantForm.email","Email Id already exists!"); 
                }
              }
              }
              System.out.println("Validating done");
//Validate dob 
System.out.println("Validating dob");
            if(userInfo.getDob().equals("")){
                    errors.rejectValue("dob", "NotEmpty.applicantForm.dob","Date of Birth cannot be empty");            
            }

            
//else{   
//        Pattern dobPattern = Pattern.compile("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$");
//            Matcher dobMatcher = dobPattern.matcher(userInfo.getDob());
//            found= dobMatcher.find() ;
//
//            if(!found){
//                errors.rejectValue("dob", "dob.pattern","Invalid DOB - Please enter valid date in dd/MM/yyyy format only");
//            }
//	}
            
    
             System.out.println("Validating dob done");
//Validate country
System.out.println("Validating country="+userInfo.getCountry());
            if(userInfo.getCountry().equalsIgnoreCase("Select Country")){
                    errors.rejectValue("country", "NotSelected.applicantForm.country","Selecting Country is mandatory");            
            }
//Validate password 
System.out.println("Validating pwd");
try{
             if(userInfo.getPassword().equals("")){
                    errors.rejectValue("password", "NotEmpty.applicantForm.password","Password cannot be empty");            
            }else{   
        Pattern pwdPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!])(?=\\S+$).{6,10}$");
            Matcher pwdMatcher = pwdPattern.matcher(userInfo.getPassword());
            found= pwdMatcher.find() ;

            if(!found){
                errors.rejectValue("password", "password.pattern","Invalid Password - Please enter 6 to 8 characters and need to contain atleast one capital, one alphabet, one number and one special character");
            }
             }
}catch(Exception e){
    errors.rejectValue("password", "NotEmpty.applicantForm.password","Password cannot be empty");
}
             System.out.println("Validating pwd deone");
        // Check the fields of ApplicantInfo.
        // (See more in property file: messages/validator.property)

                
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.applicantForm.name");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty.applicantForm.dob");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.applicantForm.gender");
        
//        if(!emailValidator.isValid(userInfo.getEmail())) {
//            // Error in email field.
//            errors.rejectValue("email", "Pattern.applicantForm.email");
//        }
//        
//        if(userInfo.getSkills()== null || userInfo.getSkills().length==0 ) {
//            errors.rejectValue("skills", "Select.applicantForm.skills");
//        }@Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")  
    }
}
