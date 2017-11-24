package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.MembersDAO;
import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersDAO membersDAO;

    public MemberDTO saveOrUpdated(MemberDTO memberDTO){
        try{
            Member member = new Member(memberDTO.getId_member(),memberDTO.getNama_member(),memberDTO.getAlamat()
                    ,memberDTO.getTanggal_lahir(),memberDTO.getJenis_kelamin(),memberDTO.getDiskon(),memberDTO.getCount(),memberDTO.getCreated_by(),
                    memberDTO.getVersion());

            member = membersDAO.saveOrUpdate(member);
            return convertToDto(member);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<MemberDTO> showAll(){
        System.out.println("Show All DAO Jalan");
        List<Member> m  = membersDAO.showAll();
        if(m.isEmpty()){
            System.out.println("Kosong");
        }
        for (Member ms: m
             ) {
            System.out.println("alamat di service");
            System.out.println(ms.getAlamat());
        }
        return convertToDTOAPI(m);
    }

    List<MemberDTO> convertToDTOAPI(List<Member> members){
        List<MemberDTO> dto = new ArrayList<>();
        for(Member member : members){
            dto.add(convertToDto(member));
        }
        return dto;
    }

    MemberDTO convertToDto(Member produk){
        MemberDTO dto = new MemberDTO(produk.getId_member(), produk.getNama_member(), produk.getAlamat(), produk.getTanggal_lahir(),
                produk.getJenis_kelamin(),produk.getDiskon(), produk.getCount(),produk.getLast_visit(), produk.getCreated_by(),
                produk.getCreated_at(), produk.getVersion());
        return dto;
    }


}
