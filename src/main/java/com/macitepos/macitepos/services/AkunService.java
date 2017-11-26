package com.macitepos.macitepos.services;

import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.model.Pengguna;

import java.util.List;

public interface AkunService {

    public Pengguna findByUsernameAndPassword(String username, String password);

    Iterable<Pengguna> listPengguna();

    Pengguna findByUsername(String username);

    Pengguna saveOrUpdate(Pengguna pengguna);

    Pengguna getIdMember(Integer id);

    void hapus(Integer id);

    public void save(Pengguna pengguna, String name);

}
