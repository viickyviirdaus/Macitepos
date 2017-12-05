package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.Transaksi_penjualanDTO;
import com.macitepos.macitepos.dto.recieverTransaksiPenjualanDTO;
import com.macitepos.macitepos.services.DetilPenjualanService;
import com.macitepos.macitepos.services.ProdukService;
import com.macitepos.macitepos.services.TransaksiPenjualanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
public class TransaksiPenjualanAPI {
    @Autowired
    TransaksiPenjualanService transaksiPenjualanService;
    @Autowired
    DetilPenjualanService detilPenjualanService;
    @Autowired
    ProdukService produkService;

    @RequestMapping(value = "/api/transaksiPenjualan/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody List<recieverTransaksiPenjualanDTO> recievedJson){

//        for ( Object isi:   recievedJson   ) {
        for (int i = 0; i<1; i++){
            System.out.println(recievedJson);

        }
//        }
//        ProdukDTO pDTO = produkService.saveOrUpdated(produkDTO);
//        return new ResponseEntity<recieverTransaksiPenjualanDTO>(recievedJson, HttpStatus.OK);
    }


}
