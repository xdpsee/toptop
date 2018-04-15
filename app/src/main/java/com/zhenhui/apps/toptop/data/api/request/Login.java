package com.zhenhui.apps.toptop.data.api.request;


public class Login {

    private String phone;

    private String secret;

    public Login(String phone, String secret) {
        this.phone = phone;
        this.secret = secret;
    }
}
