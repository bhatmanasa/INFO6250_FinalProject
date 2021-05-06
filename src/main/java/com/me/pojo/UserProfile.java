/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Manasa
 * 
 */
@Entity
@Table(name="user_profile_table")
public class UserProfile{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    int userId;
    @Column(name="Email")
    String email;
        @Column(name="FirstName")
    String firstName;
            @Column(name="LastName")
    String lastName;
        @Column(name="UserPassword")
    String password;
            @Column(name="UserRole")
    String role;   
            @Column(name="DOB")
    String dob; 
            @Column(name="DOBAccess")
            String dobAccess;
    @Column(name="ContactAccess")
    String contactAccess;
    @Column(name="Contact")
    String contact;
        @Column(name="Photo", columnDefinition = "string default defaultmale.jpeg")
    String photo; 
        @Column(name="PhotoAccess")
    String photoAccess;
        @Column(name="Country")
    String country;
        @Column(name="State")
    String state;
        @Column(name="Gender")
    String gender; 
        @Column(name="GenderAccess")
    String genderAccess;
        @Column(name="FriendList")
    String friendList; 
        @Column(name="FriendAccess")
    String friendAccess;
        @Column(name="FriendRequests")
    String friendRequests;
//    @OneToMany
//    @JoinTable (name="link_user_notifications_tbl", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "notif"))
//    private Set<Notification> notifSet = new HashSet<Notification>();
//    @OneToMany(cascade = {CascadeType.MERGE},fetch= FetchType.LAZY)
//    @JoinTable(name="link_user_notif_tbl", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "notif"))
//    Set<Notification> notifSet;
//    
    @Transient
    List<Notification> notification;   
    @Transient
    List<Message> messages;
    @Transient
    MultipartFile file;

//        @Transient
//    List<Message> messages;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public String getPhotoString() {
//        return photoString;
//    }
//
//    public void setPhotoString(String photoString) {
//        this.photoString = photoString;
//    }
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFriendList() {
        return friendList;
    }

    public void setFriendList(String friendList) {
        this.friendList = friendList;
    }


    public String getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(String friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getDobAccess() {
        return dobAccess;
    }

    public void setDobAccess(String dobAccess) {
        this.dobAccess = dobAccess;
    }

    public String getContactAccess() {
        return contactAccess;
    }

    public void setContactAccess(String contactAccess) {
        this.contactAccess = contactAccess;
    }

    public String getPhotoAccess() {
        return photoAccess;
    }

    public void setPhotoAccess(String photoAccess) {
        System.out.println("Inside bean access="+photoAccess);
        this.photoAccess = photoAccess;
    }

    public String getGenderAccess() {
        return genderAccess;
    }

    public void setGenderAccess(String genderAccess) {
        this.genderAccess = genderAccess;
    }

    public String getFriendAccess() {
        return friendAccess;
    }

    public void setFriendAccess(String friendAccess) {
        this.friendAccess = friendAccess;
    }


   public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
  }
//     public Set<Notification> getNotifications() {
//        return notifSet;
//    }   
//    public void setNotificationSet(Set notifSet) {
//        this.notifSet = notifSet;
//    }
//    public void addNewNotifSet() {
//        this.notifSet = new HashSet();
//    }
//    public void updateNotification(Notification notification) {
//        System.out.println("Updating nofit="+getNotifications());
//        getNotifications().add(notification);
//        System.out.println("Updating nofit noe="+getNotifications());
//    }
//    public Set<Notification> getNotifications() {
//        return notifSet;
//    }
//
//    public void setNotifications(Set notifSet) {
//        this.notifSet = notifSet;
//    }
//    public void addNotification() {
//        this.notifSet = new HashSet();
//    }
//    public Set<Notification> updateNotification(Notification notif) {
//        getNotifications().add(notif);
//        
//        
//        System.out.println("notifs SET="+notifSet);
//        return notifSet;
//    }

//    public String getPhotoString() {
//        return photoString;
//    }

//    public Set<Message> getMessageSet() {
//        return messageSet;
//    }
//
//    public void addMessageSet() {
//        this.messageSet = new HashSet();
//    }
//    public void updateMessageSet(Message msg) {
//        getMessageSet().add(msg);
//    }
//    public void setPhotoString(String photoString) {
//        this.photoString = photoString;
//    }

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }
}
