package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.PenggunaService;
import com.macitepos.macitepos.services.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class WarehouseController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private AkunService akunService;

    @Autowired
    private ProdukService produkService;

    @RequestMapping(value = "/warehouse")
    public String warehouse(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "w_dashboard";
    }

    @RequestMapping(value = "/suplier")
    public String suplier(){
        return "w_adminSuplier";
    }

    @RequestMapping(value = "/productWA",method = RequestMethod.GET)
    public String produk(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());

        if (produkService.showAll().isEmpty()){
            return "w_product";
        } else {
            model.addAttribute("produk", produkService.showAll());
            model.addAttribute("produkBaru", new ProdukDTO());
            System.out.println("Controller user jalan");
            return "w_product";
        }
    }
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER_PRODUK = "C:\\Users\\Vicky Virdaus\\Documents\\Blibli\\Macitepos\\src\\main\\resources\\static\\assets\\image\\produk\\";

    @PostMapping("/productWA/create")
    public String buatProduk(@RequestParam("file") MultipartFile file, @Valid ProdukDTO produkDTO
    ){
        produkDTO.setFoto_produk(file.getOriginalFilename());
        try {
            String filename;
            System.out.println("nama foto" +file.getOriginalFilename().toString());
            if(file.getOriginalFilename() == null || file.getOriginalFilename().toString().equalsIgnoreCase("")){
                filename = "defaultProfile.png";
            }else{
                filename = file.getOriginalFilename();
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            System.out.println("filename"+filename);
            Path path = Paths.get(UPLOADED_FOLDER_PRODUK + filename);
            System.out.println(path+" path");
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        produkService.saveOrUpdated(produkDTO);

        System.out.println("user create");
        return "redirect:/productWA";
    }



    @GetMapping(value = "/editProfileWA")
    public String editProfileWA(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));

        return "w_editProfile";
    }


}
