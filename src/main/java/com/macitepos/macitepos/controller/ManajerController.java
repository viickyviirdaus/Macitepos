package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller

public class ManajerController {
    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private AkunService akunService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProdukService produkService;

    @Autowired
    private MembersService membersService;

    @RequestMapping(value = "/manajer")

    public String manajer(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("dashboardPOS",true);
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        model.addAttribute("jumlahOrder", ordersService.jumlahOrder());
        model.addAttribute("jumlahPayment", ordersService.jumlahPayment());
        model.addAttribute("jumlahProduk", produkService.jumlahProduk());
        model.addAttribute("jumlahMember", membersService.jumlahMember());

        return "m_dashboard";
    }

    @GetMapping(value = "/product")
    public String product(ModelMap modelMap, Model model){
        model.addAttribute("productPOS",true);
        return "m_product";
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER_USER = "C:\\Users\\Vicky Virdaus\\Documents\\Blibli\\Macitepos\\ext-resources\\user\\";

    @PostMapping("/product/create")
    public String buatProduk(@RequestParam("file") MultipartFile file, @Valid ProdukDTO produkDTO, BindingResult bindingResult
    ){

        produkDTO.setFoto_produk(file.getOriginalFilename());
        System.out.println("ini "+bindingResult.toString());
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
            Path path = Paths.get(UPLOADED_FOLDER_USER + filename);
            System.out.println(path+" path");
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        produkService.saveOrUpdated(produkDTO);

        System.out.println("user create");
        return "redirect:/product";
    }

    @GetMapping(value = "/order")
    public String order(Model model){
        model.addAttribute("ordersPOS",true);
        return "m_orders";
    }

    @GetMapping(value = "/customer")
    public String customer(Model model){
        model.addAttribute("customerPOS", true);
        return "m_customer";
    }

    @GetMapping(value = "/reportProduct")
    public String reportProduct(Model model){
        model.addAttribute("reportPOS",true);
        return "m_reportProduct";
    }

//    @GetMapping(value = "/reportPayment")
//    public String reportPayment(){
//        return "m_reportPayment";
//    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String User(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("userPOS",true);
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());

        if (penggunaService.showAll().isEmpty()){
            return "m_user";
        } else {
            model.addAttribute("pengguna", penggunaService.showAll());
            model.addAttribute("penggunaBaru", new PenggunaDTO());
            System.out.println("Controller user jalan");
            return "m_user";
        }
    }
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D:\\blibli\\PROJECT\\Macitepos\\ext-resources\\user\\";
    @PostMapping("/user/create")
    public String buatUser(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO, BindingResult bindingResult
    ){
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

        } catch (IOException e) {
            System.out.println("failed to create user");
            e.printStackTrace();
        }
//        System.out.println("level contr " + penggunaDTO.getLevel());
//        System.out.println(penggunaDTO.getId_pengguna()+" ID 1 ");
//        penggunaService.saveOrUpdated(penggunaDTO);

        System.out.println("user create");
        return "redirect:/user";
    }

    @GetMapping(value = "/edit")
    public String edit(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("profilePOS",true);
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());

        return "m_editProfil";
    }

    @PostMapping("/user/edit/create")
    public String editUser(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO
    ){

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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/edit";
    }

    @GetMapping(value = "/itemMapping")
    public String rak(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        model.addAttribute("productPOS",true);
        return "rak";
    }

}
