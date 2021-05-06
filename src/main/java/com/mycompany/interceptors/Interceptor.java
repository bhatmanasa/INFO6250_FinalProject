/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 *
 * @author Manasa
 */
public class Interceptor extends HandlerInterceptorAdapter{

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
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
               System.out.println("when request comes this method");
//if user already logged in
   if(request.getSession().getAttribute("userId") ==null){
    response.sendRedirect("/NotLoggedIn.htm");
    }
//    if user added inputs add want to sanitize.
//    */
return true;
//        return super.preHandle(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }
//    
}
