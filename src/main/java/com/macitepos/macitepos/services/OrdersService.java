package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.OrderDAO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {


    @Autowired
    private OrderDAO orderDAO;

    public List<Transaksi_penjualanDTO> showOrder(){
        System.out.println("Show All DAO Jalan");
        List<Transaksi_penjualan> m  = orderDAO.showOrder();
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

    List<Transaksi_penjualanDTO> convertToDTOAPI(List<Transaksi_penjualan> tps){
        List<Transaksi_penjualanDTO> dto = new ArrayList<>();
        for(Transaksi_penjualan tp : tps){
            dto.add(convertToDto(tp));
        }
        return dto;
    }

    Transaksi_penjualanDTO convertToDto(Transaksi_penjualan tp){
        Transaksi_penjualanDTO dto = new Transaksi_penjualanDTO(tp.getId_penjualan(), tp.getPengguna(), tp.getMember(), tp.getTotal_penjualan(), tp.getPembayaran_penjualan(), tp.getDiskon(), tp.getKembalian_penjualan(), tp.getCreated_at(), tp.getDetil_penjualans());
        return dto;
    }
}
