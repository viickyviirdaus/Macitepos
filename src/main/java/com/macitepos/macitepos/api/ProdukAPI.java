package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdukAPI {
    @Autowired
    ProdukService produkService;

    @RequestMapping(path="/api/produk", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> Produk(){
        return produkService.showAll();
    }

    @RequestMapping(path="/api/produkApproved", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> ProdukApproved(){
        return produkService.showAllApproved();
    }

    @RequestMapping(path="/api/produkApprovedForCashier", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> ProdukApprovedforCashier(){
        return produkService.showAllApprovedForCashier();
    }

    @RequestMapping(path="/api/produkDissapproved", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> ProdukDissapproved(){
        return produkService.showAllDissapproved();
    }


    @RequestMapping(path="/api/produk/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void ProdukCreate(@RequestBody ProdukDTO produkDTO){
        ProdukDTO pDTO = produkService.saveOrUpdated(produkDTO);
    }

    @RequestMapping(path = "/api/produk/{search}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Iterable<ProdukDTO> ProdukSearch(@PathVariable String search){
        System.out.println("parameter = " +search);
        if (search.equalsIgnoreCase("all")){
            return  produkService.showAllApproved();
        } else if(produkService.search(search).isEmpty()==false){
            System.out.println("api produk search jalan");
            return produkService.search(search);
        } else {
            System.out.println("ProdukAPI findByCategory jalan");
            return produkService.findByCategory(search);
        }
    }

    @RequestMapping(path = "/api/ubahStatus/{search}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProdukDTO ubahStatusProduk (@PathVariable Integer search){
        System.out.println("ini search "+search);
        return produkService.findById(search);
    }


}
