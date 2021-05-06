/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import static com.me.dao.PostDAO.getSession;
import com.me.pojo.FriendProfile;
import com.me.pojo.UserProfile;
import com.me.pojo.Post;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Manasa
 */
public class PostProfileDAO extends PostDAO{
       public boolean addPost(Post post) {
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(post);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
           e.printStackTrace();
           return false;
        }
    }
           public List<Post> getPosts(int id, List<FriendProfile> users,boolean self){
         
             
        List<Post> postList = new ArrayList<Post>();
              if(!self){
        for(FriendProfile friend : users){
             beginTransaction();
         String hql="FROM Post where userId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id",friend.getUser2());
        List result = query.list(); // many rows
        commit();
        for(int i=0; i<result.size();i++){
            postList.add((Post)result.get(i));
        }
                  
        }
               }
   
        beginTransaction();
        String hql="FROM Post where userId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List result = query.list(); // many rows
        commit();
        //query.uniqueResult - one row
        for(int i=0; i<result.size();i++){
            postList.add((Post)result.get(i));
        }
        return postList;
         }

        public Post getPostByPostId(int id){
         
             beginTransaction();
         String hql="FROM Post where postId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id",id);
        List<Post> result = query.list(); // many rows
        commit();
        return result.get(0);
    }
}