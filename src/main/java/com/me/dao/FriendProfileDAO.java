/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import com.me.pojo.FriendProfile;
import com.me.pojo.UserProfile;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Manasa
 */
public class FriendProfileDAO extends FriendDAO{

    public boolean isFriend(int user1, int user2){
        
               beginTransaction();
        Criteria cr = getSession().createCriteria(FriendProfile.class);

        Criterion cr1 = Restrictions.eq("user1", user1);
        Criterion cr2 = Restrictions.eq("user2",user2);

// To get records matching with OR conditions
        LogicalExpression exp = Restrictions.and(cr1, cr2);
        cr.add( exp );

        commit();
        List results = cr.list();
        
        if(results.size()==0){
            beginTransaction();
        Criteria criteria = getSession().createCriteria(FriendProfile.class);

        Criterion cr12 = Restrictions.eq("user1", user2);
        Criterion cr22 = Restrictions.eq("user2",user1);

// To get records matching with OR conditions
        LogicalExpression exp2 = Restrictions.and(cr12, cr22);
        criteria.add( exp2 );

        commit();
        results = cr.list();
        }
        
        if(results.size()==0){
            return false;
        }else{
        return true;
        }
    }  
     public List<UserProfile> getUserList(int id){
        beginTransaction();
        String hql="FROM FriendProfile where user1 = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List<FriendProfile> results = query.list(); // many rows
        commit();
        
        List<UserProfile> userList = new ArrayList<UserProfile>();
        List<UserProfile> fetchedList = new ArrayList<UserProfile>();
        if(results.size() ==0){
            return userList;
        }
        UserProfileDAO userDAO = new UserProfileDAO();
        for(FriendProfile request: results){
            userList.add(userDAO.filterId(request.getUser2()));
        }
        for(UserProfile user: fetchedList){
            if(!userList.contains(user)){
            userList.add(user);
            }
        }
        return userList;
    }
        public List<FriendProfile> getList(){
        beginTransaction();
        String hql="FROM FriendProfile";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        List result = query.list(); // many rows
        //query.uniqueResult - one row
        commit();
        return result;
    }
    public List<FriendProfile> getFriendsList(int id){
        beginTransaction();
        Criteria cr = getSession().createCriteria(FriendProfile.class);
        Criterion cr1 = Restrictions.eq("user1", id);
        cr.add(cr1);
        commit();
        List results = cr.list();
        return results;
    }
       public boolean updateConnection(FriendProfile friendUser){
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernateFriend.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(friendUser);
       Transaction tx = session.beginTransaction();
       tx.commit();

       FriendProfile friendUser2 = new FriendProfile();
       friendUser2.setUser1(friendUser.getUser2());
       friendUser2.setUser2(friendUser.getUser1());
       Configuration cfg2 = new Configuration();
       SessionFactory sf2 = cfg.configure("hibernateFriend.cfg.xml").buildSessionFactory();
       Session session2=sf2.openSession();
       session2.persist(friendUser2);
       Transaction tx2 = session2.beginTransaction();
       tx2.commit();
       return true;
        }catch (Exception e){
            return false;
        }

    }
        public boolean removeConnection(int id1, int id2){
              try{   
        beginTransaction();
        String hql="DELETE FriendProfile where user1 = "+id1+" and user2 = "+id2;
        org.hibernate.query.Query query = getSession().createQuery(hql);
       int result= query.executeUpdate();
        commit();
        System.out.println("result in del query="+result);
        if(result>=0){
        String hql2="DELETE FriendProfile where user2 = "+id1+" and user1 = "+id2;
        org.hibernate.query.Query query2 = getSession().createQuery(hql2);
       result= query2.executeUpdate();
       if(result>=0){
           return true;
       }else{
           return false;
       }
        }else{
            return false;
        }
              }catch(Exception e){
                  return false;
              }
    }
       
}
