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

    @RequestMapping( method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("pengguna", new Pengguna());
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Pengguna pengguna, HttpSession session, ModelMap modelMap, Model model) {
        if (akunService.findByUsernameAndPassword(pengguna.getUsername(), pengguna.getPassword()) != null) {
            if (akunService.findBylevel("manajer").equals("manajer")) {
                session.setAttribute("nama", pengguna.getUsername());
                model.addAttribute("pengguna", akunService.listPengguna());
                return "manajer/masterManajer";
            }else{
                modelMap.addAttribute("errors", akunService.findBylevel(pengguna.getLevel_pengguna()));
                return "login";
            }
        } else {
            modelMap.addAttribute("errors", "Email or Password invalid, please try again");
            return "login";
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("nama");
        return "login";
    }
}