package com.macitepos.macitepos.dto;

import com.macitepos.macitepos.model.Role;

public class RoleDTO {

    private Integer role_id;
    private String role;


    public RoleDTO(){}
    public RoleDTO(Integer role_id, String role) {
        this.role_id = role_id;
        this.role = role;
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
