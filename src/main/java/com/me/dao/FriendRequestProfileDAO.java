package com.me.dao;

import static com.me.dao.FriendDAO.getSession;
import com.me.pojo.FriendProfile;
import com.me.pojo.FriendRequest;
import com.me.pojo.UserProfile;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manasa
 */
public class FriendRequestProfileDAO extends FriendRequestDAO {
 
    public boolean isFriend(int user1, int user2){
               beginTransaction();
        Criteria cr = getSession().createCriteria(FriendRequest.class);

        Criterion cr1 = Restrictions.eq("requestor", user1);
        Criterion cr2 = Restrictions.eq("recepient",user2);
        LogicalExpression exp = Restrictions.and(cr1, cr2);
        cr.add( exp );

        commit();
        List results = cr.list();
        if(results.size()==0){
            return false;
        }else{
        return true;
        }
    }  
            public List<FriendRequest> getList(int id){
        beginTransaction();
        String hql="FROM FriendRequest where recepient = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List result = query.list(); // many rows
        commit();
        return result;
    }

             public List<UserProfile> getUserList(int id){
        beginTransaction();
        String hql="FROM FriendRequest where recepient = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", id);
        List<FriendRequest> result = query.list(); // many rows
        //query.uniqueResult - one row
        commit();
        List<UserProfile> userList = new ArrayList<UserProfile>();
        List<UserProfile> fetchedList = new ArrayList<UserProfile>();
        if(result.size() ==0){
            return userList;
        }
        UserProfileDAO userDAO = new UserProfileDAO();
        for(FriendRequest request: result){
            userList.add(userDAO.filterId(request.getRequestor()));
        }
        for(UserProfile user: fetchedList){
            if(!userList.contains(user)){
            userList.add(user);
            }
        }
        return userList;
    }
               
            
        public boolean updateRequest(FriendRequest friendUser){
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernateFriendRequest.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(friendUser);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            return false;
        }
    }
           public boolean removeConnection(int id1, int id2){
              try{   
        beginTransaction();
        String hql="DELETE FriendRequest where recepient = :id1 and requestor = :id2";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id1", id1);
        query.setInteger("id2", id2);
       int result= query.executeUpdate();
        commit();
        if(result>=0){
    
           return true;
       }else{
           return false;
       }
        
              }catch(Exception e){
                  return false;
              }
    }
       
         
}
