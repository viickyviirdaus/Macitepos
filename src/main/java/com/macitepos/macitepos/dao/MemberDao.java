package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Member;
import com.macitepos.macitepos.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class MemberDao implements MemberService {

    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Member> listMember() {
        EntityManager em = emf.createEntityManager( );
        return em.createQuery("from Member", Member.class).getResultList();
    }

    @Override
    public Member saveOrUpdate(Member member) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Member saved = em.merge(member);

        em.getTransaction().commit();

        return saved;
    }

    @Override
    public Member getIdMember(Integer id) {
         EntityManager em = emf.createEntityManager();
         return em.find(Member.class, id);
    }

    @Override
    public void hapus(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Member.class, id));
        em.getTransaction().commit();
    }
}
