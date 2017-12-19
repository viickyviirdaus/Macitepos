package com.macitepos.macitepos.api;

import com.google.gson.Gson;
import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
public class TransaksiPenjualanAPI {

    @Autowired
    DetilPenjualanService detilPenjualanService;
    @Autowired
    ProdukService produkService;
    @Autowired
    private AkunService akunService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MembersService membersService;



    @RequestMapping(value = "/api/create/transaksiPenjualan", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void create (@RequestBody String recievedJson){
        Gson gson = new Gson();
        RecieverTransaksiPenjualanDTO recieverTransaksiPenjualanDTO[] = gson.fromJson(recievedJson,RecieverTransaksiPenjualanDTO[].class);
        System.out.println("Data Transaksi Penjualan");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id_pengguna = akunService.findByUsername(authentication.getName()).getId_pengguna();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        int totalBarangDijual = 0;
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length; i++){
            totalBarangDijual += recieverTransaksiPenjualanDTO[i].getCount_product();
        }

        //Save Penjualan
        Transaksi_penjualanDTO t = ordersService.saveOrUpdated(recieverTransaksiPenjualanDTO[0], id_pengguna,totalBarangDijual, timestamp);

        //Save Detil Penjualan
        for (int i=0; i<recieverTransaksiPenjualanDTO.length;i++){
            detilPenjualanService.saveOrUpdated(t, recieverTransaksiPenjualanDTO[i]);
        }

        //Update Jumlah Berkunjung
        membersService.updateJumlahBerkunjung(recieverTransaksiPenjualanDTO[0].getId_member(),recieverTransaksiPenjualanDTO[0].getVisit_count(),timestamp);

        //Update Stok Toko dan Stok Total
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length;i++){
            produkService.updateStokTokodanTotal(recieverTransaksiPenjualanDTO[i].getId_produk(), recieverTransaksiPenjualanDTO[0].getCount_product());
        }


        //Update Jumlah Terjual
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length;i++){
            produkService.updateTerjual(recieverTransaksiPenjualanDTO[i].getId_produk(), recieverTransaksiPenjualanDTO[0].getCount_product());
        }

    }

    @RequestMapping(path="/api/order", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> order()
    {
        return ordersService.showOrder();
    }

    @RequestMapping(path = "/api/order/day", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaksi_penjualanDTO> orderDay(){
        return  ordersService.showOrderDay();
    }

}
