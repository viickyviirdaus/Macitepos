package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Barang;
import java.util.List;

public interface BarangDao {

    List<Barang> ListBarang();

    Barang saveOrUpdte(Barang barang);

    Barang getById(Integer id);

    void hapusBarang(Integer id);
}
