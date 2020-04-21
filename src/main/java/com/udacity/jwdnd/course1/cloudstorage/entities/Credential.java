package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class Credential {
    public String userid;
    public String credentialid;
    public String url;
    public String username;
    public String key;
    public String password;
    public String decodedPassword;

    public String getCredentialId() {
        return credentialid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDecodedPassword(String password) {
        this.decodedPassword = password;
    }

}