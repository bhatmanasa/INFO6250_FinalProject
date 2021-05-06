/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.FriendProfile;
import com.me.pojo.Notification;
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
public class NotificationProfileDAO extends DAO{
       public boolean addNotif(Notification n) {
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(n);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
        public List<Notification> getNotifs(int id){

        beginTransaction();
        String hql="FROM Notification where userId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List result = query.list(); // many rows
        //query.uniqueResult - one row
        commit();
        return result;
}
         public void removeNotifications(int id){
              try{   
        beginTransaction();
        String hql="DELETE from Notification where userId = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id",id);
       int result= query.executeUpdate();
        //query.uniqueResult - one row
        commit();
              }catch(Exception e){
                  System.out.println("Error deleting notifs");
              }     
              
         }
}
