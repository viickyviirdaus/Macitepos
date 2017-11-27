package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PenggunasDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Pengguna saveOrUpdate(Pengguna pengguna){
        System.out.println("user save or update in");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pengguna saved = em.merge(pengguna);
        em.getTransaction().commit();
        em.close();
        System.out.println("user save or update out");
        return saved;
    }

    public List<Pengguna> showAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Pengguna", Pengguna.class);
        List<Pengguna> m = (List<Pengguna>) q.getResultList();
        em.close(); 
        System.out.println("Pengguna Service Show All");
        return m;
    }

}
