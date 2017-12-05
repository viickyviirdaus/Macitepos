package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Pengguna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PenggunaDao extends CrudRepository<Pengguna, Integer> {

    Pengguna findByUsernameAndPassword(String username, String password);
    Pengguna findByUsername(String username);

    @Query("select foto_pengguna, id_pengguna, nama_pengguna, alamat_pengguna, tanggal_lahir, email, level, created_at, last_modified from Pengguna")
    Iterable<Pengguna> findAllPengguna();

}

