package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.Detil_PenjualanDTO;
import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.services.DetilPenjualanService;
import com.macitepos.macitepos.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailPenjualanAPI {

    @Autowired
    DetilPenjualanService detilPenjualanService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping(value ="/api/lastOrder", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<Detil_PenjualanDTO> lastOrder(){
//        System.out.println("jalan");
//        List<Transaksi_penjualanDTO> tp = ordersService.showOrderDay();
//        System.out.println("setelah jalan");
        int id =5;
//        for (Transaksi_penjualanDTO d: tp) {
//            id = d.getId_penjualan();
//        }
        return detilPenjualanService.findByIdOrder(id);
    }

}
