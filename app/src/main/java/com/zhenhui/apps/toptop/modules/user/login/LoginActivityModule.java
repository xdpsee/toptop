package com.zhenhui.apps.toptop.modules.user.login;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    private final LoginView mView;

    @Inject
    public LoginActivityModule(LoginView mView) {
        this.mView = mView;
    }

    @Provides
    LoginView loginView() { return mView; }
}
