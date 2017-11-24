package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdukAPI {
    @Autowired
    ProdukService produkService;

    @RequestMapping(path="/api/produk", method = RequestMethod.GET)
    public Iterable<ProdukDTO> Produk(){
        return produkService.showAll();
    }
}
