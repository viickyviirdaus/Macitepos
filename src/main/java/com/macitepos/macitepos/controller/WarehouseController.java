package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class WarehouseController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private AkunService akunService;


    @RequestMapping(value = "/warehouse")
    public String warehouse(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "w_dashboard";
    }

    @RequestMapping(value = "/suplier")
    public String suplier(){
        return "w_adminSuplier";
    }

    @GetMapping(value = "/editProfileWA")
    public String editProfileWA(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));

        return "w_editProfile";
    }
}
