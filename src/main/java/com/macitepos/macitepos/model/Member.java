package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_member;
    private String nama_member;
    private String alamat;
    private String pass;

    @Version
    @Column(name="opt_version", columnDefinition = "integer DEFAULT 0")
    private Integer version;

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) throws NoSuchAlgorithmException {
//        this.pass = pass;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff) + 0xff, 16).substring(1));
        }
        this.pass = sb.toString();
    }
}
