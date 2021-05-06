/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;

import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Manasa
 */
public class PostDAO {
   private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal session = new ThreadLocal();
	private static final SessionFactory sessionFactory = 
			new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	protected PostDAO() {
		
	}
	
	public static Session getSession() {
		Session session = (Session) PostDAO.session.get();
		if(session == null) {
			session = sessionFactory.openSession();
			PostDAO.session.set(session);
		};
		return session;
	}
	
	protected void beginTransaction() {
		getSession().beginTransaction();
	}
	
	protected void commit() {
		getSession().getTransaction().commit();
	}
	
	protected void rollback() {
		getSession().getTransaction().rollback(); //rollback transaction
		getSession().close(); //close session
		PostDAO.session.set(null); //remove session from thread local
	}
	
	public static void close() {
		getSession().close(); //close session
		PostDAO.session.set(null); //remove session from thread local
	}
        
}


