package com.macitepos.macitepos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.security.MessageDigest;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pengguna")
public class Pengguna implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_pengguna;

    @Column(name = "nama_pengguna")
    private String nama_pengguna;



    @Column(name = "username")
    private String username;

    @Column(name = "alamat_pengguna")
    private String alamat_pengguna;

    @Column(name = "tanggal_lahir")
    private String tanggal_lahir;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status_pengguna")
    private String status_pengguna;

    @Column(name = "foto_pengguna")
    private String foto_pengguna;



    @Column(name = "last_modified")
    private Date last_modified;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "level_pengguna")
    private String level_pengguna;




    public void setId(int id){
        this.id_pengguna  = id;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
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

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
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

    public void setPassword(String password) throws NoSuchAlgorithmException {
        //this.password = password;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff) + 0xff, 16).substring(1));
        }
        this.password = sb.toString();
    }

    public String getStatus_pengguna() {
        return status_pengguna;
    }

    public void setStatus_pengguna(String status_pengguna) {
        this.status_pengguna = status_pengguna;
    }

    public String getFoto_pengguna() {
        return foto_pengguna;
    }

    public void setFoto_pengguna(String foto_pengguna) {
        this.foto_pengguna = foto_pengguna;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getLevel_pengguna() {
        return level_pengguna;
    }

    public void setLevel_pengguna(String level_pengguna) {
        this.level_pengguna = level_pengguna;
    }

}