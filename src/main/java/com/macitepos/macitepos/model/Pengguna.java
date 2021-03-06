package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.sql.Timestamp;


import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pengguna")
public class Pengguna implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_pengguna;

    @Column(name = "nama_pengguna")
    private String nama_pengguna;

    @Column(name = "username")
    private String username;

    @Column(name = "alamat_pengguna")
    private String alamat_pengguna;

    @Column(name = "tanggal_lahir")
    private Date tanggal_lahir;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status_pengguna")
    private boolean status_pengguna;

    @Column(name = "foto_pengguna")
    private String foto_pengguna;

    @Column(name = "last_modified")
    private Timestamp last_modified;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "level")
    private String level;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_pengguna"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


//    @OneToMany(mappedBy = "pengguna")
//    private List<Transaksi_penjualan> transaksi_penjualans;
//
//    @OneToMany(mappedBy = "pengguna")
//    private List<Transaksi_pembelian> transaksi_pembelians;

    public Pengguna(){}
    public Pengguna(Integer id_pengguna, String nama_pengguna, String username, String alamat_pengguna, Date tanggal_lahir, String email, String password, boolean status_pengguna, String foto_pengguna, Timestamp last_modified, Timestamp created_at, String level) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.id_pengguna = id_pengguna;
        this.nama_pengguna = nama_pengguna;
        this.username = username;
        this.alamat_pengguna = alamat_pengguna;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
        this.status_pengguna = status_pengguna;
        this.foto_pengguna = foto_pengguna;
        this.last_modified = timestamp;
        this.created_at = created_at;
        this.level = level;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public void setId(Integer id){
        this.id_pengguna  = id;
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

    public void setTanggal_lahir(Date tanggal_lahir) { this.tanggal_lahir = tanggal_lahir; }

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
        this.password = password;

//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(password.getBytes());
//
//        byte byteData[] = md.digest();
//
//        StringBuffer sb = new StringBuffer();
//        for (Integer i = 0; i < byteData.length; i++){
//            sb.append(Integer.toString((byteData[i] & 0xff) + 0xff, 16).substring(1));
//        }
//        this.password = sb.toString();
    }

    public boolean getStatus_pengguna() {
        return status_pengguna;
    }

    public void setStatus_pengguna(boolean status_pengguna) {
        this.status_pengguna = status_pengguna;
    }

    public String getFoto_pengguna() {
        return foto_pengguna;
    }

    public void setFoto_pengguna(String foto_pengguna) {
        this.foto_pengguna = foto_pengguna;
    }

    public Timestamp getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.last_modified = timestamp;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.created_at = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setLast_modified(Timestamp last_modified) {
        this.last_modified = last_modified;
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
//
//    public List<Transaksi_pembelian> getTransaksi_pembelians() {
//        return transaksi_pembelians;
//    }
//
//    public void setTransaksi_pembelians(List<Transaksi_pembelian> transaksi_pembelians) {
//        this.transaksi_pembelians = transaksi_pembelians;
//    }
}