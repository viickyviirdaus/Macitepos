package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller

public class KasirController {

    @Autowired
    AkunService akunService;

    @RequestMapping(value = "/kasir", method = RequestMethod.GET)
    public String kasir(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        return "c_dashboard";
    }

    @RequestMapping(value = "/kasir-product")
    public String product(){
        return "c_product";
    }

    @RequestMapping(value = "/kasir-orders")
    public String orders(){
        return "c_orders";
    }

    @RequestMapping(value = "/kasir-customer")
    public String customer(){
        return "c_customer";
    }

    @RequestMapping(value = "/kasir-report")
    public String report(){
        return "c_reportProduct";
    }

    @RequestMapping(value = "/kasir-user")
    public String user(){
        return "c_user";
    }

    @RequestMapping(value = "/kasir-profile", method = RequestMethod.GET)
    public String edit (){
        return "c_editProfil";
    }

}
