package com.macitepos.macitepos.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

public class PenggunaDTO {

    private Integer id_pengguna;
    private String nama_pengguna;
    private String username;
    private String alamat_pengguna;
    private Date tanggal_lahir;
    private String email;
    private String password;
    private boolean status_pengguna;
    private String foto_pengguna;
    private Timestamp last_modified;
    private Timestamp created_at;
    private String level;

    public PenggunaDTO(){};

    public PenggunaDTO(String nama_pengguna, String username, String password, boolean status_pengguna, String level) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        this.id_pengguna = id_pengguna;
        this.nama_pengguna = nama_pengguna;
        this.username = username;
        this.password = password;
        this.status_pengguna = status_pengguna;
        this.created_at = timestamp;
        this.level = level;
    }

    public PenggunaDTO(Integer id_pengguna, String nama_pengguna, String username, String alamat_pengguna, Date tanggal_lahir, String email, String password, boolean status_pengguna, String foto_pengguna, Timestamp last_modified, Timestamp created_at, String level) {
        this.id_pengguna = id_pengguna;
        this.nama_pengguna = nama_pengguna;
        this.username = username;
        this.alamat_pengguna = alamat_pengguna;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
        this.status_pengguna = status_pengguna;
        this.foto_pengguna = foto_pengguna;
        this.last_modified = last_modified;
        this.created_at = created_at;
        this.level = level;
    }

    public Integer getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(Integer id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getNama_pengguna() {
        return nama_pengguna;
    }

    public void setNama_pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat_pengguna() {
        return alamat_pengguna;
    }

    public void setAlamat_pengguna(String alamat_pengguna) {
        this.alamat_pengguna = alamat_pengguna;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus_pengguna() {
        return status_pengguna;
    }

    public void setStatus_pengguna(boolean status_pengguna) {
        this.status_pengguna = status_pengguna;
    }

    public String getFoto_pengguna() {
        return foto_pengguna;
    }

    public void setFoto_pengguna(String foto) {    this.foto_pengguna = foto;    }

    public Timestamp getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Timestamp last_modified) {
        this.last_modified = last_modified;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}