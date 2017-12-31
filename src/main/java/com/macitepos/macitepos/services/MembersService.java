package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.MembersDAO;
import com.macitepos.macitepos.dto.MemberDTO;
import com.macitepos.macitepos.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersDAO membersDAO;

    public MemberDTO saveOrUpdated(MemberDTO memberDTO){
        try{
            Member member = new Member(memberDTO.getId_member(),memberDTO.getNama_member(),memberDTO.getAlamat()
                    ,memberDTO.getTanggal_lahir(),memberDTO.getJenis_kelamin(),memberDTO.getEmail(),memberDTO.getDiskon(),memberDTO.getVisitCount(),memberDTO.getCreated_by(),
                    memberDTO.getVersion());

            member = membersDAO.saveOrUpdate(member);
            System.out.println("save or update customer  sukses");
            return convertToDto(member);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateJumlahBerkunjung(int id_member, int kunjungan, Timestamp timestamp){
        Member member = membersDAO.findOneMemberById(id_member);
        member.setVisitCount(kunjungan);
        member.setLast_visit(timestamp);
        membersDAO.saveOrUpdate(member);
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

    public List<MemberDTO> findByID(int ID){
        System.out.println("Parameter ID di member service find ID " + ID);
        List<Member> m = membersDAO.findById(ID);
        for (Member member:
             m) {
            System.out.println("member service fintById visit count = "+member.getVisitCount());
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

    MemberDTO convertToDto(Member member){
//        System.out.println(member.getVisitCount());
        MemberDTO dto = new MemberDTO(member.getId_member(), member.getNama_member(), member.getAlamat(), member.getTanggal_lahir(),
                member.getJenis_kelamin(), member.getEmail(),member.getDiskon(), member.getVisitCount(),member.getLast_visit(), member.getCreated_by(),
                member.getCreated_at(), member.getVersion());
        return dto;
    }

    public long jumlahMember(){
        try {
            return membersDAO.jumlahMember();
        }catch (Exception e){
            e.getMessage();
        }
        return 0;
    }

}
