package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class WarehouseController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private AkunService akunService;


    @RequestMapping(value = "/warehouse")
    public String manajer(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "w_dashboard";
    }
}
