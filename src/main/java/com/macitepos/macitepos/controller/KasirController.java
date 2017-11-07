package com.macitepos.macitepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class KasirController {

    @RequestMapping(value = "/kasir", method = RequestMethod.GET)
    public String dashboardKasir(){ return "kasir/Dashboard"; }

}
