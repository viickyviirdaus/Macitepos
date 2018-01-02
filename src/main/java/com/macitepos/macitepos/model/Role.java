package com.macitepos.macitepos.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer role_id;

    private String role;


    @ManyToMany(mappedBy = "roles")
    private Set<Pengguna> penggunaSet = new HashSet<>();

    public Role(){}
    public Role(Integer id_pengguna, String roles) {
        this.role_id = id_pengguna;
        this.role = roles;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
