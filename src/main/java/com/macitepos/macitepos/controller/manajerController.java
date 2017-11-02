package com.macitepos.macitepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/manajer")
public class manajerController {

    @RequestMapping(method = RequestMethod.GET)
    public String dashboardManajer(){
        return "manajer/dashboard";
    }

}
