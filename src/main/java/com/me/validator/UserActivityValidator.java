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
public class UserActivityValidator implements Validator{
    

    // The classes is supported to Validate
    @Override
    public boolean supports(Class<?> c) {
        return c == UserProfile.class;
    }
    
 
    @Override
    public void validate(Object target, Errors errors) {
        UserProfile userInfo = (UserProfile) target;
        boolean found=false;
       
 //Validate email
              if(userInfo.getEmail().equals("")){
                    errors.rejectValue("email", "NotEmpty.applicantForm.email","Email ID cannot be empty");            
            }else{   
            Pattern emailPattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");
            Matcher emailMatcher = emailPattern.matcher(userInfo.getEmail());
            found= emailMatcher.find() ;

            if(!found){
                errors.rejectValue("email", "email.pattern","Invalid Email ID");
            }else{
                UserProfileDAO userProfileDAO = new UserProfileDAO();
                UserProfile profile = userProfileDAO.findAccount(userInfo.getEmail());
                if(profile==null){
                   errors.rejectValue("email", "NotPresent.applicantForm.email","Email Id not registered with WeConnect!");  
                }
              }
              }      
//Validate password 
             if(userInfo.getPassword().equals("")){
                    errors.rejectValue("password", "NotEmpty.applicantForm.password","Password cannot be empty");            
            }else{   
                UserProfileDAO userProfileDAO = new UserProfileDAO();
                String result = userProfileDAO.validateCredential(userInfo.getEmail(),userInfo.getPassword());
                if(result.equals("invalidpassword")){
                   errors.rejectValue("password", "NotPresent.applicantForm.password","Incorrect Password!");  
                }     
             }
 
    }
}
