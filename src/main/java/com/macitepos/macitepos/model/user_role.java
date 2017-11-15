//package com.macitepos.macitepos.model;
//
//import javax.persistence.*;
//
//@Entity
//public class user_role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int user_id;
//
//    @ManyToOne
//    private role Role;
//
//    public  user_role (role Role){
//        this.Role = Role;
//    }
//
//    public role getRole() {
//        return Role;
//    }
//
//    public void setRole(role role) {
//        Role = role;
//    }
//}
