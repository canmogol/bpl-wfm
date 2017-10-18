package com.fererlab.wssd.rest.model;

/**
 * can | 10/18/17.
 */
public class LoginResponseModel {

    private String result;
    private String name;

    public LoginResponseModel() {
    }

    public LoginResponseModel(String result, String name) {
        this.result = result;
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
