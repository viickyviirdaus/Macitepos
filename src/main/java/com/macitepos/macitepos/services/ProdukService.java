package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.ProdukDAO;
import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.dto.ProdukDTO;
import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdukService {
    @Autowired
    private ProdukDAO produkDAO;

    public ProdukDTO saveOrUpdated(ProdukDTO produkDTO){
        try{
            Produk produk = new Produk(produkDTO.getId_produk(),produkDTO.getNama_produk(),produkDTO.getKategori());

            produk = produkDAO.saveOrUpdate(produk);
            return convertToDto(produk);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateStokTokodanTotal(int id_produk, int jumlahBeli){
        Produk produk = produkDAO.findByIdSingle(id_produk);
        int stokToko = produk.getStok_toko() - jumlahBeli;
        int stokTotal = produk.getStok_total() - jumlahBeli;
        produk.setStok_toko(stokToko);
        produk.setStok_total(stokTotal);
        produkDAO.saveOrUpdate(produk);
    }

    public void updateTerjual(int id_produk, int jumlahBeli){
        Produk produk = produkDAO.findByIdSingle(id_produk);
        int terjual = produk.getTerjual() + jumlahBeli;
        produk.setTerjual(terjual);
        produkDAO.saveOrUpdate(produk);
    }

    public List<ProdukDTO> showAll(){
        List<Produk> p  = produkDAO.showAll();
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
        ProdukDTO dto = new ProdukDTO(produk.getId_produk(),produk.getNama_produk(),produk.getKategori(),produk.getHarga_beli(),produk.getHarga_penjualan(),produk.getStok_ulang(),
                produk.getStok_total(),produk.getStok_gudang(),produk.getStok_toko(),produk.getTerjual(),produk.getFoto_produk(), produk.getNo_rak_gudang(), produk.getNo_rak_toko(),
                produk.getUpdated_by(),produk.getStatus_produk());
        return dto;
    }

    List<ProdukDTO> convertToDto(List<Produk> products){
        List<ProdukDTO> dto = new ArrayList<>();
        for(Produk produk : products){
            dto.add(convertToDto(produk));
        }
        return dto;
    }

}
