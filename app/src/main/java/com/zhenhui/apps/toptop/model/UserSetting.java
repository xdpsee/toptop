package com.zhenhui.apps.toptop.model;


import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.apps.toptop.utils.JSONUtil;

import java.io.Serializable;

import javax.inject.Inject;

public class UserSetting implements Serializable {

    private static final long serialVersionUID = -1239867734834L;

    private SharedPreferences sharedPreferences;

    @Inject
    public UserSetting(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void setToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public User getUser() {
        String value = sharedPreferences.getString("user", null);
        User user = JSONUtil.fromJsonString(value, new TypeReference<User>() {
        });

        if (null == user) {
            user = new User();
        }

        return user;
    }

    public void setUser(User user) {
        sharedPreferences.edit().putString("user", JSONUtil.toJsonString(user)).apply();
    }
}


