package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.MembersService;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller

public class KasirController {

    @Autowired
    AkunService akunService;

    @Autowired
    MembersService membersService;

    @Autowired
    ProdukService produkService;

    @RequestMapping(value = "/kasir", method = RequestMethod.GET)
    public String kasir(HttpSession session, Pengguna pengguna, Model model){
        session.setAttribute("nama", pengguna.getUsername());
        session.setAttribute("pengguna", akunService.listPengguna());
        return "c_dashboard";
    }

    @RequestMapping(value = "/kasir-product" , method = RequestMethod.GET)
    public String product(Model model){
        model.addAttribute("produk",produkService.showAll());
        return "c_product";
    }

    @RequestMapping(value = "/kasir-orders")
    public String orders(){
        return "c_orders";
    }

    @RequestMapping(value = "/kasir-customer",method = RequestMethod.GET)
    public String customer(Model model){
        if (membersService.showAll().isEmpty()){
            return "c_customer";
        } else {
            model.addAttribute("member",membersService.showAll());
            model.addAttribute("memberBaru", new MemberDTO());
            System.out.println("Controller jalan");
            return "c_customer";
        }
    }

    @RequestMapping(value = "/kasir-customer/createMember", method = RequestMethod.POST)
    public String buatMember(Model model, MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        MemberDTO mDTO = membersService.saveOrUpdated(memberDTO);
        return "redirect:/kasir-customer";
    }

    @RequestMapping(value = "/kasir-report")
    public String report(){
        return "c_reportProduct";
    }

    @RequestMapping(value = "/kasir-user")
    public String user(){
        return "c_user";
    }

    @RequestMapping(value = "/kasir-profile", method = RequestMethod.GET)
    public String edit (){
        return "c_editProfil";
    }

}
