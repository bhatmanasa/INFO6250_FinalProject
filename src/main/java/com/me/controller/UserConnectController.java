/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.FriendProfileDAO;
import com.me.dao.FriendRequestProfileDAO;
import com.me.dao.MessageProfileDAO;
import com.me.dao.NotificationProfileDAO;
import com.me.dao.TimelineProfileDAO;
import com.me.dao.UserProfileDAO;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.Message;
import com.me.pojo.Notification;
import com.me.pojo.Timeline;
import com.me.pojo.UserProfile;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author Manasa
 */
@Controller
public class UserConnectController {
        @Autowired
   private UserProfileDAO userDAO;
        @Autowired
   private FriendProfileDAO friendDAO;
        @Autowired
   private FriendRequestProfileDAO friendRequestDAO;
        @Autowired
   private TimelineProfileDAO timelineDAO;
        @Autowired
   private NotificationProfileDAO notificationeDAO;
        @Autowired
   private MessageProfileDAO messageDAO;
        
  @RequestMapping(value="/ConnectRequest/{id}/{btnConnect}.htm")
  public String connectProfile(@PathVariable("id") int id,@PathVariable("btnConnect") String action, Model model,HttpSession session, HttpServletRequest request) {
        UserProfile loggedin = userDAO.findAccountById((int)session.getAttribute("userId"));
        UserProfile user = userDAO.findAccountById(id);
    if(action.equalsIgnoreCase("ConnectNow")){
       FriendRequest profile = new FriendRequest();
       profile.setRequestor((int)session.getAttribute("userId"));
       profile.setRecepient(id);            
       if(friendRequestDAO.updateRequest(profile)){
           Notification notif = new Notification();
           notif.setDateTime(new Date());
           notif.setDescription(loggedin.getFirstName()+" "+loggedin.getLastName()+"sent you connection request");
           notif.setUserId(id);
           notif.setCategory("connectrequest");
           notif.setRead("no");
           notificationeDAO.addNotif(notif);
           model.addAttribute("connectResponse", "success");
       }else{
            model.addAttribute("connectResponse", "fail");
       }
    }else if(action.equalsIgnoreCase("Unfriend")){
        if(friendDAO.removeConnection((int)session.getAttribute("userId"),id)){
           Notification notif = new Notification();
           notif.setDateTime(new Date());
           notif.setDescription(loggedin.getFirstName()+" "+loggedin.getLastName()+"Unfriended you");
           notif.setUserId(id);
           notif.setCategory("unfriend");
           notif.setRead("no");
           notificationeDAO.addNotif(notif);
           model.addAttribute("connectResponse", "success");
        }else{
           model.addAttribute("connectResponse", "fail"); 
        }
    }else if(action.equalsIgnoreCase("Reject")){
            if(friendRequestDAO.removeConnection((int)session.getAttribute("userId"),id)){
            model.addAttribute("requestFulfill", "success");
        }else{
             model.addAttribute("requestFulfill", "fail"); 
        }
        
    }else if(action.equalsIgnoreCase("Accept")){
        boolean check = friendRequestDAO.removeConnection((int)session.getAttribute("userId"),id);
        FriendProfile profile = new FriendProfile();
       profile.setUser1((int)session.getAttribute("userId"));
       profile.setUser2(id);
       if(friendDAO.updateConnection(profile)){
            UserProfile requestorId = userDAO.findAccountById(id);
           Timeline timeline = new Timeline();
           timeline.setUserId((int)session.getAttribute("userId"));
           timeline.setDescription("Connected with "+requestorId.getFirstName()+" "+requestorId.getLastName());
           timeline.setTime(new Date());
           timelineDAO.addTimeline(timeline);
           model.addAttribute("requestFulfill", "success");
       }else{
            model.addAttribute("requestFulfill", "fail");
       }   
    }
        model.addAttribute("otherUser", user);


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
  
       @RequestMapping(value = "/RequestPage.htm")
        public ModelAndView getRequestList(Model model,HttpSession session) {
            List<UserProfile> userList = friendRequestDAO.getUserList((int)session.getAttribute("userId"));
            return new ModelAndView("request-page","requestUsers",userList);
        }
       @RequestMapping(value = "/ViewTimeline.htm")
        public ModelAndView getTimeline(Model model,HttpSession session) {
            List<Timeline> timelineList = timelineDAO.getTimeline((int)session.getAttribute("userId"));
            model.addAttribute("timelineList",timelineList);
            return new ModelAndView("timeline");
        }

      @RequestMapping(value = "/FriendPage.htm")
      public ModelAndView getFriendList(Model model,HttpSession session) {
            List<UserProfile> userList = friendDAO.getUserList((int)session.getAttribute("userId"));
            return new ModelAndView("request-page","friendUsers",userList);
      }
      @RequestMapping(value = "/Messages.htm")
      public ModelAndView viewMessages(Model model,HttpSession session) {
        List<UserProfile> friends = friendDAO.getUserList((int)session.getAttribute("userId"));
           for(UserProfile u: friends){
                u.setMessages(messageDAO.getMessage(u.getUserId(), (int)session.getAttribute("userId")));
            }
        return new ModelAndView("message-page","friends",friends);
        }

    @RequestMapping(value="/AddMessage.htm", method = RequestMethod.POST)
    public ModelAndView messageAdd(Model model,HttpSession session, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("searchId"));
        List<Message> messages = messageDAO.getMessage(id, (int)session.getAttribute("userId"));
        model.addAttribute("messages",messages);
        model.addAttribute("friendId",id);
        return new ModelAndView("message-friend");
    }
    @RequestMapping(value="/SendMessage.htm", method = RequestMethod.POST)
    public ModelAndView sendMsg(Model model,HttpSession session, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("friendId"));
        model.addAttribute("friendId",id);
        Message msg = new Message();
        msg.setDescription(request.getParameter("desc"));
        msg.setDateTime(new Date());
        UserProfile  toProfile = userDAO.findAccountById(id);
        UserProfile fromProfile = (UserProfile) session.getAttribute("user");
        msg.setToId(id);
        msg.setFromId(fromProfile.getUserId());
        msg.setFromFirstName(fromProfile.getFirstName());
        msg.setToFirstName(toProfile.getFirstName());
        messageDAO.addMessage(msg);
           Notification notif = new Notification();
           notif.setDateTime(new Date());
           notif.setCategory("messages");
           notif.setRead("no");
           notif.setDescription(fromProfile.getFirstName()+" Sent you a message!");
           notif.setUserId(id);
           notificationeDAO.addNotif(notif);
           List<Message> messages = messageDAO.getMessage(id, (int)session.getAttribute("userId"));
        return new ModelAndView("message-friend","messages",messages);
    }

    @RequestMapping(value="/ViewRequestProfile/{id}.htm")
    public String viewProfile(@PathVariable("id") int id, Model model,HttpSession session, HttpServletRequest request) {
        UserProfile user = userDAO.findAccountById(id);
        model.addAttribute("otherUser", user);
        int userId = (int)session.getAttribute("userId");
        boolean check = friendDAO.isFriend(userId, id);
        if(check){
            model.addAttribute("isFriend", user);
        }    
           boolean checkRequest = friendRequestDAO.isFriend(userId, id);
        if(checkRequest){
            model.addAttribute("isRequest", user);
        }    
        model.addAttribute("requestFlag","true");
        return "profile-page";
   }
}
