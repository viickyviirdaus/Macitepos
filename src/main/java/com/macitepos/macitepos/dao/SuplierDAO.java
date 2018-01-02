//package com.macitepos.macitepos.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//public class SuplierDAO {
//    private EntityManagerFactory emf;
//    @Autowired
//    public void setEntityManagerFactory (EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//
//    public List<Suplier> showAll () {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Query q = em.createNativeQuery("SELECT * FROM Suplier", Suplier.class);
//        List<Suplier> s = q.getResultList();
//        em.close();
//        return s;
//    }
//}
