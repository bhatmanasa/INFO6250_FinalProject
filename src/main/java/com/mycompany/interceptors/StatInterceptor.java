/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interceptors;
import com.me.dao.UserCountryDAO;
import com.me.dao.UserProfileDAO;
import com.me.pojo.UserProfile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 *
 * @author Manasa
 */
   
public class StatInterceptor extends HandlerInterceptorAdapter{
     @Autowired
   private UserCountryDAO userCountryDAO;
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("CONCURRENT HANDLING STRAT");
        super.afterConcurrentHandlingStarted(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("after display AFTER COMPLETION");

        super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                System.out.println("post handle it will display view");
                System.out.println("handler"+handler.toString());
                if(modelAndView.getModel().get("user") != null){
                UserProfile user = (UserProfile)modelAndView.getModel().get("user");
                System.out.println("model"+modelAndView.getModel().get("user"));
                System.out.println("user detal="+user.getCountry());
                userCountryDAO.updateStat(1, user.getCountry());
                
                }
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
               System.out.println("when request comes this method");
//if user already logged in

//    if user added inputs add want to sanitize.
//    */
return true;
//        return super.preHandle(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }
//    
}
