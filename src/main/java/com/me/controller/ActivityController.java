/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.CommentProfileDAO;
import com.me.dao.FriendProfileDAO;
import com.me.dao.FriendRequestProfileDAO;
import com.me.dao.NotificationProfileDAO;
import com.me.dao.PostProfileDAO;
import com.me.dao.ReportDAO;
import com.me.dao.TimelineProfileDAO;
import com.me.dao.UserProfileDAO;
import com.me.imageprocessor.UploadFile;
import com.me.pojo.Comment;
import com.me.pojo.Post;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.Notification;
import com.me.pojo.Report;
import com.me.pojo.Timeline;
import com.me.pojo.UserProfile;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Manasa
 */
@Controller
public class ActivityController {
        @Autowired
   private UserProfileDAO userDAO;
        @Autowired
   private FriendProfileDAO friendDAO;
        @Autowired
   private FriendRequestProfileDAO friendRequestDAO;
        @Autowired
   private PostProfileDAO postDAO;
        @Autowired
   private CommentProfileDAO commentDAO;
       @Autowired
   private TimelineProfileDAO timelineDAO;
    @Autowired
   private NotificationProfileDAO notificationDAO;
        @Autowired
   private ReportDAO reportDAO;
        
        
   @RequestMapping(value="/PostPage.htm", method=RequestMethod.POST)
    public ModelAndView savePost(Model model, @ModelAttribute("post") Post post, @RequestParam("postFile") MultipartFile file,HttpSession session) {

        post.setDateTime(new Date());
        post.setUserId((int)session.getAttribute("userId"));
        post.setUser(userDAO.findAccountById((int)session.getAttribute("userId")));
        post.addNewCommentSet();
        post.setFileName(post.getPostFile().getOriginalFilename());
        if(!post.getFileName().equals("")){
            UploadFile.addFile(session,post.getPostFile(),post.getFileName());
        }
        postDAO.addPost(post);
           Timeline timeline = new Timeline();
           timeline.setUserId((int)session.getAttribute("userId"));
           timeline.setDescription("Uploaded Post with description :"+post.getDescription());
           timeline.setTime(new Date());
           timelineDAO.addTimeline(timeline);          
       UserProfile profile = userDAO.findAccountById((int)session.getAttribute("userId"));            
        if((profile.getRole().equalsIgnoreCase("admin"))){
            return new ModelAndView("admin-homepage","user",profile); 
        }else{
                model.addAttribute("user", profile);
                List<FriendProfile> friends = friendDAO.getFriendsList(profile.getUserId());
                List<Notification> notifs = notificationDAO.getNotifs(profile.getUserId());
                List<Post> postList = postDAO.getPosts(profile.getUserId(),friends,false);              
                int id;
                for(int i=0; i<postList.size();i++){
                   id = postList.get(i).getPostId();
                   List<Comment> comments = commentDAO.getComments(id);
                   for(int j=0; j<comments.size(); j++){
                       UserProfile userProf =userDAO.findAccountById(comments.get(j).getUserId());
                       comments.get(j).setUser(userProf);
                    }
                    postList.get(i).setUserProfile(userDAO.findAccountById(postList.get(i).getUserId()));
                }
            List<FriendRequest> requests = friendRequestDAO.getList(profile.getUserId());
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("postList",postList);
            model.addAttribute("notifs",notifs);               
          return new ModelAndView("user-homepage","user",profile); 
        }
    }
    
    @RequestMapping(value = "/PostPage.htm", method = RequestMethod.GET)
    public String postPage(Model model,HttpSession session) {
         Post post = new Post();
         model.addAttribute("post",post);
         return "post-page";
    }
 
    @RequestMapping(value = "/ReportPage/{id}.htm")
    public String reportPage(@PathVariable int id, Model model,HttpSession session, HttpServletRequest request) {
         Report rpt = new Report();       
         int otherId= id;
         String statement = request.getParameter("statement");
          UserProfile user = (UserProfile) userDAO.findAccountById((int)session.getAttribute("userId"));
          UserProfile otherUser = userDAO.findAccountById(otherId);
          rpt.setReportedId(otherId);
          rpt.setReporterId((int)session.getAttribute("userId"));
          rpt.setTime(new Date());
          rpt.setStatus(0);
          reportDAO.addReport(rpt);
          model.addAttribute("otherUser", otherUser);
        int userId = (int)session.getAttribute("userId");
        boolean check = friendDAO.isFriend(userId, otherId);
        if(check){
            model.addAttribute("isFriend", user);
        }
        boolean checkRequest = friendRequestDAO.isFriend(userId, otherId);
        if(checkRequest){
            model.addAttribute("isRequest", user);
        }        
           return "profile-page";
    } 
        
        
    @RequestMapping(value="/CommentAdd.htm", method = RequestMethod.POST)
    public ModelAndView commentAdd(Model model, @ModelAttribute("userLoginForm") UserProfile user,HttpSession session, HttpServletRequest request) {
     String description = request.getParameter("commentDesc");
     int postId = (Integer.parseInt(request.getParameter("postId")));
     int userId = ((int)session.getAttribute("userId"));
               Comment c = new Comment();
               c.setCommentDesc(description);
               c.setPostId(postId);
               c.setUserId(userId);
               c.setCommentTime(new Date());
               UserProfile userProf = userDAO.findAccountById(((int)session.getAttribute("userId")));
               c.setUserProfile(userProf);
    commentDAO.addComment(c);
    Post post = postDAO.getPostByPostId(postId);
    post.updateCommentSet(c);
           Timeline timeline = new Timeline();
           timeline.setUserId(userId);
           timeline.setDescription("Commented on a post with description");
           timeline.setTime(new Date());
           timelineDAO.addTimeline(timeline);
    List<FriendRequest> requests = friendRequestDAO.getList((int)session.getAttribute("userId"));
    List<FriendProfile> friends = friendDAO.getFriendsList((int)session.getAttribute("userId"));
           UserProfile profile = userDAO.findAccount((String)session.getAttribute("loginId"));
           Notification notif = new Notification();
           notif.setDateTime(new Date());
           notif.setDescription(profile.getFirstName()+" "+profile.getLastName()+"commented on your post!");
           notif.setUserId(userId);
           notif.setCategory("comment");
           notif.setRead("no");
           notificationDAO.addNotif(notif);
           List<Post> postList = postDAO.getPosts(profile.getUserId(),friends,false);
               int id;
               for(int i=0; i<postList.size();i++){
                   id = postList.get(i).getPostId();
                   postList.get(i).setUserProfile(userDAO.findAccountById(postList.get(i).getUserId()));
               }
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("postList",postList);
            model.addAttribute("user",userProf);
          List<Notification> notifs = notificationDAO.getNotifs(profile.getUserId());
          model.addAttribute("notifs",notifs);
          return new ModelAndView("user-homepage","user",profile);  
    }   
}
