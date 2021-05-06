/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.CommentProfileDAO;
import com.me.dao.FriendProfileDAO;
import com.me.dao.FriendRequestProfileDAO;
import com.me.dao.PostProfileDAO;
import com.me.dao.ReportDAO;
import com.me.dao.TimelineProfileDAO;
import com.me.dao.UserCountryDAO;
import com.me.dao.UserProfileDAO;
import com.me.imageprocessor.UploadFile;
import com.me.pojo.Comment;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.Post;
import com.me.pojo.Report;
import com.me.pojo.Timeline;
import com.me.pojo.UserProfile;
import com.me.validator.UserLoginValidator;
import com.me.validator.UserProfileUpdateValidator;
import com.me.validator.UserProfileValidator;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Manasa
 */
@Controller
public class UserProfileFormController {
    
   @Autowired
   private UserProfileUpdateValidator profileValidator;
   @Autowired
   private UserProfileValidator validator;
   @Autowired
   private UserProfileDAO userDAO;
   @Autowired
   private TimelineProfileDAO timelineDAO;
   @Autowired
   private FriendProfileDAO friendDAO;
   @Autowired
   private PostProfileDAO postDAO;
   @Autowired
   private CommentProfileDAO commentDAO;
   @Autowired
   private FriendRequestProfileDAO friendRequestDAO;  
   @Autowired
   private ReportDAO reportDAO;  
   @Autowired
   private UserCountryDAO userCountryDAO;  


    public UserProfileFormController() {
    }

   @RequestMapping(value="/StartPage.htm", method=RequestMethod.GET)
    public String showForm(ModelMap model, UserProfile user){
        model.addAttribute("userForm", user);
        return "user-registration";
    }
    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }
    @RequestMapping(value="/StartPage.htm", method=RequestMethod.POST)
    public ModelAndView showSuccess(Model model, @ModelAttribute("userForm") UserProfile user, BindingResult result,HttpServletRequest request) {
       validator.validate(user, result);
       if (result.hasErrors()) {
             return new ModelAndView("user-registration");
    }
       user.setPassword(hashPassword(user.getPassword()));
       user.setContactAccess("no");
       user.setRole("user");
       user.setPhoto("defaultmale.jpeg");
       if(userDAO.addAccount(user)){
           Timeline timeline = new Timeline();
           timeline.setUserId(user.getUserId());
           timeline.setDescription("Joined WeConnect");
           timeline.setTime(new Date());
           timelineDAO.addTimeline(timeline);
          return new ModelAndView("user-homepage","user",user);
       }else{
         return new ModelAndView("user-registration");
       }
   }
    @GetMapping(value = "/EditPage.htm")
    public String editProfile(Model model,HttpSession session) {
        UserProfile user = userDAO.findAccountById((int)session.getAttribute("userId"));
        model.addAttribute("user", user);
        return "edit-profile";
    }     
    @RequestMapping(value="/ViewPosts.htm")
    public ModelAndView viewProfile(Model model,HttpSession session, HttpServletRequest request) {
                UserProfile profile = userDAO.findAccountById((int)session.getAttribute("userId"));
                List<FriendProfile> friends = friendDAO.getFriendsList(profile.getUserId());
                List<Post> postList = postDAO.getPosts(profile.getUserId(),null,true);             
                int id;
               for(int i=0; i<postList.size();i++){
                   id = postList.get(i).getPostId();
                   List<Comment> comments = commentDAO.getComments(id);
                   for(int j=0; j<comments.size(); j++){
                   comments.get(j).setUser(userDAO.findAccountById(comments.get(j).getUserId()));
               }
                 postList.get(i).setUserProfile(profile);
               }
            List<FriendRequest> requests = friendRequestDAO.getList(profile.getUserId());
            model.addAttribute("friendRequests", requests.size());
            model.addAttribute("friends", friends.size());
            model.addAttribute("postList",postList);                 
          return new ModelAndView("user-homepage","user",profile);                 
   }
    
    @RequestMapping(value="/EditPage.htm",method = RequestMethod.POST)    
    public ModelAndView saveEdited(Model model, @ModelAttribute("user") UserProfile userProfile,@RequestParam("file") MultipartFile file, BindingResult result, HttpSession session, HttpServletRequest request) throws FileNotFoundException, FileUploadException { 
        profileValidator.validate(userProfile, result);     
        int id = (int)session.getAttribute("userId");
        if (result.hasErrors()) {
              return new ModelAndView("edit-profile");      
        }
        userProfile.setUserId(id);
        userProfile.setEmail((String)session.getAttribute("loginId"));
        if(!userProfile.getFile().isEmpty()){
            if((userProfile.getFile().getOriginalFilename()!=null) &&(!userProfile.getFile().getOriginalFilename().equals(""))){
        userProfile.setPhoto(userProfile.getFile().getOriginalFilename());
            if(!userProfile.getPhoto().equals("")){
                UploadFile.addFile(session,userProfile.getFile(),userProfile.getPhoto());
            }
            }
        }
        userDAO.updateAccount(userProfile);
        session.setAttribute("user",userProfile);
         model.addAttribute("user",userProfile);
        return new ModelAndView("edit-profile"); 
    }    
    
    
    @RequestMapping(value="/SavePage.htm",method = RequestMethod.POST)    
    public ModelAndView saveIt(Model model, @ModelAttribute("user") UserProfile userProfile, BindingResult result, HttpSession session,HttpServletRequest request) throws FileNotFoundException { 
        validator.validate(userProfile, result);
        if (result.hasErrors()) {
            List<ObjectError> emailErr = result.getAllErrors();
            boolean emailCode = false;
            for(ObjectError e: emailErr){
                if(e.getCode().equalsIgnoreCase("NotNew.applicantForm.email")){
                    emailCode = true;
                }
            }
            if(!emailCode){
              return new ModelAndView("edit-profile");  
            }else if(result.getErrorCount() > 1){
             
             return new ModelAndView("edit-profile");
       }
        }
        userProfile.setUserId(userProfile.getUserId());

        userProfile.setPhoto(userProfile.getFile().getOriginalFilename());
        if(!userProfile.getPhoto().equals("")){
            UploadFile.addFile(session,userProfile.getFile(),userProfile.getPhoto());
        }
        userDAO.updateAccount(userProfile);
        session.setAttribute("user",userProfile);
        return new ModelAndView("redirect:/ProfilePage.htm"); 
    }  
    
    @RequestMapping(value="/ProfilePage.htm")
    public String showProfile(Model model, HttpSession session) throws SQLException {
     if(session.getAttribute("loginId") != null ){
       UserProfile profile = (UserProfile) session.getAttribute("user");
       List<Timeline> timelineList = timelineDAO.getTimeline(profile.getUserId());
       model.addAttribute("timelineList", timelineList);    
    }
        return "profile-page";
   }

   @InitBinder
   protected void initBinder(WebDataBinder dataBinder) {
       Object obj = dataBinder.getTarget();
       if (obj == null) {
           return;
       }
 
       if (obj.getClass() == UserProfile.class) {
       }
           dataBinder.setValidator(validator);
       }
   
   @RequestMapping(value = "/DeletePage/{id}.htm")
    public String deleteProfile(@PathVariable("id") int id, HttpSession session) {

        userDAO.deleteAccount(id);
        session.invalidate();
        return "delete-success";
    } 

    @RequestMapping(value = "/ProfPage.htm")
    public String getProf(Model model, HttpSession session) throws SQLException, FileNotFoundException {
       if(session.getAttribute("loginId") != null ){
       UserProfile profile = (UserProfile) userDAO.findAccountById((int)session.getAttribute("userId"));
       List<Timeline> timelineList = timelineDAO.getTimeline(profile.getUserId());
       model.addAttribute("timelineList", timelineList);
       }
      return "profile-page";
    }
    @RequestMapping(value = "/ViewReport.htm")
    public ModelAndView viewReports( Model model,HttpSession session)  throws SQLException, FileNotFoundException {
    List<Report> reportList = reportDAO.getReports();
    for(Report r : reportList){
        r.setReportedProfile((UserProfile)userDAO.findAccountById(r.getReportedId()));
        r.setReporterProfile((UserProfile)userDAO.findAccountById(r.getReporterId()));
    }
            model.addAttribute("reportList", reportList);
            model.addAttribute("displayPage","reports");    
          return new ModelAndView("admin-homepage");  
    } 
    
    @RequestMapping(value = "/ViewStats.htm")
    public ModelAndView viewStats( Model model,HttpSession session)  throws SQLException, FileNotFoundException {
   int inCount  = userCountryDAO.getUserCountryStat("India").size();
   int frCount  = userCountryDAO.getUserCountryStat("France").size();
   int enCount  = userCountryDAO.getUserCountryStat("England").size();
   int chCount  = userCountryDAO.getUserCountryStat("China").size();
   int amCount  = userCountryDAO.getUserCountryStat("United States of America").size();
     model.addAttribute("inCount", inCount);
     model.addAttribute("frCount", frCount);
     model.addAttribute("enCount", enCount);
     model.addAttribute("amCount", amCount);
     model.addAttribute("chCount", chCount);
     model.addAttribute("displayPage","stats");   
     return new ModelAndView("admin-homepage");  
    } 
}

