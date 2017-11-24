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

    public List<ProdukDTO> showAll(){
        System.out.println("SHOW ALL JALAN");
        List<Produk> p  = produkDAO.showAll();
        System.out.println("Show ALl");
        if (p.isEmpty()==false){
            System.out.println("Produk Services");
            for (Produk pro:p) {
                System.out.println(pro.getNama_produk());
                System.out.println(pro.getKategori());
            }
        } else if (p.isEmpty()){
            System.out.println("G Ada data");
        }
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
                produk.getStok_total(),produk.getStok_gudang(),produk.getStok_toko(),produk.getFoto_produk(), produk.getNo_rak_gudang(), produk.getNo_rak_toko(),
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
