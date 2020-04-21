package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class User {
    public String userid;
    public String username;
    public String password;
    public String salt;
    public String firstname;
    public String lastname;

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}