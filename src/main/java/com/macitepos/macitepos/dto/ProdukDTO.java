package com.macitepos.macitepos.dto;
import java.sql.Timestamp;

public class ProdukDTO {

    private int id_produk;
    private String nama_produk;
    private String kategori;
    private int harga_beli;
    private int harga_penjualan;
    private int stok_ulang;
    private int stok_total;
    private int stok_toko;
    private int stok_gudang;
    private int terjual;
    private String foto_produk;
    private String no_rak_toko;
    private Timestamp last_updated;
    private String updated_by;
    private String status_produk;
    private Timestamp created_at;
//    private List<Detil_penjualanDTO> detil_penjualans;
//    private List<Transaksi_pembelianDTO> transaksi_pembelians;


    public ProdukDTO(int id_produk, int stok_ulang, Timestamp last_updated) {
        this.id_produk = id_produk;
        this.stok_ulang = stok_ulang;
        this.last_updated = last_updated;
    }

    public ProdukDTO(){}
    public ProdukDTO(
            int id_produk,
            String nama_produk,
            String kategori,
            int harga_beli,
            int harga_penjualan,
            int stok_ulang,
            int stok_total,
            int stok_toko,
            int stok_gudang,
            int terjual,
            String foto_produk,
            String no_rak_toko,
            Timestamp last_updated,
            String updated_by,
            String status_produk,
            Timestamp created_at){
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.kategori = kategori;
        this.harga_beli = harga_beli;
        this.harga_penjualan = harga_penjualan;
        this.stok_ulang = stok_ulang;
        this.stok_total = stok_total;
        this.stok_toko = stok_toko;
        this.stok_gudang = stok_gudang;
        this.terjual = terjual;
        this.foto_produk = foto_produk;
        this.no_rak_toko = no_rak_toko;
        this.last_updated = last_updated;
        this.updated_by = updated_by;
        this.status_produk = status_produk;
        this.created_at = created_at;
    }




    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(int harga_beli) {
        this.harga_beli = harga_beli;
    }

    public int getHarga_penjualan() {
        return harga_penjualan;
    }

    public void setHarga_penjualan(int harga_penjualan) {
        this.harga_penjualan = harga_penjualan;
    }

    public int getStok_ulang() {
        return stok_ulang;
    }

    public void setStok_ulang(int stok_ulang) {
        this.stok_ulang = stok_ulang;
    }

    public int getStok_total() {
        return stok_total;
    }

    public void setStok_total(int stok_total) {
        this.stok_total = stok_total;
    }

    public int getStok_toko() {
        return stok_toko;
    }

    public void setStok_toko(int stok_toko) {
        this.stok_toko = stok_toko;
    }

    public int getTerjual() {
        return terjual;
    }

    public void setTerjual(int terjual) {
        this.terjual = terjual;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public void setFoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }

    public int getStok_gudang() {
        return stok_gudang;
    }

    public void setStok_gudang(int stok_gudang) {
        this.stok_gudang = stok_gudang;
    }

    public String getNo_rak_toko() {
        return no_rak_toko;
    }

    public void setNo_rak_toko(String no_rak_toko) {
        this.no_rak_toko = no_rak_toko;
    }

    public Timestamp getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Timestamp last_updated) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.last_updated = timestamp;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getStatus_produk() {
        return status_produk;
    }

    public void setStatus_produk(String status_produk) {
        this.status_produk = status_produk;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

//    public List<Detil_penjualanDTO> getDetil_penjualans() {
//        return detil_penjualans;
//    }
//
//    public void setDetil_penjualans(List<Detil_penjualanDTO> detil_penjualans) {
//        this.detil_penjualans = detil_penjualans;
//    }
//
//    public List<Transaksi_pembelianDTO> getTransaksi_pembelians() {
//        return transaksi_pembelians;
//    }
//
//    public void setTransaksi_pembelians(List<Transaksi_pembelianDTO> transaksi_pembelians) {
//        this.transaksi_pembelians = transaksi_pembelians;
//    }
}


