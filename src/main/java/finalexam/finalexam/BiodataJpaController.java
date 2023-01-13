/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalexam.finalexam;

import finalexam.finalexam.exceptions.NonexistentEntityException;
import finalexam.finalexam.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ACER
 */
public class BiodataJpaController implements Serializable {

    public BiodataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalexam_finalexam_jar_0.0.1-SNAPSHOTPU");
    
    public BiodataJpaController(){
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Biodata biodata) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(biodata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBiodata(biodata.getId()) != null) {
                throw new PreexistingEntityException("Biodata " + biodata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Biodata biodata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            biodata = em.merge(biodata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = biodata.getId();
                if (findBiodata(id) == null) {
                    throw new NonexistentEntityException("The biodata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Biodata biodata;
            try {
                biodata = em.getReference(Biodata.class, id);
                biodata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The biodata with id " + id + " no longer exists.", enfe);
            }
            em.remove(biodata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Biodata> findBiodataEntities() {
        return findBiodataEntities(true, -1, -1);
    }

    public List<Biodata> findBiodataEntities(int maxResults, int firstResult) {
        return findBiodataEntities(false, maxResults, firstResult);
    }

    private List<Biodata> findBiodataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Biodata.class));
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

    public Biodata findBiodata(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Biodata.class, id);
        } finally {
            em.close();
        }
    }

    public int getBiodataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Biodata> rt = cq.from(Biodata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
