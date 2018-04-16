package com.zhenhui.apps.toptop.data.api.request;

import com.zhenhui.apps.toptop.model.SocialType;

public class SocialSignin {

    private SocialType type;

    private Long uid;

    private String token;

    public SocialSignin(SocialType type, Long uid, String token) {
        this.type = type;
        this.uid = uid;
        this.token = token;
    }
}
