package com.macitepos.macitepos.controller;

import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.model.Produk;
import com.macitepos.macitepos.services.AkunService;
import com.macitepos.macitepos.services.PenggunaService;
import com.macitepos.macitepos.services.ProdukService;
//import com.macitepos.macitepos.services.SuplierService;
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
import java.util.List;


@Controller
public class WarehouseController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private AkunService akunService;

    @Autowired
    private ProdukService produkService;

//    @Autowired
//    private SuplierService suplierService;

    private static String UPLOADED_FOLDER = "D:\\blibli\\PROJECT\\Macitepos\\ext-resources\\user\\";

    @RequestMapping(value = "/warehouse")
    public String warehouse(Model model, HttpSession session) {
        model.addAttribute("warehousePOS",true);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());

//        if (produkService.showAll().isEmpty()) {
//            model.addAttribute("produkBaru", new ProdukDTO());
//            return "w_dashboard";
//        } else {
            model.addAttribute("produkApp", produkService.showAllApproved());
            List<ProdukDTO> as = produkService.showAllDissapproved();
            for (ProdukDTO a:
                    as) {
                System.out.println(a.getNama_produk());
            }
            model.addAttribute("produkDis", produkService.showAllDissapproved());
            model.addAttribute("produkBaru", new ProdukDTO());
            System.out.println("Controller ware jalan");
            return "w_dashboard";
//        }
    }

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER_PRODUK = "D:\\blibli\\PROJECT\\Macitepos\\ext-resources\\product\\";


    @PostMapping("/warehouse-product/create")
    public String buatProduk(@RequestParam("file") MultipartFile file, @Valid ProdukDTO produkDTO
    ){
        System.out.println("jalan cak create nya");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        produkDTO.setUpdated_by(authentication.getName());
        produkDTO.setFoto_produk(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        if (produkDTO.getStatus_produk() == null){
            produkDTO.setStatus_produk("dissapproved");
        }
        try {
            if(!file.getOriginalFilename().equalsIgnoreCase("")) {
                long name = System.currentTimeMillis();
                String extensi = fileName.substring(fileName.lastIndexOf(".") + 1);
                System.out.println(name + "." + extensi);
                fileName = name + "." + extensi;
                produkDTO.setFoto_produk(fileName);

                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER_PRODUK + fileName);
                Files.write(path, bytes);

                produkService. saveOrUpdated(produkDTO);
                System.out.println("produk terbuat");
            } else{
            ProdukDTO foto = produkService.findById(produkDTO.getId_produk());
            produkDTO.setFoto_produk(foto.getFoto_produk());
            produkService.saveOrUpdated(produkDTO);
        }

    } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("produk create");
        return "redirect:/warehouse";
    }

    @RequestMapping(value = "/warehouse-product/edit/{id}", method = RequestMethod.GET)
    public String editProduk(@PathVariable Integer id, Model model){
        model.addAttribute("produk", produkService.getById(id));
        return "w_formProduk";
    }

    @PostMapping("/warehouse-product/restock")
    public String restock(ProdukDTO produkDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String updated_by = akunService.findByUsername(authentication.getName()).toString();
        produkDTO.setUpdated_by(updated_by);
        produkService.restock(produkDTO);
        return "redirect:/warehouse";

    }

    @PostMapping("/warehouse-product/manage")
    public String manage(ProdukDTO produkDTO){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String updated_by = akunService.findByUsername(authentication.getName()).toString();
            produkDTO.setUpdated_by(updated_by);
            produkService.manage(produkDTO);
            return "redirect:/warehouse";

    }

    @GetMapping(value = "/warehouse-editProfile")
    public String editProfileWA(Model model){
        model.addAttribute("warehouseeditPOS",true);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pengguna", akunService.findByUsername(authentication.getName()));
//        model.addAttribute("penggunaBaru", new PenggunaDTO());
        return "w_editProfile";
    }
    @GetMapping(value = "/warehouse-rak")
    public String rak(HttpSession session, Model model){
        model.addAttribute("rakPOS",true);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("nama", akunService.findByUsername(authentication.getName()).getNama_pengguna());
        session.setAttribute("foto", akunService.findByUsername(authentication.getName()).getFoto_pengguna());
        return "rak";
    }
    @RequestMapping(value = "/warehouse-profile/save", method = RequestMethod.POST)
    public String editAction(@RequestParam("file") MultipartFile file, @Valid PenggunaDTO penggunaDTO, BindingResult bindingResult){
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
            e.printStackTrace();
        }
        return "redirect:/warehouse-editProfile";
    }
}
