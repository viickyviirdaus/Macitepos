package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class TransaksiPenjualanDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }


    public Transaksi_penjualan saveOrUpdate(Transaksi_penjualan produk){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Transaksi_penjualan saved = em.merge(produk);
        em.getTransaction().commit();
        em.close();
        return saved;
    }
}
