package com.macitepos.macitepos.services;

import com.macitepos.macitepos.model.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberService {

    @Query("select id_member, nama_member, tanggal_lahir, jenis_kelamin, alamat, diskon, created_at from Member")
    List<Member> listMember();

    Member saveOrUpdate(Member member);

    Member getIdMember(Integer id);

    void hapus(Integer id);
}
