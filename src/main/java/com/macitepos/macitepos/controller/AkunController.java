package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AkunController {

@Autowired
AkunService akunService;

    @RequestMapping("/login")
    public String login(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getPrincipal().equals("anonymousUser")) {
            System.out.println("Nama " + akunService.findByUsername(authentication.getName()).getNama_pengguna());
            return "/home";
        }else {
            return "/login";
        }
    }

    @GetMapping("/")
    public String login1()
    {
        return "/login";
    }

    @GetMapping("/home")
    public String home()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_MANAJER"))
            return "redirect:/manajer";
        if (roles.contains("ROLE_KASIR"))
            return "redirect:/kasir";
        if (roles.contains("ROLE_WAREHOUSE"))
            return "redirect:/warehouse";
        else
        return "/home";
    }

    @GetMapping("/403")
    public String error403() {
        return "/403";
    }

}