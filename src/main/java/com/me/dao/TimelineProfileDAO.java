/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.Post;
import com.me.pojo.Timeline;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 *
 * @author Manasa
 */
public class TimelineProfileDAO extends TimelineDAO{

       public boolean addTimeline(Timeline timeline) {
        try{
       Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernateTimeline.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(timeline);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            return false;
        }
    }    

 public List<Timeline> getTimeline(int userId){
   
        beginTransaction();
        String hql="FROM Timeline where userId = :userId";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("userId",userId);
        List result = query.list(); // many rows
        commit();

        return result;
    }
}

