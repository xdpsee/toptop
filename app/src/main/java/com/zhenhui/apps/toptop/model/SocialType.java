package com.zhenhui.apps.toptop.model;

public enum SocialType {
    WEIXIN(1, "微信"),
    WEIBO(2, "微博"),
    QQ(3, "QQ"),;

    public final int code;
    public final String comment;

    SocialType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static SocialType valueOf(final int code) {

        SocialType[] types = values();
        for (SocialType type : types) {
            if (type.code == code) {
                return type;
            }
        }

        throw new IllegalArgumentException("invalid code " + code);
    }
}



