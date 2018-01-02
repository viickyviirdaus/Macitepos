package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Transaksi_pembelian {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_pembelian;

//    @ManyToOne
//    private Suplier suplier;

    @ManyToOne
    private Produk produk;

    @ManyToOne
    private Pengguna pengguna;

    private int total_pembelian;

    private  int total_restock;

    private Timestamp created_at;

    public int getId_pembelian() {
        return id_pembelian;
    }

    public void setId_pembelian(int id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

//    public Suplier getSuplier() {
//        return suplier;
//    }
//
//    public void setSuplier(Suplier suplier) {
//        this.suplier = suplier;
//    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public int getTotal_pembelian() {
        return total_pembelian;
    }

    public void setTotal_pembelian(int total_pembelian) {
        this.total_pembelian = total_pembelian;
    }

    public int getTotal_restock() {
        return total_restock;
    }

    public void setTotal_restock(int total_restock) {
        this.total_restock = total_restock;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
