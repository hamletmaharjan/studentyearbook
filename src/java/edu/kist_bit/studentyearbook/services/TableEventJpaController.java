/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.services;

import edu.kist_bit.studentyearbook.entity.TableEvent;
import edu.kist_bit.studentyearbook.services.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hams
 */
public class TableEventJpaController implements Serializable {

    public TableEventJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TableEvent tableEvent) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tableEvent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TableEvent tableEvent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tableEvent = em.merge(tableEvent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tableEvent.getId();
                if (findTableEvent(id) == null) {
                    throw new NonexistentEntityException("The tableEvent with id " + id + " no longer exists.");
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
            TableEvent tableEvent;
            try {
                tableEvent = em.getReference(TableEvent.class, id);
                tableEvent.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tableEvent with id " + id + " no longer exists.", enfe);
            }
            em.remove(tableEvent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TableEvent> findTableEventEntities() {
        return findTableEventEntities(true, -1, -1);
    }

    public List<TableEvent> findTableEventEntities(int maxResults, int firstResult) {
        return findTableEventEntities(false, maxResults, firstResult);
    }

    private List<TableEvent> findTableEventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TableEvent.class));
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

    public TableEvent findTableEvent(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TableEvent.class, id);
        } finally {
            em.close();
        }
    }

    public int getTableEventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TableEvent> rt = cq.from(TableEvent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
