package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.PenggunasDAO;
import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PenggunaService {

    @Autowired
    private PenggunasDAO penggunasDAO;

    public PenggunaDTO saveOrUpdated(PenggunaDTO penggunaDTO){
        try{
            Pengguna pengguna = new Pengguna(penggunaDTO.getId_pengguna(), penggunaDTO.getNama_pengguna(),
                                            penggunaDTO.getUsername(),
                                            penggunaDTO.getAlamat_pengguna(), penggunaDTO.getTanggal_lahir(),
                                            penggunaDTO.getEmail(), penggunaDTO.getPassword(),
                                            penggunaDTO.isStatus_pengguna(), penggunaDTO.getFoto_pengguna(),
                                            penggunaDTO.getLast_modified(), penggunaDTO.getCreated_at(),
                                            penggunaDTO.getLevel()
                                            );

            pengguna = penggunasDAO.saveOrUpdate(pengguna);
            System.out.println("save or update user sukses");
            return convertToDto(pengguna);
        } catch (Exception e){
            System.out.println("pengguna service saveorupdte error");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<PenggunaDTO> showAll(){
        System.out.println("Show All user DAO Jalan");
        List<Pengguna> m  = penggunasDAO.showAll();
        if(m.isEmpty()){
            System.out.println("Kosong user dao");
        }
        for (Pengguna ms: m
             ) {
            System.out.println("alamat user di service");
            System.out.println(ms.getAlamat_pengguna());
        }
        return convertToDTOAPI(m);
    }

    List<PenggunaDTO> convertToDTOAPI(List<Pengguna> penggunas){
        List<PenggunaDTO> dto = new ArrayList<>();
        for(Pengguna pengguna : penggunas){
            dto.add(convertToDto(pengguna));
        }
        return dto;
    }

    PenggunaDTO convertToDto(Pengguna pengguna){
        PenggunaDTO dto = new PenggunaDTO(pengguna.getId_pengguna(), pengguna.getNama_pengguna(), pengguna.getUsername(),
                pengguna.getAlamat_pengguna(), pengguna.getTanggal_lahir(),  pengguna.getEmail(), pengguna.getPassword(),
                pengguna.getStatus_pengguna(),pengguna.getFoto_pengguna(), pengguna.getLast_modified(), pengguna.getCreated_at(), pengguna.getLevel());
        return dto;
    }


}
