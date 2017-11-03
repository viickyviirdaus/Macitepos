package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AkunController {

    @Autowired
    private AkunService akunService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/")
    public String login1() {
        return "/login";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/403")
    public String error403() {
        return "/403";
    }

}