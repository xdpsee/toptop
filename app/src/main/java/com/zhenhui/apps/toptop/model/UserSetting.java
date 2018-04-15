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

    public String currToken() {
        return sharedPreferences.getString("token", null);
    }

    public User currUser() {
        String value = sharedPreferences.getString("user", null);
        User user = JSONUtil.fromJsonString(value, new TypeReference<User>() {
        });

        if (null == user) {
            user = new User();
        }

        return user;
    }

    public void saveUser(String token, User user) {
        sharedPreferences.edit().putString("token", token).apply();
        sharedPreferences.edit().putString("user", JSONUtil.toJsonString(user)).apply();
    }

    public void clearUser() {
        sharedPreferences.edit().putString("token", "").apply();
        sharedPreferences.edit().putString("user", "").apply();
    }

}


