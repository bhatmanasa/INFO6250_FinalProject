/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.validator;

import com.me.dao.UserProfileDAO;
import com.me.pojo.UserProfile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Manasa
 */
public class UserProfileUpdateValidator implements Validator {
       @Override
    public boolean supports(Class<?> c) {
        return c == UserProfile.class;
    }
    
 
    @Override
    public void validate(Object target, Errors errors) {
        UserProfile userInfo = (UserProfile) target;
        boolean found=false;
        Pattern namePattern = Pattern.compile("[0-9 ]");
//Validate name
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
 
//Validate dob 
             if(userInfo.getDob().equals("")){
                   errors.rejectValue("dob", "NotEmpty.applicantForm.dob","Date of Birth cannot be empty");            
             }
//Validate State
if(userInfo.getState() != null){
    Matcher matcherState = namePattern.matcher(userInfo.getState());
            while (matcherState.find()) {
                found = true;

            }
            if(found){
                errors.rejectValue("state", "state.string","State cannot have digits or spaces -need to have alphabets only");
            }    
    }
 
    
//        Pattern dobPattern = Pattern.compile("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$");
//            Matcher dobMatcher = dobPattern.matcher(userInfo.getDob());
//            found= dobMatcher.find() ;
//
//            if(!found){
//                errors.rejectValue("dob", "dob.pattern","Invalid DOB - Please enter valid date in dd/MM/yyyy format only");
//            }
//	}
            
    
             
//Validate password 

    }
}
