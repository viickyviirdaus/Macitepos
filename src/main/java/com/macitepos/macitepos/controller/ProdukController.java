package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProdukController {
    @Autowired
    private ProdukService produkService;

    @RequestMapping(value = "/produk", method = RequestMethod.GET)
    public String produk(Model model){
        System.out.println("CONTORLLER JALAN");
//        model.addAttribute("member", memberService.listMember());
//        return "m_customer"   ;
        model.addAttribute("produk",produkService.showAll());
        return "c_product";
    }

}
