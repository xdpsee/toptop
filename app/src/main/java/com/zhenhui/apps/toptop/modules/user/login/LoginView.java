package com.zhenhui.apps.toptop.modules.user.login;


public interface LoginView {

    void showProgressStatus();

    void hideProgressStatus();

    void loginSuccess(String token);

    void showLoginError(String message);

}
