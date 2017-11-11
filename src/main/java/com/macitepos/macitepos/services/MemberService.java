package com.macitepos.macitepos.services;

import com.macitepos.macitepos.model.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberService {
 
    List<Member> listMember();

    Member saveOrUpdate(Member member);

    Member getIdMember(Integer id);

    void hapus(Integer id);
}
