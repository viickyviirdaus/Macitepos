package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Detil_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class Detil_PenjualanDAO {

    private EntityManagerFactory emf;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void saveOrUpdate(Detil_penjualan detil_penjualan){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(detil_penjualan);
        em.getTransaction().commit();
        em.close();
    }

}
