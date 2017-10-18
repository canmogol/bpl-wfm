package com.fererlab.wssd.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * can | 10/18/17.
 */
@XmlRootElement
public class LoginRequestModel {

    private String username;
    private String password;
    private String site;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "LoginRequestModel{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", site='" + site + '\'' +
            '}';
    }
}
