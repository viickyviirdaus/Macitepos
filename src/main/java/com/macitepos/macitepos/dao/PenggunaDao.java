package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Pengguna;
import org.springframework.data.repository.CrudRepository;


public interface PenggunaDao extends CrudRepository<Pengguna, Integer> {

    Pengguna findByUsernameAndPassword(String username, String password);
    Pengguna findByUsername(String username);

}
