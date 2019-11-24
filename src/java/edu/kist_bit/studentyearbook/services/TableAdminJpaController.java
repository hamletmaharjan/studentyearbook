/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.services;

import edu.kist_bit.studentyearbook.entity.TableAdmin;
import edu.kist_bit.studentyearbook.services.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hams
 */
public class TableAdminJpaController implements Serializable {

    public TableAdminJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TableAdmin tableAdmin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tableAdmin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TableAdmin tableAdmin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tableAdmin = em.merge(tableAdmin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tableAdmin.getId();
                if (findTableAdmin(id) == null) {
                    throw new NonexistentEntityException("The tableAdmin with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TableAdmin tableAdmin;
            try {
                tableAdmin = em.getReference(TableAdmin.class, id);
                tableAdmin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tableAdmin with id " + id + " no longer exists.", enfe);
            }
            em.remove(tableAdmin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TableAdmin> findTableAdminEntities() {
        return findTableAdminEntities(true, -1, -1);
    }

    public List<TableAdmin> findTableAdminEntities(int maxResults, int firstResult) {
        return findTableAdminEntities(false, maxResults, firstResult);
    }

    private List<TableAdmin> findTableAdminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TableAdmin.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TableAdmin findTableAdmin(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TableAdmin.class, id);
        } finally {
            em.close();
        }
    }

    public int getTableAdminCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TableAdmin> rt = cq.from(TableAdmin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TableAdmin checkLogin(String email) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        
        TableAdmin results = null;
        
        try{
            results = (TableAdmin) em.createNamedQuery("TableAdmin.findByEmail").setParameter("email",email).getSingleResult();
        } catch(NullPointerException | NoResultException e){
            throw new NonexistentEntityException("error occured");
        }
        return results;
        
    }
}