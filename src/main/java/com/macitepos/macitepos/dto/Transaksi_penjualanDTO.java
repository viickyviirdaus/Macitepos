package com.macitepos.macitepos.dto;

<<<<<<< HEAD
=======
import com.macitepos.macitepos.model.Detil_penjualan;
import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.model.Pengguna;

import java.sql.Timestamp;
import java.util.List;
>>>>>>> 9e144be6e73c19507568cba5c5e6ab4837e25f0f

public class Transaksi_penjualanDTO {

    private Integer id_penjualan;
    private Pengguna pengguna;
    private Member member;
    private Integer total_penjualan;
    private Integer pembayaran_penjualan;
    private Float diskon;
    private Integer kembalian_penjualan;
    private Timestamp created_at;
    private List<Detil_penjualan> detil_penjualans;

    public Transaksi_penjualanDTO(Integer id_penjualan, Pengguna pengguna, Member member, Integer total_penjualan, Integer pembayaran_penjualan, Float diskon, Integer kembalian_penjualan, Timestamp created_at, List<Detil_penjualan> detil_penjualans) {
        this.id_penjualan = id_penjualan;
        this.pengguna = pengguna;
        this.member = member;
        this.total_penjualan = total_penjualan;
        this.pembayaran_penjualan = pembayaran_penjualan;
        this.diskon = diskon;
        this.kembalian_penjualan = kembalian_penjualan;
        this.created_at = created_at;
        this.detil_penjualans = detil_penjualans;
    }

    public Integer getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(Integer id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getTotal_penjualan() {
        return total_penjualan;
    }

    public void setTotal_penjualan(Integer total_penjualan) {
        this.total_penjualan = total_penjualan;
    }

    public Integer getPembayaran_penjualan() {
        return pembayaran_penjualan;
    }

    public void setPembayaran_penjualan(Integer pembayaran_penjualan) {
        this.pembayaran_penjualan = pembayaran_penjualan;
    }

    public Float getDiskon() {
        return diskon;
    }

    public void setDiskon(Float diskon) {
        this.diskon = diskon;
    }

    public Integer getKembalian_penjualan() {
        return kembalian_penjualan;
    }

    public void setKembalian_penjualan(Integer kembalian_penjualan) {
        this.kembalian_penjualan = kembalian_penjualan;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public List<Detil_penjualan> getDetil_penjualans() {
        return detil_penjualans;
    }

    public void setDetil_penjualans(List<Detil_penjualan> detil_penjualans) {
        this.detil_penjualans = detil_penjualans;
    }
}
