package com.zhenhui.apps.toptop.modules.user.boot;

public interface AuthView {

    void showProgressStatus();

    void hideProgressStatus();

    void showErrorMessage(String error);

    void startLoginActivity(String phone);

    void startRegistryActivity();

    void loginWeiboSuccess(String token);

}

