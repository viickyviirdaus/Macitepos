package com.macitepos.macitepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/kasir")
public class KasirController {

    @RequestMapping(method = RequestMethod.GET)
    public String dashboardKasir(){
        return "kasir/editProfil";
    }

}
