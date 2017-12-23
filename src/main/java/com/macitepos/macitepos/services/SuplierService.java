package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.SuplierDAO;
import com.macitepos.macitepos.dto.SuplierDTO;
import com.macitepos.macitepos.model.Suplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuplierService {
    @Autowired
    SuplierDAO suplierDAO;
    public List<SuplierDTO> showAll () {
       List<Suplier> s = suplierDAO.showAll();
       return convertToDTOAPI(s);
    }
    public List<SuplierDTO> convertToDTOAPI (List<Suplier> supliers) {
        List<SuplierDTO> s = new ArrayList<>();
        for (Suplier suplier: supliers) {
            s.add(convertToDTO(suplier));
        }
        return s;

    }
    public SuplierDTO convertToDTO (Suplier suplier) {
    SuplierDTO suplierDTO = new SuplierDTO(suplier.getId_suplier(),suplier.getNama_suplier(),suplier.getAlamat_suplier(),
            suplier.getNo_telepon(),suplier.getFoto_suplier(),suplier.getCreated_by(),suplier.getUpdated_by(),
            suplier.getCreated_at());
    return suplierDTO;
    }
}
