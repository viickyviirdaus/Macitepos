package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.PenggunaService;
import com.macitepos.macitepos.services.ProdukService;
import com.macitepos.macitepos.services.SuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private SuplierService suplierService;

    private static String UPLOADED_FOLDER = "D:/blibli/PROJECT/Macitepos/src/main/resources/static/assets/upload/";

    @RequestMapping(value = "/warehouse")
    public String warehouse(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());

        if (produkService.showAll().isEmpty()) {
            return "w_dashboard";
        } else {
            model.addAttribute("produk", produkService.showAll());
            model.addAttribute("produkBaru", new ProdukDTO());
            System.out.println("Controller user jalan");
            return "w_dashboard";
        }
    }

    @RequestMapping(value = "/suplier")
    public String suplier(Model model){
        suplierService.showAll();
        model.addAttribute("suplier", suplierService.showAll());
        return "w_adminSuplier";
    }

        //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER_PRODUK = "C:\\Users\\Vicky Virdaus\\Documents\\Blibli\\Macitepos\\src\\main\\resources\\static\\assets\\image\\product\\";

    @PostMapping("/warehouse-product/create")
    public String buatProduk(@RequestParam("file") MultipartFile file, @Valid ProdukDTO produkDTO
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        produkDTO.setUpdated_by(authentication.getName());
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

        System.out.println("produk create");
        return "redirect:/warehouse";
    }

    @PostMapping("/warehouse-product/restock")
    public String restock(ProdukDTO produkDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String updated_by = akunService.findByUsername(authentication.getName()).toString();
        produkDTO.setUpdated_by(updated_by);
        produkService.restock(produkDTO);
        return "redirect:/warehouse";
    }

    @GetMapping(value = "/warehouse-editProfile")
    public String editProfileWA(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
//        model.addAttribute("penggunaBaru", new PenggunaDTO());
        return "w_editProfile";
    }
    @GetMapping(value = "/warehouse-rak")
    public String rak(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "w_rak";
    }
    @RequestMapping(value = "/warehouse-profile/save", method = RequestMethod.POST)
    public String editAction(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO, BindingResult bindingResult){
        String fileName = file.getOriginalFilename();
        System.out.println(penggunaDTO.getId_pengguna());
        System.out.println(penggunaDTO.getLevel());
        System.out.println(penggunaDTO.getAlamat_pengguna());
        System.out.println(penggunaDTO.getCreated_at());
        System.out.println(penggunaDTO.getEmail());
        System.out.println(penggunaDTO.getFoto_pengguna());
        System.out.println(penggunaDTO.getNama_pengguna());
        System.out.println(penggunaDTO.getLast_modified());
        System.out.println(penggunaDTO.getPassword());
        System.out.println(penggunaDTO.getTanggal_lahir());
        System.out.println(penggunaDTO.getUsername());
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

            } else {
                penggunaService.saveOrUpdated(penggunaDTO);
            }



        } catch (IOException e){
            e.printStackTrace();


        }

        return "redirect:/warehouse-editProfile";
    }
}
