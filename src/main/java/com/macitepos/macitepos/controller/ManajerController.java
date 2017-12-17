package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.OrdersService;
import com.macitepos.macitepos.services.PenggunaService;
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

    @RequestMapping(value = "/manajer")
    public String manajer(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        session.setAttribute("jumlahOrder", ordersService.jumlahOrder());
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

//    @RequestMapping(value = "/user", method=RequestMethod.GET)
//    public String user(Model model) {
//        model.addAttribute("penggunaBaru",new Pengguna() );
//        model.addAttribute("pengguna", penggunaService.listPengguna());
//        return "m_user";
//    }
//
//    @RequestMapping(value="/user/create", method=RequestMethod.POST)
//    public String simpan(Pengguna penggunaa, Model model){
//        System.out.println("simpan");
//        Pengguna pengguna = penggunaService.saveOrUpdate(penggunaa);
//        model.addAttribute("pengguna", penggunaService.listPengguna());
//        return "redirect:/m_user";
//    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String User(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
    private static String UPLOADED_FOLDER = "C:\\Users\\Vicky Virdaus\\Documents\\Blibli\\Macitepos\\src\\main\\resources\\static\\assets\\image\\";

    @PostMapping("/user/create")
    public String buatUser(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO, BindingResult bindingResult
    ){
        penggunaDTO.setFoto_pengguna(file.getOriginalFilename());
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
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            System.out.println(path+" path");
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("level contr " + penggunaDTO.getLevel());
        System.out.println(penggunaDTO.getId_pengguna()+" ID 1 ");
        penggunaService.saveOrUpdated(penggunaDTO, penggunaDTO.getFoto_pengguna());

        System.out.println("user create");
        return "redirect:/user";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
        return "m_editProfil";
    }
}
