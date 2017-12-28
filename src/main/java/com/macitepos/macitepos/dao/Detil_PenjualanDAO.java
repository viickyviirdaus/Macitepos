package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Detil_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

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

    public List<Detil_penjualan> findbyIiOrder(int idOrder){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Detil_penjualan dt WHERE dt.transaksi_penjualan_id_penjualan=:idOrder", Detil_penjualan.class);
        q.setParameter("idOrder", idOrder);
        List<Detil_penjualan> d = (List<Detil_penjualan>) q.getResultList();
        em.close();
        return d;
    }

    public Detil_penjualan findById(int id_detil){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Detil_penjualan p = em.find(Detil_penjualan.class, id_detil);
        em.getTransaction().commit();
        em.close();
        return p;
    }
}
