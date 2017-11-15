package com.macitepos.macitepos.model;

import javax.persistence.*;

@Entity
public class Detil_penjualan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_detail_penjualan;

    @ManyToOne
    private Transaksi_penjualan transaksi_penjualan;

    @ManyToOne
    private Produk produk;

    public int getId_detail_penjualan() {
        return id_detail_penjualan;
    }

    public void setId_detail_penjualan(int id_detail_penjualan) {
        this.id_detail_penjualan = id_detail_penjualan;
    }

    public Transaksi_penjualan getTransaksi_penjualan() {
        return transaksi_penjualan;
    }

    public void setTransaksi_penjualan(Transaksi_penjualan transaksi_penjualan) {
        this.transaksi_penjualan = transaksi_penjualan;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }
}
