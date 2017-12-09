package com.macitepos.macitepos.api;

import com.google.gson.Gson;
import com.macitepos.macitepos.dto.RecieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.DetilPenjualanService;
import com.macitepos.macitepos.services.ProdukService;
import com.macitepos.macitepos.services.TransaksiPenjualanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class TransaksiPenjualanAPI {
    
    @Autowired
    DetilPenjualanService detilPenjualanService;
    @Autowired
    ProdukService produkService;
    @Autowired
    private AkunService akunService;
    @Autowired
    private TransaksiPenjualanService transaksiPenjualanService;


    @RequestMapping(value = "/api/create/transaksiPenjualan", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void create (@RequestBody String recievedJson){
        Gson gson = new Gson();
        RecieverTransaksiPenjualanDTO recieverTransaksiPenjualanDTO[] = gson.fromJson(recievedJson,RecieverTransaksiPenjualanDTO[].class);
        System.out.println("Data Transaksi Penjualan");
        for (int i = 0; i<recieverTransaksiPenjualanDTO.length; i++){
            System.out.println("data indeks ke - "+i);
            System.out.println(recieverTransaksiPenjualanDTO[i].getCash());
            System.out.println(recieverTransaksiPenjualanDTO[i].getCount_product());
            System.out.println(recieverTransaksiPenjualanDTO[i].getDiscount());
            System.out.println(recieverTransaksiPenjualanDTO[i].getId_member());
            System.out.println(recieverTransaksiPenjualanDTO[i].getId_produk());
            System.out.println(recieverTransaksiPenjualanDTO[i].getRecievedAmount());
            System.out.println(recieverTransaksiPenjualanDTO[i].getTotal());
            System.out.println(recieverTransaksiPenjualanDTO[i].getVisit_count());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id_pengguna = akunService.findByUsername(authentication.getName()).getId_pengguna();
        System.out.println("ini gedang "+id_pengguna);
    }


}
