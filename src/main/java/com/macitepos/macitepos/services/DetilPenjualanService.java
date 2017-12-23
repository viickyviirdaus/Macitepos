package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.Detil_PenjualanDAO;
import com.macitepos.macitepos.dao.OrdersDAO;
import com.macitepos.macitepos.dao.ProdukDAO;
import com.macitepos.macitepos.dto.Detil_PenjualanDTO;
import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.model.Detil_penjualan;
import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetilPenjualanService {

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private ProdukDAO produkDAO;

    @Autowired
    private Detil_PenjualanDAO detil_penjualanDAO;

    public void saveOrUpdated(Transaksi_penjualanDTO transaksi_penjualanDTO, RecieverTransaksiPenjualanDTO r){
       Transaksi_penjualan order = ordersDAO.findById(transaksi_penjualanDTO.getId_penjualan());
       Produk produk = produkDAO.findById(r.getId_produk());
       Detil_penjualan detil_penjualan = new Detil_penjualan(0 ,order ,produk, r.getCount_product());
       detil_penjualanDAO.saveOrUpdate(detil_penjualan);

    }

    public List<Detil_PenjualanDTO> findByIdOrder(int idOrder){
        List<Detil_penjualan> d = detil_penjualanDAO.findbyIiOrder(idOrder);
        System.out.println("wkwkwk");
        for (Detil_penjualan D: d
             ) {
            System.out.println("idDetildi DTO"+ D.getId_detail_penjualan());
        }
        return convertToDTOAPI(d);
    }

    List<Detil_PenjualanDTO> convertToDTOAPI(List<Detil_penjualan> dp){
        List<Detil_PenjualanDTO> dto = new ArrayList<>();
        for(Detil_penjualan d : dp){
            dto.add(convertToDto(d));
        }
        return dto;
    }

    Detil_PenjualanDTO convertToDto(Detil_penjualan d){
        Detil_PenjualanDTO dto = new Detil_PenjualanDTO(d.getId_detail_penjualan(),d.getJumlah_penjualan(),d.getTransaksi_penjualan(),d.getProduk());
        return dto;
    }
}
