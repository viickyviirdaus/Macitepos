package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProdukDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Produk saveOrUpdate(Produk produk){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Produk saved = em.merge(produk);
        em.getTransaction().commit();
        em.close();
        return saved;
    }

    public List<Produk> showAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Produk", Produk.class);
        List<Produk> p = (List<Produk>) q.getResultList();
        em.close();
        return p;
    }

    public List<Produk> findByKategori(String kategori){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Produk p WHERE p.kategori=:kategori", Produk.class);
        q.setParameter("kategori", kategori);
        List<Produk> p = (List<Produk>)q.getResultList();
        em.close();
        return  p;
    }

//    public Produk findByID(int id_produk){
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Query q = em.createNativeQuery("SELECT * from Produk p  WHERE p.id_produk=:id",Produk.class);
//        q.setParameter("id", id_produk);
//        Produk produk = produk
//    }



}