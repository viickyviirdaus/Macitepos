package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdukAPI {
    @Autowired
    ProdukService produkService;

    @RequestMapping(path="/api/produk", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> Produk(){
        return produkService.showAll();
    }

    @RequestMapping(path="/api/produk/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void ProdukCreate(@RequestBody ProdukDTO produkDTO){
        ProdukDTO pDTO = produkService.saveOrUpdated(produkDTO);
    }


}
