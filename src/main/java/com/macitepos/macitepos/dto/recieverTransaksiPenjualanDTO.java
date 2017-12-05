package com.macitepos.macitepos.dto;

import java.util.List;

public class recieverTransaksiPenjualanDTO {

    private int id_produk;
    private int count_product;
    private int discount;
    private int cash;
    private int recievedAmount;
    private int total;
    private int id_member;
    private int visit_count;

    public recieverTransaksiPenjualanDTO(int id_produk, int count_product, int discount, int cash, int recievedAmount, int total, int id_member, int visit_count) {
        this.id_produk = id_produk;
        this.count_product = count_product;
        this.discount = discount;
        this.cash = cash;
        this.recievedAmount = recievedAmount;
        this.total = total;
        this.id_member = id_member;
        this.visit_count = visit_count;
    }

    public recieverTransaksiPenjualanDTO(List<recieverTransaksiPenjualanDTO> set){
        this.id_produk = set.get(0).id_produk;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getCount_product() {
        return count_product;
    }

    public void setCount_product(int count_product) {
        this.count_product = count_product;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getRecievedAmount() {
        return recievedAmount;
    }

    public void setRecievedAmount(int recievedAmount) {
        this.recievedAmount = recievedAmount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    public int getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(int visit_count) {
        this.visit_count = visit_count;
    }
}
