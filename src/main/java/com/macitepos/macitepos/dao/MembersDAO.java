package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MembersDAO {

    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Member saveOrUpdate(Member member){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member saved = em.merge(member);
        em.getTransaction().commit();
        em.close();
        return saved;
    }

    public List<Member> showAll(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Member", Member.class);
        List<Member> m = (List<Member>) q.getResultList();
        em.close();
        System.out.println("Member Service Show All");
        return m;
    }

    public List<Member> findById(int ID){
        System.out.println("ID di get Diskon Member DAO = "+ID);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT * FROM Member m WHERE m.id_member=:ID", Member.class);
        q.setParameter("ID", ID);
        List<Member> m = q.getResultList();
        em.close();
        return m;
    }
}
