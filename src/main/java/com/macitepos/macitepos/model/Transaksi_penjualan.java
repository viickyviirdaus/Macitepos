package com.macitepos.macitepos.model;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Transaksi_penjualan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_penjualan;

    @ManyToOne
    private Pengguna pengguna;

    @ManyToOne
    private Member member;

    private int total_penjualan;
    private int pembayaran_penjualan;
    private float diskon;
    private int kembalian_penjualan;
    private Timestamp created_at;

    @OneToMany(mappedBy = "transaksi_penjualan")
    private List<Detil_penjualan> detil_penjualans;


    public int getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(int id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getTotal_penjualan() {
        return total_penjualan;
    }

    public void setTotal_penjualan(int total_penjualan) {
        this.total_penjualan = total_penjualan;
    }

    public int getPembayaran_penjualan() {
        return pembayaran_penjualan;
    }

    public void setPembayaran_penjualan(int pembayaran_penjualan) {
        this.pembayaran_penjualan = pembayaran_penjualan;
    }

    public float getDiskon() {
        return diskon;
    }

    public void setDiskon(float diskon) {
        this.diskon = diskon;
    }

    public int getKembalian_penjualan() {
        return kembalian_penjualan;
    }

    public void setKembalian_penjualan(int kembalian_penjualan) {
        this.kembalian_penjualan = kembalian_penjualan;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.created_at = timestamp;
    }

    public List<Detil_penjualan> getDetil_penjualans() {
        return detil_penjualans;
    }

    public void setDetil_penjualans(List<Detil_penjualan> detil_penjualans) {
        this.detil_penjualans = detil_penjualans;
    }
}
