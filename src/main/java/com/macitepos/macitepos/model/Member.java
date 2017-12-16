package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_member;
    @Column(name = "nama_member")
    private String nama_member;
    @Column(name = "alamat_member")
    private String alamat;
    @Column(name = "tanggal_lahir")
    private Date tanggal_lahir;
    @Column(name = "jenis_kelamin")
    private String jenis_kelamin;
    @Column(name = "diskon")
    private Integer diskon;
    @Column(name = "visit_count")
    private Integer visit_count;
    @Column(name = "last_visit")
    private Timestamp last_visit;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "created_at")
    private Timestamp created_at;

    @Version
    @Column(name="opt_version", columnDefinition = "integer DEFAULT 0")
    private Integer version;

//    @OneToMany(mappedBy = "member")
//    private List<Transaksi_penjualan> transaksi_penjualans;

    public Member(){}
    public Member(Integer id_member, String nama_member, String alamat, Date tanggal_lahir, String jenis_kelamin, Integer diskon, Integer visit_count,
                  String created_by, Integer version) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.id_member = id_member;
        this.nama_member = nama_member;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.diskon = diskon;
        this.visit_count = visit_count;
        this.last_visit = timestamp;
        this.created_by = created_by;
        this.created_at = timestamp;
        this.version = version;
    }

    public Integer getId_member() {
        return id_member;
    }

    public void setId_member(Integer id_member) {
        this.id_member = id_member;
    }

    public String getNama_member() {
        return nama_member;
    }

    public void setNama_member(String nama_member) {
        this.nama_member = nama_member;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public Integer getVisitCount() {
        return visit_count;
    }

    public void setVisitCount(Integer visit_count) {
        this.visit_count = visit_count;
    }

    public Timestamp getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(Timestamp last_visit) {
        this.last_visit = last_visit;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

//    public List<Transaksi_penjualan> getTransaksi_penjualans() {
//        return transaksi_penjualans;
//    }
//
//    public void setTransaksi_penjualans(List<Transaksi_penjualan> transaksi_penjualans) {
//        this.transaksi_penjualans = transaksi_penjualans;
//    }
}
