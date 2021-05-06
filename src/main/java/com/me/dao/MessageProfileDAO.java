/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.Comment;
import com.me.pojo.Message;
import java.util.ArrayList;
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
public class MessageProfileDAO extends MessageDAO{
               
    public List<Message> getMessage(int id1, int id2){
     List<Message> res = new ArrayList<Message>();
          beginTransaction();
         String hql="FROM Message where toId = :id1 and fromId = :id2";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id1", id1);
        query.setInteger("id2", id2);
        List<Message> result = query.list(); // many rows
        commit();
        for(Message mess : result){
            res.add(mess);
        }
                    beginTransaction();
         String hql2="FROM Message where fromId = :id1 and toId = :id2";
        org.hibernate.query.Query query2 = getSession().createQuery(hql2);
        query2.setInteger("id1", id1);
        query2.setInteger("id2",id2);
        List<Message> result2 = query2.list(); // many rows
        commit();
          for(Message mess : result2){
            res.add(mess);
        }                      
      return res;
    }
    
           public boolean addMessage(Message msg) {
      
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernateMessage.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(msg);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            return false;
        }
    }
}