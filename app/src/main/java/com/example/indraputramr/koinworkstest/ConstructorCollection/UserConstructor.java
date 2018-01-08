package com.example.indraputramr.koinworkstest.ConstructorCollection;

/**
 * Created by indraputramr on 07/01/2018.
 */

public class UserConstructor {

    private String mEmail;
    private String mPassword;

    public UserConstructor() {}

    public UserConstructor(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() { return mEmail; }

    public void setEmail(String email) { this.mEmail = email; }

    public String getPassword() { return mPassword; }

    public void setPassword(String password) { this.mPassword = password; }
}
