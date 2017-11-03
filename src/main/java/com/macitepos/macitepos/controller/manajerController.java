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
@RequestMapping(value = "/manajer")
public class manajerController {
    @Autowired
    private AkunService akunService;
    @RequestMapping(method = RequestMethod.GET)
    public String manajer(HttpSession session, Pengguna pengguna, Model model) {

        session.setAttribute("nama", pengguna.getUsername());
        model.addAttribute("pengguna", akunService.listPengguna());
        return "/manajer/dashboard";
    }

}
