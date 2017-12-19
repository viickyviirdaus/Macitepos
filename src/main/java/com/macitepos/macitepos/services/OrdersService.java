package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.MembersDAO;
import com.macitepos.macitepos.dao.OrdersDAO;
import com.macitepos.macitepos.dao.PenggunasDAO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;

import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {


    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private PenggunasDAO penggunasDAO;

    @Autowired
    private MembersDAO membersDAO;

    public Transaksi_penjualanDTO saveOrUpdated(RecieverTransaksiPenjualanDTO rtp, int id_pengguna, int totalBarangDIjual, Timestamp timestamp){
        Pengguna p = penggunasDAO.findById(id_pengguna);
        Member m  = membersDAO.findOneMemberById(rtp.getId_member());
        Transaksi_penjualan transaksi_penjualan = new Transaksi_penjualan(p, m, rtp.getTotal(), totalBarangDIjual, rtp.getRecievedAmount(), rtp.getDiscount(), rtp.getCash(),timestamp);
        transaksi_penjualan = ordersDAO.saveOrUpdate(transaksi_penjualan);
        System.out.println(transaksi_penjualan.getId_penjualan());
        return convertToDto(transaksi_penjualan);
    }

    public List<Transaksi_penjualanDTO> showOrder(){
        System.out.println("Show All DAO Jalan");
        List<Transaksi_penjualan> m  = ordersDAO.showOrder();
        if(m.isEmpty()){
            System.out.println("Kosong");
        }
        for (Transaksi_penjualan ms: m
                ) {
            System.out.println("diskon di service");
            System.out.println(ms.getDiskon());
        }
        return convertToDTOAPI(m);
    }

    public List<Transaksi_penjualanDTO> showOrderDay(){
//        List<Transaksi_penjualan> t =
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String d = dateFormat.format(date).toString();
        System.out.println(d);
        List<Transaksi_penjualan> t = ordersDAO.showOrderDay(date);
        System.out.println(t);
        return convertToDTOAPI(t);
    }


    List<Transaksi_penjualanDTO> convertToDTOAPI(List<Transaksi_penjualan> tps){
        List<Transaksi_penjualanDTO> dto = new ArrayList<>();
        for(Transaksi_penjualan tp : tps){
            dto.add(convertToDto(tp));
        }
        return dto;
    }

    Transaksi_penjualanDTO convertToDto(Transaksi_penjualan tp){
        Transaksi_penjualanDTO dto = new Transaksi_penjualanDTO(tp.getId_penjualan(), tp.getPengguna(), tp.getMember(), tp.getTotal_penjualan(), tp.getTotal_barang_dijual(), tp.getPembayaran_penjualan(), tp.getDiskon(), tp.getKembalian_penjualan(), tp.getCreated_at());
        return dto;
    }

    Transaksi_penjualanDTO convertToDtoSingle(Transaksi_penjualan tp){
        Transaksi_penjualanDTO dto = new Transaksi_penjualanDTO(tp.getId_penjualan(), tp.getPengguna(), tp.getMember(), tp.getTotal_penjualan(), tp.getTotal_barang_dijual(), tp.getPembayaran_penjualan(), tp.getDiskon(), tp.getKembalian_penjualan(), tp.getCreated_at());
        return dto;
    }

    public Long jumlahOrder(){
        return ordersDAO.jumlahOrder();
    }
}
