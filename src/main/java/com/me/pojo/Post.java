/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.pojo;
import com.me.pojo.UserProfile;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Manasa
 */
@Entity
@Table(name="post_table")
public class Post {
    @Id
@GeneratedValue(strategy=GenerationType.IDENTITY)  
    int postId;
    @Column(name="Description")
    String description;
    @ManyToOne
    @JoinTable (name="link_user_post", joinColumns = @JoinColumn(name = "postId"), inverseJoinColumns = @JoinColumn(name = "userId"))
    UserProfile user;
    @Column(name="UserId")
    int userId;
    @Column(name="FileName")
    String fileName;
    @Column(name="DateTime")
    Date dateTime;
    @Transient
    MultipartFile postFile;    
    @Transient
    UserProfile userProfile;
    @OneToMany
    @JoinTable (name="link_comment_post_tbl", joinColumns = @JoinColumn(name = "post"), inverseJoinColumns = @JoinColumn(name = "comment"))
    private Set<Comment> commentSet = new HashSet<Comment>();
    
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userid) {
        this.userId = userid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MultipartFile getPostFile() {
        return postFile;
    }

    public void setPostFile(MultipartFile postFile) {
        this.postFile = postFile;
    }
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set commentSet) {
        this.commentSet = commentSet;
    }
    public void addNewCommentSet() {
        this.commentSet = new HashSet();
    }
    public void updateCommentSet(Comment comment) {
        getCommentSet().add(comment);
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    
}
