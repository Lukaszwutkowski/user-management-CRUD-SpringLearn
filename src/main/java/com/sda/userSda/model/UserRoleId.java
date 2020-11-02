package com.sda.userSda.model;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    private String login;
    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
