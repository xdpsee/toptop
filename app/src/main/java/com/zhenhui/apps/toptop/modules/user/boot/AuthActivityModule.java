package com.zhenhui.apps.toptop.modules.user.boot;

import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthActivityModule {

    private final AuthView mView;

    public AuthActivityModule(AuthView view) {
        this.mView = view;
    }

    @Provides
    @PerActivity
    AuthView authView() {
        return mView;
    }
}
