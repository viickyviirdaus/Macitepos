package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Suplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_suplier;

    private String nama_suplier;
    private String alamat_suplier;
    private String no_telepon;
    private String foto_suplier;
    private String created_by;
    private String updated_by;
    private Timestamp created_at;

    @OneToMany(mappedBy = "suplier")
    private List<Transaksi_pembelian> transaksi_pembelians;

    public int getId_suplier() {
        return id_suplier;
    }

    public void setId_suplier(int id_suplier) {
        this.id_suplier = id_suplier;
    }

    public String getNama_suplier() {
        return nama_suplier;
    }

    public void setNama_suplier(String nama_suplier) {
        this.nama_suplier = nama_suplier;
    }

    public String getAlamat_suplier() {
        return alamat_suplier;
    }

    public void setAlamat_suplier(String alamat_suplier) {
        this.alamat_suplier = alamat_suplier;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getFoto_suplier() {
        return foto_suplier;
    }

    public void setFoto_suplier(String foto_suplier) {
        this.foto_suplier = foto_suplier;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<Transaksi_pembelian> getTransaksi_pembelians() {
        return transaksi_pembelians;
    }

    public void setTransaksi_pembelians(List<Transaksi_pembelian> transaksi_pembelians) {
        this.transaksi_pembelians = transaksi_pembelians;
    }
}
