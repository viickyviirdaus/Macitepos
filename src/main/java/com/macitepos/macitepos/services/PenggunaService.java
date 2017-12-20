package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.PenggunasDAO;
import com.macitepos.macitepos.dao.RoleDAO;
import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.dto.RoleDTO;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PenggunaService {

    @Autowired
    private PenggunasDAO penggunasDAO;

    @Autowired
    private RoleDAO roleDAO;

    PenggunaDTO penggunaDTO;



    public PenggunaDTO saveOrUpdated(PenggunaDTO penggunaDTOO){

    this.penggunaDTO = penggunaDTOO;
        System.out.println("itu "+penggunaDTO.getId_pengguna());

        try{
            Pengguna pengguna = new Pengguna(penggunaDTO.getId_pengguna(), penggunaDTO.getNama_pengguna(),
                                            penggunaDTO.getUsername(),
                                            penggunaDTO.getAlamat_pengguna(), penggunaDTO.getTanggal_lahir(),
                                            penggunaDTO.getEmail(), penggunaDTO.getPassword(),
                                            penggunaDTO.isStatus_pengguna(), penggunaDTOO.getFoto_pengguna(),
                                            penggunaDTO.getLast_modified(), penggunaDTO.getCreated_at(),
                                            penggunaDTO.getLevel()
                                            );

            System.out.println("save or update user sebelum sukses"+ pengguna.getId_pengguna());
            pengguna = penggunasDAO.saveOrUpdate(pengguna);

            System.out.println("save or update user sukses"+ pengguna.getId_pengguna());
            saveToRole(pengguna.getId_pengguna());
            return convertToDto(pengguna);
        } catch (Exception e){
            System.out.println("pengguna service saveorupdte error");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public PenggunaDTO Updated(PenggunaDTO penggunaDTO) throws NoSuchAlgorithmException {
        Pengguna pengguna = penggunasDAO.findById(penggunaDTO.getId_pengguna());
        pengguna.setNama_pengguna(penggunaDTO.getNama_pengguna());
        pengguna.setAlamat_pengguna(penggunaDTO.getAlamat_pengguna());
        pengguna.setTanggal_lahir(penggunaDTO.getTanggal_lahir());
        pengguna.setEmail(penggunaDTO.getEmail());
        pengguna.setPassword(penggunaDTO.getPassword());
        if (penggunaDTO.getFoto_pengguna() != null){
        pengguna.setFoto_pengguna(penggunaDTO.getFoto_pengguna());
        }
        penggunasDAO.saveOrUpdate(pengguna);
        return convertToDto(pengguna);
    }

    public Role saveToRole(int id){

        System.out.println(penggunaDTO.getId_pengguna()+" ID 4 ");
        System.out.println("level s1" + penggunaDTO.getLevel());
        String roles;
        System.out.println("level s2" + penggunaDTO.getLevel());
        if(penggunaDTO.getLevel().equals("kasir")){
            roles = "ROLE_KASIR";
            System.out.println("level s3" + penggunaDTO.getLevel());
        }else
            roles = "ROLE_WAREHOUSE";

        try{
            System.out.println(id+"ini id pengguna terakhir dan rolenya "+roles);
            Role role = new Role(id, roles);
            System.out.println("roleeeeeeeeee "+role.getRole_id());
            role = roleDAO.saveToRole(role);
            return  role;

        }catch (Exception e){
            System.out.println(e.getMessage() + "save to Role in service error");
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
            System.out.println("id user di service pengguna");
            System.out.println(ms.getId_pengguna());
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

//    RoleDTO convertRoleToDto(Role role){
//        RoleDTO roleDTO = new  RoleDTO(role.getRole_id(), role.getRole());
//        System.out.println(roleDTO.getRole_id()+" id roledto");
//        return roleDTO;
//    }

}
