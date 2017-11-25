package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller

public class ManajerController {
    @Autowired
    private AkunService akunService;

    @RequestMapping(value = "/manajer")
    public String manajer(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "m_dashboard";
    }

    @GetMapping(value = "/product")
    public String product(ModelMap modelMap){

        return "m_product";
    }

    @GetMapping(value = "/order")
    public String order(){
        return "m_orders";
    }

    @GetMapping(value = "/customer")
    public String customer(){
        return "m_customer";
    }

    @GetMapping(value = "/reportProduct")
    public String reportProduct(){
        return "m_reportProduct";
    }

    @GetMapping(value = "/reportPayment")
    public String reportPayment(){
        return "m_reportPayment";
    }

    @RequestMapping(value = "/user", method=RequestMethod.GET)
    public String user(Model model) {
        model.addAttribute("penggunaBaru",new Pengguna() );
        model.addAttribute("pengguna", akunService.listPengguna());
        return "m_user";
    }

//    @RequestMapping(value="/user/create", method=RequestMethod.GET)
//    public String tampiUser(Model model){
//        model.addAttribute("pengguna", akunService.listPengguna());
//        return "m_user";
//    }

    @RequestMapping(value="/user/create", method=RequestMethod.POST)
    public String simpan(Pengguna penggunaa, Model model){
        System.out.println("simpan");
        Pengguna pengguna = akunService.saveOrUpdate(penggunaa);
        model.addAttribute("pengguna", akunService.listPengguna());
        return "redirect:/m_user";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Object a = akunService.findByUsername(authentication.getName()).getId_pengguna();
        //model.addAttribute("id", a);
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
        return "m_editProfil";
    }

}
