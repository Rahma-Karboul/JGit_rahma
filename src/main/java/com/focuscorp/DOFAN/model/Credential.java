package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "credential")
public class Credential {

    @Id
    private String credentialId;

    private String provider;

    private String email;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    public String getCredentialId() {
        return this.credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Credential() {
    }
}
