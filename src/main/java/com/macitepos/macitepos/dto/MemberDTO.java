package com.macitepos.macitepos.dto;

import com.macitepos.macitepos.model.Member;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDTO {
    private Integer id_member;
    private String nama_member;
    private String alamat;
    private Date tanggal_lahir;
    private String jenis_kelamin;
    private String email;
    private Integer diskon;
    private Integer visit_count;
    private Timestamp last_visit;
    private String created_by;
    private Timestamp created_at;
    private Integer version;

    public MemberDTO(){};
    public MemberDTO(Integer id_member, String nama_member, String alamat, Date tanggal_lahir, String jenis_kelamin, String email,
                     Integer diskon, Integer visit_count, Timestamp last_visit, String created_by, Timestamp created_at,
                     Integer version) {
        this.id_member = id_member;
        this.nama_member = nama_member;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.email = email;
        this.diskon = diskon;
        this.visit_count = visit_count;
        this.last_visit = last_visit;
        this.created_by = created_by;
        this.created_at = created_at;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
