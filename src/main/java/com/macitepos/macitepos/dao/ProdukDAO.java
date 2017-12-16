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
        Query q = em.createNativeQuery("SELECT * FROM Produk WHERE Produk.stok_toko > 0", Produk.class);
        List<Produk> p = (List<Produk>) q.getResultList();
        em.close();
        return p;
    }



    public List<Produk> findByCategori(String category){
        System.out.println("parameter category di Produk Dao find by Category" + category);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Produk p WHERE p.stok_toko > 0 AND p.kategori=:category", Produk.class);
        q.setParameter("category", category);
        List<Produk> p = (List<Produk>)q.getResultList();
        em.close();
        return  p;
    }

    public List<Produk> findByID(int ID){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Produk p WHERE p.id_produk=:ID", Produk.class);
        q.setParameter("ID", ID);
        List<Produk> p = (List<Produk>)q.getResultList();
        em.close();
        return  p;
    }

    public Produk findByIdSingle(int ID){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Produk p = em.find(Produk.class, ID);
        em.getTransaction().commit();
        em.close();
        return p;
    }


    public List<Produk> findByKey(String keySearch){
        System.out.println("parameter key di ProdukDao findByKey "+keySearch);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Produk p WHERE p.nama_produk LIKE :keySearch", Produk.class);
        q.setParameter("keySearch", "%"+ keySearch +"%");
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
