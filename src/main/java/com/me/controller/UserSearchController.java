/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.FriendProfileDAO;
import com.me.dao.FriendRequestProfileDAO;
import com.me.dao.NotificationProfileDAO;
import com.me.dao.ReportDAO;
import com.me.dao.UserProfileDAO;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.Notification;
import com.me.pojo.UserProfile;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Manasa
 */
@Controller
public class UserSearchController {
   @Autowired
   private UserProfileDAO userDAO;
    @Autowired
   private FriendProfileDAO friendDAO;
   @Autowired
   private FriendRequestProfileDAO friendRequestDAO;    
   @Autowired
   private NotificationProfileDAO notificationDAO;
   @Autowired
   private ReportDAO reportDAO;
           
    @RequestMapping(value = "/SearchPage.htm", method = RequestMethod.POST)
    public ModelAndView filterUserName(@RequestParam("name") String name, Model model,HttpSession session) {
    List<UserProfile> userList = userDAO.filterName(name);
                UserProfile profile = userDAO.findAccountById((int)session.getAttribute("userId"));
                List<FriendProfile> friends = friendDAO.getFriendsList(profile.getUserId());
                List<FriendRequest> requests = friendRequestDAO.getList(profile.getUserId());
                List<Notification> notifs = notificationDAO.getNotifs(profile.getUserId());
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("notifs",notifs);
            model.addAttribute("search","search");               
            return new ModelAndView("search-result","users",userList); 
    } 

  @RequestMapping(value = "/SearchPage.htm", method = RequestMethod.GET)
  public String searchProfile() {
        return "search-result";
  }    
    @RequestMapping(value = "/About.htm")
    public String showAbout() {
        return "about-page";
    }    
          
    @RequestMapping(value="/ViewOtherProfile/{id}.htm")
    public String viewProfile(@PathVariable("id") int id, Model model,HttpSession session, HttpServletRequest request) {
        UserProfile user = userDAO.findAccountById(id);
        model.addAttribute("otherUser", user);
        model.addAttribute("defaultimg", "defaultmale.jpeg");
        int userId = (int)session.getAttribute("userId");
        boolean check = friendDAO.isFriend(userId, id);
        if(check){
            model.addAttribute("isFriend", user);
        }    
        boolean checkRequest = friendRequestDAO.isFriend(userId, id);
        if(checkRequest){
            model.addAttribute("isRequest", user);
        }    
        return "profile-page";
   }

}
