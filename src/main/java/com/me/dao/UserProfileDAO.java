/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.pojo.UserProfile;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Manasa
 */
public class UserProfileDAO extends DAO{
     @Autowired
    private SessionFactory sessionFactory;

    public UserProfile findAccount(String email) { // User creates account with email id, so fetch with email.
        UserProfile result =null;
       Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM user_profile_table u WHERE u.Email = :email";
        Query query = session.createSQLQuery(sql).addEntity(UserProfile.class);
        query.setString("email",email); 
        List res= query.getResultList();
        if(res.size()>0){
            result = (UserProfile)res.get(0);
            return result;
        }
         return result;
    }
   
    public UserProfile findAccountById(int id) { 
       UserProfile result =null;
       Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       Transaction tx = session.beginTransaction();
       UserProfile user = session.get(UserProfile.class, id);
        return user;
    }
    private boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
     }
    
     public String validateCredential(String email,String pwd) { // User creates account with email id, so fetch with email.
         
       UserProfile result =null;
       Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       Transaction tx = session.beginTransaction(); 
        String sql = "SELECT * FROM user_profile_table u WHERE u.Email = :email";
        Query query = session.createSQLQuery(sql).addEntity(UserProfile.class);
        query.setString("email",email); 
        List res= query.getResultList();
        if(res.size()>0){
            result = (UserProfile)res.get(0);
            String encrypt = result.getPassword();
            boolean check = checkPass(pwd,encrypt);
            if(check){
                return "valid";
            }else{
                return "invalidpassword";
            }
        }else{
           return "invalidemail";
        }
    }
    public List<UserProfile> filterName(String name){
        beginTransaction();
        Criteria cr = getSession().createCriteria(UserProfile.class);

        Criterion firstName = Restrictions.like("firstName", name);
        Criterion lastName = Restrictions.like("lastName",name);
        LogicalExpression orExp = Restrictions.or(firstName, lastName);
        cr.add( orExp );

        commit();
        List results = cr.list();
        return results;
    }  
    
 
    public boolean addAccount(UserProfile user) {
        try{
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(user);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void updateAccount(UserProfile profile) {
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       Transaction tx = session.beginTransaction();
       //search a profile
       UserProfile user = session.get(UserProfile.class, profile.getUserId());
       //update profile
       if(profile.getFirstName() != null){
      user.setFirstName(profile.getFirstName());
       }
      user.setLastName(profile.getLastName());
      user.setGender(profile.getGender());
      user.setContact(profile.getContact());
       if(profile.getPhoto() != null){
      user.setPhoto(profile.getPhoto());
       }
      user.setEmail(profile.getEmail());
      user.setCountry(profile.getCountry());
       user.setState(profile.getState());
      user.setPhotoAccess(profile.getPhotoAccess());
       session.update(user);
       tx.commit();
    }
 
    public void deleteAccount(int id) {
          Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       Transaction tx = session.beginTransaction();
       //search a movie
       UserProfile user = session.get(UserProfile.class, id);
        if(user!=null){
       session.delete(user);
        }
       tx.commit();
    }
        public UserProfile filterId(int id){
        beginTransaction();
        Criteria cr = getSession().createCriteria(UserProfile.class);

        Criterion idCheck = Restrictions.eq("id", id);
        cr.add(idCheck);

        commit();
        List results = cr.list();
        return (UserProfile)results.get(0);
    } 
 
}
