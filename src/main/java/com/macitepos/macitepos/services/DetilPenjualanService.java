package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.Detil_PenjualanDAO;
import com.macitepos.macitepos.dao.OrderDAO;
import com.macitepos.macitepos.dao.ProdukDAO;
import com.macitepos.macitepos.dto.Detil_PenjualanDTO;
import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.model.Detil_penjualan;
import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.model.Transaksi_penjualan;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetilPenjualanService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProdukDAO produkDAO;

    @Autowired
    private Detil_PenjualanDAO detil_penjualanDAO;

    public void saveOrUpdated(Transaksi_penjualanDTO transaksi_penjualanDTO, RecieverTransaksiPenjualanDTO r){
       Transaksi_penjualan order = orderDAO.findById(transaksi_penjualanDTO.getId_penjualan());
       Produk produk = produkDAO.findByIdSingle(r.getId_produk());
       Detil_penjualan detil_penjualan = new Detil_penjualan(0 ,order ,produk, r.getCount_product());
       detil_penjualanDAO.saveOrUpdate(detil_penjualan);

    }
}
