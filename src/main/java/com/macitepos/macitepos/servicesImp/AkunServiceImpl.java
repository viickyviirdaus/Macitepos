package com.macitepos.macitepos.servicesImp;

import com.macitepos.macitepos.dao.PenggunaDao;
import com.macitepos.macitepos.model.Pengguna;
import com.macitepos.macitepos.services.AkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

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
    public Iterable<Pengguna> listPengguna() {
//        EntityManager em = emf.createEntityManager();
//        return em.createQuery("from Pengguna", Pengguna.class).getResultList();
//        return penggunaDao.findAll();
        return penggunaDao.findAllPengguna();
    }

    @Override
    public Pengguna findByUsername(String username) {

        return penggunaDao.findByUsername(username);
    }

    @Override
    public Pengguna saveOrUpdate(Pengguna pengguna) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Pengguna saved = em.merge(pengguna);

        em.getTransaction().commit();
        penggunaDao.save(pengguna);
        return saved;
    }

    @Override
    public Pengguna getIdMember(Integer id) {
        return null;
    }

    @Override
    public void hapus(Integer id) {

    }

    @Override
    public void save(Pengguna pengguna, String name) {
        return ;

    }
}
