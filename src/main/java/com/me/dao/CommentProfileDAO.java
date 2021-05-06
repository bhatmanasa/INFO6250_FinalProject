/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.Comment;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Manasa
 */
public class CommentProfileDAO extends DAO{
               
    public List<Comment> getComments(int id){

         beginTransaction();
         String hql="FROM Comment where postId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List<Comment> result = query.list(); // many rows
        commit();        
        return result;
    }
    
    public boolean addComment(Comment c) {

        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(c);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            return false;
        }
    }
}