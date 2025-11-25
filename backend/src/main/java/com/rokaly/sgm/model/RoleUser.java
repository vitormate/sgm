package com.rokaly.sgm.model;

public enum RoleUser {

    ADMIN("admin"),
    USER("user");

    private String role;

    RoleUser(String role) {
        this.role = role;
    }

}
