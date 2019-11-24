/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist_bit.studentyearbook.services;

import edu.kist_bit.studentyearbook.entity.TableStudent;
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
public class TableStudentJpaController implements Serializable {

    public TableStudentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TableStudent tableStudent) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tableStudent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TableStudent tableStudent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tableStudent = em.merge(tableStudent);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tableStudent.getId();
                if (findTableStudent(id) == null) {
                    throw new NonexistentEntityException("The tableStudent with id " + id + " no longer exists.");
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
            TableStudent tableStudent;
            try {
                tableStudent = em.getReference(TableStudent.class, id);
                tableStudent.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tableStudent with id " + id + " no longer exists.", enfe);
            }
            em.remove(tableStudent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TableStudent> findTableStudentEntities() {
        return findTableStudentEntities(true, -1, -1);
    }

    public List<TableStudent> findTableStudentEntities(int maxResults, int firstResult) {
        return findTableStudentEntities(false, maxResults, firstResult);
    }

    private List<TableStudent> findTableStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TableStudent.class));
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

    public TableStudent findTableStudent(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TableStudent.class, id);
        } finally {
            em.close();
        }
    }

    public int getTableStudentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TableStudent> rt = cq.from(TableStudent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
