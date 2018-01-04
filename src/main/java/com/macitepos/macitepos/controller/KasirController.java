package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.MembersService;
import com.macitepos.macitepos.services.PenggunaService;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Controller

public class KasirController {

    @Autowired
    AkunService akunService;

    @Autowired
    MembersService membersService;

    @Autowired
    ProdukService produkService;

    @Autowired
    PenggunaService penggunaService;

    private static String UPLOADED_FOLDER = "D:\\blibli\\PROJECT\\Macitepos\\ext-resources\\user\\";

    @RequestMapping(value = "/kasir", method = RequestMethod.GET)
    public String kasir(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LocalDate dateToday = LocalDate.now();
        model.addAttribute("dashboardPOS",true);
        model.addAttribute("dateToday",dateToday);
        model.addAttribute("produk", produkService.showAll());
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_dashboard";
    }

    @RequestMapping(value = "/kasir-printInvoice")
    public String printInvoice(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        return "invoice";
    }

    @RequestMapping(value = "/kasir-product" , method = RequestMethod.GET)
    public String product(Model model,HttpSession session){
//        model.addAttribute("produk",produkService.showAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("productPOS",true);
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_product";
    }

    @GetMapping(value = "/kasir-itemMapping")
    public String rak(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("productPOS",true);
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "rak";
    }

    @RequestMapping(value = "/kasir-orders")
    public String orders(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("ordersPOS",true);
        model.addAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        model.addAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_orders";
    }

    @RequestMapping(value = "/kasir-customer",method = RequestMethod.GET)
    public String customer(Model model,HttpSession session){
        model.addAttribute("memberBaru", new MemberDTO());
        System.out.println("Controller jalan");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("customerPOS", true);
        model.addAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        model.addAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_customer";
    }

    @RequestMapping(value = "/kasir-customer/createMember", method = RequestMethod.POST)
    public String buatMember(Model model, MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        memberDTO.setCreated_by(authentication.getName());
        memberDTO.setDiskon(5);
        memberDTO.setVisitCount(1);
        if(memberDTO.getNama_member().equalsIgnoreCase("customer")){
            memberDTO.setDiskon(0);
        }
        MemberDTO mDTO = membersService.saveOrUpdated(memberDTO);
        return "redirect:/kasir-customer";
    }

    @RequestMapping(value = "/kasir-report")
    public String report(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("reportPOS",true);
        model.addAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        model.addAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_reportProduct";
    }

    @RequestMapping(value = "/kasir-user")
    public String user(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userPOS",true);
        model.addAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        model.addAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_user";
    }

    @RequestMapping(value = "/kasir-profile", method = RequestMethod.GET)
    public String edit (Model model,HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("profilePOS",true);
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "c_editProfil";
    }

    @RequestMapping(value = "/kasir-profile/save", method = RequestMethod.POST)
    public String editAction(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO, BindingResult bindingResult){
        String fileName = file.getOriginalFilename();

        try {
            if(!file.getOriginalFilename().equalsIgnoreCase("")){
                long name = System.currentTimeMillis();
                String extensi = fileName.substring(fileName.lastIndexOf(".")+1);
                System.out.println(name+"."+extensi);
                fileName = name+"."+extensi;
                penggunaDTO.setFoto_pengguna(fileName);

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.write(path,bytes);
                penggunaService.saveOrUpdated(penggunaDTO);

            } else{
                Pengguna foto = akunService.findByUsername(penggunaDTO.getUsername());
                penggunaDTO.setFoto_pengguna(foto.getFoto_pengguna());
                penggunaService.saveOrUpdated(penggunaDTO);
            }



        } catch (IOException e){
            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "redirect:/kasir-profile";
    }

}
