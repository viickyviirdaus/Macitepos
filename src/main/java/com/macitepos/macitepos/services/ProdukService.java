package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.ProdukDAO;
import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProdukService {
    @Autowired
    private ProdukDAO produkDAO;

    public ProdukDTO saveOrUpdated(ProdukDTO produkDTO){
        if (produkDTO.getStatus_produk() == null){
            produkDTO.setStatus_produk("dissapproved");
        }else{
            produkDTO.setStatus_produk("approved");
        }
        try{
            Produk produk = new Produk(produkDTO.getId_produk(), produkDTO.getNama_produk(), produkDTO.getKategori(),
                    produkDTO.getHarga_beli(), produkDTO.getHarga_penjualan(), produkDTO.getStok_ulang(), produkDTO.getStok_total(),
                    produkDTO.getStok_toko(), produkDTO.getStok_gudang(),produkDTO.getTerjual(), produkDTO.getFoto_produk(),
                    produkDTO.getNo_rak_toko(), produkDTO.getUpdated_by(),
                    produkDTO.getStatus_produk());
                    produk = produkDAO.saveOrUpdate(produk);
            return convertToDto(produk);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateStokTokodanTotal(int id_produk, int jumlahBeli){
        Produk produk = produkDAO.findById(id_produk);
        int stokToko = produk.getStok_toko() - jumlahBeli;
        int stokTotal = produk.getStok_total() - jumlahBeli;
        produk.setStok_toko(stokToko);
        produk.setStok_total(stokTotal);
        produkDAO.saveOrUpdate(produk);
    }

    public void updateTerjual(int id_produk, int jumlahBeli){
        Produk produk = produkDAO.findById(id_produk);
        int terjual = produk.getTerjual() + jumlahBeli;
        produk.setTerjual(terjual);
        produkDAO.saveOrUpdate(produk);
    }

    public List<ProdukDTO> showAll(){
        List<Produk> p  = produkDAO.showAllApproved();
        if (p.isEmpty()==false){
            for (Produk pro:p) {
                System.out.println(pro.getNama_produk());
                System.out.println(pro.getKategori());
            }
        } else if (p.isEmpty()){
            System.out.println("G Ada data");
        }
        return convertToDtoAPI(p);
    }

    public List<ProdukDTO> showAllApproved(){
        List<Produk> p  = produkDAO.showAllApproved();
        if (p.isEmpty()==false){
            for (Produk pro:p) {
                System.out.println(pro.getNama_produk());
                System.out.println(pro.getKategori());
            }
        } else if (p.isEmpty()){
            System.out.println("G Ada data");
        }
        return convertToDtoAPI(p);
    }

    public List<ProdukDTO> showAllDissapproved(){
        List<Produk> p  = produkDAO.showAllDissaproved();
        if (p.isEmpty()==false){
            for (Produk pro:p) {
                System.out.println(pro.getNama_produk());
                System.out.println(pro.getKategori());
            }
        } else if (p.isEmpty()){
            System.out.println("G Ada data");
        }
        return convertToDtoAPI(p);
    }

    public List<ProdukDTO> findByCategory(String category){
        System.out.println("Parameter Category di product service find " + category);
        List<Produk> p = produkDAO.findByCategori(category);
        return convertToDtoAPI(p);
    }

    public ProdukDTO findById(int id){
        System.out.println("Parameter id di product service find " + id);
        Produk p = produkDAO.findById(id);
        if (p.getStatus_produk().equalsIgnoreCase("approved")){
            System.out.println(p.getStatus_produk());
            p.setStatus_produk("dissapproved");
        }else{
            p.setStatus_produk("approved");
        }
        produkDAO.saveOrUpdate(p);
        System.out.println("sukses save "+p.getStatus_produk());
        return convertToDto(p);
    }

    public List<ProdukDTO> search(String key){
        System.out.println("Parameter Key di product service search " + key);
        List<Produk> p = produkDAO.findByKey(key);
        return convertToDtoAPI(p);
    }

    List<ProdukDTO> convertToDtoAPI(List<Produk> products){
        List<ProdukDTO> dto = new ArrayList<>();
        for(Produk produk : products){
            dto.add(convertToDto(produk));
        }
        return dto;
    }

    ProdukDTO convertToDto(Produk produk){
        ProdukDTO dto = new ProdukDTO(produk.getId_produk(),produk.getNama_produk(),produk.getKategori(),produk.getHarga_beli(),
                produk.getHarga_penjualan(),produk.getStok_ulang(),produk.getStok_total(), produk.getStok_toko(), produk.getStok_gudang(),
                produk.getTerjual(), produk.getFoto_produk(), produk.getNo_rak_toko(),
                produk.getUpdated_by(),produk.getStatus_produk());
        System.out.println(dto.getStatus_produk()+" status");
        return dto;
    }

    List<ProdukDTO> convertToDto(List<Produk> products){
        List<ProdukDTO> dto = new ArrayList<>();
        for(Produk produk : products){
            dto.add(convertToDto(produk));
        }
        return dto;
    }

    public Long jumlahProduk(){
        return produkDAO.jumlahProduk();
    }

    public ProdukDTO restock(ProdukDTO produkDTO){
        System.out.println("Restock");
        System.out.println(produkDTO.getId_produk());
        System.out.println(produkDTO.getStok_ulang());
        if (produkDAO.findById(produkDTO.getId_produk()) != null){
            System.out.println("produk ketemu");
            try{
                Produk p = produkDAO.findById(produkDTO.getId_produk());
                int stok_gudang = p.getStok_gudang() + produkDTO.getStok_ulang();
                int stok_total = stok_gudang + p.getStok_toko();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String updated_by = authentication.getName().toString();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                p.setStok_gudang(stok_gudang);
                p.setStok_total(stok_total);
                p.setUpdated_by(produkDTO.getUpdated_by());
                p.setLast_updated(timestamp);
                Produk produk = produkDAO.saveOrUpdate(p);
                return convertToDto(p);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            return null;
        }

        return null;
    }


    public void itemMapping(){
        String itemMap [][] ={ {"Food","A1"}, {"Food","A2"}, {"Food","A3"}, {"Food","A4"}, {"Food","A5"},
                {"Drink","B1"}, {"Drink","B2"}, {"Drink","B3"}, {"Drink","B4"}, {"Drink","B5"},
                {"Drugs","C1"}, {"Drugs","C2"}, {"Drugs","C3"}, {"Drugs","C4"}, {"Drugs","C5"},
                {"Cosmetics","D1"}, {"Cosmetics","D2"}, {"Cosmetics","D3"}, {"Cosmetics","D4"}, {"Cosmetics","D5"},
                {"Office","E1"}, {"Office","E2"}, {"Office","E3"}, {"Office","E4"}, {"Office","E5"},
                {"Household","F1"}, {"Household","F2"}, {"Household","F3"}, {"Household","F4"}, {"Household","F5"},
        };


    }
}
