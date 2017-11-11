package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller

public class manajerController {
    @Autowired
    private AkunService akunService;

    @RequestMapping(value = "/manajer")
    public String manajer(HttpSession session, Pengguna pengguna, Model model) {
        session.setAttribute("nama", pengguna.getUsername());
        model.addAttribute("pengguna", akunService.listPengguna());
        return "m_dashboard";
    }

    @GetMapping(value = "/product")
    public String product(ModelMap modelMap){

        return "m_product";
    }

    @GetMapping(value = "/order")
    public String order(){
        return "m_orders";
    }

    @GetMapping(value = "/customer")
    public String customer(){
        return "m_customer";
    }

    @GetMapping(value = "/reportProduct")
    public String reportProduct(){
        return "m_reportProduct";
    }

    @GetMapping(value = "/reportPayment")
    public String reportPayment(){
        return "m_reportPayment";
    }

    @GetMapping(value = "/user")
    public String user(Model model) {
            model.addAttribute("pengguna", akunService.listPengguna());
        return "m_user";
    }

    @GetMapping(value = "/edit")
    public String edit(){
        return "m_editProfil";
    }

}
