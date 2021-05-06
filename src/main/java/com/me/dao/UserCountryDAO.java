/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import com.me.Stats.UserCountry;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Manasa
 */
public class UserCountryDAO  extends DAO{
               
    public List<UserCountry> getUserCountryStat(String country){

        beginTransaction();  
        String hql="FROM UserCountry u where u.country =:country";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setString("country", country);
        List<UserCountry> result = query.list(); // many rows
        commit();
        return result;
    }
    
           public boolean updateStat(int count, String country) {
        try{
          beginTransaction();
         String hql2="FROM UserCountry where country = :id1";
        org.hibernate.query.Query query2 = getSession().createQuery(hql2);
        query2.setString("id1", country);
        List<UserCountry> result2 = query2.list(); // many rows
        commit();
         if(result2.size() > 0){
             int updateCount = result2.get(0).getCountUser() + count;
             String qryString3 = "update UserCountry s set s.countUser =:count where s.country=:country";
        org.hibernate.query.Query query3 = getSession().createQuery(qryString3);
        query3.setInteger("count", updateCount);
        query3.setString("country", country);
        query3.executeUpdate();
        commit();
        }else{
              addStat(count,country);
         }
       return true;
        }catch (Exception e){
            return false;
        }
       
    }
      public boolean addStat(int count,String country) {
        try{
            UserCountry uc = new UserCountry();
            uc.setCountUser(count);
            uc.setCountry(country);
        Configuration cfg = new Configuration();
       SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
       Session session=sf.openSession();
       session.persist(uc);
       Transaction tx = session.beginTransaction();
       tx.commit();
       return true;
        }catch (Exception e){
           e.printStackTrace();
           return false;
        }
    }
                
}
