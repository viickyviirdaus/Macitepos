package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String kasir(HttpSession session, Pengguna pengguna, Model model){
        session.setAttribute("nama", pengguna.getUsername());
        session.setAttribute("pengguna", akunService.listPengguna());
        return "c_dashboard";
    }

    @RequestMapping(value = "/kasir-edit", method = RequestMethod.GET)
    public String edit (){
        return "c_editProfil";
    }
}
