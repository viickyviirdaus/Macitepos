package com.macitepos.macitepos.model;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "transaksi_penjualan")
public class Transaksi_penjualan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_penjualan;

    @ManyToOne
    private Pengguna pengguna;

    @ManyToOne
    private Member member;

//    @ManyToMany
//    private Detil_penjualan detil_penjualan;

    private Integer total_penjualan;

    private Integer pembayaran_penjualan;

    private Integer diskon;

    private Integer kembalian_penjualan;

    private Timestamp created_at;

//    @OneToMany(mappedBy = "transaksi_penjualan")
//    private List<Detil_penjualan> detil_penjualans;

    public Transaksi_penjualan(){}
    public Transaksi_penjualan(Integer id_penjualan, Pengguna pengguna, Member member, Integer total_penjualan, Integer pembayaran_penjualan, Integer diskon, Integer kembalian_penjualan, Timestamp created_at, List<Detil_penjualan> detil_penjualans) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        this.id_penjualan = id_penjualan;
        this.pengguna = pengguna;
        this.member = member;
        this.total_penjualan = total_penjualan;
        this.pembayaran_penjualan = pembayaran_penjualan;
        this.diskon = diskon;
        this.kembalian_penjualan = kembalian_penjualan;
        this.created_at = timestamp ;
//        this.detil_penjualans = detil_penjualans;
    }

    public Transaksi_penjualan(int id_penjualan, Pengguna pengguna, Member member, Integer total_penjualan, Integer pembayaran_penjualan, Integer diskon, Integer kembalian_penjualan, Timestamp timestamp) {
        this.id_penjualan = id_penjualan;
        this.pengguna = pengguna;
        this.member = member;
        this.total_penjualan = total_penjualan;
        this.pembayaran_penjualan = pembayaran_penjualan;
        this.diskon = diskon;
        this.kembalian_penjualan = kembalian_penjualan;
        this.created_at = timestamp;
    }

    public Transaksi_penjualan(Pengguna pengguna, Member member, Integer total_penjualan, Integer pembayaran_penjualan, Integer diskon, Integer kembalian_penjualan, Timestamp timestamp) {
        this.pengguna = pengguna;
        this.member = member;
        this.total_penjualan = total_penjualan;
        this.pembayaran_penjualan = pembayaran_penjualan;
        this.diskon = diskon;
        this.kembalian_penjualan = kembalian_penjualan;
        this.created_at = timestamp;
    }

    public Integer getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(Integer id_penjualan) {
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

    public Integer getTotal_penjualan() {
        return total_penjualan;
    }

    public void setTotal_penjualan(Integer total_penjualan) {
        this.total_penjualan = total_penjualan;
    }

    public Integer getPembayaran_penjualan() {
        return pembayaran_penjualan;
    }

    public void setPembayaran_penjualan(Integer pembayaran_penjualan) {
        this.pembayaran_penjualan = pembayaran_penjualan;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public Integer getKembalian_penjualan() {
        return kembalian_penjualan;
    }

    public void setKembalian_penjualan(Integer kembalian_penjualan) {
        this.kembalian_penjualan = kembalian_penjualan;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.created_at = timestamp;
    }

//    public List<Detil_penjualan> getDetil_penjualans() {
//        return detil_penjualans;
//    }
//
//    public void setDetil_penjualans(List<Detil_penjualan> detil_penjualans) {
//        this.detil_penjualans = detil_penjualans;
//    }
}
