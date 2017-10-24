package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Member;

import java.util.List;

public interface MemberDao {

    List<Member> listMember();

    Member saveOrUpdate(Member member);

    Member getIdMember(Integer id);

    void hapus(Integer id);
}
