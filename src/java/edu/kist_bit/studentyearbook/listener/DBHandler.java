/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author hams
 */
public class DBHandler implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentYearbookPU");
        sce.getServletContext().setAttribute("StudentYearbookemf",emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       EntityManagerFactory emf =  (EntityManagerFactory) sce.getServletContext().getAttribute("StudentYearbookemf");
       emf.close();
    }
    
    
}
