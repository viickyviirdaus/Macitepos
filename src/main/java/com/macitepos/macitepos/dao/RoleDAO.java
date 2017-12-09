package com.macitepos.macitepos.dao;

import com.macitepos.macitepos.dto.RoleDTO;
import com.macitepos.macitepos.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class RoleDAO {
    private EntityManagerFactory emf;
    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf){
        this.emf = emf;
    }


    public Role saveToRole(Role role){
        System.out.println("user save or update role in");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println(role.getRole_id()+"id role");
        Role saved = em.merge(role);
        System.out.println(role.getRole_id()+"id role");
        em.getTransaction().commit();
        em.close();
        System.out.println("user save or update role out");
        System.out.println(role.getRole_id()+"id role");
        return saved;
    }

}
