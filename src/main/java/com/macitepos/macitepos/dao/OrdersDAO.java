package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class OrdersDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Transaksi_penjualan saveOrUpdate(Transaksi_penjualan transaksi_penjualan){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Transaksi_penjualan saved = em.merge(transaksi_penjualan);
        em.getTransaction().commit();
        em.close();
        return saved;
    }

    public Transaksi_penjualan findById(int id_transaksi_penjualan){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Transaksi_penjualan t = em.find(Transaksi_penjualan.class, id_transaksi_penjualan);
        em.getTransaction().commit();
        em.close();
        return t;
    }



    public List<Transaksi_penjualan> showOrder(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("show order in");
        Query q = em.createNativeQuery("SELECT * FROM Transaksi_penjualan", Transaksi_penjualan.class);
        //Query q = em.createNativeQuery("select tp.member_id_member, m.nama_member, tp.diskon, tp.total_penjualan, tp.pembayaran_penjualan, tp.kembalian_penjualan, p.id_pengguna, p.created_at from transaksi_penjualan tp  JOIN member m ON tp.member_id_member = m.id_member LEFT JOIN pengguna p ON tp.pengguna_id_pengguna = p.id_pengguna", Transaksi_penjualan.class);
        List<Transaksi_penjualan> p = (List<Transaksi_penjualan>) q.getResultList();
        System.out.println("show order out");
        em.close();
        return p;
    }

    public List<Transaksi_penjualan> showOrderDay(Date today){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Transaksi_penjualan t WHERE t.created_at >=:today", Transaksi_penjualan.class);
        System.out.println(today);
        q.setParameter("today", today);
        List<Transaksi_penjualan> t = (List<Transaksi_penjualan>) q.getResultList();
        em.close();
        return t;
    }

    public List<Transaksi_penjualan> findLastOrder(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Transaksi_penjualan t ORDER BY t.id_penjualan DESC LIMIT 1",Transaksi_penjualan.class);
        List<Transaksi_penjualan> p = (List<Transaksi_penjualan>) q.getResultList();
        em.close();
        return p;
    }

    public List<Transaksi_penjualan> generalLedger(String waktu){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT id_penjualan, date_trunc(:waktu, created_at) created_at, diskon, kembalian_penjualan, pembayaran_penjualan, total_penjualan, member_id_member, pengguna_id_pengguna, total_barang_dijual FROM Transaksi_penjualan GROUP BY id_penjualan ORDER BY created_at DESC",Transaksi_penjualan.class);
        q.setParameter("waktu",waktu);
        List<Transaksi_penjualan> dto = (List<Transaksi_penjualan>) q.getResultList();
        em.close();
        return dto;
    }

    public List<Transaksi_penjualan> grafik(String waktu){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT id_penjualan, date_trunc(:waktu, created_at) created_at, diskon, kembalian_penjualan, pembayaran_penjualan, total_penjualan, member_id_member, pengguna_id_pengguna, total_barang_dijual FROM Transaksi_penjualan GROUP BY id_penjualan ORDER BY created_at ASC",Transaksi_penjualan.class);
        q.setParameter("waktu",waktu);
        List<Transaksi_penjualan> dto = (List<Transaksi_penjualan>) q.getResultList();
        em.close();
        return dto;
    }

    public Long jumlahOrder(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("show order in");
        long q = (Long) em.createQuery("SELECT count(t) FROM Transaksi_penjualan t").getSingleResult();
        System.out.println("show order out");
        em.close();
        return q;
    }

    public Long jumlahPayment(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("show order in");
        long q = (Long) em.createQuery("SELECT sum(t.total_penjualan) FROM Transaksi_penjualan t").getSingleResult();
        System.out.println("show order out");
        em.close();
        return q;
    }

}
