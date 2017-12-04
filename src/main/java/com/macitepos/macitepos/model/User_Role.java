//package com.macitepos.macitepos.model;
//
//import javax.persistence.*;
//
//@Entity
//public class User_Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int user_id;
//
//    @ManyToOne
//    private Role role;
//
//    public User_Role (Role role){
//        this.role = role;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//}