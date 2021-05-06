/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.login.controller;

import com.me.dao.CommentProfileDAO;
import com.me.dao.FriendProfileDAO;
import com.me.dao.FriendRequestProfileDAO;
import com.me.dao.NotificationProfileDAO;
import com.me.dao.PostProfileDAO;
import com.me.dao.TimelineProfileDAO;
import com.me.dao.UserProfileDAO;
import com.me.pojo.Comment;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.Notification;
import com.me.pojo.Post;
import com.me.pojo.UserProfile;
import com.me.validator.UserLoginValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Manasa
 */
@Controller
public class UserLoginFormController{
    @Autowired
   private UserLoginValidator loginValidator;
    @Autowired
   private UserProfileDAO userDAO;
        @Autowired
   private FriendProfileDAO friendDAO;
        @Autowired
   private PostProfileDAO postDAO;
        @Autowired
   private CommentProfileDAO commentDAO;
        @Autowired
   private TimelineProfileDAO timelineDAO;
        @Autowired
   private FriendRequestProfileDAO friendRequestDAO;  
        @Autowired
   private NotificationProfileDAO notificationDAO;
        
    public UserLoginFormController() {
       
    }
    
    @RequestMapping(value="/LoginPage.htm", method=RequestMethod.GET)
    public String showForm(ModelMap model, UserProfile user){
        model.addAttribute("userLoginForm", user);
        return "user-login";
    }

    @RequestMapping(value="/LoginPage.htm", method=RequestMethod.POST)
    public ModelAndView showSuccess(HttpSession session, Model model, @ModelAttribute("userLoginForm") UserProfile user, BindingResult result) {
       Map<Integer,List<Comment>> commentMap = new HashMap<>();
       loginValidator.validate(user, result);
       if (result.hasErrors()) {
             return new ModelAndView("user-login");
       }
        UserProfile profile = userDAO.findAccount(user.getEmail());                      
        if((profile.getRole().equals("admin"))){
                session.setAttribute("loginId", profile.getEmail());
                session.setAttribute("userId", profile.getUserId());
                session.setAttribute("user",profile);
                model.addAttribute("displayPage","homepage");
                model.addAttribute("loginUser",user);
                return new ModelAndView("admin-homepage","user",profile); 
        }else{   
                session.setAttribute("loginId", profile.getEmail());
                session.setAttribute("userId", profile.getUserId());
                session.setAttribute("user",profile);
                model.addAttribute("user", user);
                List<FriendProfile> friends = friendDAO.getFriendsList(profile.getUserId());
                List<Notification> notifs = notificationDAO.getNotifs(profile.getUserId());
                List<Post> postList = postDAO.getPosts(profile.getUserId(),friends,false);
                List<FriendRequest> requests = friendRequestDAO.getList(profile.getUserId());
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("postList",postList);
            model.addAttribute("notifs",notifs);
            model.addAttribute("loginUser",user);           
          return new ModelAndView("user-homepage","user",profile);
        }
   }
    
    @RequestMapping(value="/LoginBackPage.htm", method=RequestMethod.POST)
    public ModelAndView showPage(HttpSession session, Model model, HttpServletRequest request) {
            int loggedId = Integer.parseInt(request.getParameter("userLogged"));
            UserProfile profile = userDAO.findAccountById(loggedId);
             
    if((profile.getRole().equalsIgnoreCase("admin"))){
       return new ModelAndView("admin-homepage","user",profile); 
    }else{
                model.addAttribute("user", profile);
                List<FriendProfile> friends = friendDAO.getFriendsList(profile.getUserId());
                List<Notification> notifs = notificationDAO.getNotifs(profile.getUserId());
                List<Post> postList = postDAO.getPosts(profile.getUserId(),friends,false);
                List<FriendRequest> requests = friendRequestDAO.getList(profile.getUserId());
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("postList",postList);
            model.addAttribute("notifs",notifs);
            model.addAttribute("loginUser",profile);     
          return new ModelAndView("user-homepage","user",profile); 
         }
       
   }
    
    
    @RequestMapping(value = "/Logout.htm", method=RequestMethod.GET) 
    public String logout(ModelMap model, UserProfile user,HttpSession session) {
        notificationDAO.removeNotifications((int)session.getAttribute("userId"));
        session.invalidate();
        model.addAttribute("userLoginForm", user);
        return "user-login";
    }

   @InitBinder
   protected void initBinder(WebDataBinder dataBinder) {
       Object obj = dataBinder.getTarget();
       if (obj == null) {
           return;
       }
       if (obj.getClass() == UserProfile.class) {
       }
           dataBinder.setValidator(loginValidator);
       }


}
