package com.macitepos.macitepos.services;

import com.macitepos.macitepos.model.Pengguna;

import java.util.List;

public interface AkunService {

    public Pengguna findByUsernameAndPassword(String username, String password);
    List<Pengguna> listPengguna();
    Object findBylevel(String level_pengguna);


}
