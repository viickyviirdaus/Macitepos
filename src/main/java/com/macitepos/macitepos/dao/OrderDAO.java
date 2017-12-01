package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
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
}
