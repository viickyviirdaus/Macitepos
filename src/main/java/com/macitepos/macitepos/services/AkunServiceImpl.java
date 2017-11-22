package com.macitepos.macitepos.services;

import com.macitepos.macitepos.dao.PenggunaDao;
import com.macitepos.macitepos.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Service("akunService")
@Transactional
public class AkunServiceImpl implements AkunService {

    @Autowired
    private PenggunaDao penggunaDao;

    public AkunServiceImpl() {
        super();
    }

    @Override
    public Pengguna findByUsernameAndPassword(String username, String password) {
        return penggunaDao.findByUsernameAndPassword(username, password);
    }


    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Pengguna> listPengguna() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Pengguna", Pengguna.class).getResultList();
    }

    @Override
    public Pengguna findBylevel(String level_pengguna) {
        EntityManager em = emf.createEntityManager();
        return null;
    }

    @Override
    public Pengguna findByUsername(String username) {
        return penggunaDao.findByUsername(username);
    }
}
