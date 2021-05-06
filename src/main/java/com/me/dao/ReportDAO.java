/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import static com.me.dao.DAO.getSession;
import com.me.pojo.Report;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Manasa
 */
public class ReportDAO extends DAO{
       public boolean addReport(Report n) {
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
           public List<Report> getReports(){

        beginTransaction();
        String hql="FROM Report where status = :id";
        org.hibernate.query.Query query = getSession().createQuery(hql);
        query.setInteger("id", 0);
        List result = query.list(); // many rows
        //query.uniqueResult - one row
        commit();
        return result;
} 

}
