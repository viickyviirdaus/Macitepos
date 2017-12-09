package com.macitepos.macitepos.dto;

import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.model.Transaksi_penjualan;

public class Detil_PenjualanDTO {

    private int id_detail_penjualan;
    private Transaksi_penjualan transaksi_penjualan;
    private Produk produk;

    public Detil_PenjualanDTO(){}
    public Detil_PenjualanDTO(int id_detail_penjualan, Transaksi_penjualan transaksi_penjualan, Produk produk) {
        this.id_detail_penjualan = id_detail_penjualan;
        this.transaksi_penjualan = transaksi_penjualan;
        this.produk = produk;
    }

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
